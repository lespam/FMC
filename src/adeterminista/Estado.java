/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adeterminista;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author LesPam
 */
public class Estado {
    private List<Estado> transicion0;
    private List<Estado> transicion1;
    private int tipo;
    private char nombre;
    private static int idCounter=65;

    public Estado(int tipo,int letra) {
        this.tipo = tipo;
        this.nombre = (char)letra;
        this.transicion0 = new ArrayList<Estado>();
        this.transicion1 = new ArrayList<Estado>();
    }
    public Estado() {
        this.tipo = 0;
        this.transicion0 = new ArrayList<Estado>();
        this.transicion1 = new ArrayList<Estado>();
        this.nombre = ((char)idCounter);
        idCounter++;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public char getNombre() {
        return nombre;
    }

    public Integer getNombreEntero() {
        return Integer.valueOf(nombre);
    }

    public void setTransicion0(Estado estado) {
        this.transicion0.add(estado);
    }
    public Iterator<Estado> getTransiciones0()
    {
        return this.transicion0.iterator();
    }
    
    public void setTransicion1(Estado estado) {
        this.transicion0.add(estado);
    }
    public Iterator<Estado> getTransiciones1()
    {
        return this.transicion1.iterator();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {   return false;  }
        if(Integer.valueOf(this.nombre) == Integer.valueOf(((Estado)obj).nombre))
            return true;
        else 
            return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.nombre);
        return hash;
    }
    
    @Override
    public String toString() {
        return "Estado " +nombre+ " {Transiciones0= " + transicion0.size() + ", Transiciones1= " + transicion1.size()+ ", Tipo=" + tipo;
    }
    
    
    
}
