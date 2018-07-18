package com.higgsup.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
public class SchoolsDTO implements Cloneable {
    private Integer id;
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ClazzDTO> clazzDTOs;

    public List<ClazzDTO> getClazzDTOs() {
        return clazzDTOs;
    }

    public void setClazzDTOs(List<ClazzDTO> clazzDTOs) {
        this.clazzDTOs = clazzDTOs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
