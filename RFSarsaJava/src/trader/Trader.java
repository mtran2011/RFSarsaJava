package trader;

import java.util.HashMap;
import java.util.Map;

import asset.Asset;
import environment.Exchange;
import learner.Learner;

public abstract class Trader {
	
	private class HoldingAndPrice {
		private int holding;
		private double lastSeenPrice;
	}
		
	private String name;
	private double utility;
	private Learner learner;
	private Exchange exchange;
	private Map<Asset, HoldingAndPrice> holdings;
	
	public Trader(String name, double utility, Learner learner, Exchange exchange) {
		assert utility >= 0;
		this.name = name;
		this.utility = utility;
		this.learner = learner;
		this.exchange = exchange;
		holdings = new HashMap<Asset, HoldingAndPrice>();
		
		// TODO
		
		this.exchange.registerTrader(this);
	}
	
	public void getNotified(Asset asset, double price) {
		// TODO Auto-generated
	}
	
	public void resetEpisode() {
		// TODO Auto-generated
	}
	
	public abstract void placeOrders();
}
