/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pablocompany.practicano1lfp.backend;

import com.pablocompany.practicano1lfp.backDefrontend.AnalizadorLexicoException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

/**
 *
 * @author pablo
 */
//Clase que se encarga de leer las entradas recibidas de un JtextPane
public class LectorEntradas {

    //Atributo que permite almacenar en una estructura dinamica todas las lineas de texto registradas
    //Se le da un preset para evitar mucho consumo de memoria por clonacion
    private ArrayList<String> listaTexto = new ArrayList<>(6000);

    //Metodo que permite transformar todo el texto de entrada al arreglo 
    public void transformarTexto(String texto, JTextPane paneLog) throws AnalizadorLexicoException {
        
        if(!listaTexto.isEmpty()){
            listaTexto.clear();
        }

        BufferedReader bufer = new BufferedReader(new StringReader(texto));

        String linea;
        
        try {
            while ((linea = bufer.readLine()) != null) {
                listaTexto.add(linea);
            }
        } catch (IOException ex) {
            throw new AnalizadorLexicoException("No se ha podido procesar el texto de entrada");
        }
        
         System.out.println("Tamanio transformado" + this.listaTexto.size()); 

    }
    
    //Metodo set que permite referenciar el arreglo extraido hacia el interno de la clase
    public void setLista(ArrayList<String> listaParametro, JTextPane paneLog){
        this.listaTexto = listaParametro;
        
        System.out.println("Tamanio " + this.listaTexto.size());   
    }
    
    //Metodo que retorna el listado de textos almacenados en el componente
    public ArrayList<String> getListado(){
        return this.listaTexto; 
    }
    
    //Metodo que permite pasar por parametro un arraylist e imprimirlo en un JtextPane
    public void imprimirLog(ArrayList<String> listaExtraida, JTextPane textPane) throws BadLocationException{    
        StyledDocument doc = textPane.getStyledDocument();
        for (int i = 0; i < listaExtraida.size(); i++) {
            doc.insertString(doc.getLength(), listaExtraida.get(i) + "\n", null);
        }
    }
    

}
