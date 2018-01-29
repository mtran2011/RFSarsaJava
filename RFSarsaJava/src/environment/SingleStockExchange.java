package environment;

import java.util.Iterator;
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
		for (Trader trader: getTraders()) {
			trader.resetEpisode();
		}
	}

	@Override
	public void simulate(double dt) {		
		Set<Asset> stockSet = this.getAssets();
		assert stockSet.size() == 1;
		Iterator<Asset> iterator = stockSet.iterator();
		Asset stock = iterator.next();		
		stock.simulate(dt);
		notifyTraders();
	}	
}