package com.jeweleryguard.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
	
@Entity
public class Attachment {
	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)	 
	 @Column(name = "attachment_id")
	    private int id;
	 	
	 	@JsonIgnore
	    @OneToOne
	    private File file;
	    private String name;
	    
	    @JsonIgnore
	    @ManyToOne
	    private Jewelry jewelry;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public File getFile() {
			return file;
		}
		public void setFile(File file) {
			this.file = file;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Jewelry getJewelry() {
			return jewelry;
		}
		public void setJewelry(Jewelry jewelry) {
			this.jewelry = jewelry;
		}
	    
	    
	    
}
