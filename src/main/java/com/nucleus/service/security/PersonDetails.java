package com.nucleus.service.security;

import com.nucleus.model.security.Person;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;

public class PersonDetails implements UserDetails {

    Person person;
    public PersonDetails() {
        super();
    }

    public PersonDetails(Person person) {
        this.person = person;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        HashSet<SimpleGrantedAuthority> setRoles = new HashSet<>();

        setRoles.add(new SimpleGrantedAuthority(this.person.getPersonRole()));
        return setRoles;

    }

    @Override
    public String getPassword() {
        return this.person.getPersonPassword();

    }

    @Override
    public String getUsername() {
        return this.person.getPersonName();

    }

    @Override
    public boolean isAccountNonExpired() {
        return true;

    }

    @Override
    public boolean isAccountNonLocked() {
        return true;

    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;

    }

    @Override
    public boolean isEnabled() {
        return true;

    }

}
