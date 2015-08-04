package fourword_shared.messages;

import java.io.Serializable;

/**
 * Created by jonathan on 2015-06-26.
 */
public interface MsgListener<T extends MsgType> extends Serializable {
    boolean handleMessage(Msg<T> msg);
    void lostConnection();
    void establishedConnection();
}
