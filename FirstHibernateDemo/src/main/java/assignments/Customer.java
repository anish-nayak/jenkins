package assignments;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="CUSTOMER")
public class Customer {

	@Id
	@GeneratedValue
	@Column(name="id")
	private Long customerId;

	@Column(name="name")
	private String name;

	@OneToOne(cascade={CascadeType.PERSIST, CascadeType.ALL}, fetch=FetchType.EAGER)
	@JoinColumn(name="enquiry_id")
	private Enquiry enquiry;
	public Customer()
	{
		
	}
	public Customer(String name, Enquiry enquiry) {
		this.name = name;
		this.enquiry = enquiry;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Enquiry getEenquiry() {
		return enquiry;
	}

	public void setEnquiry(Enquiry enquiry) {
		this.enquiry = enquiry;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String toString() {
		return customerId + " - " + name  + " - enquiry: " + enquiry;
	}
}
	