/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.pablocompany.practicano1lfp;

import com.pablocompany.practicano1lfp.frontend.MenuPrincipal;

/**
 *
 * @author pablo
 */
public class PracticaNo1LFP {

    public static void main(String[] args) {
        
        iniciarAplicacion();
        
    }
    
    //Metodo que se encarga de inicializar la aplicacion
    public static void iniciarAplicacion(){
        MenuPrincipal menu = new MenuPrincipal();
        menu.setVisible(true);
    }
}
