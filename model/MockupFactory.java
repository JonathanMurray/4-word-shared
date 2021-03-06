package fourword_shared.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Created by jonathan on 2015-06-29.
 */
public class MockupFactory {

    public static GameResult createResult(){
        HashMap<String,GridModel> grids = new HashMap();
        HashSet<String> botNames = new HashSet<String>();
        grids.put("Johny", createGrid(6,6));
        botNames.add("Johny");
        grids.put("Sandy", createGrid(6,6));
        HashSet<String> words = createWords();
        List<Cell> bonusCells = new ArrayList<Cell>();
        bonusCells.add(new Cell(0,0));

        return new GameResult( grids, botNames, words, bonusCells);
    }

    public static HashSet<String> createWords(){
        HashSet<String> words = new HashSet<String>();
        words.add("band");
        words.add("and");
        words.add("an");
        words.add("anse");
        words.add("du");
        words.add("dum");
        words.add("bal");
        words.add("al");
        words.add("de");
        words.add("dem");
        words.add("sa");
        words.add("sand");
        return words;
    }

    public static GridModel createGrid(int numCols, int numRows){
        GridModel grid = new GridModel(numCols, numRows);
        for(int x = 0; x < numCols; x++){
            for(int y =0; y < numRows; y++){
                grid.setCharAtCell(Util.randomLetter(), new Cell(x,y));
            }
        }
        return grid;
    }

    public static GridModel createGrid1(){
        return createGrid(new char[][]{
                new char[]{'B', 'A', 'L'},
                new char[]{'A', 'N', 'D'},
                new char[]{'N', 'S', 'U'},
                new char[]{'D', 'E', 'M'},
                new char[]{'D', 'E', 'M'}});
    }

    public static GridModel createGrid2(){
        return createGrid(new char[][]{
                new char[]{'S', 'A', 'L'},
                new char[]{'A', 'N', 'D'},
                new char[]{'N', 'S', 'U'},
                new char[]{'D', 'E', 'M'},
                new char[]{'D', 'E', 'M'}});
    }

    public static GridModel createGrid(char[][] letters){
        GridModel grid = new GridModel(letters.length, letters[0].length);
        for(int x = 0; x < letters.length; x ++){
            for(int y = 0; y < letters[0].length; y++){
                grid.setCharAtCell(letters[x][y], new Cell(x,y));
            }
        }
        return grid;
    }

}
