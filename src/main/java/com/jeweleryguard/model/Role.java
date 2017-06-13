package com.jeweleryguard.model;

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
	@ManyToMany(mappedBy = "roles")
	private Collection<User> users;
	@ManyToMany
	@JoinTable(
			name = "roles_privileges",
			joinColumns = @JoinColumn(
					name = "role_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(
					name = "privilege_id", referencedColumnName = "id"))
	private Collection<Privilege> privileges;	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	public Collection<User> getUsers() {
		return users;
	}

	public void setUsers(Collection<User> users) {
		this.users = users;
	}

	public Collection<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Collection<Privilege> privileges) {
		this.privileges = privileges;
	}
}
