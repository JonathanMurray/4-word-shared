package fourword_shared.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jonathan on 2015-07-19.
 */
public class GameSettings implements Cloneable, Serializable{
    public static final long serialVersionUID = 1L;

    public static class Entry<T>{
        public final Attribute attribute;
        public final T value;

        public Entry(Attribute attribute, T value) {
            this.attribute = attribute;
            this.value = value;
        }
    }

    public Collection<Entry> getAllEntries(){
        List<Entry> entries = new ArrayList<Entry>();
        for(IntAttribute intAttribute : IntAttribute.values()){
            entries.add(new Entry<Integer>(intAttribute, intAttributes.get(intAttribute)));
        }
        return entries;
    }

    public GameSettings(){
        setupAttributes();
    }

    public interface Attribute{}

    public enum IntAttribute implements Attribute{
        ROWS, COLS, TIME_PER_TURN;
    }

    public enum BoolAttribute implements Attribute{
        CUSTOM_RULES, PREPLACED_RANDOM;
    }

    private HashMap<IntAttribute, Integer> intAttributes = new HashMap<IntAttribute, Integer>();
    private HashMap<BoolAttribute, Boolean> boolAttributes = new HashMap<BoolAttribute, Boolean>();

    private void setupAttributes(){
        intAttributes.put(IntAttribute.COLS, 4);
        intAttributes.put(IntAttribute.ROWS, 4);
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

    public GameSettings copy(){
        try {
            return (GameSettings) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public String toString(){
        return "" +  intAttributes + boolAttributes;
    }
}
