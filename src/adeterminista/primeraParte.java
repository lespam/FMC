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
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
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
    public Queue<Estado> queue = new LinkedList<Estado>();
    public Map<Integer,Estado> auxilio = new HashMap<Integer,Estado>();
    
    public void ponerTransiciones(Estado estado, int tipoTrans, Map<Integer,Estado> subconjunto)
    {
        Estado nuevo = new Estado();
        int numNuevo = nuevo.getNombreEntero();
        
        int ceros = estado.getNumTransiciones0();
        int unos = estado.getNumTransiciones1();
        
        Estado aux0 = new Estado();
        int numAux0 = aux0.getNombreEntero();
        
        Estado aux1 = new Estado();
        int numAux1 = aux1.getNombreEntero();
        
        auxilio.putIfAbsent(numAux0, aux0);
        auxilio.putIfAbsent(numAux1, aux1);
        auxilio.putIfAbsent(numNuevo, nuevo);
        auxilio.get(numNuevo).setTransicion0(auxilio.get(numAux0));
        auxilio.get(numNuevo).setTransicion1(auxilio.get(numAux1));
        
        if(ceros>0)
        {
            estado.getTransiciones0().forEachRemaining(e->subconjunto.putIfAbsent(e.getNombreEntero(), e));
        }
            
        
    }
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            FileReader reader = new FileReader(FILENAME);
            FileWriter writer = new FileWriter(RESULT, false);
            
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

            //int y = Character.getNumericValue(x);
            
            while ((character = reader.read()) != -1) {
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
                    estados.putIfAbsent(claveM, madre);
                    hijo = new Estado(0,character);
                    estados.putIfAbsent(hijo.getNombreEntero(), hijo);
                    
                    estados.get(claveM).setTransicion0(estados.get(hijo.getNombreEntero()));
                }
                if(anterior==10)
                {   
                    tabs = 0;
                    madre = new Estado();
                    int claveM = madre.getNombreEntero();
                    estados.putIfAbsent(claveM, madre);
                    
                    hijo = new Estado(0,character);
                    estados.putIfAbsent(hijo.getNombreEntero(), hijo);
                    
                    estados.get(claveM).setTransicion0(estados.get(hijo.getNombreEntero()));
                }
                if(anterior==9)
                {   
                    tabs++;
                    int claveM = madre.getNombreEntero();
                    
                    if(tabs==1){ //significa que asignaremos a los estados si son de aceptación o no
                        final int aceptacion = Character.getNumericValue(character);
                        estados.get(claveM).getTransiciones0().forEachRemaining(e-> e.setTipo(aceptacion));

                    }
                    else if(tabs==2)
                    {
                        hijo = new Estado(0,character);
                        estados.putIfAbsent(hijo.getNombreEntero(), hijo);
                        estados.get(claveM).setTransicion1(estados.get(hijo.getNombreEntero()));
                    }
                    else if(tabs==3){ //significa que asignaremos a los estados si son de aceptación o no
                        final int aceptacion = Character.getNumericValue(character);
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
                    if(tabs==0){ //significa que asignaremos a los estados si son de aceptación o no
                        hijo = new Estado(0,character);
                        
                        estados.putIfAbsent(hijo.getNombreEntero(), hijo);
                        estados.get(claveM).setTransicion0(estados.get(hijo.getNombreEntero()));
                    }
                    else
                    {
                        hijo = new Estado(0,character);
                        estados.putIfAbsent(hijo.getNombreEntero(), hijo);
                        estados.get(claveM).setTransicion1(estados.get(hijo.getNombreEntero()));
                    }

                }
                anterior=character;
            }
            reader.close();
            
            Map<Integer, Estado> adetermin = new HashMap<Integer, Estado>();
            Map<Integer, Estado> subconjunto = new HashMap<Integer, Estado>();
            Queue<Estado> queue = new LinkedList<Estado>();
            int cuantos = estados.size();
            for(int i=65; i<65+cuantos; i++)
            {
                System.out.println(i);
                System.out.println(estados.get(i).toString());
                queue.add(estados.get(i));
            }
            
            Estado auxiliar = queue.remove();
            adetermin.putIfAbsent(auxiliar.getNombreEntero(), auxiliar);
            int ceros = adetermin.get(auxiliar.getNombreEntero()).getNumTransiciones0();
            int unos = adetermin.get(auxiliar.getNombreEntero()).getNumTransiciones1();
            if(ceros>1)
            {
                for(int i=1; i<=ceros; i++)
                {
                    subconjunto.putIfAbsent(auxiliar.getTransicion0(i).getNombreEntero(), auxiliar.getTransicion0(i));
                }
                subconjunto.;
            }
            else
                subconjunto.putIfAbsent(auxiliar.getTransicion0(1).getNombreEntero(), auxiliar.getTransicion0(1));
            
            
            //Ahora que tenemos en nuestro conjunto "estados" todos los estados,
            //comenzamos a pasar de noDet a Det
            int x = 0;
            System.out.println(x|0);
            writer.write("Hello World");
            writer.write("\r\n");   // write new line
            writer.write(estados.toString());
            writer.close();
 
        } catch (IOException e) {
            e.printStackTrace();
        }     
        
        
    }
    
}
