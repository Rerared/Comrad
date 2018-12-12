package com.rerared.comrad.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class UserInfo implements Serializable {
  
	private static final long serialVersionUID = 4330664202230893474L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String name;
    private String sirname;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    private String filename;



	protected UserInfo() {
		super();
	}

	public UserInfo(String name, String sirname, User user) {
		super();
		this.author = user;
		this.name = name;
		this.sirname = sirname;
	}
	
	public String getAuthorName() {
		return author != null ? author.getUsername() : "<none>";
	}
	
	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSirname() {
		return sirname;
	}

	public void setSirname(String sirname) {
		this.sirname = sirname;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
}
