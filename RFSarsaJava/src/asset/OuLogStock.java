package asset;

import java.util.Random;

public class OuLogStock extends Stock {
	private double kappa;
	private double mu;
	private double sigma;
	private Random random;
	
	public OuLogStock(double price, double minprice, double maxprice, double kappa, double mu, double sigma) {		
		super(price, minprice, maxprice);
		assert kappa >= 0 && sigma >= 0;
		this.kappa = kappa;
		this.mu = mu;
		this.sigma = sigma;
		random = new Random();
	}

	@Override
	public void simulate(double dt) {
		double price = getPrice();
		if (price == 0) {
			return;
		}
		double oldLog = Math.log(price);
		double dlogS = kappa * (mu - oldLog) * dt + sigma * Math.sqrt(dt) * random.nextGaussian();
		price = price * Math.exp(dlogS);
		setPrice(price);
	}

}