package adeterminista;
import java.util.HashSet;
import java.util.Set;
import java.util.*;
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
    /*
    //PRUEBAS DE CODIGO (NO FINAL)
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
    */
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
            System.out.println(estados.toString());

            Queue<Estado> queue = new LinkedList<Estado>();
            Queue<Estado> colaAux = new LinkedList<Estado>();
            
            int cuantos = estados.size();
            int trans0 = 0;
            int trans1 = 0;
            for(int i=65; i<65+cuantos; i++)
            {
                System.out.println(estados.get(i).toString());
                queue.add(estados.get(i));
            }
            
            Map<Integer, Estado> adetermin = new HashMap<Integer, Estado>();
            Estado auxMadre = queue.poll();
            int contador=1;
            colaAux.add(auxMadre);
            
            
            while(!colaAux.isEmpty()){
                System.out.println("COLA: "+colaAux.peek().toString());
                System.out.println("ENTRO AL WHILE");
                if(adetermin.containsValue(colaAux.peek()))
                {
                    colaAux.poll();
                    System.out.println("Ya lo había metido");
                    contador++;
                }
                else
                {          
                    auxMadre = colaAux.poll();
                    System.out.println("TRANS0 "+auxMadre.transicion0.toString());
                    System.out.println("TRANS1 "+auxMadre.transicion1.toString());
                    adetermin.putIfAbsent(contador, auxMadre);
                    System.out.println("elemento "+contador+": "+adetermin.get(contador).toString());
                    trans0 = adetermin.get(contador).getNumTransiciones0();
                    trans1 = adetermin.get(contador).getNumTransiciones1();
                    if(trans0>1)
                    {
                        Estado nuevo= new Estado();
                        int auxTipo=auxMadre.getTipo();
                        List<Estado> a0 = new ArrayList<>();
                        List<Estado> a1 = new ArrayList<>();
                        List<Character> nom = new ArrayList<>();
                        for(int i=0; i<trans0;i++)
                        {
                            int car = auxMadre.getTransicion0(i).getNombreEntero();
                            auxTipo = auxTipo|auxMadre.getTransicion0(i).getTipo();
                            a0.addAll(auxMadre.getTransicion0(i).transicion0);
                            a1.addAll(auxMadre.getTransicion0(i).transicion1);
                            // add elements to al, including duplicates
                            if(i==0){
                                nuevo.nombre.clear();
                                nuevo.nombre.addAll(auxMadre.getTransicion0(0).nombre);}
                            else
                                nuevo.addNombre((char)car);
                        }
                        nom.addAll(nuevo.nombre);
                        Set<Character> hnom = new HashSet<>();
                        hnom.addAll(nom);
                        nom.clear();
                        nom.addAll(hnom);
                        nuevo.nombre.clear();
                        nuevo.nombre.addAll(nom);
                        Set<Estado> hs = new HashSet<>();
                        hs.addAll(a0);
                        a0.clear();
                        a0.addAll(hs);
                        nuevo.transicion0.addAll(a0);
                        nuevo.transicion1.addAll(a1);
                        nuevo.setTipo(auxTipo);
                        colaAux.add(nuevo);
                        adetermin.get(contador).transicion0.clear();
                        adetermin.get(contador).setTransicion0(nuevo);
                        System.out.println("Transiciones 0: "+adetermin.get(1).transicion0.toString());
                    }
                    else
                    {
                        colaAux.add(auxMadre.getTransicion0(0));
                        System.out.println("Lo que agregó: "+auxMadre.getTransicion0(0).toString());
                    }
                    if(trans1>1)
                    {
                        Estado nuevo= new Estado();
                        int auxTipo=auxMadre.getTipo();
                        List<Estado> a0 = new ArrayList<>();
                        List<Estado> a1 = new ArrayList<>();
                        List<Character> nom = new ArrayList<>();
                        for(int i=0; i<trans1;i++)
                        {
                            int car = auxMadre.getTransicion1(i).getNombreEntero();
                            auxTipo = auxTipo|auxMadre.getTransicion1(i).getTipo();
                            a0.addAll(auxMadre.getTransicion1(i).transicion0);
                            a1.addAll(auxMadre.getTransicion1(i).transicion1);
                            // add elements to al, including duplicates
                            if(i==0){
                                nuevo.nombre.clear();
                                nuevo.nombre.addAll(auxMadre.getTransicion1(0).nombre);}
                            else
                                nuevo.addNombre((char)car);
                        }
                        nom.addAll(nuevo.nombre);
                        Set<Character> hnom = new HashSet<>();
                        hnom.addAll(nom);
                        nom.clear();
                        nom.addAll(hnom);
                        nuevo.nombre.clear();
                        nuevo.nombre.addAll(nom);
                        Set<Estado> hs = new HashSet<>();
                        hs.addAll(a0);
                        a0.clear();
                        a0.addAll(hs);
                        nuevo.transicion0.addAll(a0);
                        nuevo.transicion1.addAll(a1);
                        nuevo.setTipo(auxTipo);
                        colaAux.add(nuevo);
                        adetermin.get(contador).transicion1.clear();
                        adetermin.get(contador).setTransicion0(nuevo);
                        System.out.println("Transiciones 1: "+adetermin.get(1).transicion1.toString());
                    }
                    else
                    {
                        colaAux.add(auxMadre.getTransicion1(0));
                        System.out.println(adetermin.get(1).transicion1.toString());
                    }
                    contador++;
                }
            }
            int[] x = {1,2,3,5,8};
            for(int l=0; l<x.length;l++)
            {
                System.out.println("Estado "+l+": "+adetermin.get(x[l]));
                System.out.println("   T0 "+adetermin.get(x[l]).transicion0.toString());
                System.out.println("   T1 "+adetermin.get(x[l]).transicion1.toString());
            }
            
            
            
            
            
            /*PRUEBAS DE CODIGO (NO FINAL)
            Map<Integer, Estado> adetermin = new HashMap<Integer, Estado>();
            Map<Integer, Estado> subconjunto = new HashMap<Integer, Estado>();
            Queue<Estado> queue = new LinkedList<Estado>();
            
            
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
            
            */
            
            //Ahora que tenemos en nuestro conjunto "estados" todos los estados,
            //comenzamos a pasar de noDet a Det

            
            writer.write("Hello World");
            writer.write("\r\n");   // write new line
            writer.write(estados.toString());
            writer.close();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}