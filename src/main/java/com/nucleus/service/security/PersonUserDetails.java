package com.nucleus.service.security;

import com.nucleus.dao.security.PersonDaoImpl;
import com.nucleus.model.security.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PersonUserDetails implements UserDetailsService {

    @Autowired
    private PersonDaoImpl personDao;

    public PersonDaoImpl getPersonDemo() {
        return personDao;

    }
    public void setPersonRepo(PersonDaoImpl personRepo) {
        this.personDao = personRepo;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Person person = this.personDao.getPerson(username);
        System.out.println("user details : " + person);
        if (person == null){
            throw new UsernameNotFoundException("Person Not In Our Database. Checked Via Spring Security");

        }
        return new PersonDetails(person);

    }

}