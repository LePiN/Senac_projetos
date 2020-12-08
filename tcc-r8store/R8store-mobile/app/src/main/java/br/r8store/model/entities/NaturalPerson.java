package br.r8store.model.entities;


import java.util.Date;

import br.r8store.enums.Enum_Gender;

public abstract class NaturalPerson extends SessionEntity {

    private static final long serialVersionUID = 9144342926702460801L;

    private String identifier;

    private Date birthday;

    private Enum_Gender gender;

    public NaturalPerson(String identifier, Date birthday, Enum_Gender gender) {
        super();
        this.identifier = identifier;
        this.birthday = birthday;
        this.gender = gender;
    }

    public NaturalPerson() {
        super();
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Enum_Gender getGender() {
        return gender;
    }

    public void setGender(Enum_Gender gender) {
        this.gender = gender;
    }

}

