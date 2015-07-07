package fourword_shared.messages;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonathan on 2015-06-29.
 */
public class MsgStringList extends Msg {

    public static final long serialVersionUID = 1L;

    public List<String> list;

    public MsgStringList(MsgType type, List<String> list) {
        super(type);
        this.list = new ArrayList(list);
    }

    public String toString(){
        return type() + "" + list;
    }
}
