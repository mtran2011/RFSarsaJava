package environment;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import asset.Asset;

public abstract class Exchange {
	private Set<Trader> traders;
	private Map<Asset, AssetConfig> assets;
	private Map<Asset, Double> previousPrices;
	private Map<Asset, Double> currentPrices;	
	
	public Exchange(Map<Asset, AssetConfig> assets) {
		traders = new HashSet<Trader>();
		this.assets = assets;
		
	}
	
	public boolean registerTrader(Trader trader) {
		boolean alreadyContained = traders.add(trader);
		return alreadyContained;
	}
	
	public abstract void notifyTraders();
	
	// reset the asset and also ask each trader to reset
	public abstract void resetEpisode();
	
	// execute an order and return transaction cost
	public abstract double execute(Order order);
	
	// simulate the assets for one step and then notify traders of new asset prices
	public abstract void simulate(double dt);
}