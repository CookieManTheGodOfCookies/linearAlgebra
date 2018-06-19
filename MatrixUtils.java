package matrixCalc;

import java.io.*;
/**
* Support class to define some matrix operations
**/
public final class MatrixUtils {
	/**
	* The summation of to matrices
	* @param A first Matrix
	* @param B second Matrix
	* @return a matrix that is sum of two matrices
	* @throws IllegalArgumentException if matrices dimensions doesn't match
	**/
	public static Matrix sum(Matrix A, Matrix B) throws IllegalArgumentException {
		if(A.getN() != B.getN() || A.getM() != B.getM())
			throw new IllegalArgumentException();

		Matrix result = new Matrix(A.getN(), A.getM());
		for(int i = 0; i < A.getN(); i++)
			for(int j = 0; j < A.getM(); j++)
				result.setNumber(i, j, A.getNumber(i,j) + B.getNumber(i,j));

		return result;
	}

	/**
	* Substracts two matrices
	* @param A first matrix
	* @param B second matrix
	* @return a matrix that is result of substraction of two matrices
	* @throws IllegalArgumentException if matrices dimensions doesn't match
	**/
	public static Matrix substract(Matrix A, Matrix B) throws IllegalArgumentException {
		if(A.getN() != B.getN() || A.getM() != B.getM())
			throw new IllegalArgumentException();

		Matrix result = new Matrix(A.getN(), A.getM());
		for(int i = 0; i < A.getN(); i++)
			for(int j = 0; j < B.getM(); j++)
				result.setNumber(i, j, A.getNumber(i,j) - B.getNumber(i,j));

		return result;
	}

	/**
	* Multiplies two matrices
	* @param A first matrix
	* @param B second matrix
	* @return a matrix that is a result of multiplication of two matrices
	* @throws IllegalArgumentException if firs matrices number of cols doesn't match second's number of rows
	**/
	public static Matrix multiply(Matrix A, Matrix B) throws IllegalArgumentException {
		int rowsA = A.getN();
		int rowsB = B.getN();
		int colsA = A.getM();
		int colsB = B.getM();

		if(colsA != rowsB)
			throw new IllegalArgumentException("Can't multiply matrices of these dimensions.");

		Matrix result = new Matrix(rowsA, colsB);
		double temp = 0;
		for(int i = 0; i < rowsA; i++) {
			for(int j = 0; j < colsB; j++) {
				temp = 0;
				for(int k = 0; k < colsA; k++) {
					temp += A.getNumber(i, k) * B.getNumber(k, j);
				}
				result.setNumber(i, j, temp);
			}
		}

		return result;
	}
}