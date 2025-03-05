
public class MatSumMain {
	int matrix[][]=
		{
				{ 1, 2, 3, 4, 5} ,
				{ 2, 2, 2, 2, 2 } ,
				{ 3, 0, 3, 8, 3 } ,
				{ 4, 4, 4, 4, 3 } ,
				{ 5, 5, 5, 5, 5 } } ;
	int rows = matrix.length;
	int cols = matrix[0].length;
	int results[];
	void printVector(int[] vec) {
		System.out.print("[");
		for(int i=0; i<vec.length; i++){
			System.out.print(vec[i]+" ");
		}
		System.out.println("]");
	}
	void printMatrix() {
		for(int i=0; i<rows; i++){
			printVector(matrix[i]);
		}
	}
	private int sumRow(int[] vIn) {
		int tSum=0;
		for(int j=0; j<vIn.length; j++) {
			tSum+=vIn[j];
		}
		return tSum;
	}
	public void exec() {
		results=new int[rows];
		printMatrix();
		for(int i=0; i<rows; i++) {
			results[i]=sumRow(matrix[i]);
		}
		printVector(results);
	}
	public static void main(String[] args) {
		MatSumMain msm = new MatSumMain();
		msm.exec();
		System.out.println("Main: finito");
	}

}
