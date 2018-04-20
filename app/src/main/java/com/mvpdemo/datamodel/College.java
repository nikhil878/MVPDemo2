package com.mvpdemo.datamodel;

import java.util.ArrayList;

public class College {

    private int id;
    private String name, address, description, phoneNumber, email, coursesAvailable, images;
    /*private ArrayList<String> coursesAvailable;
    private ArrayList<String> images;*/

    public College() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCoursesAvailable() {
        return coursesAvailable;
    }

    public void setCoursesAvailable(String coursesAvailable) {
        this.coursesAvailable = coursesAvailable;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
