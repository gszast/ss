package com.jeweleryguard.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
	
@Entity
public class File {
	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name = "file_id")
	    private int id;
	    private String name;
	    @ManyToOne(cascade = CascadeType.REFRESH)
	    private User user;
	    @OneToOne
	    private Attachment attachment;
	    
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
		public User getUser() {
			return user;
		}
		public void setUser(User user) {
			this.user = user;
		}


}
