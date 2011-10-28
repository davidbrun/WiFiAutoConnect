/*
 * File:   Loader.java
 * Author: David Brun <brundavid@gmail.com>
 *
 * Created on 28 oct. 2011, 14:31:44
 */

package fr.davidbrun.wifiautoconnect;

import fr.davidbrun.wifiautoconnect.views.FrmMain;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * This class loads the application by creating the main window
 * 
 * @author David Brun <brundavid@gmail.com>
 */
public class Loader
{
    // <editor-fold defaultstate="collapsed" desc="Public methods">
    
    /**
     * The entry point of the application
     * 
     * @param args The command line arguments
     */
    public static void main(String args[])
    {
        // Try to set the system Look and Feel
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e) { }
        
        // Start up the GUI
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new FrmMain().setVisible(true);
            }
        });
    }
    
    // </editor-fold>
}