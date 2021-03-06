package fourword_shared.messages;

import fourword_shared.model.*;

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

        public static final long serialVersionUID = 1L;

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

        public static final long serialVersionUID = 1L;

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
        public final GameSettings settings;
        public final String[] sortedPlayerNames;
        public final boolean[] bots;
        public GameIsStarting(GameSettings settings, String[] sortedPlayerNames, boolean[] bots) {
            super(ServerMsg.GAME_IS_STARTING);
            this.settings = settings;
            this.sortedPlayerNames = sortedPlayerNames;
            this.bots = bots;
        }
    }

    public static class SetLetterAtCell extends Msg<ServerMsg>{
        public static final long serialVersionUID = 1L;
        public final Cell cell;
        public final char letter;

        public SetLetterAtCell(Cell cell, char letter) {
            super(ServerMsg.SET_LETTER_AT_CELL);
            this.cell = cell;
            this.letter = letter;
        }
    }

    public static class SetBonusCell extends Msg<ServerMsg>{
        public static final long serialVersionUID = 1L;
        public final Cell cell;

        public SetBonusCell(Cell cell) {
            super(ServerMsg.SET_BONUS_CELL);
            this.cell = cell;
        }
    }

    public static class LobbySetAttribute<T extends MsgType> extends Msg<T>{
        public static final long serialVersionUID = 1L;
        public final GameSettings.Attribute attribute;
        public final Serializable value;

        private LobbySetAttribute(T type, GameSettings.Attribute attribute, Serializable value) {
            super(type);
            this.attribute = attribute;
            this.value = value;
        }

        public static LobbySetAttribute<ServerMsg> serverMsg(GameSettings.Attribute attribute, Serializable value){
            return new LobbySetAttribute(ServerMsg.LOBBY_SET_ATTRIBUTE, attribute, value);
        }

        public static LobbySetAttribute<ClientMsg> clientMsg(GameSettings.Attribute attribute, Serializable value){
            return new LobbySetAttribute(ClientMsg.LOBBY_SET_ATTRIBUTE, attribute, value);
        }

        public String toString(){
            return "SetAttr(" + attribute + " := " + value + ")";
        }


    }

//    public static class LobbySetDim extends Msg<ClientMsg>{
//        public static final long serialVersionUID = 1L;
//        public int numCols;
//        public int numRows;
//        public LobbySetDim(int numCols, int numRows){
//            super(ClientMsg.LOBBY_SET_DIMENSIONS);
//            this.numCols = numCols;
//            this.numRows = numRows;
//        }
//        public String toString(){
//            return "MsgLobbySetDim(" + numCols + ", " + numRows + ")";
//        }
//    }

//    public static class LobbySetTimeLimit extends ObjectMsg<Integer, ClientMsg>{
//        public static final long serialVersionUID = 1L;
//        public LobbySetTimeLimit(int secsPerTurn){
//            super(ClientMsg.LOBBY_SET_TIME_LIMIT, secsPerTurn);
//        }
//    }

    public static class LobbyState extends ObjectMsg<Lobby, ServerMsg>{
        public static final long serialVersionUID = 1L;
        public LobbyState(Lobby lobby) {
            super(ServerMsg.LOBBY_STATE, lobby);
        }
    }

    public static class LobbyStateChange extends Msg<ServerMsg>{
        public static final long serialVersionUID = 1L;

        public LobbyStateChange(Action action, String playerName) {
            super(ServerMsg.LOBBY_STATE_CHANGE);
            this.action = action;
            this.playerName = playerName;
        }

        public enum Action{
            INVITED, ACCEPTED_INVITE, BOT_ADDED, LEFT, NEW_HOST;
        }
        public final Action action;
        public final String playerName;

        public String toString(){
            return "LobbyChange(" + playerName + " " + action + ")";
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
        final public GameSettings gameSettings;
        final public int numBots;

        public QuickStartGame(GameSettings gameSettings, int numBots) {
            super(ClientMsg.QUICK_START_GAME);
            this.gameSettings = gameSettings;
            this.numBots = numBots;
        }
    }

    public static class RequestOnlinePlayersInfo extends Msg<ClientMsg>{
        public static final long serialVersionUID = 1L;

        public RequestOnlinePlayersInfo(){
            super(ClientMsg.REQUEST_ONLINE_PLAYERS_INFO);
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



    public static class RequestPickAndPlaceLetter extends Msg<ServerMsg>{
        public static final long serialVersionUID = 1L;

        public RequestPickAndPlaceLetter() {
            super(ServerMsg.DO_PICK_AND_PLACE_LETTER);
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

    public static class YouWereKicked extends Msg<ServerMsg>{
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

    public static class LeaveGame extends Msg<ClientMsg>{
        public static final long serialVersionUID = 1L;
        public LeaveGame(){
            super(ClientMsg.LEAVE_GAME);
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

    public static class PlayerInfoUpdate extends ObjectMsg<PlayerInfo, ServerMsg>{
        public static final long serialVersionUID = 1L;
        public PlayerInfoUpdate(PlayerInfo playerInfo){
            super(ServerMsg.PLAYER_INFO_UPDATE, playerInfo);
        }
    }

    public static class PlayerLoggedOut extends ObjectMsg<String, ServerMsg>{
        public static final long serialVersionUID = 1L;
        public PlayerLoggedOut(String playerName){
            super(ServerMsg.PLAYER_LOGGED_OUT, playerName);
        }
    }

    public static class OnlinePlayersInfo extends ListMsg<PlayerInfo, ServerMsg>{
        public static final long serialVersionUID = 1L;
        public OnlinePlayersInfo(List<PlayerInfo> onlinePlayers) {
            super(ServerMsg.ONLINE_PLAYERS_INFO, onlinePlayers);
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

    public static class GameEnded extends Msg<ServerMsg> {
        public static final long serialVersionUID = 1L;
        public final String leaverName;
        public GameEnded(String leaverName) {
            super(ServerMsg.GAME_ENDED);
            this.leaverName = leaverName;

        }
    }

    public static class GameCrashed extends Msg<ServerMsg>{
        public static final long serialVersionUID = 1L;
        public GameCrashed(){
            super(ServerMsg.GAME_CRASHED);
        }
    }
}
