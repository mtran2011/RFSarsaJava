package environment;

import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import asset.Asset;
import trader.Trader;

public abstract class Exchange {
	private Set<Trader> traders;
	private Map<Asset, AssetConfig> assets;	
	
	public Exchange(Map<Asset, AssetConfig> assets) {
		traders = new HashSet<Trader>();
		this.assets = assets;		
	}
	
	public int getMaxHolding(Asset asset) {
		if (!assets.containsKey(asset)) {
			return -1;
		}
		return assets.get(asset).getMaxholding();		
	}
	
	private double roundPrice(double price, int rounding) {
		assert rounding == 0 || rounding == 1 || rounding ==2;
		if (rounding == 0) {
			return Math.round(price);
		} else if (rounding == 1) {
			return Math.round(price * 10.0) / 10.0;
		}
		return Math.round(price * 100.0) / 100.0;
	}
	
	public boolean addAsset(Asset asset, AssetConfig config) {
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
		
	public void registerTrader(Trader trader) {
		traders.add(trader);
		for (Entry<Asset, AssetConfig> entry: assets.entrySet()) {
			Asset asset = entry.getKey();
			AssetConfig config = entry.getValue();
			double price = roundPrice(asset.getPrice(), config.getRounding());
			trader.getNotified(asset, price);
		}
	}
	
	private void notifyTraders() {
		for (Entry<Asset, AssetConfig> entry: assets.entrySet()) {
			Asset asset = entry.getKey();
			AssetConfig config = entry.getValue();
			double price = roundPrice(asset.getPrice(), config.getRounding());
			for (Trader trader: traders) {
				trader.getNotified(asset, price);
			}
		}
	}
	
	// reset the asset and also ask each trader to reset
	public abstract void resetEpisode();	
	
	public double execute(Order order) {
		// execute an order and return the total transaction cost
		// return null if the order is for an asset not 
	}
	
	// simulate the assets for one step and then notify traders of new asset prices
	public abstract void simulate(double dt);
}