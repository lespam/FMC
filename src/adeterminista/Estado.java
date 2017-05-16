/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adeterminista;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LesPam
 */
public class Estado {
    private List<Estado> transicion0;
    private List<Estado> transicion1;
    private int tipo;
    private String nombre;
    private static int idCounter=65;

    public Estado(int tipo,int letra) {
        this.tipo = tipo;
        this.nombre = String.valueOf((char)letra);
        this.transicion0 = new ArrayList<Estado>();
        this.transicion1 = new ArrayList<Estado>();
    }
    public Estado() {
        this.tipo = 0;
        this.transicion0 = new ArrayList<Estado>();
        this.transicion1 = new ArrayList<Estado>();
        this.nombre = String.valueOf((char)idCounter);
        idCounter++;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }


    public void setTransicion0(Estado estado) {
        this.transicion0.add(estado);
    }
    public String getTransiciones0()
    {
        return this.transicion0.toString();
    }
    
    public void setTransicion1(Estado estado) {
        this.transicion0.add(estado);
    }
    public String getTransiciones1()
    {
        return this.transicion0.toString();
    }
    public boolean equals(Estado obj) {
        if (obj == null) {   return false;  }
        if (!this.nombre.equals(obj.nombre)) {  return false;    }
        return true;
    }
    
    
    

    @Override
    public String toString() {
        return "Estado " +nombre+ " {Transiciones0= " + transicion0.size() + ", Transiciones1= " + transicion1.size()+ ", Tipo=" + tipo;
    }
    
    
    
}
