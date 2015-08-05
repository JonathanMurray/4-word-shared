package fourword_shared.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by jonathan on 2015-06-22.
 */
public class GridModel implements Serializable {

    public static final long serialVersionUID = 1L;
    private static final char NULL_CHAR = '\u0000';

    private char[][] charCells;
    private int numCols;
    private int numRows;

    public GridModel(int numCols, int numRows){
        charCells = new char[numCols][numRows];
        this.numCols = numCols;
        this.numRows = numRows;
    }

    public Cell getRandomFreeCell(){
        List<Cell> freeCells = new ArrayList<Cell>();
        for(int x = 0; x < numCols; x ++){
            for(int y = 0; y < numRows; y ++){
                if(charCells[x][y] == NULL_CHAR){
                    freeCells.add(new Cell(x, y));
                }
            }
        }
        return freeCells.get(new Random().nextInt(freeCells.size()));
    }

    public void setCharAtCell(char ch, Cell cell){
        assertIsLetter(ch);
        charCells[cell.x()][cell.y()] = ch;
    }

    private boolean isLetter(char letter){
        return (letter >= 'A' && letter <= 'Z') || letter == 'Å' || letter == 'Ä' || letter == 'Ö';
    }

    private void assertIsLetter(char letter){
        if(!isLetter(letter)){
            throw new IllegalArgumentException("invalid leter: '" + letter + "'  (" + (int)letter + ")");
        }
    }

    public char getCharAtCell(Cell cell){
        return charCells[cell.x()][cell.y()];
    }

    public char getCharAtCell(int x, int y){
        return charCells[x][y];
    }

    public boolean hasCharAtCell(Cell cell){
        return getCharAtCell(cell) != NULL_CHAR;
    }

    public void removeCharAtCell(Cell cell){
        charCells[cell.x()][cell.y()] = NULL_CHAR;
    }

    private String getCol(int colIndex){
        StringBuilder s = new StringBuilder();
        for(int y = 0; y < numRows; y++){
            s.append(getCharAtCell(new Cell(colIndex, y)));
        }
        return s.toString();
    }

    private String getRow(int rowIndex){
        StringBuilder s = new StringBuilder();
        for(int x = 0; x < numCols; x++){
            s.append(getCharAtCell(new Cell(x, rowIndex)));
        }
        return s.toString();
    }

    public List<String> getCols(){
        List<String> cols = new ArrayList<String>();
        for(int x = 0; x < numCols; x++){
            cols.add(getCol(x));
        }
        return cols;
    }

    public List<String> getRows(){
        List<String> rows = new ArrayList<String>();
        for(int y = 0; y < numRows; y++){
            rows.add(getRow(y));
        }
        return rows;
    }

    public int getNumCols(){
        return numCols;
    }

    public int getNumRows(){
        return numRows;
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("\n ----------\n");
        for(int y = 0; y < numRows; y++){
            s.append("| ");
            for(int x = 0; x < numCols; x++){
                char cell = charCells[x][y];
                s.append(cell != 0 ? cell : "*");
                s.append(" ");
            }
            s.append("|\n");
        }
        s.append(" ----------");
        return s.toString();
    }

    public String shortString(){
        return numCols + "x" + numRows;
    }
}
