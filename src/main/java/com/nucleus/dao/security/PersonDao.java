package com.nucleus.dao.security;

import com.nucleus.model.security.Person;

public interface PersonDao {
    Person getPerson(String personName);
}
