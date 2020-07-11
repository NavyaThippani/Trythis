package com.hcl.app.domainVO;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
@NamedEntityGraph(name = "CustomersWithOrderId", attributeNodes = {
		@NamedAttributeNode(value = "name"),
		@NamedAttributeNode(value = "orders", subgraph = "ordersGraph") }, subgraphs = { @NamedSubgraph(name = "ordersGraph", attributeNodes = {
		@NamedAttributeNode(value = "id"),
		@NamedAttributeNode(value = "campaignId"), 
		@NamedAttributeNode(value = "items", subgraph = "itemsGraph")
		}) },subclassSubgraphs={@NamedSubgraph(name="itemsGraph",attributeNodes={
				@NamedAttributeNode(value="itemId"),
				@NamedAttributeNode(value="itemName")
		})})
public class Customer {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "number_of_purchases")
	private int numberOfPurchases;

	@Column(name = "cust_email")
	private String customerEmail;

	public Customer() {
		super();
	}

	public Customer(int id, String name, int numberOfPurchases, String email) {
		super();
		this.id = id;
		this.name = name;
		this.numberOfPurchases = numberOfPurchases;
		customerEmail = email;
	}

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private Set<Order> orders;

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "id=" + id + "name=" + name + "numberOfPurchases"
				+ numberOfPurchases + "customerEmail" + customerEmail;
	}

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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the numberOfPurchases
	 */
	public int getNumberOfPurchases() {
		return numberOfPurchases;
	}

	/**
	 * @param numberOfPurchases
	 *            the numberOfPurchases to set
	 */
	public void setNumberOfPurchases(int numberOfPurchases) {
		this.numberOfPurchases = numberOfPurchases;
	}

	/**
	 * @return the orders
	 */
	public Set<Order> getOrders() {
		return orders;
	}

	/**
	 * @param orders
	 *            the orders to set
	 */
	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	/**
	 * @return the customerEmail
	 */
	public String getCustomerEmail() {
		return customerEmail;
	}

	/**
	 * @param customerEmail
	 *            the customerEmail to set
	 */
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

}
