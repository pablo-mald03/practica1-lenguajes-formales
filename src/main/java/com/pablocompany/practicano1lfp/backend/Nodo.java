/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pablocompany.practicano1lfp.backend;

/**
 *
 * @author pablo
 */
//Clase que permite darle un sentido a cada cadena de caracter
public class Nodo {

    private char caracter;
    private int linea;
    private int columna;
    private Token tipo;

    public Nodo(char caracter, int fila, int columna, Token tipo) {
        this.caracter = caracter;
        this.linea = fila;
        this.columna = columna;
        this.tipo = tipo;
    }

    //--------------------------APARTADO DE METODOS QUE SIRVEN PARA PODER SABER EL VALOR DE TOKEN O DE ESTADO---------------------
    public char getCaracter() {
        return caracter;
    }

    public int getLinea() {
        return linea;
    }

    public int getColumna() {
        return columna;
    }

    public Token getToken() {
        return tipo;
    }
    //--------------------------FIN DEL APARTADO DE METODOS QUE SIRVEN PARA PODER SABER EL VALOR DE TOKEN O DE ESTADO---------------------

    //--------------------------APARTADO DE METODOS QUE SIRVEN PARA PODER MODIFICAR EL VALOR DE TOKEN O DE ESTADO---------------------
    public void setCaracter(char caracter) {
        this.caracter = caracter;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public void setTipo(Token tipo) {
        this.tipo = tipo;
    }
    //--------------------------FIN DEL APARTADO DE METODOS QUE SIRVEN PARA PODER MODIFICAR EL VALOR DE TOKEN O DE ESTADO---------------------

}
