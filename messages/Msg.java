package fourword_shared.messages;

import fourword_shared.model.Cell;
import fourword_shared.model.GameResult;
import fourword_shared.model.Lobby;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jonathan on 2015-06-23.
 */
public abstract class Msg<T extends MsgType> implements Serializable{

    public static final long serialVersionUID = 1L;

    private int id;
    private final T type;

    public Msg(T type){
        this.type = type;
    }

    public void setId(int id){
        this.id = id;
    }

    public T type(){
        return type;
    }

    public int id(){
        return id;
    }

    public String toString(){
        return type.toString();
    }

    private static class ObjectMsg<Clazz, Type extends MsgType> extends Msg<Type>{

        private Clazz o;

        public ObjectMsg(Type type, Clazz o) {
            super(type);
            this.o = o;
        }

        public Clazz get(){
            return o;
        }

        public String toString(){
            return type() + "(" + o + ")";
        }
    }

    private static class ListMsg<Clazz, Type extends MsgType> extends Msg<Type>{
        private List<Clazz> list;

        public ListMsg(Type type, List<Clazz> list){
            super(type);
            this.list = list;
        }

        public List<Clazz> get(){
            return list;
        }

        public String toString(){
            return type() + list.toString();
        }
    }

    public static class GameFinished extends ObjectMsg<GameResult, ServerMsg>{
        public static final long serialVersionUID = 1L;
        public GameFinished( GameResult result) {
            super(ServerMsg.GAME_FINISHED, result);
        }
    }

    public static class GameIsStarting extends Msg<ServerMsg>{
        public static final long serialVersionUID = 1L;
        public int numCols;
        public int numRows;
        public String[] sortedPlayerNames;
        public GameIsStarting(int numCols, int numRows, String[] sortedPlayerNames) {
            super(ServerMsg.GAME_IS_STARTING);
            this.numCols = numCols;
            this.numRows = numRows;
            this.sortedPlayerNames = sortedPlayerNames;
        }
    }

    public static class LobbySetDim extends Msg<ClientMsg>{
        public static final long serialVersionUID = 1L;
        public int numCols;
        public int numRows;
        public LobbySetDim(int numCols, int numRows){
            super(ClientMsg.LOBBY_SET_DIMENSIONS);
            this.numCols = numCols;
            this.numRows = numRows;
        }
        public String toString(){
            return "MsgLobbySetDim(" + numCols + ", " + numRows + ")";
        }
    }

    public static class LobbyState extends ObjectMsg<Lobby, ServerMsg>{
        public static final long serialVersionUID = 1L;
        public LobbyState(Lobby lobby) {
            super(ServerMsg.LOBBY_STATE, lobby);
        }
    }

    public static class PickAndPlaceLetter extends Msg<ClientMsg>{
        public static final long serialVersionUID = 1L;
        public final char letter;
        public final Cell cell;
        public PickAndPlaceLetter(char letter, Cell cell) {
            super(ClientMsg.PICK_AND_PLACE_LETTER);
            if(letter == 0 || cell == null){
                throw new IllegalArgumentException("" + letter + " , " + cell);
            }
            this.letter = letter;
            this.cell = cell;
        }
        public String toString(){
            return "MsgPickAndPlace{" + letter + ", " + cell + "}";
        }
    }

    public static class PlaceLetter extends ObjectMsg<Cell, ClientMsg>{
        public static final long serialVersionUID = 1L;
        public PlaceLetter(Cell cell){
            super(ClientMsg.PLACE_LETTER, cell);
        }
    }

    public static class QuickStartGame extends Msg<ClientMsg>{
        public static final long serialVersionUID = 1L;

        final public int numCols;
        final public int numRows;
        final public int numBots;

        public QuickStartGame(int numCols, int numRows, int numBots) {
            super(ClientMsg.QUICK_START_GAME);
            this.numCols = numCols;
            this.numRows = numRows;
            this.numBots = numBots;
        }
    }

    public static class RequestPlaceLetter extends Msg<ServerMsg>{
        public static final long serialVersionUID = 1L;

        public char letter;
        public String playerName;

        public RequestPlaceLetter(char letter, String playerName) {
            super(ServerMsg.DO_PLACE_LETTER);
            this.letter = letter;
            this.playerName = playerName;
        }
    }

    public static class Ok extends Msg<ServerMsg>{
        public static final long serialVersionUID = 1L;
        public Ok() {
            super(ServerMsg.OK);
        }
    }

    public static class No extends ObjectMsg<String, ServerMsg>{
        public static final long serialVersionUID = 1L;
        public No(String text) {
            super(ServerMsg.NO, text);
        }
    }

    public static class YouAreInvited extends ObjectMsg<String, ServerMsg>{
        public static final long serialVersionUID = 1L;
        public YouAreInvited(String inviterName) {
            super(ServerMsg.YOU_ARE_INVITED, inviterName);
        }
    }

    public static class YouWereKicked extends Msg{
        public static final long serialVersionUID = 1L;
        public YouWereKicked(){
            super(ServerMsg.YOU_WERE_KICKED);
        }
    }

    public static class LogIn extends ObjectMsg<String, ClientMsg>{
        public static final long serialVersionUID = 1L;
        public LogIn(String loginName){
            super(ClientMsg.LOGIN, loginName);
        }
    }

    public static class KickFromLobby extends ObjectMsg<String, ClientMsg>{
        public static final long serialVersionUID = 1L;
        public KickFromLobby(String kickedName){
            super(ClientMsg.KICK_FROM_LOBBY, kickedName);
        }
    }

    public static class ConfirmGameStarting extends ObjectMsg<String, ClientMsg>{
        public static final long serialVersionUID = 1L;
        public ConfirmGameStarting(String hostName){
            super(ClientMsg.CONFIRM_GAME_STARTING, hostName);
        }
    }

    public static class LeaveLobby extends Msg<ClientMsg>{
        public static final long serialVersionUID = 1L;
        public LeaveLobby(){
            super(ClientMsg.LEAVE_LOBBY);
        }
    }

    public static class InviteToLobby extends ObjectMsg<String, ClientMsg>{
        public static final long serialVersionUID = 1L;
        public InviteToLobby(String invitedName){
            super(ClientMsg.INVITE_TO_LOBBY, invitedName);
        }
    }

    public static class AddBot extends Msg<ClientMsg>{
        public static final long serialVersionUID = 1L;
        public AddBot(){
            super(ClientMsg.ADD_BOT_TO_LOBBY);
        }
    }

    public static class StartGameFromlobby extends Msg<ClientMsg>{
        public static final long serialVersionUID = 1L;
        public StartGameFromlobby(){
            super(ClientMsg.START_GAME_FROM_LOBBY);
        }
    }

    public static class LogOut extends Msg<ClientMsg>{
        public static final long serialVersionUID = 1L;
        public LogOut(){
            super(ClientMsg.LOGOUT);
        }
    }

    public static class CreateLobby extends Msg<ClientMsg> {
        public static final long serialVersionUID = 1L;

        public CreateLobby() {
            super(ClientMsg.CREATE_LOBBY);
        }
    }

    public static class PlayerDoneThinking extends ObjectMsg<String, ServerMsg>{
        public static final long serialVersionUID = 1L;
        public PlayerDoneThinking(String playerName) {
            super(ServerMsg.GAME_PLAYER_DONE_THINKING, playerName);
        }
    }

    public static class PlayersTurn extends ObjectMsg<String, ServerMsg>{
        public static final long serialVersionUID = 1L;
        public PlayersTurn(String playerName) {
            super(ServerMsg.GAME_PLAYERS_TURN, playerName);
        }
    }

    public static class OnlinePlayers extends ListMsg<String, ServerMsg>{
        public static final long serialVersionUID = 1L;
        public OnlinePlayers(List<String> onlinePlayers) {
            super(ServerMsg.ONLINE_PLAYERS, onlinePlayers);
        }
    }

    public static class AcceptInvite extends Msg<ClientMsg>{
        public static final long serialVersionUID = 1L;
        public AcceptInvite(){
            super(ClientMsg.ACCEPT_INVITE);
        }
    }

    public static class DeclineInvite extends Msg<ClientMsg>{
        public static final long serialVersionUID = 1L;
        public DeclineInvite(){
            super(ClientMsg.DECLINE_INVITE);
        }
    }
}
