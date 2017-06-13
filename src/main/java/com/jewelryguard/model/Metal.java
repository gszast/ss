package com.jewelryguard.model;

import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.*;

@Entity
public class Metal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name = "metal_id")
    private int id;
    @Column(name = "name")
    @NotEmpty(message = "*Please provide name")
    private String name;
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
    
    
}
