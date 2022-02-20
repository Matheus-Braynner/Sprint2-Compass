package model.entities;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.Objects;

public class Product {
	private Integer id;
	private String name;
	private String description;
	private Double discount;
	private Date beginDate;
	
	public Product() {
		
	}

	public Product(Integer id, String name, String description, Double discount, Date beginDate) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.discount = discount;
		this.beginDate = beginDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Date getBeginDate() {
		Date beginDate = new Date();
		Calendar cal = Calendar.getInstance();
		beginDate = cal.getTime();
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	
	public void sortProducts() {
		LinkedList<String> listProducts = new LinkedList<>();
		
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", discount=" + discount
				+ ", beginDate=" + beginDate + "]";
	}
}
