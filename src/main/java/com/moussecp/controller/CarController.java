package com.moussecp.controller;

import com.moussecp.domain.Car;
import com.moussecp.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class CarController {

    @Autowired
    private CarService carService;

    @RequestMapping(value = {"/", "/car", "/home"}, method = GET)
    public String carManagerHome() {
        return "home";
    }

    @RequestMapping(value = "/list", method = GET)
    public String carList(Model model) {
        List<Car> carList = carService.findAll();
        model.addAttribute("carList", carList);
        return "list";
    }

    @RequestMapping(value = "/add", method = GET)
    public String carAdd() {
        System.out.println("add car");
        return "add";
    }

    @RequestMapping(value = "/add", method = POST)
    public String carAddSubmit(@ModelAttribute("car") @Valid Car car, BindingResult result) {
        if (result.hasErrors()) {
            // show the form again, with the errors
            return "add";
        }

        // validation was successful
        carService.add(car);
        return "redirect:/list";
    }
}