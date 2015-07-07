package fourword_shared.model;

import java.io.Serializable;

/**
 * Created by jonathan on 2015-06-22.
 */
public class Cell implements Serializable {

    public static final long serialVersionUID = 1L;

    private int x;
    private int y;
    public Cell(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int x(){
        return x;
    }

    public int y(){
        return y;
    }

    @Override
    public boolean equals(Object o){
        return ((Cell)o).x == x && ((Cell)o).y == y;
    }

    @Override
    public int hashCode(){
        return ("" + x + y).hashCode();
    }

    public String toString(){
        return "[" + x + ", " + y + "]";
    }
}
