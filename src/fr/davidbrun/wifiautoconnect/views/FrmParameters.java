/*
 * File:   FrmParameters.java
 * Author: David Brun <brundavid@gmail.com>
 *
 * Created on 26 oct. 2011, 23:56:17
 */

package fr.davidbrun.wifiautoconnect.views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 * Represent the parameters window of the application
 * 
 * @author David Brun <brundavid@gmail.com>
 */
public class FrmParameters extends JDialog
{
    // <editor-fold defaultstate="collapsed" desc="Fields">
    
    // Swing components
    JPanel panelMain;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    
    /**
     * Create a new parameters window
     * 
     * @param parent The parent frame of this window
     * @param modal The modality of the dialog window
     */
    public FrmParameters(JFrame parent, boolean modal)
    {
        // The JDialog enable the frame to be modal
        super(parent, modal);
        
        // First make sure the parent frame is visible
        parent.setVisible(true);
        
        // Initialize the SWING components
        this.initComponents(parent);
        this.getContentPane().setVisible(false);
        this.setVisible(false);
        
        // Add listeners to the SWING components
        this.addListeners();
        
        // Center the about box in the main frame
        this.centerFrameInParent(parent);
        
        // Avoid blinks
        this.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowOpened(WindowEvent e)
            {
                getContentPane().setVisible(true);
                setVisible(true);
            }
        });
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Private methods">
    
    /**
     * Initialize the Swing components
     * 
     * @param parent The parent frame of this window
     */
    private void initComponents(JFrame parent)
    {
        // Attributes of the window
        int windowWidth = 500;
        Dimension dimension = new Dimension(windowWidth, 300); // tmp
        
        // Disable the ability to resize the frame
        this.setResizable(false);
        
        // Defaut operation on close
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(parent);
        
        // Create the main panel of the window
        panelMain = new JPanel();
        panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.Y_AXIS));
        
        // Create the tabbed panel
        TabbedPanel tabbedPanel = new TabbedPanel(dimension);
        tabbedPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        tabbedPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelMain.add(tabbedPanel);
        
        // begin tmp
        Dimension d = new Dimension(250, 100);
        JPanel tmp0 = new JPanel();
        tmp0.setBackground(Color.red);
        setSize(tmp0, d);
        
        JPanel tmp1 = new JPanel();
        tmp1.setBackground(Color.green);
        setSize(tmp1, d);
        
        JPanel tmp2 = new JPanel();
        tmp2.setBackground(Color.blue);
        setSize(tmp2, d);
        
        tabbedPanel.addTabPanel("Test 0", new ImageIcon("1.png"), tmp0);
        tabbedPanel.addTabPanel("Test 1", new ImageIcon("2.png"), tmp1);
        tabbedPanel.addTabPanel("Test 2", new ImageIcon("3.png"), tmp2);
        tabbedPanel.selectTabPanel(0);
        // end tmp
        
        // Add swing components
        this.setContentPane(panelMain);
        
        // Update internationalized texts
        this.initTextsI18n();
        
        // Pack before to display
        this.pack();
    }
    
    /**
     * Add listeners to the components
     */
    private void addListeners()
    {
        // Close the frame when typing ESC
        this.panelMain.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Exit");
        this.panelMain.getActionMap().put("Exit", new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                setVisible(false);
            }
        });
    }
    
    /**
     * Center the window in the parent window
     * 
     * @param parent The parent window
     */
    private void centerFrameInParent(JFrame parent)
    {
        // Works also if the about frame is larger than the main frame
        int x = parent.getWidth() / 2 - this.getWidth() / 2 + parent.getX();
        int y = parent.getHeight() / 2 - this.getHeight() / 2 + parent.getY();

        this.setLocation(x, y);
    }
    
    /**
     * This method is called to change the displayed texts depending on the selected locale
     */
    private void initTextsI18n()
    {
        // The locale is already set! (in the main window)
    }
    
    /**
     * Fix the size of a swing component
     * 
     * @param target The component to fix the size
     * @param d The new size of the component
     */
    private void setSize(JComponent target, Dimension d)
    {
        target.setMinimumSize(d);
        target.setMaximumSize(d);
        target.setPreferredSize(d);
        target.setSize(d);
    }
    
    // </editor-fold>
}