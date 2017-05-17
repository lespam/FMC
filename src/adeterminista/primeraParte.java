/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adeterminista;
import java.util.HashSet;
import java.util.Set;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author LesPam
 */
public class primeraParte {

    private static final String FILENAME = "nodeterminista.txt";
    private static final String RESULT = "determinista.txt";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            FileReader reader = new FileReader(FILENAME);
            int character;
            
            //Creo el conjunto de estados
            //Set<Estado> estadosSet = new HashSet<Estado>();
            Map<Integer, Estado> estados = new HashMap<Integer, Estado>();
            
            //Agrego el primer estado (A)
            Estado madre = new Estado(0,90);
            //Y creo un estado genérico para agregar las transiciones
            Estado hijo;
            
            int anterior=0;            
            int tabs=0;
            
            while ((character = reader.read()) != -1) {
                System.out.println("Caracter: "+character);
                //caracter especial es el 10 para linfeed
                //caracter de espacio es el 9
                //caracter de cero es el 48
                //caracter de coma es el 44
                //caracter de uno es el 49
                //System.out.print((char) character);
                //System.out.print( String.format("%04x", (int) character));
                if(anterior==0)
                {   
                    madre = new Estado();
                    int claveM = madre.getNombreEntero();
                    System.out.println("clave: "+claveM);//QUITAR
                    estados.put(claveM, madre);
                    
                    hijo = new Estado(0,character);
                    estados.put(hijo.getNombreEntero(), hijo);
                    
                    estados.get(claveM).setTransicion0(estados.get(hijo.getNombreEntero()));
                    System.out.println(estados.toString());//QUITAR
                }
                if(anterior==10)
                {   
                    tabs = 0;
                    madre = new Estado();
                    int claveM = madre.getNombreEntero();
                    System.out.println("clave: "+claveM);//QUITAR
                    estados.put(claveM, madre);
                    
                    hijo = new Estado(0,character);
                    estados.put(hijo.getNombreEntero(), hijo);
                    
                    estados.get(claveM).setTransicion0(estados.get(hijo.getNombreEntero()));
                    System.out.println(estados.toString());//QUITAR
                }
                if(anterior==9)
                {   
                    tabs++;
                    int claveM = madre.getNombreEntero();
                    
                    if(tabs==1){ //significa que asignaremos a los estados si son de aceptación o no
                        final int aceptacion = Integer.valueOf((char)character);
                        estados.get(claveM).getTransiciones0().forEachRemaining(e-> e.setTipo(aceptacion));
                    }
                    else if(tabs==2)
                    {
                        hijo = new Estado(0,character);
                        estados.put(hijo.getNombreEntero(), hijo);
                        estados.get(claveM).setTransicion1(estados.get(hijo.getNombreEntero()));
                    }
                    else if(tabs==3){ //significa que asignaremos a los estados si son de aceptación o no
                        final int aceptacion = Integer.valueOf((char)character);
                        estados.get(claveM).getTransiciones1().forEachRemaining(e-> e.setTipo(aceptacion));
                    }
                }
                if(anterior==48 || anterior==49)
                {   
                    //No pasa nada
                }
                if(anterior==44)
                {   
                    int claveM = madre.getNombreEntero();
                    System.out.println("CLAVE: "+claveM);//QUITAR
                    System.out.println("TABS: "+tabs);//QUITAR
                    if(tabs==0){ //significa que asignaremos a los estados si son de aceptación o no
                        hijo = new Estado(0,character);
                        System.out.println("hijo: "+hijo.getNombreEntero());//QUITAR
                        
                        estados.put(hijo.getNombreEntero(), hijo);
                        System.out.println(estados.toString());
                        estados.get(claveM).setTransicion0(estados.get(hijo.getNombreEntero()));
                    }
                    else
                    {
                        hijo = new Estado(0,character);
                        estados.put(hijo.getNombreEntero(), hijo);
                        estados.get(claveM).setTransicion1(estados.get(hijo.getNombreEntero()));
                    }
                }
        
                //estados.add(new Estado(0,Integer.valueOf('C')));
                anterior=character;
            }
            reader.close();
            
            FileWriter writer = new FileWriter(RESULT, false);
            writer.write("Hello World");
            writer.write("\r\n");   // write new line
            writer.write(estados.toString());
            writer.close();
 
        } catch (IOException e) {
            e.printStackTrace();
        }     
        
        
    }
    
}
