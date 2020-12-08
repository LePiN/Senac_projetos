package br.r8store.model.entities;

import br.r8store.enums.Enum_Type_User;

public abstract class SessionEntity extends MasterEntity {

    private Enum_Type_User type;

    private String name;

    private String email;

    private String tel;

    private String cel;

    public SessionEntity() {
        super();
    }

    public Enum_Type_User getType() {
        return type;
    }

    public void setType(Enum_Type_User type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCel() {
        return cel;
    }

    public void setCel(String cel) {
        this.cel = cel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

