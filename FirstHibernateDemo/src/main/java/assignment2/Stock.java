package assignment2;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="STOCK")
public class Stock implements Serializable {

	@Id
	@GeneratedValue
	@Column(name="id")
	private Long stockId;
	
	@Column(name="Stock_Value")
	private double stock_value;
	
	@ManyToOne
	@JoinColumn(name="market_id")
	private Market market;
	
	public Stock() {}
	
	public Stock(double stock_value) {
		this.stock_value = stock_value;
	}
	public Stock(double stock_value, Market market) {
		this.stock_value = stock_value;
		this.market = market;
	}
	public Long getStockId() {
		return stockId;
	}
	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}
	public double getStock_value() {
		return stock_value;
	}
	public void setStock_value(double stock_value) {
		this.stock_value = stock_value;
	}
	public Market getMarket() {
		return market;
	}
	public void setMarket(Market market) {
		this.market = market;
	}
	public String toString() {
		return "Stock: " + stockId + " - " + stock_value;
	}
}