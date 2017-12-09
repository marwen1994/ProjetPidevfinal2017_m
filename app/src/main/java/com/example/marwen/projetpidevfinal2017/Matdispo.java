package com.example.marwen.projetpidevfinal2017;

/**
 * Created by marwen on 05/12/2017.
 */

public class Matdispo {
    private int id ;
    private String name;
    private String description;
    private int qte;
    private String image_name;
    private String image_path ;

    public Matdispo(int id, String name, String description, int qte, String image_name, String image_path) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.qte = qte;
        this.image_name = image_name;
        this.image_path = image_path;
    }

    public Matdispo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }
}
