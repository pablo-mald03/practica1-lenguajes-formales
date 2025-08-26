/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.pablocompany.practicano1lfp.frontend;

import com.pablocompany.practicano1lfp.backDefrontend.AnalizadorLexicoException;
import com.pablocompany.practicano1lfp.backDefrontend.ColocarFondos;
import com.pablocompany.practicano1lfp.backDefrontend.IlustrarLabels;
import com.pablocompany.practicano1lfp.backend.ConfigDatos;
import com.pablocompany.practicano1lfp.backend.ConfigException;
import com.pablocompany.practicano1lfp.backend.LectorEntradas;
import com.pablocompany.practicano1lfp.backend.ManejadorArchivos;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;

/**
 *
 * @author pablo
 */
public class MenuPrincipal extends javax.swing.JFrame {

    //Variable que permite saber que menu de operaciones se despliegara
    private int gestionVentanas;

    //instancia que permite subir archivos de texto
    private ManejadorArchivos manipuladorDirectorios;
    private LectorEntradas leerEntradas;

    //Atributo que permite saber si el archivo ya fue cargado
    //true si el archivo ya se cargo
    //false si el archivo no se ha cargado
    private boolean yaCargado;

    /**
     * Creates new form MenuPrincipal
     */
    public MenuPrincipal() {
        initComponents();

        this.setLocationRelativeTo(null);

        ColocarFondos pintarPanel = new ColocarFondos(this, this.panelPrincipal);

        pintarPanel.pintarPaneles("/com/pablocompany/practicano1/target/images/overlay2.png");

        ImageIcon icono = new ImageIcon(getClass().getResource("/com/pablocompany/practicano1/target/images/perfildef.png"));

        IlustrarLabels labelPerfil = new IlustrarLabels(this.panelBarraPrincipal, 60, 60, "", this.lblPerfil);
        labelPerfil.cambiarLabel(icono);

        ImageIcon iconoMedio = new ImageIcon(getClass().getResource("/com/pablocompany/practicano1/target/images/insertar.png"));

        IlustrarLabels labelMedio = new IlustrarLabels(this.panelBarraPrincipal, 50, 50, "", this.lblEleccion);
        labelMedio.cambiarLabel(iconoMedio);

        this.txtAreaDirectorioArchivo.setEditable(false);
        this.textLogErrores.setEditable(false);
        this.textEdicionArchivo.setEditable(true);
        this.textEdicionArchivo.setCaretColor(Color.BLACK);

        this.gestionVentanas = 0;
        this.yaCargado = false;

        //Se instancia la clase para poder operar con archivos de texto
        this.manipuladorDirectorios = new ManejadorArchivos();
        try {
            this.leerEntradas = new LectorEntradas();
        } catch (ConfigException ex) {
            System.out.println("Error de Lectura" + ex.getMessage());
        }

    }

    //Metodo que ayuda a poder tomar decisiones en el menu
    public boolean tomarDecision(String mensaje, String Titulo) {
        int opcion = JOptionPane.showConfirmDialog(
                this,
                mensaje,
                Titulo,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        return opcion == JOptionPane.YES_OPTION;
    }

    //========================================REGION DE METODOS UTILIZADOS PARA CADA FUNCIONALIDAD==============================
    //Metodo que se utiliza para manejar todos los componentes y funciones previas al muestreo de busquedas
    public void operarBusquedas() {

    }

    //Metodo que se utiliza para manejar todos los componentes y funciones previas a la configuracion de instrucciones
    public void modificarConfig() {

    }

    //Metodo que se utiliza para manejar todos los componentes y funciones previas a la generacion de reportes
    public void operarReportes() {

    }

    //Metodo que se utiliza para manejar todos los componentes y funciones previas a la generacion de reportes
    public void reestablecerUI() {

    }

    //========================================FIN DE LA REGION DE METODOS UTILIZADOS PARA CADA FUNCIONALIDAD==============================
    //------------------------------------APARTADO DE METODOS QUE SE UTILIZAN PARA DINAMIZAR LA UI---------------------------
    //Metodo que sirve para poder mostrar la seleccion de la busqueda de palabras
    //1 busqueda de palabras
    //2 Edicion del archivo config 
    //3 Visualizacion de reportes
    public void mostrarBusquedas() {

        ImageIcon iconoMedio = new ImageIcon(getClass().getResource("/com/pablocompany/practicano1/target/images/busquedaDatos.png"));

        IlustrarLabels labelMedio = new IlustrarLabels(this.panelBarraPrincipal, 50, 50, "", this.lblEleccion);
        labelMedio.cambiarLabel(iconoMedio);

        this.lblEleccionesDadas.setText("Busqueda de Patrones");

        //Reinicia el permiso para accionar botones
        this.gestionVentanas = 1;

        operarBusquedas();

        this.btnConfig.setBackground(new Color(0x323844));
        this.btnGenerarReportes.setBackground(new Color(0x323844));
        this.btnBusquedaPatrones.setBackground(new Color(0x2DB20C));

    }

    //Metodo que genera la interaccion entre modificar el archivo de configuracion
    public void cambiarConfiguracion() {

        ImageIcon iconoMedio = new ImageIcon(getClass().getResource("/com/pablocompany/practicano1/target/images/subirArchivo.png"));

        IlustrarLabels labelMedio = new IlustrarLabels(this.panelBarraPrincipal, 50, 50, "", this.lblEleccion);
        labelMedio.cambiarLabel(iconoMedio);
        this.lblEleccionesDadas.setText("Editar Configuracion");

        //Reinicia el permiso para accionar botones
        this.gestionVentanas = 2;

        modificarConfig();

        this.btnBusquedaPatrones.setBackground(new Color(0x323844));
        this.btnGenerarReportes.setBackground(new Color(0x323844));
        this.btnConfig.setBackground(new Color(0x2DB20C));

    }

    //Metodo que genera la interaccion entre generar reportes
    public void generarReportes() {

        ImageIcon iconoMedio = new ImageIcon(getClass().getResource("/com/pablocompany/practicano1/target/images/reportes.png"));

        IlustrarLabels labelMedio = new IlustrarLabels(this.panelBarraPrincipal, 50, 50, "", this.lblEleccion);
        labelMedio.cambiarLabel(iconoMedio);
        this.lblEleccionesDadas.setText("Generar Reportes");

        //Reinicia el permiso para accionar botones
        this.gestionVentanas = 3;

        operarReportes();

        this.btnBusquedaPatrones.setBackground(new Color(0x323844));
        this.btnConfig.setBackground(new Color(0x323844));
        this.btnGenerarReportes.setBackground(new Color(0x2DB20C));

    }

    //Metodo que se encarga de regresar a la interfaz inicial
    public void regresarInicio() {

        ImageIcon iconoMedio = new ImageIcon(getClass().getResource("/com/pablocompany/practicano1/target/images/insertar.png"));

        IlustrarLabels labelMedio = new IlustrarLabels(this.panelBarraPrincipal, 50, 50, "", this.lblEleccion);
        labelMedio.cambiarLabel(iconoMedio);
        this.lblEleccionesDadas.setText("Edicion de Archivos");

        //Reinicia el permiso para accionar botones
        this.gestionVentanas = 0;

        reestablecerUI();

        this.btnBusquedaPatrones.setBackground(new Color(0x323844));
        this.btnConfig.setBackground(new Color(0x323844));
        this.btnGenerarReportes.setBackground(new Color(0x323844));

    }

    //------------------------------------FIN DEL APARTADO DE METODOS QUE SE UTILIZAN PARA DINAMIZAR LA UI---------------------------
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        panelBarraPrincipal = new javax.swing.JPanel();
        lblEleccion = new javax.swing.JLabel();
        lblHome = new javax.swing.JLabel();
        lblAdmin = new javax.swing.JLabel();
        lblPerfil = new javax.swing.JLabel();
        lblEleccionesDadas = new javax.swing.JLabel();
        barraLateral = new javax.swing.JPanel();
        btnBusquedaPatrones = new javax.swing.JButton();
        btnGenerarReportes = new javax.swing.JButton();
        btnConfig = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        labelDatos = new javax.swing.JLabel();
        btnSubirArchivo = new javax.swing.JButton();
        lblTitulo = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtAreaDirectorioArchivo = new javax.swing.JTextArea();
        btnQuitarArchivo = new javax.swing.JButton();
        labelOperaciones1 = new javax.swing.JLabel();
        scrollAreaEdicion = new javax.swing.JScrollPane();
        textEdicionArchivo = new javax.swing.JTextPane();
        lblAnalisis = new javax.swing.JLabel();
        btnAnalisis = new javax.swing.JButton();
        lblAnalisis1 = new javax.swing.JLabel();
        scrollAreaEdicion1 = new javax.swing.JScrollPane();
        textLogErrores = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        panelBarraPrincipal.setBackground(new java.awt.Color(50, 56, 68));
        panelBarraPrincipal.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        lblHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/pablocompany/practicano1/target/images/home2.png"))); // NOI18N
        lblHome.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHomeMouseClicked(evt);
            }
        });

        lblAdmin.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        lblAdmin.setForeground(new java.awt.Color(255, 255, 255));
        lblAdmin.setText("Usuario");

        lblEleccionesDadas.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        lblEleccionesDadas.setForeground(new java.awt.Color(255, 255, 255));
        lblEleccionesDadas.setText("Edicion de Archivos");

        javax.swing.GroupLayout panelBarraPrincipalLayout = new javax.swing.GroupLayout(panelBarraPrincipal);
        panelBarraPrincipal.setLayout(panelBarraPrincipalLayout);
        panelBarraPrincipalLayout.setHorizontalGroup(
            panelBarraPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBarraPrincipalLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lblPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAdmin)
                .addGap(472, 472, 472)
                .addComponent(lblEleccion, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEleccionesDadas, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblHome)
                .addGap(24, 24, 24))
        );
        panelBarraPrincipalLayout.setVerticalGroup(
            panelBarraPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBarraPrincipalLayout.createSequentialGroup()
                .addGroup(panelBarraPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelBarraPrincipalLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelBarraPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelBarraPrincipalLayout.createSequentialGroup()
                                .addComponent(lblEleccion, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelBarraPrincipalLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(panelBarraPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEleccionesDadas, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelBarraPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblHome)
                                .addGroup(panelBarraPrincipalLayout.createSequentialGroup()
                                    .addComponent(lblAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(12, 12, 12))))))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        barraLateral.setBackground(new java.awt.Color(50, 56, 68));
        barraLateral.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        barraLateral.setForeground(new java.awt.Color(0, 0, 0));

        btnBusquedaPatrones.setBackground(new java.awt.Color(50, 56, 68));
        btnBusquedaPatrones.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        btnBusquedaPatrones.setForeground(new java.awt.Color(255, 255, 255));
        btnBusquedaPatrones.setText("Busqueda de Patrones");
        btnBusquedaPatrones.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(255, 255, 255)));
        btnBusquedaPatrones.setFocusable(false);
        btnBusquedaPatrones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBusquedaPatronesActionPerformed(evt);
            }
        });

        btnGenerarReportes.setBackground(new java.awt.Color(50, 56, 68));
        btnGenerarReportes.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        btnGenerarReportes.setForeground(new java.awt.Color(255, 255, 255));
        btnGenerarReportes.setText("Generar Reportes");
        btnGenerarReportes.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(255, 255, 255)));
        btnGenerarReportes.setFocusable(false);
        btnGenerarReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarReportesActionPerformed(evt);
            }
        });

        btnConfig.setBackground(new java.awt.Color(50, 56, 68));
        btnConfig.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        btnConfig.setForeground(new java.awt.Color(255, 255, 255));
        btnConfig.setText("Editar Configuracion");
        btnConfig.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(255, 255, 255)));
        btnConfig.setFocusable(false);
        btnConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfigActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout barraLateralLayout = new javax.swing.GroupLayout(barraLateral);
        barraLateral.setLayout(barraLateralLayout);
        barraLateralLayout.setHorizontalGroup(
            barraLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(barraLateralLayout.createSequentialGroup()
                .addGap(294, 294, 294)
                .addComponent(btnBusquedaPatrones, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnConfig, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnGenerarReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        barraLateralLayout.setVerticalGroup(
            barraLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(barraLateralLayout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addGroup(barraLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBusquedaPatrones, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConfig, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGenerarReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(50, 56, 68)));
        jPanel1.setOpaque(false);

        labelDatos.setFont(new java.awt.Font("Liberation Sans", 1, 32)); // NOI18N
        labelDatos.setForeground(new java.awt.Color(83, 31, 11));
        labelDatos.setText("Requisitos de carga de archivos:");

        btnSubirArchivo.setBackground(new java.awt.Color(48, 148, 92));
        btnSubirArchivo.setFont(new java.awt.Font("Liberation Sans", 1, 22)); // NOI18N
        btnSubirArchivo.setForeground(new java.awt.Color(255, 255, 255));
        btnSubirArchivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/pablocompany/practicano1/target/images/importFile.png"))); // NOI18N
        btnSubirArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubirArchivoActionPerformed(evt);
            }
        });

        lblTitulo.setFont(new java.awt.Font("Liberation Sans", 1, 28)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(42, 48, 60));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitulo.setText("Directorio Archivo:");

        txtAreaDirectorioArchivo.setColumns(20);
        txtAreaDirectorioArchivo.setFont(new java.awt.Font("Liberation Sans", 0, 26)); // NOI18N
        txtAreaDirectorioArchivo.setRows(5);
        jScrollPane3.setViewportView(txtAreaDirectorioArchivo);

        btnQuitarArchivo.setBackground(new java.awt.Color(148, 47, 47));
        btnQuitarArchivo.setFont(new java.awt.Font("Liberation Sans", 1, 22)); // NOI18N
        btnQuitarArchivo.setForeground(new java.awt.Color(255, 255, 255));
        btnQuitarArchivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/pablocompany/practicano1/target/images/removerArchivo.png"))); // NOI18N
        btnQuitarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarArchivoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(labelDatos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(12, 12, 12)
                        .addComponent(btnSubirArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnQuitarArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelDatos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnQuitarArchivo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(btnSubirArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        labelOperaciones1.setFont(new java.awt.Font("Liberation Sans", 1, 30)); // NOI18N
        labelOperaciones1.setForeground(new java.awt.Color(83, 31, 11));
        labelOperaciones1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelOperaciones1.setText("Archivo de entrada:");

        textEdicionArchivo.setBackground(new java.awt.Color(228, 228, 228));
        textEdicionArchivo.setFont(new java.awt.Font("Liberation Serif", 1, 20)); // NOI18N
        textEdicionArchivo.setForeground(new java.awt.Color(115, 112, 112));
        textEdicionArchivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textEdicionArchivoKeyReleased(evt);
            }
        });
        scrollAreaEdicion.setViewportView(textEdicionArchivo);

        lblAnalisis.setFont(new java.awt.Font("Liberation Sans", 1, 32)); // NOI18N
        lblAnalisis.setForeground(new java.awt.Color(83, 31, 11));
        lblAnalisis.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAnalisis.setText("Analizar Manualmente:");

        btnAnalisis.setBackground(new java.awt.Color(46, 136, 80));
        btnAnalisis.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        btnAnalisis.setText("Analizar Texto");
        btnAnalisis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalisisActionPerformed(evt);
            }
        });

        lblAnalisis1.setFont(new java.awt.Font("Liberation Sans", 1, 32)); // NOI18N
        lblAnalisis1.setForeground(new java.awt.Color(83, 31, 11));
        lblAnalisis1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAnalisis1.setText("Errores Encontrados:");

        textLogErrores.setBackground(new java.awt.Color(228, 228, 228));
        textLogErrores.setFont(new java.awt.Font("Liberation Serif", 0, 20)); // NOI18N
        textLogErrores.setForeground(new java.awt.Color(140, 1, 25));
        textLogErrores.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textLogErroresKeyReleased(evt);
            }
        });
        scrollAreaEdicion1.setViewportView(textLogErrores);

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBarraPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelPrincipalLayout.createSequentialGroup()
                                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(lblAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, 658, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                                        .addGap(235, 235, 235)
                                        .addComponent(btnAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(lblAnalisis1, javax.swing.GroupLayout.PREFERRED_SIZE, 662, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 6, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipalLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrollAreaEdicion1)))
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(labelOperaciones1, javax.swing.GroupLayout.PREFERRED_SIZE, 861, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(scrollAreaEdicion, javax.swing.GroupLayout.PREFERRED_SIZE, 857, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addComponent(barraLateral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addComponent(panelBarraPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(lblAnalisis)
                        .addGap(12, 12, 12)
                        .addComponent(btnAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblAnalisis1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scrollAreaEdicion1, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addComponent(labelOperaciones1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scrollAreaEdicion, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(barraLateral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(panelPrincipal, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHomeMouseClicked
        //Reinicia la GUI AL INICIO
        regresarInicio();
    }//GEN-LAST:event_lblHomeMouseClicked

    private void btnBusquedaPatronesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusquedaPatronesActionPerformed
        //Lleva a la opcion de buscar palabras
        mostrarBusquedas();
    }//GEN-LAST:event_btnBusquedaPatronesActionPerformed

    private void btnGenerarReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarReportesActionPerformed
        //Boton que permite generar los reportes
        generarReportes();


    }//GEN-LAST:event_btnGenerarReportesActionPerformed

    private void btnConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfigActionPerformed
        //Boton que despliega las opciones para editar el config
        cambiarConfiguracion();

    }//GEN-LAST:event_btnConfigActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        //Codiciona que se debe de confirmar si se quiere cerrar la aplicacion
        if (tomarDecision("Esta seguro que desea salir de la aplicacion?", "Salir de la aplicacion")) {
            System.exit(0);
        }
    }//GEN-LAST:event_formWindowClosing

    private void btnSubirArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubirArchivoActionPerformed

        try {
            //Ejecuta la accion para Elegir el archivo

            if (this.manipuladorDirectorios.elegirArchivoEntrada()) {

                ArrayList<String> listaObtenida = this.manipuladorDirectorios.convertirEntrada();

                this.leerEntradas.transformarTexto(this.textEdicionArchivo.getText(), this.textEdicionArchivo);

                //PENDIENTE REMOVER
                //this.leerEntradas.imprimirLog(listaObtenida, this.textEdicionArchivo);

                this.leerEntradas.setLista(listaObtenida, this.textEdicionArchivo);

                this.txtAreaDirectorioArchivo.setText(this.manipuladorDirectorios.getPath());
                this.yaCargado = true;

                this.leerEntradas.analizarEntradas(this.textEdicionArchivo, this.textLogErrores);

            }

        } catch (BadLocationException | AnalizadorLexicoException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error de Ejecucion", JOptionPane.ERROR_MESSAGE);
        } catch (ConfigException ex) {
           JOptionPane.showMessageDialog(this, ex.getMessage(), "Error de Carga", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_btnSubirArchivoActionPerformed

    private void btnQuitarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarArchivoActionPerformed
        //Permite cerrar el archivo que ya fue editado durante el proceso guardando los datos
        if (this.yaCargado) {

            if (tomarDecision("Deseas cerrar este archivo cargado\nSe guardaran todos los cambios hechos", "Confirmar cierre")) {

                try {
                    String directorio = this.manipuladorDirectorios.getPath();

                    this.leerEntradas.transformarTexto(this.textEdicionArchivo.getText(), this.textEdicionArchivo);

                    ArrayList<String> lista = this.leerEntradas.getListado();

                    this.manipuladorDirectorios.guardarArchivo(directorio, lista);

                    this.txtAreaDirectorioArchivo.setText("");
                    this.manipuladorDirectorios.reiniciarPath();
                    this.textEdicionArchivo.setText("");
                    this.yaCargado = false;

                } catch (AnalizadorLexicoException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error de Guardado", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Todavia no has cargado ningun archivo para poder cerrarlo", "No hay ningun archivo cargado aun", JOptionPane.INFORMATION_MESSAGE);
        }


    }//GEN-LAST:event_btnQuitarArchivoActionPerformed

    private void textEdicionArchivoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textEdicionArchivoKeyReleased

        int code = evt.getKeyCode();
        if (code == KeyEvent.VK_CONTROL || code == KeyEvent.VK_SHIFT
                || code == KeyEvent.VK_ALT
                || code == KeyEvent.VK_LEFT || code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_UP || code == KeyEvent.VK_DOWN
                || code == KeyEvent.VK_TAB || code == KeyEvent.VK_CAPS_LOCK) {
            return; // ignorar estas teclas
        }
        
        if(this.textEdicionArchivo.getText().isBlank()){
            return;
        }

        try {
            //Detecta cada vez que se cambia una palabra
            this.leerEntradas.transformarTexto(this.textEdicionArchivo.getText(), this.textEdicionArchivo);
            this.leerEntradas.analizarEntradas(this.textEdicionArchivo,this.textLogErrores);

        } catch (AnalizadorLexicoException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error de Ejecucion", JOptionPane.ERROR_MESSAGE);
        } catch (ConfigException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error de Carga", JOptionPane.ERROR_MESSAGE);
        } catch (BadLocationException ex) {
             JOptionPane.showMessageDialog(this, ex.getMessage(), "Error de pintado", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_textEdicionArchivoKeyReleased

    private void btnAnalisisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalisisActionPerformed
        //Testing para mientras
        ConfigDatos configurar = new ConfigDatos();
        try {
            configurar.cargarDesdeJson();
        } catch (ConfigException ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }//GEN-LAST:event_btnAnalisisActionPerformed

    private void textLogErroresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textLogErroresKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_textLogErroresKeyReleased

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel barraLateral;
    private javax.swing.JButton btnAnalisis;
    private javax.swing.JButton btnBusquedaPatrones;
    private javax.swing.JButton btnConfig;
    private javax.swing.JButton btnGenerarReportes;
    private javax.swing.JButton btnQuitarArchivo;
    private javax.swing.JButton btnSubirArchivo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labelDatos;
    private javax.swing.JLabel labelOperaciones1;
    private javax.swing.JLabel lblAdmin;
    private javax.swing.JLabel lblAnalisis;
    private javax.swing.JLabel lblAnalisis1;
    private javax.swing.JLabel lblEleccion;
    private javax.swing.JLabel lblEleccionesDadas;
    private javax.swing.JLabel lblHome;
    private javax.swing.JLabel lblPerfil;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelBarraPrincipal;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JScrollPane scrollAreaEdicion;
    private javax.swing.JScrollPane scrollAreaEdicion1;
    private javax.swing.JTextPane textEdicionArchivo;
    private javax.swing.JTextPane textLogErrores;
    private javax.swing.JTextArea txtAreaDirectorioArchivo;
    // End of variables declaration//GEN-END:variables
}
