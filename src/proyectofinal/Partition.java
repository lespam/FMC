/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinal;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class Partition {
    private List<State> states = new ArrayList();
    private List<String> names = new ArrayList();
    private int tag;
    private int tag1;
    private int tag2;

    public Partition() {
    }
    
    
    public Partition addState(State state){
        states.add(state);
        names.add(state.getName());
        return this;
    }

    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }


    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
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
    
    
}
