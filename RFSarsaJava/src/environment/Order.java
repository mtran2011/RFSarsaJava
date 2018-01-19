package environment;

import java.util.Map;

import asset.Asset;

public class Order {
	private Map<Asset, Integer> assetsAndQuantities;
	
	public Order(Map<Asset, Integer> assetsAndQuantities) {
		this.assetsAndQuantities = assetsAndQuantities;
	}

	public Map<Asset, Integer> getAssetsAndQuantities() {
		return assetsAndQuantities;
	}
	
	public int getQuantity(Asset asset) {
		return assetsAndQuantities.get(asset);
	}
}
