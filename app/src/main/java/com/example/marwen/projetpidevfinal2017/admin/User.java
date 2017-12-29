package com.example.marwen.projetpidevfinal2017.admin;

/**
 * Created by marwen on 25/12/2017.
 */

public class User {
    String name ;
    String email;
    String Grpouname;
    String image_path;
    String status;

    public User(String name, String email, String grpouname, String image_path,String status) {
        this.name = name;
        this.email = email;
        Grpouname = grpouname;
        this.image_path = image_path;
        this.status = status;
    }

    public User() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGrpouname() {
        return Grpouname;
    }

    public void setGrpouname(String grpouname) {
        Grpouname = grpouname;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }
}
