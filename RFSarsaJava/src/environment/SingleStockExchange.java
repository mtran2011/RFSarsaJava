package environment;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import asset.Asset;
import asset.Stock;
import trader.Trader;

public class SingleStockExchange extends Exchange {

	public SingleStockExchange(Stock stock, AssetConfig config) {
		super();
		addAsset(stock, config);
	}

	@Override
	public void resetEpisode() {
		// only reset the trader, not reset the stock price
		for (Trader trader: this.getTraders()) {
			trader.resetEpisode();
		}
	}

	@Override
	public void simulate(double dt) {		
		Map<Asset, AssetConfig> stockMap = this.getAssets();
		assert stockMap.size() == 1;
		Iterator<Asset> iterator = stockMap.keySet().iterator();
		Asset stock = iterator.next();
		AssetConfig config = stockMap.get(stock);		
		stock.simulate(dt);
		double price = roundPrice(stock.getPrice(), config.getRounding());
		for (Trader trader: this.getTraders()) {
			trader.getNotified(stock, price);
		}
	}
	
}