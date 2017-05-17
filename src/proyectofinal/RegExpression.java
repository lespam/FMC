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
 * @author DELL
 */
public class RegExpression {
    private String estado;// La letra estado que equivale a la expresión
    private String expresion;// La expresión de equivalencia
    
    public RegExpression(String estado, String expresion){
        this.estado = estado;
        this.expresion = expresion;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public String getExpresion() {
        return expresion;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public void setExpresion(String expresion) {
        this.expresion = expresion;
    }
    
    @Override
    public String toString() {
        return estado + " = " + expresion;
    }
}
