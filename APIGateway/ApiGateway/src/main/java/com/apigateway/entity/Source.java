package com.apigateway.entity;

import java.util.ArrayList;

class Product {

	private String id;
	private String name;
	private int quantity;
	private float price;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Product(String id, String name, int quantity, float price) {
		super();
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}

}

class Cart {

	public ArrayList<Product> productList;

	public ArrayList<Product> getProductList() {
		return productList;
	}

	public void setProductList(ArrayList<Product> productList) {
		this.productList = productList;
	}

	public Cart() {
		super();
	}

	public Cart(ArrayList<Product> productList) {
		super();
		this.productList = productList;
	}

	public int totalItem() {
		int totalQuantity = 0;
		for (Product product : productList) {
			totalQuantity += product.getQuantity();
		}
		return totalQuantity;
	}

	public float netPrice() {
		int totalPrice = 0;
		for (Product product : productList) {
			totalPrice += product.getPrice() * product.getQuantity();
		}
		return totalPrice;
	}

}

public class Source {
	public static void main(String[] args) {
		Cart obj = new Cart();
		Product p1 = new Product("Ab01", "Product1", 3, 200);
		Product p2 = new Product("Ab02", "Product2", 5, 500);
		obj.productList.add(p1);
		obj.productList.add(p2);
		System.out.println(obj.netPrice());
		System.out.println(obj.totalItem());
	}
}
