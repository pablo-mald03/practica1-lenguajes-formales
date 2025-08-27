/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pablocompany.practicano1lfp.backend;

/**
 *
 * @author pablo
 */
//Excepcion que se encarga de nombrar excepciones que no hace falta sel analisis tras error del token
public class ErrorPuntualException extends Exception{
    
    public ErrorPuntualException(String mensaje){
        super(mensaje);
    }
    
}
