package com.jeweleryguard.model;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.List;

@Entity
public class Jewelry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)	 
    @Column(name = "jewelry_id")
    private int id;
    @Column(name = "brand")
    private String brand;
    @Column(name = "weight")
    private float weight;
    @Column(name = "metal")
    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(name = "jewelry_metal", joinColumns = @JoinColumn(name = "jewelry_id"), inverseJoinColumns = @JoinColumn(name = "metal_id"))
    private List<Metal> metalList;

    @Column(name = "name")
    private String name;
    @Column(name = "lost")
    @Value(value = "false")
    private int lost;
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "category")
    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(name = "jewelry_category", joinColumns = @JoinColumn(name = "jewelry_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categoryList;

    @OneToMany(cascade = CascadeType.REFRESH)
    @JoinTable(name = "jewelry_file", joinColumns = @JoinColumn(name = "jewelry_id"), inverseJoinColumns = @JoinColumn(name = "file_id"))
    private List<MyFile> fileList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public List<Metal> getMetalList() {
        return metalList;
    }

    public void setMetalList(List<Metal> metalList) {
        this.metalList = metalList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLost() {
        return lost;
    }

    public void setLost(int lost) {
        this.lost = lost;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public List<MyFile> getMyFileList() {
        return fileList;
    }

    public void setMyFileList(List<MyFile> fileList) {
        this.fileList = fileList;
    }
}
