package com.hcl.app.domainVO;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cust_order")

public class Order {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "campaign_id")
	private int campaignId;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@Column(name = "time_stamp")
	private Date timeStamp;
	
	public Order() {
		// TODO Auto-generated constructor stub
	}
	
	public Order(int id,int campaign_id) {
	}
	

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private Set<Items> items;

	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the campaignId
	 */
	public int getCampaignId() {
		return campaignId;
	}

	/**
	 * @param campaignId
	 *            the campaignId to set
	 */
	public void setCampaignId(int campaignId) {
		this.campaignId = campaignId;
	}



	/**
	 * @return the timeStamp
	 */
	public Date getTimeStamp() {
		return timeStamp;
	}

	/**
	 * @param timeStamp
	 *            the timeStamp to set
	 */
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @return the items
	 */
	public Set<Items> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(Set<Items> items) {
		this.items = items;
	}

	
	
}
