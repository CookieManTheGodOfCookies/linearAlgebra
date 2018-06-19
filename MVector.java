package matrixCalc;

/**
* A matrix that represents a vector(horizontal length = 1).
**/
public final class MVector extends Matrix {

	/**
	* Makes a matrix-vector of n elements filled with zeros
	* @param n number of elements
	**/
	public MVector(int n) {
		super(n,1);
	}

	/**
	* Makes a matrix-vector of n elements filled with specified value
	* @param n number of elements
	* @param fill value to fill
	**/
	public MVector(int n, double fill) { super(n, 1, fill); }
	
	@Override
	public String toString() {
		String vector = "";
		for(int i = 0; i < this.n; i++) {
			vector += "(" + numbers[i][0] + ")\n";
		}
		return vector;
	}

	public static void main(String[] args) {
		MVector m = new MVector(4);
		System.out.print(m);
	}
}