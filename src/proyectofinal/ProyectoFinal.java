/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinal;

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
 * @author LDVG
 */
public class ProyectoFinal {

    /**
     * @param args the command line arguments
     */
    private static final String FILENAME = "test.txt";
    private static final String RESULT = "result.txt";
    
    public static int findTag(List<Partition> partList,char state){
        int i=0; boolean found=false;
        while(i<partList.size()&& !found){
            if(partList.get(i).getNames().contains(String.valueOf(state))){
                found = true;
            }else{
                i++;
            }
            
        }
        return i;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        BufferedReader br = null;
	FileReader fr = null;
        int iteration=1;
        
        Automaton automaton = new Automaton();
        List<Partition> partitions = new ArrayList<Partition>();
        List<Partition> partitionsAux = new ArrayList<Partition>();
        
        //Lee el archivo de texto
	try {

            fr = new FileReader(FILENAME);
            br = new BufferedReader(fr);

            String sCurrentLine;

            br = new BufferedReader(new FileReader(FILENAME));

            while ((sCurrentLine = br.readLine()) != null) {
                    //Lee línea por línea y los separa por cada tab
                    String[] split=sCurrentLine.split("\t");
                    //Crea el autómata 
                    automaton.getStates().add(new State(split[0].charAt(0),Integer.parseInt(split[1]),split[2].charAt(0),Integer.parseInt(split[3])));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
                if (fr != null)
                    fr.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        
        
        boolean added = false;
        for(int i=0;i<automaton.getStates().size();i++){
            if(partitions.isEmpty()){ //Si la partición está vacía agrega el primer estado 
                partitions.add(new Partition().addState(automaton.getStates().get(i)));
                partitions.get(0).setTag1(automaton.getStates().get(i).getType0());// Estos dos taggs dependen del output
                partitions.get(0).setTag2(automaton.getStates().get(i).getType1());
                //partitions.get(0).setTag(1);
            }else{
                for(int j=0;j<partitions.size();j++){//si la partición no está vacía entonces agrega
                    //el estado a la partición que pertenece dependiendo de los taggs
                    if(partitions.get(j).getTag1()==automaton.getStates().get(i).getType0() 
                            && partitions.get(j).getTag2()==automaton.getStates().get(i).getType1()){
                        partitions.get(j).addState(automaton.getStates().get(i));
                        added=true;
                    }
                }
                if(added==false){//Si no existe una partición con sus taggs entonces crea una y agrega el estado
                    partitions.add(new Partition().addState(automaton.getStates().get(i)));
                    partitions.get(partitions.size()-1).setTag1(automaton.getStates().get(i).getType0());
                    partitions.get(partitions.size()-1).setTag2(automaton.getStates().get(i).getType1());
                }
                added=false;
            }          
        }

        System.out.println("Iteration: "+iteration);
        iteration++;
        for(int i=0;i<partitions.size();i++){
            System.out.println("----");
            for(int j=0;j<partitions.get(i).getNames().size();j++){
                System.out.println(partitions.get(i).getNames().get(j));
            }
        }     
        
        //Mientras la partición actual y la partición anterior sean de
        //Distinto tamaño entonces sigue iterando
        while(partitions.size()!=partitionsAux.size()){
           
        
            added=false;
             for(int i=0;i<partitions.size();i++){//Itera sobre todos las particiones
                 int j =0;
                 while(j<partitions.get(i).getStates().size()){//Itera sobre los estados de la particion i
                     if(partitionsAux.isEmpty()){//si  particionsaux está vacía crea una partición y la inserta
                         partitionsAux.add(new Partition().addState(partitions.get(i).getStates().get(j)));
                         //agrega los tags a la particion
                         partitionsAux.get(0).setTag1(findTag(partitions,partitions.get(i).getStates().get(j).getTransition0()));
                         partitionsAux.get(0).setTag2(findTag(partitions,partitions.get(i).getStates().get(j).getTransition1()));
                     }
                     else{//sino va a revisar a que parrtición pertenece
                         for(int k=0;k<partitionsAux.size();k++){
                             //si coinciden sus taggs con los de alguna partición
                             //y ese estado se encontraba en la misma partición de
                             //las particiones anteriores, entonces se agrega
                            
                             if(partitionsAux.get(k).getTag1()==findTag(partitions,partitions.get(i).getStates().get(j).getTransition0())
                                     && partitionsAux.get(k).getTag2()==findTag(partitions,partitions.get(i).getStates().get(j).getTransition1())
                                     && findTag(partitions,partitions.get(i).getStates().get(j).getName().charAt(0))
                                     == findTag(partitions,partitionsAux.get(k).getNames().get(0).charAt(0))
                                     && !added){
                                 partitionsAux.get(k).addState(partitions.get(i).getStates().get(j));
                                 added=true;                             
                             }
                         }
                         if(!added){//si no se encontró ninguna partición entonces se agrega
                         //crear nueva particion en partitionsAux y agregar tags
                         partitionsAux.add(new Partition().addState(partitions.get(i).getStates().get(j)));
                         //agrega tags a particion
                         partitionsAux.get(partitionsAux.size()-1).setTag1(findTag(partitions,partitions.get(i).getStates().get(j).getTransition0()));
                         partitionsAux.get(partitionsAux.size()-1).setTag2(findTag(partitions,partitions.get(i).getStates().get(j).getTransition1()));
                        }
                     }
                     added=false;
                    j++;
                 }     
             }
             
            //si las particion actual fue igual a la anterior significa que
            //ya se llegó al automata mínimo y termina el loop
             if(partitions.size()==partitionsAux.size()){
                 break;
             }else{
                 //En caso contrario se limpia la partición anterior
                 //Y se establece la partición anterior como la actual
                    partitions.clear();
                    partitions.addAll(partitionsAux);
                    partitionsAux.clear();
             }
            System.out.println("Iteration: "+iteration);
            for(int i=0;i<partitions.size();i++){
                System.out.println("----");
                for(int j=0;j<partitions.get(i).getNames().size();j++){
                    System.out.println(partitions.get(i).getNames().get(j));
                }
            }
            iteration++;
        }
        
//      Crea el autómata mínimo con base en las particiones
        Automaton finalAutomaton = new Automaton();
        for(int i=0;i<partitions.size();i++){
            finalAutomaton.addState(partitions.get(i).getStates().get(0));
        }
        
        System.out.println("Automata final: ");        
        for(int i=0;i<finalAutomaton.getStates().size();i++){
            System.out.println(finalAutomaton.getStates().get(i).toString());
        }
        
        //Reenombra las transiciones del autómata con base en las particiones
        //Tomando la primer transición como el estado A, el segundo como el B
        //y así sucesivamente
        char trans0, trans1;
        int tran0,tran1;
         for(int i=0;i<finalAutomaton.getStates().size();i++){
            trans0= finalAutomaton.getStates().get(i).getTransition0();
            trans1=finalAutomaton.getStates().get(i).getTransition1();            
            tran0=findTag(partitions,trans0)+65;
            tran1=findTag(partitions,trans1)+65;
            finalAutomaton.getStates().get(i).setTransition0((String.valueOf((char)tran0)).charAt(0));
            finalAutomaton.getStates().get(i).setTransition1((String.valueOf((char)tran1)).charAt(0));
        }
         
        System.out.println("Automata final 2: ");        
        for(int i=0;i<finalAutomaton.getStates().size();i++){
            System.out.println(finalAutomaton.getStates().get(i).toString());
        }
        
        
        System.out.println("-------------");
        // Este codigo asume que en el automata result.txt hay un estado de aceptacion y que 
        // el automata es minimizado y deterministico.
        System.out.println("Expresión regular de autómata. ");
        System.out.println("Ecuaciones recursivas desde lambda: ");
        RegExpression expresionLambda = null;
        List<RegExpression> expresionesDependientes = new ArrayList<RegExpression>();
        
        for(int i=0; i < finalAutomaton.getStates().size(); i++){//Encuentra expresion lambda para estado de aceptacion.
            if(finalAutomaton.getStates().get(i).getType0()==1){
                expresionLambda = new RegExpression(""+finalAutomaton.getStates().get(i).getTransition0(),"(1+0)");
            }
            if(finalAutomaton.getStates().get(i).getType1()==1){
                expresionLambda = new RegExpression(""+finalAutomaton.getStates().get(i).getTransition1(),"(1+0)");
            }
            expresionesDependientes.add(new RegExpression(finalAutomaton.getStates().get(i).getName()
                                                            ,"0"+finalAutomaton.getStates().get(i).getTransition0()+" + "+
                                                            "1"+finalAutomaton.getStates().get(i).getTransition1()));
            System.out.println(expresionesDependientes.get(i).toString());
        }
        System.out.println(expresionLambda.toString()+"λ");
        
        // Con estado de aceptación aplica Estrella de Kleene para despejar estados subsecuentes
        // y crear ecuación para expresión regular del autómata.
        
        for(int j=0; j < finalAutomaton.getStates().size(); j++){
            for(int i=0; i < expresionesDependientes.size(); i++){
                if(expresionesDependientes.get(i).getExpresion().contains(expresionLambda.getEstado())){
                    System.out.println("SE encontro el estado en la expresion de:"+expresionesDependientes.get(i).getEstado());
                    System.out.println("Se aplica estrella de Kleene");
                    expresionLambda.setExpresion(expresionesDependientes.get(i).getExpresion().replace(expresionLambda.getEstado(),"("+expresionLambda.getExpresion()+")"));
                    expresionLambda.setEstado(expresionesDependientes.get(i).getEstado());
                    expresionLambda.setExpresion(expresionLambda.getExpresion().replace(expresionesDependientes.get(i).getEstado(),""));
                    System.out.println("SE reemplazo por:"+expresionLambda.getExpresion());
                    expresionesDependientes.remove(i);
                }
            }
        }
        expresionLambda.setExpresion(expresionLambda.getExpresion().replaceAll("[a-zA-Z]", ""));
        System.out.println("-------------");
        System.out.println("EXPRESION REGULAR");
        System.out.println(expresionLambda.getExpresion());
        System.out.println("-------------");
        
        
        State st;
        BufferedWriter bw = null;
	FileWriter fw = null;

        try {            

            fw = new FileWriter(RESULT);
            bw = new BufferedWriter(fw);
            for(int i=0;i<finalAutomaton.getStates().size();i++){
                st = finalAutomaton.getStates().get(i);
                bw.write(st.getTransition0()+"\t"+st.getType0()+"\t"+
                        st.getTransition1()+"\t"+st.getType1());
                bw.newLine();
                //System.out.println(finalAutomaton.getStates().get(i).toString());
            }
            

            System.out.println("Done");

        } catch (IOException e) {

                e.printStackTrace();

        } finally {

                try {

                        if (bw != null)
                                bw.close();

                        if (fw != null)
                                fw.close();

                } catch (IOException ex) {

                        ex.printStackTrace();

                }

        }

         
    }
    
}
