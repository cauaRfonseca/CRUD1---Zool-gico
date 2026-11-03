package com.renan.teste_spring.controllers;

import com.renan.teste_spring.model.Car;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/carros")
public class CarController {
    private List<Car> listCar = new ArrayList<>();
    @GetMapping("/index")
    public String index(ModelMap model){
        model.addAttribute("carros", listCar);
        model.addAttribute("size", listCar.size());
        return "carros/index";
    }

    @GetMapping("/new")
    public String carNew(ModelMap model) {
        model.addAttribute("car", new Car());
        return "carros/new";
    }

    @PostMapping("/create")
    public String carCreate(@ModelAttribute Car car, ModelMap model){
        // Imprime os dados no console do servidor
        System.out.println("############ criando novo user ##########################");
        System.out.println("Nome: " + car.getPlaca());
        System.out.println("Arroba: " + car.getModelo());
        System.out.println("Nome: " + car.getTipo());
        System.out.println("######################################");

        // Passa o objeto de volta para exibir os dados na tela de sucesso
        long id = listCar.size()+1;
        listCar.add(new Car(id, car.getPlaca(), car.getModelo(), car.getTipo()));
        model.addAttribute("car", car);
        return "redirect:/carros/index";
    }

    @GetMapping("/edit/{id}")
    public String editCar(@PathVariable Long id, ModelMap model){
        int idInt = Math.toIntExact((id-1L));
        Car carEdit = listCar.get(idInt);
        model.addAttribute("car",  carEdit );
        return "carros/edit";
    }

    @PostMapping("/update/{id}")
    public String userUpdate(@PathVariable Long id, @ModelAttribute Car car, ModelMap model){
        // Imprime os dados no console do servidor
        System.out.println("############ EDITANDO user ##########################");
        System.out.println("ID: " + car.getId());
        System.out.println("Placa: " + car.getPlaca());
        System.out.println("Modelo: " + car.getModelo());
        System.out.println("Tipo: " + car.getTipo());
        System.out.println("######################################");
        int idInt = Math.toIntExact((id-1L));
        Car userEdit = listCar.get(idInt);
        userEdit.setPlaca(car.getPlaca());
        userEdit.setModelo(car.getModelo());
        userEdit.setTipo(car.getTipo());
        model.addAttribute("car", car);
        return "redirect:/carros/index";
    }

    @GetMapping("/show/{id}") // detalhes do usuário
    public String show(@PathVariable Long id, ModelMap model) {
        int idInt = Math.toIntExact((id-1L));
        Car carShow = listCar.get(idInt);
        model.addAttribute("car", carShow);
        return "/carros/show";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        int idInt = Math.toIntExact((id-1L));
        listCar.remove(idInt);
        return "redirect:/carros/index";
    }
}