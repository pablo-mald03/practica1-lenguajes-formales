/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pablocompany.practicano1lfp.backend;

import com.pablocompany.practicano1lfp.backDefrontend.AnalizadorLexico;
import com.pablocompany.practicano1lfp.backDefrontend.CrearTableros;
import com.pablocompany.practicano1lfp.backDefrontend.ModificarTabla;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;

/**
 *
 * @author pablo
 */
public class GenerarReportes {

    //------------------APARTADO DE MANEJO DE DIRECTORIOS--------------
    private File guardarArchivo;

    private File directorioArchivo;

    private String pathDefinitivo = "Reportes" + File.separator;

    private final String PATH_PREDETERMINADO = "Reportes" + File.separator;
    //------------------FIN DEL APARTADO DE MANEJO DE DIRECTORIOS--------------

    //------------------APARTADO DE MANEJO DE ATRIBUTOS DEL REPORTE--------------
    //Atributo booleano para saber si hay errores
    //true si hay errores
    private boolean hayErrores;

    //Lista de errores 
    private ArrayList<String> listaErrores = new ArrayList<>(5000);

    //------------------FIN DEL APARTADO DE MANEJO DE ATRIBUTOS DEL REPORTE--------------
    public GenerarReportes() {

        this.hayErrores = false;
        setPathPredeterminado();
    }

    //Metodo que retorna si el directorio predeterminado existe 
    //False no existe
    public boolean directorioExiste() {
        return this.directorioArchivo.exists();
    }

    //Metodo util para reestablecer el directorio predeterminado
    public final void setPathPredeterminado() {

        File folder = new File("Reportes");
        if (!folder.exists()) {
            folder.mkdirs();
        }

        this.guardarArchivo = new File(PATH_PREDETERMINADO);
        this.directorioArchivo = folder;
        this.pathDefinitivo = PATH_PREDETERMINADO;

    }

    //Metodo util para poder mostrar los errores en pantalla en la tabla 
    public void generarReporteGeneral(ArrayList<Sentencia> sentenciasListado, JLabel labelCantidadError, JLabel labelPorcentaje, JTextArea cantidadTokensArea) throws ErrorPuntualException {

        //Solo se encarga de actualizar el estado de errores y de paso lo cuenta
        int cantidadErrores = 0;
        for (Sentencia sentencia : sentenciasListado) {

            for (int i = 0; i < sentencia.limiteLexemas(); i++) {

                Lexema lexemaEvaluado = sentencia.getListaLexema(i);
                if (!lexemaEvaluado.getCadenaError().isBlank()) {
                    if (!this.hayErrores) {
                        this.hayErrores = true;
                    }

                    cantidadErrores++;
                }

            }

        }

        labelCantidadError.setText("Cantidad de Errores: " + cantidadErrores);

        ArrayList<String> listaTokens = new ArrayList<>();
        listaTokens.add(Token.IDENTIFICADOR.getTipo());
        listaTokens.add(Token.NUMERO.getTipo());
        listaTokens.add(Token.DECIMAL.getTipo());
        listaTokens.add(Token.CADENA.getTipo());
        listaTokens.add(Token.PALABRA_RESERVADA.getTipo());
        listaTokens.add(Token.PUNTUACION.getTipo());
        listaTokens.add(Token.OPERADOR.getTipo());
        listaTokens.add(Token.AGRUPACION.getTipo());
        listaTokens.add(Token.COMENTARIO_LINEA.getTipo());
        listaTokens.add(Token.COMENTARIO_BLOQUE.getTipo());
        listaTokens.add(Token.ERROR.getTipo());

        //Cuenta todos los lexemas escritos
        int lexemasEncontrados = 0;

        //Cuenta todos los errores encontrados
        int erroresEncontrados = 0;

        for (Sentencia sentencia : sentenciasListado) {

            for (int i = 0; i < sentencia.limiteLexemas(); i++) {

                Lexema lexemaEvaluado = sentencia.getListaLexema(i);

                lexemasEncontrados++;

                if (!lexemaEvaluado.getCadenaError().isBlank()) {

                    erroresEncontrados++;
                }

                for (Nodo nodoRecorrido : lexemaEvaluado.obtenerListaNodo()) {

                    if (listaTokens.contains(nodoRecorrido.getToken().getTipo())) {

                        listaTokens.remove(nodoRecorrido.getToken().getTipo());

                    }

                }

            }

        }

        int totalValidos = lexemasEncontrados - erroresEncontrados;
        double porcentajeCalificacion = (totalValidos * 100.0) / lexemasEncontrados;

        labelPorcentaje.setText("Porcentaje Tokens Validos: " + Math.floor(porcentajeCalificacion) + "%");

        cantidadTokensArea.setText("");

        if (listaTokens.isEmpty()) {
            cantidadTokensArea.setText("Tokens no utilizados: SE HAN UTILIZADO TODOS LOS TOKENS");
        }else{
            
            cantidadTokensArea.setText("Tokens no utilizados: ");
            
            for (String listaToken : listaTokens) {
                cantidadTokensArea.setText(cantidadTokensArea.getText() + " | "+listaToken);
                
            }
            
        }

    }

    //Metodo util para poder mostrar los errores en pantalla en la tabla 
    public void generarReporteErrores(ArrayList<Sentencia> sentenciasListado, ModificarTabla modificarTabla, CrearTableros crearTablero) throws ErrorPuntualException {

        for (Sentencia sentencia : sentenciasListado) {

            for (int i = 0; i < sentencia.limiteLexemas(); i++) {

                Lexema lexemaEvaluado = sentencia.getListaLexema(i);
                if (!lexemaEvaluado.getCadenaError().isBlank()) {
                    this.hayErrores = true;
                    break;
                }

            }

            if (this.hayErrores) {
                break;
            }

        }

        if (!this.hayErrores) {
            throw new ErrorPuntualException("No hay ningun error registrado en el analisis");
        }

        if (!this.listaErrores.isEmpty()) {
            this.listaErrores.clear();
        }

        crearTablero.vaciarTablero();

        for (Sentencia sentencia : sentenciasListado) {

            for (int i = 0; i < sentencia.limiteLexemas(); i++) {

                Lexema lexemaEvaluado = sentencia.getListaLexema(i);

                if (!lexemaEvaluado.getCadenaError().isBlank()) {

                    this.listaErrores.add(lexemaEvaluado.getCadenaError());

                    String coordenada = "(";
                    coordenada += String.valueOf(lexemaEvaluado.getFilaCoordenada()) + " - ";

                    int columnaTope = 0;

                    for (Nodo nodoRecorrido : lexemaEvaluado.obtenerListaNodo()) {

                        if (nodoRecorrido.getToken() != Token.ERROR) {
                            break;
                        }

                        columnaTope = nodoRecorrido.getColumna();

                    }

                    coordenada += String.valueOf(columnaTope) + ")";

                    this.listaErrores.add(coordenada);

                }

            }

        }

        if (this.listaErrores.isEmpty()) {
            throw new ErrorPuntualException("No se han encontrado errores");
        }

        String[] titulos = {"Cadena de Error", "Posicion"};
        crearTablero.tableroConTitulo(titulos, this.listaErrores.size() / 2, 2, true);
        modificarTabla.reendereizarTablero();

        int iterador = 0;

        for (int i = 0; i < this.listaErrores.size(); i += 2) {

            String simbolo = this.listaErrores.get(i);
            String posString = this.listaErrores.get(i + 1);

            modificarTabla.colocarTextos(iterador, 0, simbolo);
            modificarTabla.colocarTextos(iterador, 1, posString);
            iterador++;
        }

    }

    //Metodo que permite comunicar a la UI con la interaccion para generar reporte de errores
    public void generarReporteErrores() throws ErrorPuntualException {
        reportarErroresCSV(this.listaErrores, "ReporteErrores", "Cadena_Error,Posicion");
    }

    //Metodo que permite exportar .csv de los errores
    public void reportarErroresCSV(ArrayList<String> lista, String nombreArchivo, String headersArchivo) throws ErrorPuntualException {

        if (!this.hayErrores) {
            throw new ErrorPuntualException("No hay ningun error registrado en el analisis");
        }

        if (this.listaErrores.isEmpty()) {
            throw new ErrorPuntualException("No hay reporte de errores cargado aun\nGenere primero el reporte para poder exportarlo");
        }

        if (!directorioExiste()) {
            setPathPredeterminado();
        }

        //Se genera la hora de exportacion para evitar duplicados
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String fechaHora = ahora.format(formatter);

        try (FileWriter writer = new FileWriter(this.pathDefinitivo + nombreArchivo + "_" + fechaHora + ".csv")) {
            writer.append(headersArchivo + "\n");

            for (int i = 0; i < lista.size(); i += 2) {
                String campo1 = lista.get(i);
                String campo2 = lista.get(i + 1);

                writer.append(campo1).append(",")
                        .append(campo2).append(",")
                        .append("\n");

            }

        } catch (IOException e) {
            throw new ErrorPuntualException("No se ha podido exportar el reporte" + e.getMessage());
        }
    }

}
