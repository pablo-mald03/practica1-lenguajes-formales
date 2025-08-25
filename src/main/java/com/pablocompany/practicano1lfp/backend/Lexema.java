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
    
    private Token estadoAnalisis;

    public Lexema(String lexemaGenerado, int lineaCoordenada) {

        this.lexemaGenerado = lexemaGenerado;
        this.lineaCoordenada = lineaCoordenada;
    }

    //Retorna el lexema por si se necesita 
    public String getLexema() {
        return lexemaGenerado;
    }

    //Permite saber en todo momento la fila del lexema
    public int getFilaCoordenada() {
        return lineaCoordenada;
    }

    //Metodo encargado para ir clasificando los estados y tipos de todas las letras que componen el lexema
    public int separarNodos(String palabra, int columna, int fila) {

        int columnaNodo = columna;
        
        for (int i = 0; i < palabra.length(); i++) {

            char caracter = palabra.charAt(i);
            this.listaNodos.add(new Nodo(caracter, fila, columnaNodo, Token.INDEFINIDO));
            columnaNodo++;
        }

        System.out.println("\ntamanio noditos " + this.listaNodos.size());

        for (Nodo nodito : listaNodos) {

            System.out.println("Nodo: " + nodito.getCaracter() + " Fila: " + nodito.getLinea() + " Columna: " + nodito.getColumna() + " Tipo: " + nodito.getToken().getTipo());
        }
        
        return columnaNodo;

    }

}
