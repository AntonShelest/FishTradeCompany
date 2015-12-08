package com.bionic.edu.entity;

import java.util.Collection;

import javax.persistence.*;

@Entity

public class SaleParcel {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private java.sql.Timestamp submitted;
    private java.sql.Timestamp shipped;
    private java.sql.Timestamp closed;
    private String status;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="userId")
    private User user;
    
    @OneToMany(mappedBy="saleParcel", cascade=CascadeType.PERSIST)
    private Collection<SaleParcelItem> saleParcelItems;

    public SaleParcel(){   }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public java.sql.Timestamp getSubmitted() {
		return submitted;
	}

	public void setSubmitted(java.sql.Timestamp submitted) {
		this.submitted = submitted;
	}

	public java.sql.Timestamp getShipped() {
		return shipped;
	}

	public void setShipped(java.sql.Timestamp shipped) {
		this.shipped = shipped;
	}

	public java.sql.Timestamp getClosed() {
		return closed;
	}

	public void setClosed(java.sql.Timestamp closed) {
		this.closed = closed;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Collection<SaleParcelItem> getSaleParcelItems() {
		return saleParcelItems;
	}

	public void setSaleParcelItems(Collection<SaleParcelItem> saleParcelItems) {
		this.saleParcelItems = saleParcelItems;
	}
}
