package com.inventory.manager.domain.moviment;

import lombok.Getter;

@Getter
public enum Type {
    INPUT("imput"),
    OUTPUT("output"),
    ADJUSTMENT("adjustment"),
    TRANSFER("transfer");

    private final String type;

    Type(String type){
        this.type = type;
    }

}
