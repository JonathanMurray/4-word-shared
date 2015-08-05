package fourword_shared.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by jonathan on 2015-06-24.
 */
public class GameResult implements Serializable{

    public static final long serialVersionUID = 1L;

    private final HashMap<String, GridModel> grids;
    private final HashSet<String> botNames;
    private final HashSet<String> lowerWords;

    public GameResult(HashMap<String, GridModel> grids, HashSet<String> botNames, HashSet<String> lowerWords){
        this.grids = grids;
        this.botNames = botNames;
        this.lowerWords = lowerWords;
    }

    public HashMap<String, GridModel> grids(){
        return grids;
    }

    public HashSet<String> lowerWords(){
        return lowerWords;
    }

    public boolean isBot(String playerName){
        return botNames.contains(playerName);
    }

    public String toString(){
        return lowerWords.toString();
    }
}
