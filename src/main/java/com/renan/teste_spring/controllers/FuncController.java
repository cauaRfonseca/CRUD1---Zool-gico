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
    private List<Func> listFunc = new ArrayList<Func>();

    @GetMapping("/index")
    public String index(ModelMap model){
        try {
            // 1. Abre a Conexão
            Base.open("org.sqlite.JDBC", "jdbc:sqlite:meu_app.db", "", "");

            // 3. Busca Todos os usuários do banco
            List<Func> funcs = new ArrayList<>(Func.findAll());
            model.addAttribute("funcs", funcs);

            // 4. Conta Tudo
            model.addAttribute("size", Func.count());

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            // 5. Fecha a conexão para não travar o banco
            Base.close();
        }

        return "funcs/index";
    }

    @GetMapping("/new")
    public String funcNew(ModelMap model) {
        model.addAttribute("func", new Func());
        return "funcs/new";
    }

    @PostMapping("/create")
    public String funcCreate(@ModelAttribute Func func, ModelMap model){
        // Imprime os dados no console do servidor
        System.out.println("############ criando novo user ##########################");
        System.out.println("Nome: " + func.getName());
        System.out.println("Arroba: " + func.getFunção());
        System.out.println("Nome: " + func.getCpf());
        System.out.println("######################################");

        // Passa o objeto de volta para exibir os dados na tela de sucesso
        long id = listFunc.size()+1;
        listFunc.add(new Func(func.getName(), func.getFunção(), func.getCpf()));
        model.addAttribute("func", func);
        return "redirect:/funcs/index";
    }

    @GetMapping("/edit/{id}")
    public String editFunc(@PathVariable Long id, ModelMap model){
        int idInt = Math.toIntExact((id-1L));
        Func funcEdit = listFunc.get(idInt);
        model.addAttribute("func",  funcEdit );
        return "funcs/edit";
    }

    @PostMapping("/update/{id}")
    public String userUpdate(@PathVariable Long id, @ModelAttribute Func func, ModelMap model){
        // Imprime os dados no console do servidor
        System.out.println("############ EDITANDO user ##########################");
        System.out.println("ID: " + func.getId());
        System.out.println("Nome: " + func.getName());
        System.out.println("Arroba: " + func.getFunção());
        System.out.println("Nome: " + func.getCpf());
        System.out.println("######################################");
        int idInt = Math.toIntExact((id-1L));
        Func funcEdit = listFunc.get(idInt);
        funcEdit.setName(func.getName());
        funcEdit.setFunção(func.getFunção());
        funcEdit.setCpf(func.getCpf());
        model.addAttribute("func", func);
        return "redirect:/funcs/index";
    }

    @GetMapping("/show/{id}") // detalhes do usuário
    public String show(@PathVariable Long id, ModelMap model) {
        int idInt = Math.toIntExact((id-1L));
        Func funcShow = listFunc.get(idInt);
        model.addAttribute("func", funcShow);
        return "funcs/show";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        int idInt = Math.toIntExact((id-1L));
        listFunc.remove(idInt);
        return "redirect:/funcs/index";
    }
}

