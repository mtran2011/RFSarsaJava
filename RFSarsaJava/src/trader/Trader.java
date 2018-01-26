package trader;

import java.util.Map;

import asset.Asset;
import learner.Learner;

public class Trader {
	
	private Map<Asset, Integer> holdings;
	private Map<Asset, Double> lastSeenPrices;
	private Learner learner;

	public Trader() {
		// TODO Auto-generated constructor stub
	}
	
	public void getNotified(Asset asset, double price) {
		// TODO Auto-generated
	}
	
	public void resetEpisode() {
		// TODO Auto-generated
	}
}
