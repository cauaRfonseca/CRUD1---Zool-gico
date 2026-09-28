package com.renan.teste_spring.model;

public class Func {
    private long id;
    private String name;
    private String função;
    private String cpf;

    public Func (){

    }

    public Func(long id, String name, String função, String cpf){
        this.id = id;
        this.função = função;
        this.name = name;
        this.cpf = cpf;

    }

    public String getCpf() {
        return cpf;
    }

    public long getId() {
        return id;
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

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
