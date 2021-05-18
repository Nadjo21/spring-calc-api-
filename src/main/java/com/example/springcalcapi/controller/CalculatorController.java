package com.example.springcalcapi.controller;

import com.example.springcalcapi.model.OpElementary;
import org.springframework.web.bind.annotation.*;

@RestController
public class CalculatorController {


    //declaration opsCounter pour compter le nombre de fois ou j'appelle mes requetes
    private int opsCounter = 0;


    // Objectif : retourner un tableau de chaînes de caractères avec les opérations permises par l'API lorsqu'on appelle l'API avec un GET sur le point d'accès /api/calculator.

    @GetMapping("/api/calculator/listops")
    public String[] getPossibleOperation() {
        String[] possibleOperations = new String[7];
        possibleOperations[0] = "Addition";
        possibleOperations[1] = "Soustraction";
        possibleOperations[2] = "Multiplication";
        possibleOperations[3] = "Division";
        possibleOperations[4] = "Puissance";
        possibleOperations[5] = "Transformation en binaire";
        possibleOperations[6] = "Transformation en hexadécimal";


        //ou declaration du tableau sous cette forme  :
        //String[] possibleOperations = new String[] {"Addition","Soustraction","Multiplication","Division","Puissance","Transformation en binaire","Transformation en hexadécimal"};

        return possibleOperations;
    }


//Objectif : retourner le résultat d'une opération élémentaire (addition, soustraction, ...) lorsqu'on appelle l'API avec un GET sur le point d'accès /api/calculator.
    //Exemple d'appel : /api/calculator?operation=Add&lhs=12&rhs=15
    //lhs et rhs pour left hand side et right hand side

    @GetMapping("/api/calculator")
    public double calculator(@RequestParam String operation, @RequestParam int lhs, @RequestParam int rhs) {
        opsCounter++;

        switch (operation) {
            case "Addition":
                return lhs + rhs;
            case "Soustraction":
                return lhs - rhs;
            case "Multiplication":
                return lhs * rhs;
            case "Division":
                return lhs / rhs;
            default:
                return 0;
        }

    }


    //Objectif : retourner le résultat d'une opération élémentaire (addition, soustraction, ...) lorsqu'on appelle l'API avec un POST sur le point d'accès /api/calculator.


    @PostMapping("api/api/calculator")
    //je declare ma methode -  le request body se base sur les attributs declarés dans la classe OpElementary-
    //ensuite dans le swagger , je peux verifier le résultat en remplaçant les donnees du responseBody
    public double calculator(@RequestBody OpElementary opelementary) {

        opsCounter++;
        //j'appelle ma methode cree dans la classe Opelementary
        return opelementary.calculate();
    }

    //

    @GetMapping("/api/calculator/pow")
    public double pow(@RequestParam int number, @RequestParam int pow) {
        opsCounter++;
        return Math.pow(number, pow);

//ou via une boucle:
//    double result=number;
//    for (int i= 1; i <pow ; i++) {
//        result=result*number;
//    }
//    return result;
//    }

    }


    //Proposer un point d'accès GET pour récupérer le nombre d'opérations effectuées depuis le démarrage du serveur
    @GetMapping("/api/calculator/stats")
//getStat est une fonction declenchée lorsqu'une requete http arrive sur /api/calculator/ stat ou autre definis ci dessus
    public int getStats() {
        return opsCounter;
    }


    //Proposer un point d'accès GET pour transformer un nombre entier en binaire

    @GetMapping("/api/calculator/format")
    public String format(@RequestParam int number, @RequestParam String format) {
        opsCounter++;

        if (format.equals("binary")) {
            return Integer.toBinaryString(number);
        } else if (format.equals("hexa")) {
            return Integer.toHexString(number);
        } else {
            return "format inconnu";

        }

    }
}