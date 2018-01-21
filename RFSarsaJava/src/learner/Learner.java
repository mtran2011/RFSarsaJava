package learner;

import java.util.Map;

import common.RLVec;
import common.StateActionPair;

public abstract class Learner {
	protected StateActionPair lastStateAction;
	
	protected abstract Map<RLVec, Double> findAction(RLVec state);
	
	public abstract RLVec learnAndAct(double reward, RLVec state);
	
	public void resetEpisode() {
		lastStateAction = null;		
	}
}