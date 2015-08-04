package fourword_shared.model;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jonathan on 2015-06-27.
 */
public class Lobby implements Serializable{

    public static final long serialVersionUID = 1L;

    public static final int MAX_PLAYERS = 4;

    private String hostName;
    private HashMap<String, LobbyPlayer> players = new HashMap();
    private ArrayList<String> sortedNames = new ArrayList();
    private GameSettings gameSettings = new GameSettings();

    public Lobby( String hostName){
        this.hostName = hostName;
        players.put(hostName, LobbyPlayer.connectedHuman(hostName));
        sortedNames.add(hostName);
    }

    public GameSettings getSettings(){
        return gameSettings;
    }

    public void addPlayer(LobbyPlayer player){
        players.put(player.name, player);
        sortedNames.add(player.name);
    }

    public void removePlayer(String name){
        if(players.containsKey(name)){
            players.remove(name);
            sortedNames.remove(name);
        }else{
            throw new IllegalArgumentException(sortedNames + " doesn't contain '" + name + "'");
        }
    }

    public ArrayList<LobbyPlayer> getAllBots(){
        ArrayList<LobbyPlayer> bots = new ArrayList();
        for(LobbyPlayer player : players.values()){
            if(!player.isHuman){
                bots.add(player);
            }
        }
        return bots;
    }

    public ArrayList<LobbyPlayer> getAllHumans(){
        ArrayList<LobbyPlayer> humans = new ArrayList();
        for(LobbyPlayer player : players.values()){
            if(player.isHuman){
                humans.add(player);
            }
        }
        return humans;
    }

    public boolean containsPlayer(String name){
        return sortedNames.contains(name);
    }

    public void setNewHost( String hostName){
        if(!sortedNames.contains(hostName)){
            throw new IllegalArgumentException("host " + hostName + " is not in lobby: " + sortedNames);
        }
        this.hostName = hostName;
    }

    public String getHost(){
        return hostName;
    }

    public int size(){
        return sortedNames.size();
    }

    public void setConnected(String playerName){
        players.get(playerName).hasConnected = true;
    }

    public boolean isConnected(String playerName){
        return players.get(playerName).hasConnected;
    }

    public boolean isHuman(String playerName){
        return players.get(playerName).isHuman;
    }

    public LobbyPlayer getPlayer(String name){
        return players.get(name);
    }

    public String getNameAtIndex(int index){
        return sortedNames.get(index);
    }

    public List<String> sortedNames(){
        return sortedNames;
    }

    public boolean[] bots(){
        boolean[] bots = new boolean[players.size()];
        for(int i = 0; i < players.size(); i++){
            String name = sortedNames.get(i);
            boolean isBot = !players.get(name).isHuman;
            bots[i] = isBot;
        }
        return bots;
    }

    public Lobby getCopy(){
        HashMap<String, LobbyPlayer> playersCopy = new HashMap();
        for(String k : players.keySet()){
            playersCopy.put(k, players.get(k).getCopy());
        }
        ArrayList<String> sortedNamesCopy = new ArrayList(sortedNames);
        Lobby copy = new Lobby( hostName);
        copy.sortedNames = sortedNamesCopy;
        copy.players = playersCopy;
        copy.gameSettings = gameSettings.clone();
//        copy.numCols = numCols;
//        copy.numRows = numRows;
//        copy.timeLimit = timeLimit;
        return copy;
    }

    public String toString(){
        return "Lobby({" + gameSettings + "}, " + hostName + ", " + players + ", " + sortedNames + ")";
    }
}
