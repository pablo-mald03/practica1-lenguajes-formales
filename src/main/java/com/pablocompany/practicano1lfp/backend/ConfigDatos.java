/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pablocompany.practicano1lfp.backend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author pablo
 */
//Clase encargada de controlar todos los tokenes necesarios y permitidos
public class ConfigDatos {

    private ArrayList<String> palabrasReservadas = new ArrayList<>();
    private ArrayList<String> operadores = new ArrayList<>();
    private ArrayList<String> puntuacion = new ArrayList<>();
    private ArrayList<String> agrupacion = new ArrayList<>();

    private Comentarios comments;

    public ConfigDatos() {
        this.comments = new Comentarios();
    }

    //Verifica que sea paralabra reservada
    public boolean esPalabrasReservadas(String palabra) {
        return palabrasReservadas.contains(palabra);
    }

    //Verifica que sea operador matematico
    public boolean esOperadores(char operador) {
        return operadores.contains(String.valueOf(operador));
    }

    //Verifica que sea signo de puntuacion
    public boolean esPuntuacion(char puntuar) {
        return puntuacion.contains(String.valueOf(puntuar));
    }

    //Verifica que sea simbolo de agrupacion
    public boolean esAgrupacion(char agrupar) {
        return agrupacion.contains(String.valueOf(agrupar));
    }

    //Verifica que sea comentario de una sola linea
    public boolean esComentarioLinea(String entrada) {
        return entrada.equals(this.comments.getComentarioLinea());
    }

    //Metodos de verificacion que permiten Analizarr el comentario fin
    public boolean esBloqueComentarioInicial(String inicio) {
        return inicio.equals(this.comments.getBloqueInicio());
    }

    //Verifica que sea comentario multilinea
    public boolean esBloqueComentarioFin(String fin) {
        return fin.equalsIgnoreCase(this.comments.getBloqueFin());
    }

    //Metodo que se encarga de leer y procesar todo a arrayList
    public void cargarDesdeJson() throws ConfigException {

        if (!this.palabrasReservadas.isEmpty()) {
            this.palabrasReservadas.clear();
        }

        if (!this.operadores.isEmpty()) {
            this.operadores.clear();
        }
        if (!this.puntuacion.isEmpty()) {
            this.puntuacion.clear();
        }
        if (!this.agrupacion.isEmpty()) {
            this.agrupacion.clear();
        }

        InputStream ruta = getClass().getResourceAsStream("/com/pablocompany/practicano1/target/configuracion/config.json");

        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                sb.append(linea.trim());
            }

            String json = sb.toString();

            this.palabrasReservadas = extraerArray(json, "palabrasReservadas");
            this.operadores = extraerArray(json, "operadores");
            this.puntuacion = extraerArray(json, "puntuacion");
            this.agrupacion = extraerArray(json, "agrupacion");

            cargarComentarios(json);

        } catch (IOException ex) {
            throw new ConfigException("No se ha podido procesar el config.json");
        }

    }

    //Metodo que sirve para extraer del .json toda la configuracion de tokens
    private ArrayList<String> extraerArray(String json, String clave) {
        ArrayList<String> lista = new ArrayList<>();
        int pos = json.indexOf("\"" + clave + "\"");
        if (pos == -1) {
            return lista;
        }

        pos = json.indexOf("[", pos);
        if (pos == -1) {
            return lista;
        }

        int fin = pos + 1;
        boolean dentroComillas = false;
        StringBuilder elemento = new StringBuilder();

        while (fin < json.length()) {
            char c = json.charAt(fin);
            if (c == '"') {
                dentroComillas = !dentroComillas;
            } else if (c == ',' && !dentroComillas) {
                if (elemento.length() > 0) {
                    lista.add(elemento.toString().trim());
                    elemento.setLength(0);
                }
            } else if (c == ']' && !dentroComillas) {
                if (elemento.length() > 0) {
                    lista.add(elemento.toString().trim());
                }
                break;
            } else {
                elemento.append(c);
            }
            fin++;
        }

        return lista;

    }

//Metodo que se encarga de instanciar todos los comentarios PENDIENTE
    private void cargarComentarios(String cargado) {

        String json = cargado;

        int inicio = json.indexOf("\"comentarios\"") + "\"comentarios\"".length();
        inicio = json.indexOf("{", inicio);

        int fin = json.indexOf("}", inicio);

        String comentariosJson = json.substring(inicio + 1, fin).trim();

        String linea = null;
        String bloqueInicio = null;
        String bloqueFin = null;

        for (String parte : comentariosJson.split(",")) {
            String[] keyValue = parte.split(":");
            if (keyValue.length != 2) {
                continue;
            }

            String key = keyValue[0].trim().replace("\"", "");
            String value = keyValue[1].trim().replace("\"", "");

            switch (key) {
                case "linea" ->
                    linea = value;
                case "bloqueInicio" ->
                    bloqueInicio = value;
                case "bloqueFin" ->
                    bloqueFin = value;
            }
        }

        this.comments.setLinea(linea);
        this.comments.setBloqueInicio(bloqueInicio);
        this.comments.setBloqueFin(bloqueFin);

    }

}
