package assignments;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ENQUIRY")
public class Enquiry {

	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	@Column(name="Enq_ques")
	private String Enq_ques;
	
	public Enquiry()
	{}
	
	public Enquiry(String Enq_ques) {
		this.Enq_ques = Enq_ques;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEnq_ques() {
		return Enq_ques;
	}

	public void setEnq_ques(String Enq_ques) {
		this.Enq_ques = Enq_ques;
	}

	public String toString() {
		return id + " - " + Enq_ques;
	}
	
}
