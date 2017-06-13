package com.jewelryguard.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Transient;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int id;
	@Column(name = "email")
	@Email(message = "*Podaj poprawny email ( @ . )")
	@NotEmpty(message = "*Email jest wymagany")
	private String email;
	@Column(name = "password")
	@Length(min = 6, message = "*Haslo musi zawierac minimum 6 znakow")
	@NotEmpty(message = "*Haslo jest wymagane")
	@Transient
	private String password;
	@Column(name = "name")
	@NotEmpty(message = "*Imię jest wymagane")
	private String name;
	@Column(name = "last_name")
	@NotEmpty(message = "*Nazwisko jest wymagane")
	private String lastName;
	@Column(name = "active")
	@Value(value = "1")
	private int active;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Jewelry> jewelryList;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public List<Jewelry> getJewelryList() {
		return jewelryList;
	}

	public void setJewelryList(List<Jewelry> jewelryList) {
		this.jewelryList = jewelryList;
	}
}
