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
        // show the jframe window, provide a new controller object
        new MainFrame(new Controller()).setVisible(true);
        /**
         *  Logins:
         * user id       password       role
         *     54           1           OM
         *     55           2           SM
         *     56           3           R
         *     57           4           T
         */
    }
    
}
