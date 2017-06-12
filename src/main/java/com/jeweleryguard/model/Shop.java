package com.jeweleryguard.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Shop {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name = "shop_id")
	private int id;
	@Column(name = "password")
	@Length(min = 6, message = "*Haslo musi zawierac minimum 6 znakow")
	@NotEmpty(message = "*Haslo jest wymagane")
	@Transient
	private String password;
	
	@Column(name = "name")
	@NotEmpty(message = "*Nazwa jest wymagane")
	private String name;

	@Column(name = "last_name")
	private String description;

	@Column(name = "address")
	private String address;
	
	@Column(name = "active")
	@Value(value = "1")
	private int active;
	
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.REFRESH)
	@JoinTable(name = "shop_employee", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "shop_id"))
	private List<User> employeeList;

	@JsonIgnore
	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinTable(name = "shop_owner", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "shop_id"))
	private User owner;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public List<User> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<User> employeeList) {
		this.employeeList = employeeList;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	
}
