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
    private List<Character> nombre;
    private static int idCounter=65;

    public Estado(int tipo,int letra) {
        this.tipo = tipo;
        this.nombre.add((char)letra);
        this.transicion0 = new ArrayList<Estado>();
        this.transicion1 = new ArrayList<Estado>();
    }
    public Estado() {
        this.tipo = 0;
        this.transicion0 = new ArrayList<Estado>();
        this.transicion1 = new ArrayList<Estado>();
        this.nombre.add((char)idCounter);
        idCounter++;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
    public void addNombre(char nombre) {
        this.nombre.add(nombre);
    }

    public List<Character> getNombre() {
        return nombre;
    }

    public Integer getNombreEntero() {
        return Integer.valueOf(nombre.get(0));
    }

    public void setTransicion0(Estado estado) {
        this.transicion0.add(estado);
    }
    public Iterator<Estado> getTransiciones0()
    {
        return this.transicion0.iterator();
    }
    
    public Estado getTransicion0(int i)
    {
        return this.transicion0.get(i);
    }
    
    public Estado getTransicion1(int i)
    {
        return this.transicion1.get(i);
    }
    
    public int getNumTransiciones0()
    {
        return this.transicion0.size();
    }
    
    public void setTransicion1(Estado estado) {
        this.transicion1.add(estado);
    }
    public Iterator<Estado> getTransiciones1()
    {
        return this.transicion1.iterator();
    }
    
    public int getNumTransiciones1()
    {
        return this.transicion1.size();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {   return false;  }
        if(Integer.valueOf(this.getNombreEntero()) == Integer.valueOf(((Estado)obj).getNombreEntero()))
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
