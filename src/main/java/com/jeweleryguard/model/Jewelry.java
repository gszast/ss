package com.jeweleryguard.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Value;

@Entity
public class Jewelry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)	 
    @Column(name = "jewelry_id")
    private int id;
    @Column(name = "brand")
    @NotEmpty(message = "*Please provide a brand")
    private String brand;
    @Column(name = "weight")
    private float weight;
    @Column(name = "metal")
    @NotEmpty(message = "*Please provide metal")
    private String metal;
    @Column(name = "name")
    @NotEmpty(message = "*Please provide name")
    private String name;
    @Column(name = "lost")
    @Value(value = "false")
    private int lost;
    @ManyToOne(cascade = CascadeType.REFRESH)
    private User user;
    @ManyToOne(cascade = CascadeType.REFRESH)
    private Category category;
    @OneToMany(cascade = CascadeType.REFRESH)
    private List<Attachment> attachmentList;

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

    public String getMetal() {
        return metal;
    }

    public void setMetal(String metal) {
        this.metal = metal;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
