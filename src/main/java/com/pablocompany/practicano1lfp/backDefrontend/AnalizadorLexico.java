/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pablocompany.practicano1lfp.backDefrontend;

import com.pablocompany.practicano1lfp.backend.ConfigDatos;
import com.pablocompany.practicano1lfp.backend.ConfigException;
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

    //==============================REGION DE APARTADOS DE CONSTANTES GRAMATICA======================================
    // Letras
    private final char[] ABECEDARIO = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    // Dígitos
    private final char[] DIGITOS = "0123456789".toCharArray();

    //------------------Subregion de gramatica extraidas del config.json----------------------------
    
    private ConfigDatos constantesConfig;
    
    //------------------Fin de la Subregion de gramatica extraidas del config.json----------------------------
    
    
    //==============================FIN DE LA REGION DE APARTADOS DE CONSTANTES GRAMATICA======================================
    //Estructura dinamica encargada de almacenar por completo caracter a caracter
    private ArrayList<Sentencia> listaSentencias = new ArrayList<>(5000);

    //Atributo que permite referenciar a la modificacion del JTextPane
    private JTextPane areaAnalisis;

    //Se conserva una lista para poder dar el paso al analisis de datos (SOLO ES PROVISIONAL)
    private ArrayList<String> listaEntrada = new ArrayList<>(6000);

    public AnalizadorLexico(JTextPane areaAnalisis, ArrayList<String> listaExtraida) throws ConfigException {
        this.areaAnalisis = areaAnalisis;
        this.listaEntrada = listaExtraida;
        
        this.constantesConfig = new ConfigDatos();
        
        this.constantesConfig.cargarDesdeJson();

    }

    //Metodo que permite inicializar la separacion de lexemas FINALIZADO
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

            int columna = 1;

            for (int j = 0; j < sentenciaActiva.limiteLexemas(); j++) {

                Lexema lexemaDado = sentenciaActiva.getLexema(j);

                int fila = lexemaDado.getFilaCoordenada();

                String palabra = lexemaDado.getLexema();

                //Metodo que se encarga de separar todos los nodos
                columna = lexemaDado.separarNodos(palabra, columna, fila);
                columna++;
            }

        }

    }

    //============================REGION QUE PERMITE EL ANALISIS DE CADA LEXEMA CON SUS RESPECTIVOS NODOS===========================
    //Metodo principal y unico para analizar cada lexema moviendose entre estados
    public void recorrerAnalisis() {

        for (int i = 0; i < this.listaSentencias.size(); i++) {

            //Sentencia sentenciaActiva = this.listaSentencias.get(i);
            
            
            
        }

    }

    //============================FIN DE LA REGION QUE PERMITE EL ANALISIS DE CADA LEXEMA CON SUS RESPECTIVOS NODOS===========================
}
