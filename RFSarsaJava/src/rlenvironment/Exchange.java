package rlenvironment;

import java.util.HashSet;
import java.util.Set;

public abstract class Exchange {
	private Set<Trader> traders;
	
	public Exchange() {
		traders = new HashSet<Trader>();
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