package br.r8store.model.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Person extends NaturalPerson {

    private User user;

    public Person() {
        super();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}

