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
 
            while ((character = reader.read()) != -1) {
                System.out.println(character);
                //caracter especial es el 10 para linfeed
                //caracter de espacio es el 9
                //caracter de cero es el 48
                //caracter de uno es el 49
                //System.out.print((char) character);
                //System.out.print( String.format("%04x", (int) character));
            }
            reader.close();
            
            FileWriter writer = new FileWriter(RESULT, true);
            writer.write("Hello World");
            writer.write("\r\n");   // write new line
            writer.write("Good Bye!");
            writer.close();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Set<Estado> estados = new HashSet<Estado>();
        Estado estadoA = new Estado();
        Estado estadoB = new Estado();
        Estado estadoC = new Estado();
        System.out.println(estados.add(estadoA));
        System.out.println(estados.add(estadoB));
        System.out.println(estados.add(estadoC));
        Estado estadoCe = new Estado(0,Integer.valueOf('C'));
        System.out.println("C "+estadoC.toString());
        System.out.println(estadoCe.toString());
        System.out.println("Sera: "+estadoCe.equals(estadoC));
        System.out.println("String de A"+estadoA.toString());
        System.out.println("String de B"+estadoB.toString());
        System.out.println(estadoA.getTransiciones0());
        System.out.println(estadoB.getTransiciones0());

    }
    
}
