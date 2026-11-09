package com.renan.teste_spring.config;

import org.javalite.activejdbc.Base;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        // 1. Abre a ligação no início da aplicação
        System.out.println("Ligar à base de dados SQLite...");
        try {
            Base.open("org.sqlite.JDBC", "jdbc:sqlite:meu_app.db", "", "");

            // 2. Garante que a tabela existe
            String sqlCreate = "CREATE TABLE IF NOT EXISTS funcs (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "first_name VARCHAR(56), " +
                    "cpf VARCHAR(56), " +
                    "função VARCHAR(100))";
            Base.exec(sqlCreate);

            // A PARTIR DAQUI A LIGAÇÃO ESTÁ ABERTA E A TABELA EXISTE

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Base.close();
            System.out.println("Ligação encerrada.");
        }
    }
}