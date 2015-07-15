package fourword_shared.model;

import java.io.Serializable;

/**
 * Created by jonathan on 2015-07-09.
 */
public class PlayerInfo implements Serializable {
    public static final long serialVersionUID = 1L;

    final public String name;
    final public boolean isInLobby;
    final public int numOthersInLobby;
    final public boolean isLobbyHost;
    final public boolean isInGame;
    final public int numOthersInGame;

    private PlayerInfo(String name, boolean isInLobby, int numOthersInLobby, boolean isLobbyHost, boolean isInGame, int numOthersInGame) {
        this.name = name;
        this.isInLobby = isInLobby;
        this.numOthersInLobby = numOthersInLobby;
        this.isLobbyHost = isLobbyHost;
        this.isInGame = isInGame;
        this.numOthersInGame = numOthersInGame;
    }

    public static PlayerInfo inMenu(String name){
        return new PlayerInfo(name, false, 0, false, false, 0);
    }

    public static PlayerInfo inLobby(String name, boolean isHost, int numOthersInLobby){
        return new PlayerInfo(name, true, numOthersInLobby, isHost, false, 0);
    }

    public static PlayerInfo inGame(String name, int numOthersInGame){
        return new PlayerInfo(name, false, 0, false, true, numOthersInGame);
    }

    public String infoString(){
        String infoText = "";
        if(isInGame){
            infoText += "in game with " + numOthersInGame + " other(s)";
        }else if(isInLobby){
            if(isLobbyHost){
                infoText += "hosting lobby for ";
            }else{
                infoText += "in lobby with ";
            }
            infoText += numOthersInLobby + " other(s)";
        }else{
            infoText += "in menu";
        }
        return infoText;
    }

    public String toString(){
        return infoString();
    }
}
