/*
 * File:   FrmMain.java
 * Author: David Brun <brundavid@gmail.com>
 *
 * Created on 4 sept. 2011, 16:20:28
 */

package fr.davidbrun.wifiautoconnect.views;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Represent the main window of the application
 * 
 * @author David Brun <brundavid@gmail.com>
 */
public class FrmMain extends javax.swing.JFrame
{
    // <editor-fold defaultstate="collapsed" desc="Fields">
    
    // Swing components
    private JButton buttonLaunchPause;
    private JButton buttonAutoManual;
    private JComboBox comboBoxProfile;
    private JLabel labelConnectionState;
    private JLabel labelAuthState;
    private JLabel labelAuthMode;
    private WiFiAnimatedIcon wifiAnimatedIcon;
    private JLabel labelNotifications;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    
    /**
     * Create a new main window
     */
    public FrmMain()
    {
        initComponents();
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Private methods">
    
    /**
     * This method is called from within the constructor to initialize the form
     */
    private void initComponents()
    {
        // Set the size of the window
        this.setMinimumSize(new Dimension(450, 410));
        // Get the content pane
        Container pane = this.getContentPane();
        // No layout for the rows: manual placement
        pane.setLayout(null);
        
        // The first row
        JPanel row0 = new JPanel(new BorderLayout());
        // The first row, left column
        JPanel row0_col0 = new JPanel();
        row0_col0.setLayout(new BoxLayout(row0_col0, BoxLayout.Y_AXIS));
        JLabel label1 = new JLabel("Profil sélectionné :");
	row0_col0.add(label1);
        label1.setAlignmentX(Component.CENTER_ALIGNMENT);
        label1.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        row0_col0.add(Box.createRigidArea(new Dimension(0, 3)));
	comboBoxProfile = new JComboBox();
	row0_col0.add(comboBoxProfile);
        comboBoxProfile.setMaximumSize(new Dimension(165, 24));
        comboBoxProfile.setAlignmentX(Component.CENTER_ALIGNMENT);
        comboBoxProfile.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        // The first row, right column
        JPanel row0_col1 = new JPanel();
        row0_col1.setLayout(new BoxLayout(row0_col1, BoxLayout.Y_AXIS));
        JLabel label2 = new JLabel("État du réseau :");
        row0_col1.add(label2);
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);
        label2.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        row0_col1.add(Box.createRigidArea(new Dimension(0, 6)));
        labelConnectionState = new JLabel("Connecté !");
	row0_col1.add(labelConnectionState);
        labelConnectionState.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelConnectionState.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        // Add the components in the first row
        row0.add(row0_col0, BorderLayout.LINE_START);
        row0.add(row0_col1, BorderLayout.LINE_END);
        
        // The middle row
        JPanel row1 = new JPanel();
        row1.setLayout(new BoxLayout(row1, BoxLayout.Y_AXIS));
	wifiAnimatedIcon = new WiFiAnimatedIcon(200, this.getBackground());
        wifiAnimatedIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        wifiAnimatedIcon.setAlignmentY(Component.TOP_ALIGNMENT);
	row1.add(wifiAnimatedIcon);
        labelNotifications = new JLabel("Notifications ici");
        labelNotifications.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelNotifications.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        row1.add(labelNotifications);
        
        // The last row
        JPanel row2 = new JPanel(null);
        // The last row, left column
        JPanel row2_col0 = new JPanel();
        row2_col0.setLayout(new BoxLayout(row2_col0, BoxLayout.Y_AXIS));
        buttonLaunchPause = new JButton("Marche");
	row2_col0.add(buttonLaunchPause);
        buttonLaunchPause.setMaximumSize(new Dimension(140, 35));
        buttonLaunchPause.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonLaunchPause.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        row2_col0.add(Box.createRigidArea(new Dimension(0, 5)));
        labelAuthState = new JLabel("Auto Connect en pause", SwingConstants.CENTER);
        row2_col0.add(labelAuthState);
        labelAuthState.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelAuthState.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        labelAuthState.setMinimumSize(new Dimension(215, 20));
        // The last row, right column
        JPanel row2_col1 = new JPanel();
        row2_col1.setLayout(new BoxLayout(row2_col1, BoxLayout.Y_AXIS));
	buttonAutoManual = new JButton("Automatique");
	row2_col1.add(buttonAutoManual);
        buttonAutoManual.setMaximumSize(new Dimension(140, 35));
        buttonAutoManual.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonAutoManual.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        row2_col1.add(Box.createRigidArea(new Dimension(0, 5)));
        labelAuthMode = new JLabel("Authentification manuelle", SwingConstants.CENTER);
	row2_col1.add(labelAuthMode);
        labelAuthMode.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelAuthMode.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        labelAuthMode.setMinimumSize(new Dimension(215, 20));
        // Add the components in the last row
        row2.add(row2_col0);
        row2.add(row2_col1);
        row2_col0.setSize(new Dimension(215, 75));
        row2_col1.setSize(new Dimension(215, 75));
        row2_col0.setLocation(0, 0);
        row2_col1.setLocation(215, 0);
        
        // Add the rows to the content pane
        pane.add(row0);
        pane.add(row1);
        pane.add(row2);
        // Manually size the rows
        row0.setSize(330, 50);
        row1.setSize(wifiAnimatedIcon.getWidth(), wifiAnimatedIcon.getHeight() + 15);
        row2.setSize(430, 75);
        // Manually place the rows
        row0.setLocation((this.getWidth() - row0.getWidth()) / 2, 15);
        row1.setLocation((this.getWidth() - row1.getWidth()) / 2 - 2, 80);
        row2.setLocation((this.getWidth() - row2.getWidth()) / 2, wifiAnimatedIcon.getHeight() + 115);
        
        // Set the other properties of the window
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("WiFi Auto Connect");
        this.setResizable(false);
        
        // Pack before to display
        this.pack();
        
        // Add event listeners
        buttonLaunchPause.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent evt)
            {
                buttonLaunchPause_MouseClicked(evt);
            }
        });
        buttonAutoManual.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent evt)
            {
                buttonAutoManual_MouseClicked(evt);
            }
        });
        
        // Center the window on the screen
        this.setLocationRelativeTo(null);
    }
    
    /**
     * Handle a click on the launch / pause button
     * 
     * @param evt The mouse event args
     */
    private void buttonLaunchPause_MouseClicked(MouseEvent evt)
    {
        if (buttonLaunchPause.getText().equalsIgnoreCase("Marche"))
        {
            buttonLaunchPause.setText("Pause");
            labelAuthState.setText("Auto Connect en marche");
        }
        else if (buttonLaunchPause.getText().equalsIgnoreCase("Pause"))
        {
            buttonLaunchPause.setText("Marche");
            labelAuthState.setText("Auto Connect en pause");
        }
    }
    /**
     * Handle a click on the automatic / manual button
     * 
     * @param evt The mouse event args
     */
    private void buttonAutoManual_MouseClicked(MouseEvent evt)
    {
        if (buttonAutoManual.getText().equalsIgnoreCase("Automatique"))
        {
            buttonAutoManual.setText("Manuel");
            labelAuthMode.setText("Authentification automatique");
        }
        else if (buttonAutoManual.getText().equalsIgnoreCase("Manuel"))
        {
            buttonAutoManual.setText("Automatique");
            labelAuthMode.setText("Authentification manuelle");
        }
    }
    
    // </editor-fold>
    
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