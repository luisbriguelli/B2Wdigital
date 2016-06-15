import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.Scanner;


/**
* 
* Created by Luis Fernando Briguelli da Silva on 13/06/2016.
* 
*/
public class Main {
	static int rowsNumber;
	static int[ ][ ] array;
	static Scanner in;
	
	public static void main(String args[] ) throws Exception {
        in = new Scanner(System.in);
        
        System.out.print("Press 1 to manual input or 2 to generated input: ");
        int option = Integer.parseInt(in.nextLine().trim());
        if(option == 1){
        	manualInput();
        }else{
        	randomInput();
        }
    }
	
	//Automatically generates the number of rows and the values of triangle elements
	//PS: The number of rows and the triangle values range from zero to nine. If necessary change the values, you must change "random.nextInt() parameter". 
	static void randomInput() {
		Random random = new Random();
		rowsNumber = random.nextInt(10);
		if(isRowsNumberValid(rowsNumber)){
			array = new int[rowsNumber][rowsNumber];
	    	for(int i=0; i<rowsNumber; i++) {
	        	for(int j=0; j<=i; j++){
	    			array[i][j] = random.nextInt(10);
	        	}
	    	}
	    	showTriangle();
            startSumAndExecutionTime();
		}
	}

	private static void manualInput() {
		System.out.print("Enter the number of rows: ");
    	rowsNumber = Integer.parseInt(in.nextLine().trim());
    	
        if(isRowsNumberValid(rowsNumber)){
        	array = new int[rowsNumber][rowsNumber];
        	for(int i=0; i<rowsNumber; i++) {
	        	for(int j=0; j<=i; j++){
        			System.out.format("Enter the value of row %d and column %d: ",i,j);
        			array[i][j] = in.nextInt();
	        	}
        	}
        	showTriangle();
            startSumAndExecutionTime();
        }
	}
	
	//Check if the rows number is valid
	static boolean isRowsNumberValid(int rowsNumber ){
		if(rowsNumber > 0){
			return true;
		}else{
			System.out.println("The number of rows must be greater than zero");
			return false;
		}
	}
	
	//Calculates runtime of the calculateHellTriangle method showing its value 
	//The runtime is displayed in nanoseconds, milliseconds and seconds 
	static void startSumAndExecutionTime() {
		Instant startTime = Instant.now();
		
		System.out.format("\nTotal sum: %d \n",calculateHellTriangle(1,0,array[0][0]));
    	
    	Instant endTime = Instant.now();
    	System.out.println("Runtime: " + Duration.between(startTime, endTime).toNanos() +" nanos");
    	System.out.println("Runtime: " + Duration.between(startTime, endTime).toMillis() +" millis");
    	
    	NumberFormat formatter = new DecimalFormat("#0.00000");
    	System.out.println("Runtime: " + formatter.format(Duration.between(startTime, endTime).getSeconds()/1000d) +" seconds");
	}
	
	/**
	*Recursive function that calculate the highest possible value from row one
	*
	*@param currentRow The initial value must be 1
	*@param lastColumn is the column index of the last element selected. The initial value must be 2
	*@param sum is the total sum until the moment. The initial value of sum must be the value of the row element one 
	*
	*/
	static int calculateHellTriangle(int currentRow, int lastColumn, int sum) {
		//Arrived the base of the triangle
		if(currentRow == rowsNumber){
			return sum;
		}else{
			int elementOne = array[currentRow][lastColumn]; //node left child 
			int elementTwo = array[currentRow][lastColumn+1]; //node right child;

			if(calculateHellTriangle(currentRow+1,lastColumn, sum+elementOne) >
					calculateHellTriangle(currentRow+1,lastColumn+1, sum+elementTwo)){
				return calculateHellTriangle(currentRow+1,lastColumn, sum+elementOne);
			}else{
				return calculateHellTriangle(currentRow+1,lastColumn+1, sum+elementTwo);
			}
		}
	}
	
	private static void showTriangle() {
		System.out.println("Triangle view:");
		int blankSpaces = 0;
		for(int i=0; i<rowsNumber; i++) {
			blankSpaces =(rowsNumber-i);
			//Print blank spaces to set the triangle 
			if(blankSpaces > 0){
				System.out.print(String.format("%" +blankSpaces+ "s"," "));
			}

			//Print the element value
			for(int j=0; j<=i; j++){
    			System.out.print(array[i][j] +" ");
        	}
        	System.out.println();
    	}
	}

}
