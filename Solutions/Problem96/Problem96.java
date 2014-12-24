import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Problem96 {

    public static void main(String[] args) throws IOException{
        String b = "100920000524010000000000070050008102000000000402700090060000000000030945000071006";
        BufferedReader br = new BufferedReader(new FileReader("/Users/stephen/Downloads/p096_sudoku.txt"));
        String line = "";
        int solution = 0;
        while ((line = br.readLine()) != null){
            Board board = new Board(line);
            board = solveBoard(board);


            Cell[] cells = board.getCells();
            int puzzleSolution = 0;
            for (int i = 0; i < 3; i++){
                int cellValue = cells[i].getValue();
                puzzleSolution *= 10;
                puzzleSolution += cellValue;
            }
            printGameBoard(board);
            System.out.println("\n the puzzle solution is " + puzzleSolution);
            solution += puzzleSolution;
        }
        System.out.println("\n"+solution);
    }

    public static Board solveBoard(Board board){

        // Initiate the possible lists for non-assigned values
        // defaulting to all numbers. The possible lists will be
        // cleaned in the next method.
        establishPossibleLists(board);

        //Establish the "possible numbers" for each Cell that is not already assigned
        solveLogically(board);
        if (isBoardSolved(board)){
            return board;
        }

        //The above methods are enough to consistently solve easy and medium
        //level problems. From here, recursive backtracking is used to solve the
        //harder level problems.
        return solveRecursively(board);

    }

    //Attempts to solve board without recursive backtracking
    //It solves by checking possible values for each cell and making
    //comparisons to determine if the cells can be assigned a definite value
    public static void solveLogically(Board board){
        do{
            removeConfirmedFromPossibleLists(board);
        } while (simplifyPossibilities(board));
    }

    // Initiate the possible lists for non-assigned values
    // defaulting to all numbers (1 to 9). The possible lists will
    // be trimmed down in the 'removeConfirmedFromPossibleLists' method
    public static void establishPossibleLists(Board b){
        Cell[] cells = b.getCells();
        for (int i = 0; i < cells.length; i++){
            if (!cells[i].isAssigned()){
                List<Integer> possibleList = new ArrayList<Integer>();
                for (int j = 1; j < 10; j++){
                    possibleList.add(j);
                }
                cells[i].setPossibles(possibleList);
            }
        }
    }

    // This method is used to loop through the Cells, reducing the possible lists
    // It does this by comparing the possible numbers against numbers that are confirmed
    // If a number is confirmed, it can be removed from the possibleList
    // Once a possibleList is reduced to only one number, the cell can be assigned that number
    public static void removeConfirmedFromPossibleLists(Board b){
        Cell[] cells = b.getCells();
        boolean changeOccurred;

        // every time a list is narrowed down, recalculate all lists in case the change affects them
        do {
            //checks to see if a list was narrowed down in this loop instance
            //if a change occurred, we repeat the loop
            changeOccurred = false;

            //Looping through all the cells, to act on each one
            for (int i = 0; i < cells.length; i++) {
                //Ensure that we only act on cells that are not already assigned
                if (!cells[i].isAssigned()) {
                    List<Integer> possibleList = cells[i].getPossibles();

                    Cell[] cellSquare = b.getSquareByCellIndex(i);
                    Cell[] cellColumn = b.getColumnByCellIndex(i);
                    Cell[] cellRow = b.getRowByCellIndex(i);

                    //removing numbers from possibleLists that are confirmed to be in the same square
                    for (int j = 0; j < cellSquare.length; j++) {
                        Cell c = cellSquare[j];
                        if (c.isAssigned() && possibleList.contains(c.getValue())) {
                            possibleList.remove(new Integer(c.getValue()));
                            changeOccurred = true;
                        }
                    }

                    //removing numbers from possibleLists that are confirmed to be in the same column
                    for (int j = 0; j < cellColumn.length; j++) {
                        Cell c = cellColumn[j];
                        if (c.isAssigned() && possibleList.contains(c.getValue())) {
                            possibleList.remove(new Integer(c.getValue()));
                            changeOccurred = true;
                        }
                    }

                    //removing numbers from possibleLists that are confirmed to be in the same row
                    for (int j = 0; j < cellRow.length; j++) {
                        Cell c = cellRow[j];
                        if (c.isAssigned() && possibleList.contains(c.getValue())) {
                            possibleList.remove(new Integer(c.getValue()));
                            changeOccurred = true;
                        }
                    }

                    //if a possibleList is completely narrowed down to 1 element, we assign the cell
                    //to the value of the remaining integer
                    if (possibleList.size() == 1) {
                        cells[i].setValue(possibleList.get(0));
                        cells[i].setPossibles(new ArrayList<Integer>());
                        cells[i].setAssigned(true);
                    } else {
                        cells[i].setPossibles(possibleList);
                    }
                }
            }
        } while (changeOccurred);
    }

    // This method compares the possibleLists for each cell in a group and determines
    // if a possibility appears only once for any individual cell. From there, we can assign
    // that possibility to the cell.
    // This function returns true if a change has been made, so that we know to repeat
    // the function to see if further changes can be made (after first rerunning removeConfirmedFromPossibleLists)
    public static boolean simplifyPossibilities(Board b){
        Cell[] cells = b.getCells();

        for (int i = 0; i < cells.length; i++){
            //ensure that we only work with cells that are not already assigned
            if (!cells[i].isAssigned()) {
                Cell[] cellSquare = b.getSquareByCellIndex(i);
                Cell[] cellRow = b.getRowByCellIndex(i);
                Cell[] cellColumn = b.getColumnByCellIndex(i);
                List<Integer> currentCellPossibleList = cells[i].getPossibles();

                //We create a master list of all possibilities that occur within the group
                //We exclude the the current cell so that we can compare the current cell's
                //possibilities to the rest of the groups'
                List<Integer> combinedPossibleList = new ArrayList<Integer>();
                for (int j = 0; j < cellRow.length; j++) {
                    //ensure that we are excluding the current cell from the master list
                    if (j != (i % 9)) {
                        List<Integer> currentPossibleList = cellRow[j].getPossibles();
                        for (int k = 0; k < currentPossibleList.size(); k++){
                            combinedPossibleList.add(currentPossibleList.get(k));
                        }
                    }
                }
                for (int j = 0; j < currentCellPossibleList.size(); j++){
                    int currentCellTest = currentCellPossibleList.get(j);
                    if (!combinedPossibleList.contains(currentCellTest)){
                        //since the currentCellTest is unique to the possibleList of
                        //only this cell, we can assign it as a known value to the cell
                        cells[i].setValue(currentCellTest);
                        cells[i].setPossibles(new ArrayList<Integer>());
                        cells[i].setAssigned(true);
                        return true;
                    }
                }

                //We create a master list of all possibilities that occur within the group
                //We exclude the the current cell so that we can compare the current cell's
                //possibilities to the rest of the groups'
                combinedPossibleList = new ArrayList<Integer>();
                for (int j = 0; j < cellColumn.length; j++) {
                    if (j != (i/9)){
                        List<Integer> currentPossibleList = cellColumn[j].getPossibles();
                        for (int k = 0; k < currentPossibleList.size(); k++){
                            combinedPossibleList.add(currentPossibleList.get(k));
                        }
                    }
                }
                for (int j = 0; j < currentCellPossibleList.size(); j++){
                    int currentCellTest = currentCellPossibleList.get(j);
                    if (!combinedPossibleList.contains(currentCellTest)){
                        //since the currentCellTest is unique to the possibleList of
                        //only this cell, we can assign it as a known value to the cell
                        cells[i].setValue(currentCellTest);
                        cells[i].setPossibles(new ArrayList<Integer>());
                        cells[i].setAssigned(true);
                        return true;
                    }
                }

                //We create a master list of all possibilities that occur within the group
                //We exclude the the current cell so that we can compare the current cell's
                //possibilities to the rest of the groups'
                combinedPossibleList = new ArrayList<Integer>();
                for (int j = 0; j < cellSquare.length; j++) {
                    if (i != b.squarePositionToCellNumber(b.getSquareNumberFromIndex(i),j)){
                        List<Integer> currentPossibleList = cellSquare[j].getPossibles();
                        for (int k = 0; k < currentPossibleList.size(); k++){
                            combinedPossibleList.add(currentPossibleList.get(k));
                        }
                    }
                }
                for (int j = 0; j < currentCellPossibleList.size(); j++){
                    int currentCellTest = currentCellPossibleList.get(j);
                    if (!combinedPossibleList.contains(currentCellTest)){
                        //since the currentCellTest is unique to the possibleList of
                        //only this cell, we can assign it as a known value to the cell
                        cells[i].setValue(currentCellTest);
                        cells[i].setPossibles(new ArrayList<Integer>());
                        cells[i].setAssigned(true);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // This method used to print the current state of the game board, as well
    // as the state of all the 'possibility lists'. It prints the number of cells
    // that are currently marked as solved
    public static void printGameBoard(Board b){
        Cell[] cells = b.getCells();
        int numberSolved = 0;
        for (int i = 0; i < cells.length; i++){
            if (i%9 == 0) System.out.println();
            System.out.print(" " + cells[i].getValue());
        }
        System.out.println();
        for (int i = 0; i < cells.length; i++){
            if (!cells[i].isAssigned()){
                System.out.print("\n" + i + ":");
                for (int j = 0; j < cells[i].getPossibles().size(); j++){
                    System.out.print(" " + cells[i].getPossibles().get(j));
                }
            } else {
                numberSolved++;
            }
        }
        System.out.print("\n\nNumber solved: " + numberSolved);
    }

    public static Board solveRecursively(Board b){
        Cell[] cells = b.getCells();

        int indexFirstNonAssigned = -1;
        for (int i = 0; i < cells.length; i++){
            if (!cells[i].isAssigned()){
                 indexFirstNonAssigned = i;
                break;
            }
        }

        Board newBoard = cloneBoard(b);
        if (b.getCells()[indexFirstNonAssigned].getPossibles().size() == 0){
            return solveRecursively(b.getParentBoard());
        }
        int valueToTry = b.getCells()[indexFirstNonAssigned].getPossibles().remove(0);
        newBoard.setParentBoard(b);
        Cell[] newBoardCells = newBoard.getCells();

        Cell newCell = newBoardCells[indexFirstNonAssigned];
        newCell.setValue(valueToTry);
        newCell.setAssigned(true);
        newCell.setPossibles(new ArrayList<Integer>());
        solveLogically(newBoard);

        if (isBoardSolved(newBoard)){
            return newBoard;
        } else if (isBoardValid(newBoard)) {
            return solveRecursively(newBoard);
        } else {
            return solveRecursively(newBoard.getParentBoard());
        }


        /*
        Cell oldCell = cells[indexFirstNonAssigned];
        Cell newCell = newBoardCells[indexFirstNonAssigned];
        for (int i = 0; i < oldCell.getPossibles().size(); i++){
            newCell.setValue(oldCell.getPossibles().get(i));
            newCell.setAssigned(true);
            newCell.setPossibles(new ArrayList<Integer>());
            solveLogically(newBoard);
            if (isBoardSolved(newBoard)){
                //it is complete, the board is solved
            } else if (isBoardValid(b)){
                //the board is valid and we can continue
            } else if (i == oldCell.getPossibles().size()) {
                //the board is not valid and we have exhausted
                //possible solutions, we must try the next element of
                //the parent possibleList
            } else {
                //the board is not valid, move to the next element of the possible list
            }
        }*/
    }

    //Helper method used for solving recursively
    //This method returns a new copy of a Board
    public static Board cloneBoard(Board b){
        return new Board(b);
    }

    //Helper method used for solving recursively
    //This method checks a board to see if the current solution's is valid
    //For example, ensures that two numbers do not appear in the same row twice
    public static boolean isBoardValid(Board b){
        for (int i = 0; i < 9; i++){
            if(!isCellArrayValid(b.getRow(i))) return false;
        }
        for (int i = 0; i < 9; i++){
            if(!isCellArrayValid(b.getColumn(i))) return false;
        }
        for (int i = 0; i < 9; i++){
            if(!isCellArrayValid(b.getSquare(i))) return false;
        }
        for (int i = 0; i < b.getCells().length; i++){
            if (!b.getCells()[i].isAssigned() && b.getCells()[i].getPossibles().size() == 0) return false;
        }
        return true;
    }
    public static boolean isCellArrayValid(Cell[] cells){
        int[] counter = new int[10];
        for (int i = 0; i < cells.length; i++){
            if (cells[i].isAssigned()){
                counter[cells[i].getValue()]++;
                if (counter[cells[i].getValue()] > 1) return false;
            }
        }
        return true;
    }

    //Returns true if a board is completely solved
    //False otherwise
    public static boolean isBoardSolved(Board b){
        Cell[] c = b.getCells();
        for (int i = 0; i < c.length; i++){
            if (!c[i].isAssigned()) return false;
        }
        return isBoardValid(b);
    }
}