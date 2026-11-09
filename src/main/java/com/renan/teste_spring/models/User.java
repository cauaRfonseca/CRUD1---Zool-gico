package com.renan.teste_spring.models;

import org.javalite.activejdbc.Model;

public class User extends Model {

    public String getFunção() {
        return getString("função");
    }

    public void setFunção(String função) {
        set("função", função);
    }

    public String getName() {
        return getString("name");
    }

    public void setName(String name) {
        set("name", name);
    }

    public String getCpf() {
        return getString("cpf");
    }

    public void setCpf(String cpf) {
        set("cpf", cpf);
    }
}