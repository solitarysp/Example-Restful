package com.higgsup.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Test {
    private String name;

    public Test(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
