package com.nucleus.dao.engine.impl;

import com.nucleus.dao.engine.EngineDao;
import com.nucleus.dto.file.PdfDTO;
import com.nucleus.mapper.file.PdfMapper;
import com.nucleus.model.engine.Link;
import com.nucleus.model.engine.Pdf;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EngineDaoImpl implements EngineDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private Logger logger;

    @Override
    public List<Object[]> retrieveSuggestions(String keyword) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);
            Root<Link> root = criteriaQuery.from(Link.class);

            Join<Link, String> joinKeyword = root.join("urlKeywords");

            criteriaQuery.multiselect(root.get("id"),root.get("urlTitle"), root.get("url"),root.get("urlDescription"))
                    .distinct(true)
                    .where(builder.like(joinKeyword, keyword + "%"));
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            logger.error(e);
            return new ArrayList<>();
        }
    }

    @Override
    public PdfDTO findByID(String id) {
        List<Pdf> files;
        try(Session session = sessionFactory.openSession();){
            files = session.createQuery("FROM Pdf WHERE id = :id", Pdf.class)
                    .setParameter("id", id)
                    .getResultList();
        }catch (Exception e){
            logger.error(e);
            return null;
        }

        return files.isEmpty() ? null : PdfMapper.toDTO(files.get(0));
    }

}


