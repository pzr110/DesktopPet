/**
 * Copyright 2020 bejson.com
 */
package com.pzr.desktoppet;


import java.io.Serializable;

public class PetsBean implements Serializable {

    private static final long serialVersionUID = -4782179800383825169L;
    private int id;
    private String name;
    private String image;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

}