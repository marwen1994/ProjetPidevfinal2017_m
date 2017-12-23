package com.example.marwen.projetpidevfinal2017;

/**
 * Created by marwen on 22/12/2017.
 */

public class Matnondisponible {

    private int id ;
    private String image_path;
    private String image_name;
    private String description;
    private int prix;
    private String name ;
    private String Groupename ;
    private String 	url ;

    public Matnondisponible(int id, String image_path, String image_name, String description, int prix, String name, String groupename, String url) {
        this.id = id;
        this.image_path = image_path;
        this.image_name = image_name;
        this.description = description;
        this.prix = prix;
        this.name = name;
        this.Groupename = groupename;
        this.url = url;
    }

    public Matnondisponible(String image_path, String image_name, String description, int prix, String name, String groupename, String url) {
        this.image_path = image_path;
        this.image_name = image_name;
        this.description = description;
        this.prix = prix;
        this.name = name;
        this.Groupename = groupename;
        this.url = url;
    }

    public Matnondisponible() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroupename() {
        return Groupename;
    }

    public void setGroupename(String groupename) {
        this.Groupename = groupename;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
