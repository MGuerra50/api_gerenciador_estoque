package com.inventory.manager.domain.moviment;

public enum Type {
    INPUT("imput"),
    OUTPUT("output"),
    ADJUSTMENT("adjustment"),
    TRANSFER("transfer");

    private String type;

    Type(String type){
        this.type = type;
    }

    public String getType (){
        return type;
    }
}
