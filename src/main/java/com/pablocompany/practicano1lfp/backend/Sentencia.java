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
//Clase que permitira accesar al conjunto de lexemas registrados
public class Sentencia {
    
    
    private ArrayList<Lexema> listaLexemas;
    private int filaSentencia; 

    public Sentencia(ArrayList<Lexema> listaLexema,int fila ) {
        this.listaLexemas = listaLexema;
        this.filaSentencia = fila;
        
    }
    
    
    //Metodo que permite acceder al lexema almacenado en la lista
    public Lexema getListaLexema(int indice){
        return this.listaLexemas.get(indice);
    }
    
    //Metodo que retorna el limite de los lexemas almacenados
    public int limiteLexemas(){
        return this.listaLexemas.size();
    }
    
    public ArrayList<Lexema> obtenerListadoLexemas(){
        return this.listaLexemas;
    }
    
    
    
}
