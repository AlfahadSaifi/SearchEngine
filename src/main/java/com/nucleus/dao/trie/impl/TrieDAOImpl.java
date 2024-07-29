package com.nucleus.dao.trie.impl;

import com.nucleus.dao.trie.TrieDAO;
import com.nucleus.model.engine.Link;
import com.nucleus.model.engine.Pdf;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Repository
public class TrieDAOImpl implements TrieDAO {
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private Logger logger;

    @Override
    public List<Link> fetchUrlDataForTrieGeneration() {
        List<Link> result;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String hql = "SELECT e FROM Link e JOIN FETCH e.urlKeywords";
            Query<Link> query = session.createQuery(hql, Link.class);
            result = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            logger.error(e);
            return new ArrayList<>();
        }
        return result;
    }

    @Override
    public List<String> fetchKeywordForTrieGeneration() {
        List<String> keywordList=new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            // HQL query to fetch the list of all urlKeywords
            NativeQuery query = session.createNativeQuery("select URL_KEYWORDS from keyword_table_search_Engine_Project ");
            List<Object> result = query.list();

            // Cast the result to a list of strings
            List<String> keywords = result.stream()
                    .map(Object::toString)
                    .collect(Collectors.toList());

            // Print or process the list of keywords
            keywordList.addAll(keywords);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
        }
        return keywordList;
    }

    @Override
    public List<Pdf> fetchFileDataForTrieGeneration() {
        List<Pdf> result;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String hql = "SELECT e FROM Pdf e JOIN FETCH e.fileKeywords";
            Query<Pdf> query = session.createQuery(hql, Pdf.class);
            result = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            logger.error(e);
            return new ArrayList<>();
        }
        return result;
    }

    @Override
    public List<String> fetchKeywordFileForTrieGeneration() {
        List<String> keywordList=new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            // HQL query to fetch the list of all urlKeywords
            NativeQuery query = session.createNativeQuery("select file_keywords from file_keyword_table_search_Engine_Project ");
            List<Object> result = query.list();

            // Cast the result to a list of strings
            List<String> keywords = result.stream()
                    .map(Object::toString)
                    .collect(Collectors.toList());

            // Print or process the list of keywords
            keywordList.addAll(keywords);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
        }
        return keywordList;
    }
}