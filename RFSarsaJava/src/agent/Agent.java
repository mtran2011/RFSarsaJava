package agent;

public interface Agent {
	public Action learnAndReact(double reward, State state);
	public void resetEpisode();
}