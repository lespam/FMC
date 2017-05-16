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
public class Automaton {
    List<State> states = new ArrayList<State>();

    public Automaton() {
        
    }
    
        public void addState(State state){
        states.add(state);
    }

    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }

   
    
    
    
}
