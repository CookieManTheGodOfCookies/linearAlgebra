package matrixCalc;

import java.util.Scanner;
import java.io.*;

/**
* Simple Matrix of doubles. Remember that size of matrix is immutable after making an instance.
**/
public class Matrix {
	// number of rows
	protected int n;
	// number of columns
	protected int m;
	// numbers
	protected double [][] numbers;

	/**
	* Create a n x m matrix filled with zeros
	* @param n number of rows
	* @param m number of columns
	* @throws IllegalArgumentException if either n or m is less or equals to 0
	**/
	public Matrix(int n, int m) throws IllegalArgumentException {
		if(n <= 0 || m <= 0)
			throw new IllegalArgumentException("Negative matrix size!");
		this.n = n;
		this.m = m;
		numbers = new double[n][m];
		for(int i = 0; i < this.n; i++)
			for(int j = 0; j < this.m; j++)
				numbers[i][j] = 0;
	}

	/**
	* Create a n x m matrix filled with fill number
	* @param n number of rows
	* @param m number of columns
	* @param fill fill number
	* @throws IllegalArgumentException if either n or m is less or equals to 0
	**/
	public Matrix(int n, int m, double fill) throws IllegalArgumentException {
		if(n <= 0 || m <= 0)
			throw new IllegalArgumentException("Negative matrix size!");
		this.n = n;
		this.m = m;
		numbers = new double[n][m];
		for(int i = 0; i < this.n; i++)
			for(int j = 0; j < this.m; j++)
				numbers[i][j] = fill;
	}

	/**
	* Returns number of rows
	* @return integer - number of rows
	**/
	public int getN() { return this.n; }

	/**
	* Returns number of columns
	* @return integer - number of columns
	**/
	public int getM() { return this.m; }

	/**
	* Sets an (i,j) element with value
	* @param i integer - vertical index of element
	* @param j integer - horizontal index of element
	* @param value value to set
	* @throws IllegalArgumentException if one of indexes is out of bounds
	**/
	public void setNumber(int i, int j, double value) throws IllegalArgumentException {
		if(i < 0 || i >= n || j < 0 || j >= m)
			throw new IllegalArgumentException("Index out of bounds!");
		this.numbers[i][j] = value;
	}

	/**
	* Returns a number from (i,j) position
	* @param i integer - vertical index of element
	* @param j integet - horizontal index of element
	* @return value of (i,j)'s element
	* @throws IllegalArgumentException if one of indexes is out of bounds
	**/
	public double getNumber(int i, int j) throws IllegalArgumentException {
		if(i < 0 || i >= n || j < 0 || j >= m)
			throw new IllegalArgumentException("Index out of bounds!");
		return this.numbers[i][j];
	}

	/**
	* Adds a value to every element of matrix.
	* Add a negative value to substract (wow!).
	* @param value double - value to add
	**/
	public void addNumber(double value) {
		for(int i = 0; i < this.n; i++)
			for(int j = 0; j < this.m; j++)
				this.numbers[i][j] += value;
	}

	/**
	* Multiplies the matrix by a number.
	* @param value double - number to multiply by.
	* @return matrix - the result of multiplication
	**/
	public Matrix multiplyBy(double value) {
		Matrix result = new Matrix(this.n, this.m);
		for(int i = 0; i < this.n; i++)
			for(int j = 0; j < this.m; j++)
				result.setNumber(i,j, numbers[i][j] * value);

		return result;
	}

	/**
	* Matrix keyboard input
	**/
	public void input() {
		Scanner in = new Scanner(System.in);
		for(int i = 0; i < this.n; i++)
			for(int j = 0; j < this.m; j++)
				this.numbers[i][j] = in.nextDouble();
	}

	/**
	* Returns a submatrix made of elements in rectangle between points x and y:
	* x coords are (xi, xj), y coords are (yi,ji)
	* @param xi integer - vertical coordinate of left top corner of submatrix
	* @param xj integer - horizontal coordinate of left top corner of submatrix
	* @param yi integer - vertical coordinate of right bottom corner of submatrix
	* @param yj integer - horizontal coordinate of right bottom corner of submatrix
	* 
	* @throws IllegalArgumentException if coords are out of bounds or can't make a rectangle between these points
	* @return submatrix - a sub-matrix of the matrix
	**/
	public Matrix getSubmatrix(int xi, int xj, int yi, int yj) throws IllegalArgumentException {
		if(xi >= yi || xj >= yj || xi < 0 || xj < 0 || yi < 0 || yj < 0)
			throw new IllegalArgumentException("These coordinates doesn't make a submatrix");

		int submatrixN = yi - xi;
		int submatrixM = yj - xj;

		Matrix submatrix = new Matrix(submatrixN, submatrixM);

		/*
		There i,j go through initial matrix, mi, mj go through submatrix
		*/
		int i = 0, mi = 0, j = 0, mj = 0;
		for(i = xi, mi = 0; i < yi && mi < submatrixN; i++, mi++) {
			for(j = xj, mj = 0; j < yj && mj < submatrixM; j++, mj++) {
				submatrix.numbers[mi][mj] = this.numbers[i][j];
			}
		}
		return submatrix;
	}

	/**
	* Returns a row with index i
	* @param i integer - index of a row
	* @return a matrix (row)
	* @throws IllegalArgumentException if index is out of bounds
	**/
	public Matrix getRow(int i) throws IllegalArgumentException {
		if(i >= this.n || i < 0)
			throw new IllegalArgumentException("Index out of bounds!");
		Matrix row = new Matrix(1,this.m);

		for(int j = 0; j < this.m; j++) {
			row.numbers[0][j] = this.numbers[i][j];
		}
		return row;
	}

	/**
	* Returns a column with index j
	* @param j integer - index of a column
	* @return a matrix (column)
	* @throws IllegalArgumentException if index is out of bounds
	**/
	public Matrix getColumn(int j) throws IllegalArgumentException {
		if(j >= this.m || j < 0)
			throw new IllegalArgumentException("Index out of bounds!");
		Matrix column = new Matrix(this.n, 1);

		for(int i = 0; i < this.n; i++) {
			column.numbers[i][0] = this.numbers[i][j];
		}

		return column;
	}

	/**
	* Returns true if the matrix is square, else false
	* @return true if square, else false
	**/
	public boolean isSquare() { return this.m == this.n; }

	/**
	* Returns true if the matrix is upper triangular, else false
	* @return true if lower triangular, else false
	**/
	public boolean isUpperTriangular() {
		if(!isSquare())
			return false;
		else {
			boolean isUpTr = true;
			for(int i = 1; i < this.n; i++) {
				for(int j = 0; j < i; j++) {
					if(numbers[i][j] != 0) {
						isUpTr = false;
						break;
					}
				}
			}
			return isUpTr;
		}
	}

	/**
	* Returns true if matrix is lower triangular, else false
	* @return true if lower triangular, else false
	**/
	public boolean isLowerTriangular() {
		if(!isSquare())
			return false;
		else {
			boolean isLoTr = true;
			for(int i = 0; i < this.n; i++) {
				for(int j = i + 1; j < this.m; j++) {
					if(numbers[i][j] != 0) {
						isLoTr = false;
						break;
					}
				}
			}
			return isLoTr;
		}
	}

	/**
	* Transposes a matrix
	* @return a matrix that is the result of transposition
	**/
	public Matrix transpose() {
		Matrix result = new Matrix(getM(), getN());

		for(int i = 0; i < getN(); i++) {
			for(int j = 0; j < getM(); j++) {
				result.setNumber(j,i, numbers[i][j]);
			}
		}
		return result;
	}


	/**
	* Multiplies a row by a value
	* @param index index of row
	* @param value a value to multiply by
	* @throws IllegalArgumentException if index is out of bounds
	**/
	public void multiplyRow(int index, double value) throws IllegalArgumentException {
		if(index < 0 || index > this.n)
			throw new IllegalArgumentException("Index is out of bounds!");

		for(int i = 0; i < this.m; i++)
			numbers[index][i] *= value;
	}

	/**
	* Multiplies a column by a value
	* @param index index of column
	* @param value a value to multiply by
	* @throws IllegalArgumentException if index is out of bounds
	**/
	public void multiplyColumn(int index, double value) throws IllegalArgumentException {
		if(index < 0 || index > this.m)
			throw new IllegalArgumentException("Index is out of bounds!");

		for(int i = 0; i < this.n; i++)
			numbers[i][index] *= value;
	}

	/**
	* Adds a (i) row to another (j) row
	* @param index1 index of first row
	* @param index2 index of target row
	* @throws IllegalArgumentException if one of indexes is out of bounds
	**/ 
	public void sumRow(int index1, int index2) throws IllegalArgumentException {
		if(index1 < 0 || index2 < 0 || index1 > this.n || index2 > this.n)
			throw new IllegalArgumentException("One of indexes is out of bounds!");

		for(int i = 0; i < this.m; i++)
			numbers[index2][i] += numbers[index1][i];
	}

	/**
	* Adds a col to another col
	* @param index1 index of first col
	* @param index2 index of target col
	* @throws IllegalArgumentException if one of indexes is out of bounds
	**/
	public void sumCol(int index1, int index2) throws IllegalArgumentException {
		if(index1 < 0 || index1 > this.m || index2 < 0 || index2 > this.m)
			throw new IllegalArgumentException("One of indexes is out of bounds!");

		for(int i = 0; i < this.n; i++)
			numbers[i][index2] += numbers[i][index1];
	}

	/**
	* Substracts a row from another row
	* @param index1 index of first row
	* @param index2 index of target row
	* @throws IllegalArgumentException if one of indexes is out of bounds
	**/
	public void substRow(int index1, int index2) throws IllegalArgumentException {
		if(index1 < 0 || index1 > this.n || index2 < 0 || index2 > this.n)
			throw new IllegalArgumentException("One of indexes is out of bounds!");

		for(int i = 0; i < this.n; i++)
			numbers[index2][i] -= numbers[index1][i];
	}

	/**
	* Substracts a col from another col
	* @param index1 index of first col
	* @param index2 index of target col
	* @throws IllegalArgumentException if one of indexes is out of bounds
	**/
	public void substCol(int index1, int index2) throws IllegalArgumentException {
		if(index1 < 0 || index1 > this.m || index2 < 0 || index2 > this.m)
			throw new IllegalArgumentException("One of indexes is out of bounds!");

		for(int i = 0; i < this.n; i++)
			numbers[i][index2] -= numbers[i][index1];
	}

	/**
	* Gets minor of an element with specified indexes
	* @param i vertical index
	* @param j horizontal index
	* @throws IllegalArgumentException if one of indexes is out of bounds
	* @return matrix - minor of an element
	**/
	public Matrix getMinor(int i, int j) throws IllegalArgumentException {
		if(i < 0 || i > this.n || j < 0 || j > this.m)
			throw new IllegalArgumentException("One of indexes is out of bounds!");

		Matrix minor = new Matrix(this.n - 1, this.m - 1);
		int ii, ci, jj, cj;
		for(ii = 0, ci = 0; ii < this.n; ii++) {
			if(ii == i) continue;
			for(jj = 0, cj = 0; jj < this.m; jj++) {
				if(jj == j) continue;
				minor.setNumber(ci, cj, numbers[ii][jj]);
				cj++;
			}
			ci++;
		}

		return minor;
	}

	/**
	* Gets determinant of square matrix
	* @return determinant of matrix
	* @throws UnsupportedOperationException if matrix is not square
	**/
	public double det() throws UnsupportedOperationException {
		if(!isSquare())
			throw new UnsupportedOperationException("Can't calculate determinant of not square matrix!");

		if(this.n == 1) return numbers[0][0];
		if(this.n == 2) return numbers[0][0] * numbers[1][1] - numbers[0][1] * numbers[1][0];
		else {
			double det = 0;
			for(int i = 0; i < this.m; i++) {
				if(i % 2 == 0) det += numbers[0][i] * this.getMinor(0, i).det();
				else det -= numbers[0][i] * this.getMinor(0,i).det();
			}
			return det;
		}
	}


	/**
	* @return true if matrix is invertible, else false
	**/
	public boolean isInvertible() {
		if(!isSquare())
			return false;
		else if (det() == 0)
			return false;
		else return true;
	}

	/**
	* Returns a cofactor matrix
	* @return matrix made of cofactor
	* @throws UnsupportedOperationException if matrix isn't square
	**/

	public Matrix getCofactorMatrix() throws UnsupportedOperationException {
		if(!isSquare())
			throw new UnsupportedOperationException("Can't calculate cofactor matrix for not square matrix!");
		Matrix cofactorMatrix = new Matrix(this.n, this.m);
		for(int i = 0; i < this.n; i++) {
			for(int j = 0; j < this.m; j++) {
				cofactorMatrix.setNumber(i,j, (this.getMinor(i,j).det() * Math.pow(-1, (i+j))));
			}
		}
		return cofactorMatrix;
	}

	/**
	* Gets adjugate matrix for this matrix
	* @return adjugate matrix
	* @throws UnsupportedOperationException if matrix isn't square
	**/
	public Matrix getAdjugateMatrix() throws UnsupportedOperationException {
		if(!isSquare())
			throw new UnsupportedOperationException("Can't get adjugate from not square matrix!");

		return this.getCofactorMatrix().transpose();
	}

	/**
	* Gets an inversve matrix of this matrix
	* @return inverse matrix
	* @throws UnsupportedOperationException if matrix isn't invertible
	**/
	public Matrix getInverse() throws UnsupportedOperationException {
		if(!isSquare() || !isInvertible())
			throw new UnsupportedOperationException("Matrix is not invertible!");
		else return this.getAdjugateMatrix().multiplyBy(1 / this.det());
	}


	@Override
	public String toString() {
		String matrix = "";
		for(int i = 0; i < this.n; i++) {
			matrix += "(";
			for(int j = 0; j < this.m; j++) {
				if(j == this.m - 1) {
					matrix += this.numbers[i][j] + ")\n";
					break;
				}
				matrix += this.numbers[i][j] + ", ";
			}
		}
		return matrix;
	}
}