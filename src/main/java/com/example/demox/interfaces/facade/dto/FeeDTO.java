package com.example.demox.interfaces.facade.dto;

public class FeeDTO {
    private final String value;
    private final String currenct;

    public FeeDTO(String value, String currenct) {
        this.value = value;
        this.currenct = currenct;
    }

    public String getValue() {
        return value;
    }

    public String getCurrenct() {
        return currenct;
    }
}
