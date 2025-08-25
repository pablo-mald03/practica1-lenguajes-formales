/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pablocompany.practicano1lfp.backDefrontend;

import com.pablocompany.practicano1lfp.backend.Lexema;
import com.pablocompany.practicano1lfp.backend.Sentencia;
import java.util.ArrayList;
import javax.swing.JTextPane;

/**
 *
 * @author pablo
 */
//Clase que permite analizar por estados cada una de 
public class AnalizadorLexico {

    //Estructura dinamica encargada de almacenar por completo caracter a caracter
    private ArrayList<Sentencia> listaSentencias = new ArrayList<>(5000);

    //Atributo que permite referenciar a la modificacion del JTextPane
    private JTextPane areaAnalisis;

    //Se conserva una lista para poder dar el paso al analisis de datos (SOLO ES PROVISIONAL)
    private ArrayList<String> listaEntrada = new ArrayList<>(6000);

    public AnalizadorLexico(JTextPane areaAnalisis, ArrayList<String> listaExtraida) {
        this.areaAnalisis = areaAnalisis;
        this.listaEntrada = listaExtraida;

    }

    //Metodo que permite inicializar la separacion de lexemas 
    public void descomponerLexemas() {

        //Ciclo que permite recorrer linea por linea para ir generando las instancias e indicar en que linea estan 
        for (int i = 0; i < listaEntrada.size(); i++) {
            int linea = i + 1;

            String filaTexto = listaEntrada.get(i);

            StringBuilder cadenaCompleta = new StringBuilder();

            boolean entreComillas = false;

            ArrayList<Lexema> lexemaSeparado = new ArrayList<>(5000);

            if (filaTexto.isBlank()) {
                continue;
            }

            //Ciclo que permite ir armanndo los lexemas
            for (int j = 0; j < filaTexto.length(); j++) {

                char caracter = filaTexto.charAt(j);

                if (caracter == '\"') {

                    entreComillas = !entreComillas;
                    cadenaCompleta.append(caracter);

                } else if (Character.isWhitespace(caracter) && !entreComillas) {

                    if (cadenaCompleta.length() > 0) {

                        lexemaSeparado.add(new Lexema(cadenaCompleta.toString(), linea));
                        cadenaCompleta.setLength(0);
                    }
                } else {
                    cadenaCompleta.append(caracter);
                }

            }

            if (cadenaCompleta.length() > 0) {
                lexemaSeparado.add(new Lexema(cadenaCompleta.toString(), linea));
                cadenaCompleta.setLength(0);
            }

            if (!lexemaSeparado.isEmpty()) {
                this.listaSentencias.add(new Sentencia(lexemaSeparado, linea));
            }

        }

        for (int i = 0; i < this.listaSentencias.size(); i++) {

            Sentencia sentenciaActiva = this.listaSentencias.get(i);

            for (int j = 0; j < sentenciaActiva.limiteLexemas(); j++) {

                Lexema lexemaDado = sentenciaActiva.getLexema(j);
                System.out.println("lexema: " + lexemaDado.getLexema() + " Linea: " + lexemaDado.getLineaCoordenada());

            }

        }

    }

}
