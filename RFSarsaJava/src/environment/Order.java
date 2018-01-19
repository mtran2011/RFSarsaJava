package environment;

import asset.Asset;

public class Order {
	private Asset asset;
	private int quantity;
	
	public Asset getAsset() {
		return asset;
	}

	public int getQuantity() {
		return quantity;
	}

	public Order(Asset asset, int quantity) {
		this.asset = asset;
		this.quantity = quantity;
	}

}
