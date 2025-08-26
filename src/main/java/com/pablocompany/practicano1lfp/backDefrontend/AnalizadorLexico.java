/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pablocompany.practicano1lfp.backDefrontend;

import com.pablocompany.practicano1lfp.backend.ConfigDatos;
import com.pablocompany.practicano1lfp.backend.ConfigException;
import com.pablocompany.practicano1lfp.backend.Lexema;
import com.pablocompany.practicano1lfp.backend.Nodo;
import com.pablocompany.practicano1lfp.backend.Sentencia;
import com.pablocompany.practicano1lfp.backend.Token;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

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

    //Permite tener la referencia a los datos del json
    private ConfigDatos constantesConfig;

    //==============================FIN DE LA REGION DE APARTADOS DE CONSTANTES GRAMATICA======================================
    //Estructura dinamica encargada de almacenar por completo caracter a caracter
    private ArrayList<Sentencia> listaSentencias = new ArrayList<>(5000);

    //Atributo que permite referenciar a la modificacion del JTextPane
    private JTextPane areaAnalisis;

    //Atributo que sirve para exponer los errores
    private JTextPane logErrores;

    //Se conserva una lista para poder dar el paso al analisis de datos (SOLO ES PROVISIONAL)
    private ArrayList<String> listaEntrada = new ArrayList<>(6000);

    public AnalizadorLexico(JTextPane areaAnalisis, ArrayList<String> listaExtraida, JTextPane paneErrores, ConfigDatos configuracion) throws ConfigException {
        this.areaAnalisis = areaAnalisis;

        this.logErrores = paneErrores;
        this.listaEntrada = listaExtraida;

        this.constantesConfig = configuracion;

    }

    //Metodo que permite inicializar la separacion de lexemas FINALIZADO
    public void descomponerLexemas() throws BadLocationException {

        //Ciclo que permite recorrer linea por linea para ir generando las instancias e indicar en que linea estan 
        for (int i = 0; i < listaEntrada.size(); i++) {
            int linea = i + 1;

            String filaTexto = listaEntrada.get(i);

            StringBuilder cadenaCompleta = new StringBuilder();

            boolean entreComillas = false;

            ArrayList<Lexema> lexemaSeparado = new ArrayList<>(5000);

            if (filaTexto.isBlank()) {
                lexemaSeparado.add(new Lexema("", linea));
                this.listaSentencias.add(new Sentencia(lexemaSeparado, linea));
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

                Lexema lexemaDado = sentenciaActiva.getListaLexema(j);

                int fila = lexemaDado.getFilaCoordenada();

                String palabra = lexemaDado.getLexema();

                if (palabra.isBlank()) {
                    continue;
                }

                //Metodo que se encarga de separar todos los nodos
                columna = lexemaDado.separarNodos(palabra, columna, fila);
                columna++;
            }

        }

        recorrerAnalisis();

    }

    //============================REGION QUE PERMITE EL ANALISIS DE CADA LEXEMA CON SUS RESPECTIVOS NODOS===========================
    //Metodo principal y unico para analizar cada lexema moviendose entre estados
    public void recorrerAnalisis() throws BadLocationException {

        for (int i = 0; i < this.listaSentencias.size(); i++) {

            Sentencia sentenciaActiva = this.listaSentencias.get(i);

            for (int j = 0; j < sentenciaActiva.limiteLexemas(); j++) {

                Lexema lexemaDado = sentenciaActiva.getListaLexema(j);

                String palabra = lexemaDado.getLexema();

                if (palabra.isBlank()) {
                    continue;
                }

                if (!lexemaDado.esYaDeclarado()) {

                    if (buscarGeneralizaciones(lexemaDado, sentenciaActiva, this.listaSentencias)) {
                        continue;
                    }

                    //Continua viajando entre estados si no es generalidad
                    //System.out.println("Pilin pilin");
                }
            }

        }

        pintarLogSalida();

    }

    //Metodo que sirve cuando la cadena se compone de cierta forma que el token esta escrito literal como en el .json
    public boolean buscarGeneralizaciones(Lexema lexemaActual, Sentencia lineaPosicionada, ArrayList<Sentencia> listaSentencias) {

        //Detecta si es palabra reservada directamente
        if (this.constantesConfig.esPalabrasReservadas(lexemaActual.getLexema())) {
            lexemaActual.generalizarNodo(Token.PALABRA_RESERVADA);
            lexemaActual.setYaDeclarado(true);
            return true;
        }

        //Detecta si es operador directamente
        if (lexemaActual.getLongitudNodo() == 1 && this.constantesConfig.esOperadores(lexemaActual.getLexema().charAt(0))) {
            lexemaActual.generalizarNodo(Token.OPERADOR);
            lexemaActual.setYaDeclarado(true);
            return true;
        }

        //Detecta si es signo de agrupacion directamente
        if (lexemaActual.getLongitudNodo() == 1 && this.constantesConfig.esAgrupacion(lexemaActual.getLexema().charAt(0))) {
            lexemaActual.generalizarNodo(Token.AGRUPACION);
            lexemaActual.setYaDeclarado(true);
            return true;
        }

        //Detecta si es signo de puntuacion directamente
        if (lexemaActual.getLongitudNodo() == 1 && this.constantesConfig.esPuntuacion(lexemaActual.getLexema().charAt(0))) {
            lexemaActual.generalizarNodo(Token.PUNTUACION);
            lexemaActual.setYaDeclarado(true);
            return true;
        }

        if (lexemaActual.getLongitudNodo() > 1) {

            //Detecta si es comentario directamente DE UNA LINEA
            String lexemaInicial = String.valueOf(lexemaActual.getValorNodo(0).getCaracter()) + String.valueOf(lexemaActual.getValorNodo(1).getCaracter());

            if (this.constantesConfig.esComentarioLinea(lexemaInicial)) {

                for (Lexema posicion : lineaPosicionada.obtenerListadoLexemas()) {

                    posicion.generalizarNodo(Token.COMENTARIO_LINEA);
                    posicion.setYaDeclarado(true);
                }

                return true;
            }

        }

        System.out.println("llega a verificar");
        //Busca la generalidad de poder generar un comentario multilinea
        if (lexemaActual.getLongitudNodo() > 1) {
            System.out.println("Si entra");

            //Detecta si es comentario directamente DE UNA LINEA
            String cadenaLexema = String.valueOf(lexemaActual.getValorNodo(0).getCaracter()) + String.valueOf(lexemaActual.getValorNodo(1).getCaracter());

            if (this.constantesConfig.esBloqueComentarioInicial(cadenaLexema)) {

                lexemaActual.generalizarNodo(Token.COMENTARIO_BLOQUE);
                lexemaActual.setYaDeclarado(true);

                boolean finHallado = false;

                for (Sentencia sentenciaIndex : listaSentencias) {

                    for (Lexema posicion : sentenciaIndex.obtenerListadoLexemas()) {

                        int indice = posicion.getLongitudNodo()-1;

                        if (posicion.getLongitudNodo() > 1) {

                            
                            String lineaCierre = String.valueOf(posicion.getValorNodo(indice - 1).getCaracter()) + String.valueOf(posicion.getValorNodo(indice).getCaracter());
                            posicion.generalizarNodo(Token.COMENTARIO_BLOQUE);
                            posicion.setYaDeclarado(true);

                            if (this.constantesConfig.esBloqueComentarioFin(lineaCierre)) {
                                finHallado = true;

                                break;

                            }
                        }

                    }

                    if (finHallado) {
                        break;
                    }

                }

                return true;
            }

        }

        return false;
    }

    //============================FIN DE LA REGION QUE PERMITE EL ANALISIS DE CADA LEXEMA CON SUS RESPECTIVOS NODOS===========================
    //METODO UNICO QUE SIRVE PARA COLOREAR LOS LOG DE SALIDA
    public void pintarLogSalida() throws BadLocationException {

        int posicionCaret = this.areaAnalisis.getCaretPosition();

        limpiarAreaAnalisis();

        for (int i = 0; i < this.listaSentencias.size(); i++) {

            Sentencia sentenciaActiva = this.listaSentencias.get(i);

            for (Lexema lexemaDado : sentenciaActiva.obtenerListadoLexemas()) {

                if (lexemaDado.getLexema().isBlank()) {
                    continue;
                }

                for (Nodo nodo : lexemaDado.obtenerListaNodo()) {

                    Color colorTexto = obtenerColorPorToken(nodo.getToken());

                    insertarToken(String.valueOf(nodo.getCaracter()), colorTexto);

                }
                insertarToken(" ", Color.BLACK);
            }

            insertarToken("\n", Color.BLACK);

        }

        try {

            this.areaAnalisis.setCaretPosition(posicionCaret);

        } catch (Exception e) {
            this.areaAnalisis.setCaretPosition(0);
        }

    }

    // Método que mapea el token a su color
    private Color obtenerColorPorToken(Token tipo) {
        switch (tipo) {
            case PALABRA_RESERVADA:
                return Color.BLUE;
            case IDENTIFICADOR:
                return new Color(0x6B4627);
            case NUMERO:
                return new Color(0x50CC3B);
            case DECIMAL:
                return Color.BLACK;
            case CADENA:
                return new Color(0xF0760E);
            case COMENTARIO_LINEA:
            case COMENTARIO_BLOQUE:
                return new Color(0x1B6615);
            case OPERADOR:
                return new Color(0xC2D106);
            case AGRUPACION:
                return new Color(0x991CB8);
            case ERROR:
                return Color.RED;
            default:
                return new Color(0x737070);
        }
    }

    //Metodo que trabaja en conjunto para poder ir pintando letra a letra
    private void limpiarAreaAnalisis() throws BadLocationException {
        StyledDocument doc = this.areaAnalisis.getStyledDocument();
        doc.remove(0, doc.getLength());

    }

    // Método para insertar texto con un color específico
    private void insertarToken(String texto, Color color) throws BadLocationException {

        StyledDocument doc = this.areaAnalisis.getStyledDocument();
        // Crear estilo temporal
        SimpleAttributeSet estilo = new SimpleAttributeSet();
        StyleConstants.setForeground(estilo, color);
        // Inserta al final del documento
        doc.insertString(doc.getLength(), texto, estilo);

    }

}
