package com.bionic.edu.entity;

import java.util.Collection;

import javax.persistence.*;

@Entity

public class FishType {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    
    @OneToMany(mappedBy="fishType")
    private Collection<FishItem> fishItems;

    public FishType(){   }

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Collection<FishItem> getFishItems() {
		return fishItems;
	}

	public void setFishItems(Collection<FishItem> fishItems) {
		this.fishItems = fishItems;
	}
}