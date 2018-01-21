package common;

public interface RLVec {
	@Override
	public int hashCode();
	@Override
	public boolean equals(Object obj);
	public double[] toArray();
}
