package asset;

public abstract class Stock implements Asset {

	private double price;
	private double minprice;
	private double maxprice;	
	
	public Stock(double price, double minprice, double maxprice) {
		assert minprice < price && price < maxprice;
		assert minprice >= 0;
		this.price = price;
		this.minprice = minprice;
		this.maxprice = maxprice;
	}
	
	@Override
	public double getPrice() {
		return price;
	}
	
	protected void setPrice(double price) {
		price = Math.min(price, maxprice);
		price = Math.max(price, minprice);
		this.price = price;
	}
}