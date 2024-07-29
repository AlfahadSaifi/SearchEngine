package com.nucleus.dao.url_upload.impl;

import com.nucleus.dao.url_upload.UrlUploadDao;
import com.nucleus.dto.link.LinkDTO;
import com.nucleus.dto.file.PdfDTO;
import com.nucleus.mapper.link.LinkMapper;
import com.nucleus.mapper.file.PdfMapper;
import com.nucleus.model.engine.CustomSequence;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class UrlUploadDaoImpl implements UrlUploadDao {
    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    private Logger logger;

    @Override
    public boolean savaUrlData(LinkDTO linkDTO) {

        boolean status = true;
        Transaction transaction;
        try (Session session = sessionFactory.openSession()) {
            logger.info("(Repository) Data : " + linkDTO);
            transaction = session.beginTransaction();

            session.save(LinkMapper.toEntity(linkDTO));

            transaction.commit();
        }catch (HibernateException e){
            logger.error(e);
            status = false;
        }

        return status;
    }

    @Override
    public boolean savaFileData(PdfDTO pdf) {
        boolean status = true;
        Transaction transaction;
        try (Session session = sessionFactory.openSession()) {
            logger.info("(Repository) Data : " + pdf);
            transaction = session.beginTransaction();

            session.save(PdfMapper.toEntity(pdf));

            transaction.commit();
        }catch (HibernateException e){
            logger.error(e);
            status = false;
        }

        return status;
    }

    @Override
    public int getNextSequence(){
        Transaction transaction=null;
        try (Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();
            CustomSequence sequence = session.get(CustomSequence.class, 1);
            int nxtVal = sequence.getValue();
            sequence.setValue(sequence.getValue()+1);
            session.update(sequence);
            transaction.commit();
            return nxtVal;

        } catch (Exception e) {
            if (transaction!=null){
                transaction.rollback();
            }
            logger.error("Problem Occurred During Data Fetch Next Value");
            logger.error(e);
            return 0;
        }
    }

}
