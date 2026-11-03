package com.renan.teste_spring.model;

public class Car {
    private long id;
    private String placa;
    private String modelo;
    private String tipo;

    public Car (){

    }

    public Car(long id, String placa, String modelo, String tipo){
        this.id = id;
        this.placa = placa;
        this.modelo = modelo;
        this.tipo = tipo;

    }

    public String getTipo() {return tipo;}

    public long getId() {
        return id;
    }

    public String getModelo() {
        return modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
}
