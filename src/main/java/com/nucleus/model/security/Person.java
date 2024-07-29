package com.nucleus.model.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "security_table_search_engine_project")
public class Person {
    @Id
    @Column(name = "user_security_id")
    private String personId;
    @Column(name = "username", nullable = false)
    private String personName;
    @Column(name = "password", nullable = false)
    private String personPassword;
    @Column(name = "role", nullable = false)
    private String personRole;
    @Column(name = "enabled", nullable = false)
    private int enabled;
    public Person() {
        super();

    }

    public Person(String personId, String personName, String personPassword, String personRole, int enabled) {
        this.personId = personId;
        this.personName = personName;
        this.personPassword = personPassword;
        this.personRole = personRole;
        this.enabled = enabled;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonPassword() {
        return personPassword;
    }

    public void setPersonPassword(String personPassword) {
        this.personPassword = personPassword;
    }

    public String getPersonRole() {
        return personRole;
    }

    public void setPersonRole(String personRole) {
        this.personRole = personRole;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "Person{" +
                "personId='" + personId + '\'' +
                ", personName='" + personName + '\'' +
                ", personPassword='" + personPassword + '\'' +
                ", personRole='" + personRole + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}






