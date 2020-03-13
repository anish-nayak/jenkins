package com.hibernate.FirstHibernateDemo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="EMPLOYEE_MASTER")
public class Employee {
	
	@GeneratedValue(strategy=GenerationType.AUTO, generator="pr")
	@SequenceGenerator(name="pr", sequenceName="prod_gen")
	@Id
	@Column(name="e_id")
	private Long id;
	
	@Column(name="e_name")
	private String name;
	
	@Column(name="e_location")
	private String location;
	
	@Column(name="e_salary")
	private double salary;

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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", location=" + location + ", salary=" + salary + "]";
	}

	public Employee(Long id, String name, String location, double salary) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.salary = salary;
	}

}
