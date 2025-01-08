/*
 * The following code will take the file name of a pre-created file and check if is a magic square using the private method
 * Alternatively it will create a file that contains the size and details of a magic square
 * The system will also format the output of the println to show the matrix and if it is a magic square.
 * @author James Taylor
 * 
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class MagicSquare implements MagicSquareInterface {
    private int[][] magicArray;
    private Boolean magicResult = true;

    //Constructor that checks if the given file exists then creates and populates the 2D array from the given information.
    public MagicSquare(String fileName) throws FileNotFoundException{
        Path path = Paths.get(fileName);
        if (Files.notExists(path)){
            System.out.println("File could not be open, please check the file exists");
            System.exit(1);
        }
       readMatrix(fileName);
    }

    //Constructor that creates a magic square from the given number and writes it to a file with the provided name
    public MagicSquare(String fileName, int number) throws IOException{
        //Creates a valid magic square
        magicArray = new int[number][number];
        
        int row = number  - 1;
        int col = number / 2;

        int oldRow;
        int oldCol;
        for (int i = 1; i <= (Math.pow((double) number, 2)); i++){
            magicArray[row][col] = i;

            oldRow = row;
            oldCol = col;
            row++;
            col++;

            if (row == number){
                row = 0;
            }
            if (col == number){
                col = 0;
            }
            
            if (magicArray[row][col] != 0){
                row = oldRow ;
                col = oldCol;
                row--;
            }
        }
        //Writes the array to a file
        writeMatrix(magicArray, fileName);

    }

    //Checks if the matrix is a magic square or not and returns a boolean result
    public boolean isMagicSquare() {
        double magicNum = (magicArray.length * ((Math.pow((double) magicArray.length, 2)) + 1) ) /2;

        for (int i = 0; i <= magicNum; i++){
            int occur = 0;
            for (int j = 0 ; j < magicArray.length ; j++){
                for ( int k = 0; k < magicArray[j].length; k++){
                    if (magicArray[j][k] == i){
                        occur++;
                    }
                }
                if (occur > 1){
                    magicResult = false;
                    break;
                }
            }

        }
        //Checks horizontally for magic number
        for (int i = 0 ; i < magicArray.length ; i++){
            double magicHor = magicNum;
            for ( int j = 0; j < magicArray[i].length; j++){
                magicHor -= magicArray[i][j];
            }
            if (magicHor != 0){
                magicResult = false;
                break;
            }
        }
        // Checks vertically for magic number
        if (magicResult != false){
            for (int i = 0 ; i < magicArray.length ; i++){
                double magicVert = magicNum;
                for ( int j = 0; j < magicArray[i].length; j++){
                    magicVert -= magicArray[j][i];
                }
                if (magicVert != 0){
                    magicResult = false;
                    break;
                }
            }
        }

         // Checks diagonally for magic number
         if (magicResult != false){
            double magicDiag = magicNum;
            for (int i = 0 ; i < magicArray.length ; i++){
                magicDiag -= magicArray[i][i];
                }

            if (magicDiag != 0){
                magicResult = false;
            }
        }

         // Checks diagonally for magic number from top right corner
         if (magicResult != false){
            double magicDiag = magicNum;
            int i = 0;
            int j = magicArray.length - 1;
            while ( i < magicArray.length && j >= 0){
                magicDiag = magicDiag -= magicArray[i][j];
                i++;
                j--;
            }
            if (magicDiag != 0){
                magicResult = false;
            }
        }
        return magicResult;
    }

    // Returns a copy of the magic square array
    public int[][] getMatrix() {
        int[][] magicCopy = new int[magicArray.length][];
        
        for (int i = 0; i < magicArray.length; i++){
                magicCopy[i] = magicArray[i].clone();
        }

        return magicCopy;
    }
    
    //Creates the matrix of the given file and populates the 2D array
    private int[][] readMatrix(String filename) throws FileNotFoundException {
        File file = new File(filename);
        try{
            Scanner fileScan = new Scanner(file);
            while (fileScan.hasNextLine()){
                int magicNumber = fileScan.nextInt();
                magicArray = new int[magicNumber][magicNumber];
                for(int i = 0 ; i < magicArray.length; i++){
                    for(int j = 0 ; j < magicArray[i].length; j++){
                    magicArray[i][j] = fileScan.nextInt();
                    }
                }

            }
            fileScan.close();
            //Checks the array is written correctly
        }
        catch( Exception e){
            }


        return magicArray;
    }

    //writes the matrix to the given file
    private void writeMatrix(int[][] matrix, String fileName) throws IOException{
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(new File(fileName)));
            pw.println(matrix.length);
            for (int i = 0 ; i < matrix.length; i++){
               for (int j =0; j <matrix[i].length; j++){
                    pw.print(matrix[i][j]);
                
                    if ( j != matrix.length - 1){
                        pw.print(" ");
                    }

                    if (j == matrix.length - 1 && i != matrix.length - 1){
                        pw.print("\n");
                    }
               }
            }
            pw.close();
        } catch (FileNotFoundException e) {
            System.out.println("File could not be created");
        }
    }

    //Formats the output to show the matrix along with if it is a magic square
    public String toString(){
        String output = "The matrix:\n\t";

        for (int i = 0 ; i < magicArray.length; i++){
            for (int j =0; j <magicArray[i].length; j++){
                 output += magicArray[i][j];
             
                 if ( j != magicArray.length - 1){
                    output += " ";
                 }

                 if (j == magicArray.length - 1 && i == magicArray.length - 1){
                    output +="\n";
                    break;
                 }
                 if(j == magicArray.length - 1) {
                    output +="\n\t";
                 }
            }
         }
         if (magicResult != true){
            output += "is not a magic square";
         } 
         else {
            output += "is a magic square";
         }
        return output;
    }
}
