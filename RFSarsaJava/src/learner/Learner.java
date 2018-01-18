package learner;

public abstract class Learner {
	private StateActionPair lastStateAction;
	
	public Learner() {
		lastStateAction = null;
	}
	
	protected abstract Map<RLAction, Double> findAction(RLState state);
	
	public abstract RLAction learnAndAct(double reward, RLState state);
	
	public void resetEpisode() {
		lastStateAction = null; 
	}
}