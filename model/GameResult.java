package fourword_shared.model;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by jonathan on 2015-06-24.
 */
public class GameResult implements Serializable{

    public static final long serialVersionUID = 1L;

    private final HashMap<String, GridModel> grids;
    private final HashSet<String> botNames;
    private final HashSet<String> lowerWords;
    private final List<Cell> bonusCells;

    public GameResult(HashMap<String, GridModel> grids, HashSet<String> botNames, HashSet<String> lowerWords, List<Cell> bonusCells){
        this.grids = grids;
        this.botNames = botNames;
        this.lowerWords = lowerWords;
        this.bonusCells = bonusCells;
    }

    public HashMap<String, GridModel> grids(){
        return grids;
    }

    public HashSet<String> lowerWords(){
        return lowerWords;
    }

    public List<Cell> bonusCells(){
        return bonusCells;
    }

    public String toString(){
        return lowerWords.toString();
    }
}
