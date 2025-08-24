/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pablocompany.practicano1lfp.backend;

/**
 *
 * @author pablo
 */
//Clase que representa los diferentes tipos de comentarios
public class Comentarios {

    private String linea;
    private String bloqueInicio;
    private String bloqueFin;

    // getters y setters
    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public String getBloqueInicio() {
        return bloqueInicio;
    }

    public void setBloqueInicio(String bloqueInicio) {
        this.bloqueInicio = bloqueInicio;
    }

    public String getBloqueFin() {
        return bloqueFin;
    }

    public void setBloqueFin(String bloqueFin) {
        this.bloqueFin = bloqueFin;
    }

}
