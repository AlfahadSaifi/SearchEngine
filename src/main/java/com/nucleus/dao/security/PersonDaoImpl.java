package com.nucleus.dao.security;

import com.nucleus.model.security.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDaoImpl {
    @Autowired
    SessionFactory sessionFactory;
    public Person getPerson(String personName) {
        Session session = sessionFactory.openSession();

        return session.createQuery("FROM Person WHERE personName = :personName", Person.class)
                .setParameter("personName", personName)
                .uniqueResult();

    }

}
