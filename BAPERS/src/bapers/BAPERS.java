/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers;

import bapers.controller.Controller;
import bapers.gui.MainFrame;

/**
 *
 * @author CameronE
 */
public class BAPERS {
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new MainFrame(new Controller()).setVisible(true);
        
    }
    
}
