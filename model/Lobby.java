package fourword_shared.model;


import io.netty.util.Attribute;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jonathan on 2015-06-27.
 */
public class Lobby implements Serializable{

//    public static final AttributeName<Integer> ROWS = new AttributeName();
//    public static final AttributeName<Integer> COLS = new AttributeName();
//    public static final AttributeName<Integer> TIME_PER_TURN = new AttributeName();
//    public static final AttributeName<Boolean> CUSTOM_RULES = new AttributeName();
//    public static final AttributeName<Boolean> PREPLACED_RANDOM = new AttributeName();

    public interface Attribute{}

    public enum IntAttribute implements Attribute{
        ROWS, COLS, TIME_PER_TURN;
    }

    public enum BoolAttribute implements Attribute{
        CUSTOM_RULES, PREPLACED_RANDOM;
    }

    private HashMap<IntAttribute, Integer> intAttributes = new HashMap<IntAttribute, Integer>();
    private HashMap<BoolAttribute, Boolean> boolAttributes = new HashMap<BoolAttribute, Boolean>();


    public static final long serialVersionUID = 1L;

    private String hostName;
    private HashMap<String, LobbyPlayer> players = new HashMap();
    private ArrayList<String> sortedNames = new ArrayList();
//    public int numCols = 4;
//    public int numRows = 4;
//    public int timeLimit = 0;
//    public boolean useCustomRules = false;
//    public boolean preplacedRandomLetters = false;
//    private HashMap<AttributeName, Attribute> attributes;



    public Lobby( String hostName){
        this.hostName = hostName;
        players.put(hostName, LobbyPlayer.connectedHuman(hostName));
        sortedNames.add(hostName);
        setupAttributes();
    }

    private void setupAttributes(){
        intAttributes.put(IntAttribute.COLS, 0);
        intAttributes.put(IntAttribute.ROWS, 0);
        intAttributes.put(IntAttribute.TIME_PER_TURN, 0);
        boolAttributes.put(BoolAttribute.CUSTOM_RULES, false);
        boolAttributes.put(BoolAttribute.PREPLACED_RANDOM, false);
    }

    public <T> void setAttribute(Attribute attribute, T value){
        if(attribute instanceof IntAttribute){
            intAttributes.put((IntAttribute)attribute, (Integer)value);
        }else if(attribute instanceof BoolAttribute){
            boolAttributes.put((BoolAttribute)attribute, (Boolean)value);
        }else{
            throw new IllegalArgumentException("attr: " + attribute + ", value: " + value);
        }
    }

    public int getInt(IntAttribute attribute){
        return intAttributes.get(attribute);
    }

    public void setInt(IntAttribute attribute, int value){
        intAttributes.put(attribute, value);
    }

    public boolean getBool(BoolAttribute attribute){
        return boolAttributes.get(attribute);
    }

    public void setBool(BoolAttribute attribute, boolean value){
        boolAttributes.put(attribute, value);
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

    public Lobby getCopy(){
        HashMap<String, LobbyPlayer> playersCopy = new HashMap();
        for(String k : players.keySet()){
            playersCopy.put(k, players.get(k).getCopy());
        }
        ArrayList<String> sortedNamesCopy = new ArrayList(sortedNames);
        Lobby copy = new Lobby( hostName);
        copy.sortedNames = sortedNamesCopy;
        copy.players = playersCopy;
        copy.intAttributes = intAttributes;
        copy.boolAttributes = boolAttributes;
//        copy.numCols = numCols;
//        copy.numRows = numRows;
//        copy.timeLimit = timeLimit;
        return copy;
    }

    public String toString(){
        return "Lobby({" + intAttributes + ", " + boolAttributes + "}, " + hostName + ", " + players + ", " + sortedNames + ")";
    }
}
