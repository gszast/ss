package com.jewelryguard.model;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
public class MyFile {
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name = "file_id")
	    private int id;
	 private String path;
	 @ManyToOne
	 private Jewelry jewelry;

	public MyFile() {
	}

	public MyFile(Jewelry jewelry) {
		this.jewelry = jewelry;
	}

	public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}

	public Jewelry getJewelry() {
		return jewelry;
	}

	public void setJewelry(Jewelry jewelry) {
		this.jewelry = jewelry;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
