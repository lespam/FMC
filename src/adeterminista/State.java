/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adeterminista;

/**
 *
 * @author LesPam
 */
public class State {
    private char transition0;
    private int type0;
    private  char transition1;
    private int type1;
    private int tag1;
    private int tag2;
    private String name;
    private static int idCounter=65;

    public State(char transition0, int type0, char transition1, int type1) {
        this.transition0 = transition0;
        this.type0 = type0;
        this.transition1 = transition1;
        this.type1 = type1;
        this.name = String.valueOf((char)idCounter);
        idCounter++;
    }

    public char getTransition0() {
        return transition0;
    }

    public int getType0() {
        return type0;
    }

    public char getTransition1() {
        return transition1;
    }

    public int getType1() {
        return type1;
    }

    public void setTransition0(char transition0) {
        this.transition0 = transition0;
    }

    public void setType0(int type0) {
        this.type0 = type0;
    }

    public void setTransition1(char transition1) {
        this.transition1 = transition1;
    }

    public void setType1(int type1) {
        this.type1 = type1;
    }

    public int getTag1() {
        return tag1;
    }

    public int getTag2() {
        return tag2;
    }

    public void setTag1(int tag1) {
        this.tag1 = tag1;
    }

    public void setTag2(int tag2) {
        this.tag2 = tag2;
    }

    public String getName() {
        return name;
    }
    
    
    
    

    @Override
    public String toString() {
        return "State{" + "transition0=" + transition0 + ", type0=" + type0 + ", transition1=" + transition1 + ", type1=" + type1 + '}';
    }
    
    
    
}
