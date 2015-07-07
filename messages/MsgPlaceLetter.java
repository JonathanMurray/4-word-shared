package fourword_shared.messages;

import fourword_shared.model.Cell;

/**
 * Created by jonathan on 2015-06-27.
 */
public class MsgPlaceLetter extends Msg<ClientMsg> {

    public static final long serialVersionUID = 1L;

    public final Cell cell;

    public MsgPlaceLetter(Cell cell) {
        super(ClientMsg.PLACE_LETTER);
        this.cell = cell;
    }

    public String toString(){
        return "MsgPlace(" + cell + ")";
    }
}
