/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pablocompany.practicano1lfp.backend;

/**
 *
 * @author pablo
 */

//Excepcion interna nunca tratada para poder comunicar que se detecto un error
public class ErrorEncontradoException extends Exception{
    
    public ErrorEncontradoException(String mensaje){
        super(mensaje);
    }
    
}
