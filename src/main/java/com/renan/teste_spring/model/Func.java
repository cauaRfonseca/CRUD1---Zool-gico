package com.renan.teste_spring.model;

import org.javalite.activejdbc.Model;

public class Func extends Model {
    private String name;
    private String função;
    private String cpf;

    public Func (){

    }

    public Func(String name, String função, String cpf){
        this.função = função;
        this.name = name;
        this.cpf = cpf;

    }

    public String getCpf() {
        return cpf;
    }

    public String getFunção() {
        return função;
    }

    public String getName() {
        return name;
    }

    public void setFunção(String função) {
        this.função = função;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setName(String name) {
        this.name = name;
    }
}
