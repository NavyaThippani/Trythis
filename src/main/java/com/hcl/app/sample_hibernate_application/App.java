package com.hcl.app.sample_hibernate_application;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.hcl.app.domainVO.Customer;
import com.hcl.app.domainVO.Items;
import com.hcl.app.domainVO.Order;

public class App {
	private static EntityManager entityManager;

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("CustomerPU");
		entityManager = emf.createEntityManager();

		//saveCustomer(entityManager);

		//problemNPlusOne();

		//earlyLoadingWithInnerJoin();

		//earlyLoadingWithLeftJoin();
		
		//problemNPlusOneItems();
		
		earlyLoadingWithLeftJoinItems();

	}

	private static void saveCustomer(EntityManager entityManager) {
		entityManager.getTransaction().begin();

		Customer customer = new Customer();
		customer.setName("navyakrishna11");
		customer.setCustomerEmail("krishnavya11@gmail.com");
		customer.setNumberOfPurchases(10);

		Set<Order> orders = new HashSet<Order>();

		Order order = new Order();
		order.setCampaignId(10);
		order.setTimeStamp(new Date());
		order.setCustomer(customer);

		orders.add(order);

		order = new Order();
		order.setCampaignId(10);
		order.setTimeStamp(new Date());
		order.setCustomer(customer);
		orders.add(order);

		customer.setOrders(orders);

		entityManager.persist(customer);

		entityManager.getTransaction().commit();

	}

	private static void problemNPlusOne() {
		List<Customer> resultList = entityManager.createQuery(
				"SELECT c FROM Customer AS c", Customer.class)
				.getResultList();
		for (Customer customerEntity : resultList) {
			Set<Order> orders = customerEntity.getOrders();
			for (Order orderEntity : orders) {
				System.out.println(" OrderID: " + orderEntity.getId());
			}
		}
	}

	private static void earlyLoadingWithInnerJoin() {
		List<Customer> resultList = entityManager.createQuery(
				"SELECT c FROM Customer AS c JOIN FETCH c.orders AS o",
				Customer.class).getResultList();

	}

	private static void earlyLoadingWithLeftJoin() {
		EntityGraph entityGraph = entityManager
				.getEntityGraph("CustomersWithOrderId");
		List<Customer> resultList = entityManager
				.createQuery("SELECT c FROM Customer AS c", Customer.class)
				.setHint("javax.persistence.fetchgraph", entityGraph)
				.getResultList();

		System.out.println(" Printing first :"+resultList);

		for (Customer customer : resultList) {
			Set<Order> orders = customer.getOrders();
			for (Order order : orders) {
				System.out.println(order.getId());

			}
		}
	}
	
	
	private static void problemNPlusOneItems() {
		List<Customer> resultList = entityManager.createQuery(
				"SELECT c FROM Customer AS c", Customer.class)
				.getResultList();
		for (Customer customerEntity : resultList) {
			Set<Order> orders = customerEntity.getOrders();
			for (Order orderEntity : orders) {
				System.out.println(" OrderID: " + orderEntity.getId());
				Set<Items> itemsEntity = orderEntity.getItems();
				for(Items item : itemsEntity){
					System.out.println("Item Id : "+item.getItemId());
				}
			}
		}
	}
	
	
	private static void earlyLoadingWithLeftJoinItems() {
		EntityGraph entityGraph = entityManager
				.getEntityGraph("CustomersWithOrderId");
		List<Customer> resultList = entityManager
				.createQuery("SELECT c FROM Customer AS c", Customer.class)
				.setHint("javax.persistence.fetchgraph", entityGraph)
				.getResultList();

		System.out.println(" Printing first :"+resultList);

		for (Customer customer : resultList) {
			Set<Order> orders = customer.getOrders();	
			
				
				for (Order order : orders) {
					System.out.println(order.getId());
				Set<Items> itemsEntity = order.getItems();
				for(Items item : itemsEntity){
					System.out.println("Item Id : "+item.getItemId());
				}
			}
		}
	}

}
