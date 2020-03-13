package pakage2;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="MARKET")
public class Market {

	@Id
	@GeneratedValue
	@Column(name="id")
	private Long marketId;
	
	@Column(name="Market_Name")
	private String market_name;
	
	@OneToMany(cascade={CascadeType.PERSIST},fetch=FetchType.EAGER, mappedBy = "market")
	private Set<Stock> stocks;
	
	public Market() {}
	
	public Market(String market_name, Set<Stock> stocks) {
		this.market_name = market_name;
		this.stocks = stocks;
	}

	public Long getMarketId() {
		return marketId;
	}

	public void setMarketId(Long marketId) {
		this.marketId = marketId;
	}

	public String getMarket_name() {
		return market_name;
	}

	public void setMarket_name(String market_name) {
		this.market_name = market_name;
	}

	public Set<Stock> getStock() {
		return stocks;
	}

	public void setStock(Set<Stock> stocks) {
		this.stocks = stocks;
	}
	
	public String toString() {
		return marketId + " - " + market_name + " - " + stocks;
	}
}
