package ca.sheridancollege.midterm.controllers;


import ca.sheridancollege.midterm.database.DatabaseAccess;
import ca.sheridancollege.midterm.model.Fruit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FruitController {

    @Autowired
    private DatabaseAccess da;

    List<Fruit> fruits = new ArrayList<Fruit>();

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/newOrder")
    public String newOrder(){
        return "redirect:/";
    }

    @PostMapping("/processFruit")
    public String processFruit(Model model,
                                @RequestParam int appleQ,
                                @RequestParam int orangeQ,
                                @RequestParam int grapeQ){

        for(int i = 1; i <= appleQ; i++){
            fruits.add(new Fruit("apple", 2, 1.00));
        }
        for(int i = 1; i <= orangeQ; i++){
            fruits.add(new Fruit("orange", 3, 1.25));
        }
        for(int i = 1; i <= grapeQ; i++){
            fruits.add(new Fruit("grape", 1, 0.75));
        }

        double grandTotal = 0;
        for(Fruit f : fruits){
            grandTotal += f.getSubTotal();
        }

        for(Fruit f : fruits){
            da.insertFruit(f.getFType(), f.getWeight(), f.getSubTotal());
        }

        model.addAttribute("fruits", fruits);

        return "FruitsList";
    }


}
