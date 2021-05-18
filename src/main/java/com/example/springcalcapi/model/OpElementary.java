package com.example.springcalcapi.model;


// la creation de cette classe me permet de recuperer ensuite l'ob

public class OpElementary {
    private String operation;
    private int lhs;
    private int rhs;


    public OpElementary(String operation, int lhs, int rhs) {
        this.operation = operation;
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public String getOperation() {
        return operation;
    }

    public int getLhs() {
        return lhs;
    }

    public int getRhs() {
        return rhs;
    }


    //methode crée pour alleger le code dans le POST
    public double calculate(){
        switch (operation){
            case "Addition":
                return lhs+rhs;
            case "Soustraction":
                return lhs-rhs;
            case "Multiplication":
                return lhs*rhs;
            case "Division":
                return lhs/rhs;
            default:
                return 0;
        }
    }

}
