/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pablocompany.practicano1lfp.backend;

import java.util.ArrayList;

/**
 *
 * @author pablo
 */

//Clase encargada de manejar palabra por palabra solo contemplando la linea en la que va para reportes
public class Lexema {
    
    //Estructura dinamica encargada de almacenar por completo caracter a caracter
    private ArrayList<Nodo> listaNodos = new ArrayList<>(5000);
    
    //Atributo que representa el lexema completo de la palabra
    private String lexemaGenerado; 
    
    //Representa la linea en la que se encuetra situado el lexema
    private int lineaCoordenada; 

    public Lexema(String lexemaGenerado, int lineaCoordenada) {
        
        this.lexemaGenerado = lexemaGenerado;
        this.lineaCoordenada = lineaCoordenada;
    }

    public String getLexema() {
        return lexemaGenerado;
    }

    public int getLineaCoordenada() {
        return lineaCoordenada;
    }
    
    
    
    
    
    
}
