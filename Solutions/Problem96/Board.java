public class Board {

    private Cell[] cells;
    private Board parentBoard;

    public Board(String s){
        this.parentBoard = null;
        cells = new Cell[81];
        for (int i = 0; i < s.length(); i++){
            Cell c = new Cell(Character.getNumericValue(s.charAt(i)));
            cells[i] = c;
        }
    }

    //This constructor used to clone a Board
    public Board (Board b){
        this.cells = new Cell[81];
        for (int i = 0; i < b.getCells().length; i++){
            this.cells[i] = new Cell(b.getCells()[i]);
        }
    }

    public Board getParentBoard() {
        return parentBoard;
    }

    public void setParentBoard(Board parentBoard) {
        this.parentBoard = parentBoard;
    }

    public Cell[] getCells() {
        return cells;
    }

    public Cell[] getColumn(int a){
        Cell[] cellArray = new Cell[9];
        int current = a;
        for (int i = 0; i < 9; i++){
            cellArray[i] = cells[current];
            current += 9;
        }
        return cellArray;
    }

    public Cell[] getRow(int a){
        Cell[] cellArray = new Cell[9];
        int current = a*9;
        for (int i = 0; i < 9; i++){
            cellArray[i] = cells[current];
            current++;
        }
        return cellArray;
    }

    public Cell[] getRowByCellIndex(int a){
        return getRow(a/9);
    }

    public Cell[] getColumnByCellIndex(int a){
        return getColumn(a % 9);
    }

    public Cell[] getSquareByCellIndex(int a){
        return getSquare(getSquareNumberFromIndex(a));
    }

    public Cell[] getSquare(int a){
        Cell[] cellArray = new Cell[9];
        int counter = 0;
        int current = 0;
        if (a == 0) current = 0;
        if (a == 1) current = 3;
        if (a == 2) current = 6;
        if (a == 3) current = 27;
        if (a == 4) current = 30;
        if (a == 5) current = 33;
        if (a == 6) current = 54;
        if (a == 7) current = 57;
        if (a == 8) current = 60;

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                cellArray[counter] = cells[current];
                counter++;
                if (j != 2) current++;
            }
            current += 7;
        }
        return cellArray;
    }

    public int getSquareNumberFromIndex(int a){
        int row = a/9;
        int column = a%9;

        int squareRow = row/3;
        int squareColumn = column/3;
        return (3 * squareRow + squareColumn);
    }

    //converts the square number and position within the
    //square to the cell index on the entire gameboard
    public int squarePositionToCellNumber(int square, int posnWithinSquare){
        int starting = 0;
        if (square == 0){

        } else if (square == 1){
            starting = 3;
        } else if (square == 2){
            starting = 6;
        } else if (square == 3){
            starting = 27;
        } else if (square == 4){
            starting = 30;
        } else if (square == 5){
            starting = 33;
        } else if (square == 6){
            starting = 54;
        } else if (square == 7){
            starting = 57;
        } else if (square == 8){
            starting = 60;
        }

        int position = starting;
        int column = posnWithinSquare%3;
        int row = posnWithinSquare/3;
        position = position + column + row*9;

        return position;
    }
}
