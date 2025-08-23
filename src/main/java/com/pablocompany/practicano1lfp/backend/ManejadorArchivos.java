/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pablocompany.practicano1lfp.backend;

import com.pablocompany.practicano1lfp.backDefrontend.AnalizadorLexicoException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JTextPane;


/**
 *
 * @author pablo
 */
//Clase encargada para poder leer y cargar archivos hacia la entrada de datos 
public class ManejadorArchivos {

    //Atributo que guarda el path para la lectura de archivos;
    private String pathEntrada;

    //Metodo que permite elegir el archivo txt y cargarlo directamente a un buffer
    public boolean elegirArchivoEntrada() throws AnalizadorLexicoException {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Selecciona el archivo para Procesar");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int resultado = chooser.showOpenDialog(null);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            File seleccionado = chooser.getSelectedFile();
            if (seleccionado.exists()) {

                this.pathEntrada = seleccionado.getAbsolutePath();
                return true;

            } else {
                throw new AnalizadorLexicoException("No se ha encontrado el archivo seleccionado");
            }
        }

        return false;
    }

    //Metodo que transforma el path de entrada a un arreglo
    public ArrayList<String> convertirEntrada() throws AnalizadorLexicoException {

        ArrayList<String> listaLectura = new ArrayList<>(6000);
        
        if(this.pathEntrada == null || this.pathEntrada.isBlank()){
            throw new AnalizadorLexicoException("No se ha definido aun un archivo para cargar");
        }
        
        File archivo = new File(this.pathEntrada);

        if (!archivo.exists() || !archivo.isFile()) {
            throw new AnalizadorLexicoException("El destinatario seleccionado no existe o no es un archivo");
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (!linea.isEmpty()) {
                    listaLectura.add(linea); // guardamos la línea tal cual
                }
            }
        } catch (IOException ex) {
            throw new AnalizadorLexicoException("No se ha podido procesar el archivo seleccionado");
        }

        if (listaLectura.isEmpty()) {
            throw new AnalizadorLexicoException("El archivo seleccionado esta vacio");
        }

        System.out.println("Tamanio lista " + listaLectura.size());
        //Retorna el arraylist en el mismo formato para procesarlo
        return listaLectura;
    }
    
    
    //Metodo que retorna el path seleccionado
    public String getPath(){
        return this.pathEntrada;
    }

}
