package environment;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import asset.Asset;
import trader.Trader;

public abstract class Exchange {
	private Set<Trader> traders;
	private Map<Asset, AssetConfig> assets;	
	
	private double roundPrice(double price, int rounding) {
		assert rounding == 0 || rounding == 1 || rounding ==2;
		if (rounding == 0) {
			return Math.round(price);
		} else if (rounding == 1) {
			return Math.round(price * 10.0) / 10.0;
		}
		return Math.round(price * 100.0) / 100.0;
	}
	
	protected Exchange() {
		traders = new HashSet<Trader>();
		assets = new HashMap<Asset, AssetConfig>();
	}
	
	protected Set<Asset> getAssets() {
		return assets.keySet();
	}
	
	protected Set<Trader> getTraders() {
		return traders;
	}
	
	protected boolean addAsset(Asset asset, AssetConfig config) {
		if (assets.containsKey(asset)) {
			return false;
		}
		assets.put(asset, config);
		double price = roundPrice(asset.getPrice(), config.getRounding());		
		for (Trader trader: traders) {
			trader.getNotified(asset, price);
		}
		return true;
	}

	protected void notifyTraders() {
		for (Entry<Asset, AssetConfig> entry: assets.entrySet()) {
			Asset asset = entry.getKey();
			AssetConfig config = entry.getValue();
			double price = roundPrice(asset.getPrice(), config.getRounding());
			for (Trader trader: traders) {
				trader.getNotified(asset, price);
			}
		}
	}
		
	public void registerTrader(Trader trader) {
		traders.add(trader);
		for (Entry<Asset, AssetConfig> entry: assets.entrySet()) {
			Asset asset = entry.getKey();
			AssetConfig config = entry.getValue();
			double price = roundPrice(asset.getPrice(), config.getRounding());
			trader.getNotified(asset, price);
		}
	}
	
	public int getMaxHolding(Asset asset) {
		assert assets.containsKey(asset); 
		return assets.get(asset).getMaxholding();		
	}
	
	public double execute(Order order) {
		// execute an order and return the total transaction cost
		// return -1 if the order is for an asset not
		Asset asset = order.getAsset();
		int quantity = order.getQuantity();
		assert assets.containsKey(asset);
		AssetConfig config = assets.get(asset);
		double numLots = Math.abs(quantity) * 1.0 / config.getLotsize();
		double spreadCost = numLots * config.getTick();
		double impactCost = Math.pow(numLots, 2) * config.getTick();
		return spreadCost + impactCost;
	}

	// reset the required asset and also ask each trader to reset
	public abstract void resetEpisode();	
	
	// simulate the assets for one step and then notify traders of new asset prices
	public abstract void simulate(double dt);
}