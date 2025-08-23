/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pablocompany.practicano1lfp.backDefrontend;

/**
 *
 * @author pablo
 */
//Clase que controlara las excepciones del analizador lexico
public class AnalizadorLexicoException extends  Exception{
    
    public AnalizadorLexicoException(String mensaje){
        super(mensaje);
    }
}
