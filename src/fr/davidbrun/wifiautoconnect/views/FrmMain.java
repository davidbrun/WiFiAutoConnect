/*
 * File:   FrmMain.java
 * Author: David Brun <brundavid@gmail.com>
 *
 * Created on 4 sept. 2011, 16:20:28
 */

package fr.davidbrun.wifiautoconnect.views;

import fr.davidbrun.wifiautoconnect.utils.I18nUtil;
import java.awt.Color;
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
    private JLabel labelProfile;
    private JLabel labelLabelConnectionState;
    private JButton buttonLaunchPause;
    private JButton buttonAutoManual;
    private JComboBox comboBoxProfile;
    private JLabel labelConnectionState;
    private JLabel labelAutoConnectState;
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
        // Build the GUI
        initComponents();
        // Load the texts
        initTextsI18n();
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Private methods">
    
    /**
     * This method is called from within the constructor to initialize the form
     */
    private void initComponents()
    {
        // Set the size of the window
        this.setMinimumSize(new Dimension(450, 404));
        // Get the content pane
        Container pane = this.getContentPane();
        // No layout for the rows: manual placement
        pane.setLayout(null);
        
        // The first row
        JPanel row0 = new JPanel(null);
        // The first row, left column
        JPanel row0_col0 = new JPanel();
        row0_col0.setLayout(new BoxLayout(row0_col0, BoxLayout.Y_AXIS));
        labelProfile = new JLabel("Profil sélectionné :", SwingConstants.CENTER);
	row0_col0.add(labelProfile);
        labelProfile.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelProfile.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        labelProfile.setMinimumSize(new Dimension(215, 20));
        row0_col0.add(Box.createRigidArea(new Dimension(0, 3)));
	comboBoxProfile = new JComboBox();
	row0_col0.add(comboBoxProfile);
        comboBoxProfile.setMaximumSize(new Dimension(155, 24));
        comboBoxProfile.setAlignmentX(Component.CENTER_ALIGNMENT);
        comboBoxProfile.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        // The first row, right column
        JPanel row0_col1 = new JPanel();
        row0_col1.setLayout(new BoxLayout(row0_col1, BoxLayout.Y_AXIS));
        labelLabelConnectionState = new JLabel("État du réseau :", SwingConstants.CENTER);
        row0_col1.add(labelLabelConnectionState);
        labelLabelConnectionState.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelLabelConnectionState.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        labelLabelConnectionState.setMinimumSize(new Dimension(215, 20));
        row0_col1.add(Box.createRigidArea(new Dimension(0, 6)));
        labelConnectionState = new JLabel("Connecté !", SwingConstants.CENTER);
	row0_col1.add(labelConnectionState);
        labelConnectionState.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelConnectionState.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        labelConnectionState.setMinimumSize(new Dimension(215, 20));
        // Add the components in the first row
        row0.add(row0_col0);
        row0.add(row0_col1);
        row0_col0.setSize(new Dimension(215, 75));
        row0_col1.setSize(new Dimension(215, 75));
        row0_col0.setLocation(0, 0);
        row0_col1.setLocation(215, 0);
        
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
        row2_col0.add(Box.createRigidArea(new Dimension(0, 3)));
        labelAutoConnectState = new JLabel("Auto Connect en pause", SwingConstants.CENTER);
        row2_col0.add(labelAutoConnectState);
        labelAutoConnectState.setForeground(Color.GRAY);
        labelAutoConnectState.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelAutoConnectState.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        labelAutoConnectState.setMinimumSize(new Dimension(215, 20));
        // The last row, right column
        JPanel row2_col1 = new JPanel();
        row2_col1.setLayout(new BoxLayout(row2_col1, BoxLayout.Y_AXIS));
	buttonAutoManual = new JButton("Automatique");
	row2_col1.add(buttonAutoManual);
        buttonAutoManual.setMaximumSize(new Dimension(140, 35));
        buttonAutoManual.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonAutoManual.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        row2_col1.add(Box.createRigidArea(new Dimension(0, 3)));
        labelAuthMode = new JLabel("Authentification manuelle", SwingConstants.CENTER);
	row2_col1.add(labelAuthMode);
        labelAuthMode.setForeground(Color.GRAY);
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
        row0.setSize(430, 50);
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
        if (buttonLaunchPause.getText().equalsIgnoreCase(I18nUtil.getInstance().getI18nMsg("fr.davidbrun.wifiautoconnect.views.FrmMain.buttonLaunchPause.Launch")))
        {
            buttonLaunchPause.setText(I18nUtil.getInstance().getI18nMsg("fr.davidbrun.wifiautoconnect.views.FrmMain.buttonLaunchPause.Pause"));
            labelAutoConnectState.setText(I18nUtil.getInstance().getI18nMsg("fr.davidbrun.wifiautoconnect.views.FrmMain.labelAutoConnectState.Launched"));
        }
        else if (buttonLaunchPause.getText().equalsIgnoreCase(I18nUtil.getInstance().getI18nMsg("fr.davidbrun.wifiautoconnect.views.FrmMain.buttonLaunchPause.Pause")))
        {
            buttonLaunchPause.setText(I18nUtil.getInstance().getI18nMsg("fr.davidbrun.wifiautoconnect.views.FrmMain.buttonLaunchPause.Launch"));
            labelAutoConnectState.setText(I18nUtil.getInstance().getI18nMsg("fr.davidbrun.wifiautoconnect.views.FrmMain.labelAutoConnectState.Paused"));
        }
    }
    /**
     * Handle a click on the automatic / manual button
     * 
     * @param evt The mouse event args
     */
    private void buttonAutoManual_MouseClicked(MouseEvent evt)
    {
        if (buttonAutoManual.getText().equalsIgnoreCase(I18nUtil.getInstance().getI18nMsg("fr.davidbrun.wifiautoconnect.views.FrmMain.buttonAutoManual.Auto")))
        {
            buttonAutoManual.setText(I18nUtil.getInstance().getI18nMsg("fr.davidbrun.wifiautoconnect.views.FrmMain.buttonAutoManual.Manual"));
            labelAuthMode.setText(I18nUtil.getInstance().getI18nMsg("fr.davidbrun.wifiautoconnect.views.FrmMain.labelAuthMode.Auto"));
        }
        else if (buttonAutoManual.getText().equalsIgnoreCase(I18nUtil.getInstance().getI18nMsg("fr.davidbrun.wifiautoconnect.views.FrmMain.buttonAutoManual.Manual")))
        {
            buttonAutoManual.setText(I18nUtil.getInstance().getI18nMsg("fr.davidbrun.wifiautoconnect.views.FrmMain.buttonAutoManual.Auto"));
            labelAuthMode.setText(I18nUtil.getInstance().getI18nMsg("fr.davidbrun.wifiautoconnect.views.FrmMain.labelAuthMode.Manual"));
        }
    }
    
    /**
     * This method is called to change the displayed texts depending on the selected locale
     */
    private void initTextsI18n()
    {
        I18nUtil.getInstance().setLocale(this.getLocale().getLanguage(), this.getLocale().getCountry());
//        I18nUtil.getInstance().setLocale("en", "US");
        this.buttonAutoManual.setText(I18nUtil.getInstance().getI18nMsg("fr.davidbrun.wifiautoconnect.views.FrmMain.buttonAutoManual.Manual"));
        this.buttonLaunchPause.setText(I18nUtil.getInstance().getI18nMsg("fr.davidbrun.wifiautoconnect.views.FrmMain.buttonLaunchPause.Pause"));
        this.labelAuthMode.setText(I18nUtil.getInstance().getI18nMsg("fr.davidbrun.wifiautoconnect.views.FrmMain.labelAuthMode.Auto"));
        this.labelAutoConnectState.setText(I18nUtil.getInstance().getI18nMsg("fr.davidbrun.wifiautoconnect.views.FrmMain.labelAutoConnectState.Launched"));
        this.labelConnectionState.setText(I18nUtil.getInstance().getI18nMsg("fr.davidbrun.wifiautoconnect.views.FrmMain.labelConnectionState.Disconnected"));
        this.labelLabelConnectionState.setText(I18nUtil.getInstance().getI18nMsg("fr.davidbrun.wifiautoconnect.views.FrmMain.labelLabelConnectionState"));
        this.labelNotifications.setText(I18nUtil.getInstance().getI18nMsg("fr.davidbrun.wifiautoconnect.views.FrmMain.labelNotifications"));
        this.labelProfile.setText(I18nUtil.getInstance().getI18nMsg("fr.davidbrun.wifiautoconnect.views.FrmMain.labelProfile"));
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