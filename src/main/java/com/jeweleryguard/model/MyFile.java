package com.jeweleryguard.model;

import javax.persistence.*;
	
@Entity
public class MyFile {
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name = "file_id")
	    private int id;
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
