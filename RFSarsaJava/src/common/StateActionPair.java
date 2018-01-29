package common;

public class StateActionPair implements RLVec {
	private RLVec state;
	private RLVec action;
	
	public StateActionPair() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int hashCode();
	@Override
	public boolean equals(Object obj);
	
	@Override
	public double[] toArray() {
		// TODO Auto-generated method stub
	}
}
