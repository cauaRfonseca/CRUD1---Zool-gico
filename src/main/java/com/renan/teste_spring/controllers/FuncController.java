package com.renan.teste_spring.controllers;

import com.renan.teste_spring.model.Func;
import org.javalite.activejdbc.Base;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/funcs")
public class FuncController {

    private static final String DB_URL = "jdbc:sqlite:meu_app.db";

    @GetMapping("/index")
    public String index(ModelMap model) {
        try {
            Base.open("org.sqlite.JDBC", DB_URL, "", "");

            List<Func> funcs = new ArrayList<>(Func.findAll());
            model.addAttribute("funcs", funcs);
            model.addAttribute("size", Func.count());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            Base.close();
        }
        return "funcs/index";
    }

    @GetMapping("/new")
    public String funcNew(ModelMap model) {
        try {
            Base.open("org.sqlite.JDBC", DB_URL, "", "");
            model.addAttribute("func", new Func());
        } finally {
            Base.close();
        }
        return "funcs/new";
    }

    @PostMapping("/create")
    public String funcCreate(
            @RequestParam("name") String name,
            @RequestParam("função") String funcao,
            @RequestParam("cpf") String cpf
    ) {
        try {
            Base.open("org.sqlite.JDBC", DB_URL, "", "");

            Func novoFunc = new Func();
            novoFunc.setName(name);
            novoFunc.setFunção(funcao);
            novoFunc.setCpf(cpf);
            novoFunc.saveIt();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            Base.close();
        }
        return "redirect:/funcs/index";
    }

    @GetMapping("/show/{id}")
    public String show(@PathVariable Long id, ModelMap model) {
        try {
            Base.open("org.sqlite.JDBC", DB_URL, "", "");

            Func funcShow = Func.findById(id);
            model.addAttribute("func", funcShow);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            Base.close();
        }
        return "funcs/show";
    }

    @GetMapping("/edit/{id}")
    public String editFunc(@PathVariable Long id, ModelMap model) {
        try {
            Base.open("org.sqlite.JDBC", DB_URL, "", "");

            Func funcEdit = Func.findById(id);
            model.addAttribute("func", funcEdit);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            Base.close();
        }
        return "funcs/edit";
    }

    @PostMapping("/update/{id}")
    public String userUpdate(
            @PathVariable Long id,
            @RequestParam("name") String name,
            @RequestParam("função") String funcao,
            @RequestParam("cpf") String cpf
    ) {
        try {
            Base.open("org.sqlite.JDBC", DB_URL, "", "");

            Func funcEdit = Func.findById(id);
            if (funcEdit != null) {
                funcEdit.setName(name);
                funcEdit.setFunção(funcao);
                funcEdit.setCpf(cpf);
                funcEdit.saveIt();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            Base.close();
        }
        return "redirect:/funcs/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        try {
            Base.open("org.sqlite.JDBC", DB_URL, "", "");

            Func funcDelete = Func.findById(id);
            if (funcDelete != null) {
                funcDelete.delete();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            Base.close();
        }
        return "redirect:/funcs/index";
    }
}