package com.jewelryguard.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Role {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name = "role_id")
	private int id;
	@Column(name="role")
	private String role;
	public void setId(int id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

}
