package TheExam;

import java.util.Arrays;
import java.util.Scanner;

public class P02_Matrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] size = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int rows = size[0];
        int cols = size[1];
        String[][] matrix = new String[rows][cols];

        int myRow = 0;
        int myCol = 0;
        int firstRow = 0;
        int firstCol = 0;
        int secondRow = 0;
        int secondCol = 0;
        int thirdRow = 0;
        int thirdCol = 0;

        boolean firstIsFound = false;
        boolean secondIsFound = false;

        for (int row = 0; row < rows; row++) {
            String[] input = scanner.nextLine().split("\\s+");
            for (int col = 0; col < cols; col++) {
                matrix[row][col] = input[col];

                if (matrix[row][col].equals("B")){
                    myRow = row;
                    myCol = col;
                }
                if (matrix[row][col].equals("P")){

                    if (!firstIsFound && !secondIsFound){
                        firstRow = row;
                        firstCol = col;
                        firstIsFound = true;
                    }else if (firstIsFound && !secondIsFound){
                        secondRow = row;
                        secondCol = col;
                        secondIsFound = true;
                    }else if (firstIsFound && secondIsFound){
                        thirdRow = row;
                        thirdCol = col;
                    }
                }
            }
        }

        String command = scanner.nextLine();
        int foundedOpponents = 0;
        int moveCounter = 0;

        while (!command.equals("Finish")){

            int myOldRow = myRow;
            int myOldCol = myCol;

            switch (command){
                //"up", " down", "right", and "left"
                case "up":
                    myRow -= 1;
                    moveCounter ++;
                    break;
                case "down":
                    myRow += 1;
                    moveCounter++;
                    break;
                case "right":
                    myCol += 1;
                    moveCounter++;
                    break;
                case "left":
                    myCol -= 1;
                    moveCounter++;
                    break;
            }

            if (myRow >= rows || myCol >= cols || myRow < 0 || myCol < 0){
                myRow = myOldRow;
                myCol = myOldCol;
                moveCounter -= 1;
            }
//            if (myRow >= rows){
//                myRow -= 1;
//                moveCounter --;
//            } else if (myCol >= cols){
//                myCol -= 1;
//            } else if (myRow < 0){
//                myRow += 1;
//                moveCounter --;
//            } else if (myCol < 0){
//                myCol += 1;
//                moveCounter --;
//            }
            if (matrix[myRow][myCol].equals("O")){
                myRow = myOldRow;
                myCol = myOldCol;
                moveCounter -= 1;
            }
            if (matrix[myRow][myCol].equals("P")){
                foundedOpponents += 1;
                matrix[myRow][myCol] = "-";
            }

            if (foundedOpponents == 3){
                break;
            }


            command = scanner.nextLine();
        }

        System.out.println("Game over!");
        System.out.printf("Touched opponents: %d Moves made: %d%n", foundedOpponents, moveCounter);

    }
    private static void printMatrix(String[][] matrix, int rows, int cols) {
                for (int row = 0; row < rows; row++) {
                    for (int col = 0; col < cols; col++) {
                        System.out.print(matrix[row][col] + " ");
                    }
                    System.out.println(); //свали курсора на следващия ред
                }
            }
    //        printMatrix(matrix, rows, cols);
//        System.out.println(myRow);
//        System.out.println(myCol);
//        System.out.println(firstRow);
//        System.out.println(firstCol);
//        System.out.println(secondRow);
//        System.out.println(secondCol);
//        System.out.println(thirdRow);
//        System.out.println(thirdCol);
}
