/*
 * File:   FrmMain.java
 * Author: David Brun <brundavid@gmail.com>
 *
 * Created on 4 sept. 2011, 16:20:28
 */

package fr.davidbrun.wifiautoconnect.views;

import fr.davidbrun.wifiautoconnect.utils.I18nUtil;
import fr.davidbrun.wifiautoconnect.utils.OSUtil;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
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
    private JLabel labelLabelProfile;
    private JLabel labelLabelConnectionState;
    private JButton buttonLaunchPause;
    private JButton buttonAutoManual;
    private JLabel labelProfile;
    private JLabel labelConnectionState;
    private JLabel labelAutoConnectState;
    private JLabel labelAuthMode;
    private WiFiAnimatedIcon wifiAnimatedIcon;
    private JLabel labelNotifications;
    // Menu bar
    private JMenuBar menuBar;
    private JMenu menuFile;
    private JMenuItem menuFile_Parameters;
    private JMenuItem menuFile_Quit;
    private JMenu menuProfiles;
    private JMenu menuHelp;
    private JMenuItem menuHelp_Update;
    private JMenuItem menuHelp_Doc;
    private JMenuItem menuHelp_FAQ;
    private JMenuItem menuHelp_About;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    
    /**
     * Create a new main window
     */
    public FrmMain()
    {
        //Displays the menu bar on the top of the screen (mac style)
        System.setProperty("apple.laf.useScreenMenuBar", "true");
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
        this.setMinimumSize(new Dimension(450, (!OSUtil.IS_MAC ? 420 : 395)));
        // Get the content pane
        Container pane = this.getContentPane();
        // No layout for the rows: manual placement
        pane.setLayout(null);
        
        // The first row
        JPanel row0 = new JPanel(null);
        // The first row, left column
        JPanel row0_col0 = new JPanel();
        row0_col0.setLayout(new BoxLayout(row0_col0, BoxLayout.Y_AXIS));
        labelLabelProfile = new JLabel("Profil sélectionné :", SwingConstants.CENTER);
	row0_col0.add(labelLabelProfile);
        labelLabelProfile.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelLabelProfile.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        labelLabelProfile.setMinimumSize(new Dimension(215, 20));
        row0_col0.add(Box.createRigidArea(new Dimension(0, 4)));
	labelProfile = new JLabel("SFR WiFi Public", SwingConstants.CENTER);
	row0_col0.add(labelProfile);
        labelProfile.setMaximumSize(new Dimension(215, 20));
        labelProfile.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelProfile.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        // The first row, right column
        JPanel row0_col1 = new JPanel();
        row0_col1.setLayout(new BoxLayout(row0_col1, BoxLayout.Y_AXIS));
        labelLabelConnectionState = new JLabel("État du réseau :", SwingConstants.CENTER);
        row0_col1.add(labelLabelConnectionState);
        labelLabelConnectionState.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelLabelConnectionState.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        labelLabelConnectionState.setMinimumSize(new Dimension(215, 20));
        row0_col1.add(Box.createRigidArea(new Dimension(0, 4)));
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
        row1.setLocation((this.getWidth() - row1.getWidth()) / 2 - 2, 75);
        row2.setLocation((this.getWidth() - row2.getWidth()) / 2, wifiAnimatedIcon.getHeight() + 110);
        
        // Create the menu
        this.menuFile = new JMenu("Fichier");
        this.menuProfiles = new JMenu("Profils");
        this.menuHelp = new JMenu("Aide");
        this.menuFile_Parameters = new JMenuItem("Paramètres");
        this.menuFile_Quit = new JMenuItem("Quitter");
        this.menuFile.add(this.menuFile_Parameters);
        this.menuFile.add(new JSeparator());
        this.menuFile.add(this.menuFile_Quit);
        this.menuHelp_Update = new JMenuItem("Vérifier les mises à jour");
        this.menuHelp_Doc = new JMenuItem("Documentation");
        this.menuHelp_FAQ = new JMenuItem("FAQ");
        this.menuHelp_About = new JMenuItem("À propos de WiFi Auto Connect");
        this.menuHelp.add(this.menuHelp_Update);
        this.menuHelp.add(new JSeparator());
        this.menuHelp.add(this.menuHelp_Doc);
        this.menuHelp.add(this.menuHelp_FAQ);
        if (!OSUtil.IS_MAC) // On mac os, the About menu is present in the application menu
        {
            this.menuHelp.add(new JSeparator());
            this.menuHelp.add(this.menuHelp_About);
        }
        this.menuBar = new JMenuBar();
        if (!OSUtil.IS_MAC) // On mac os, Preferences & Quit are already present in the application menu
            this.menuBar.add(this.menuFile);
        this.menuBar.add(this.menuProfiles);
        this.menuBar.add(this.menuHelp);
        
        // Add shortcuts (mnemonics are added when we load texts for internationalization)
        this.menuFile_Quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        this.menuHelp_Doc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
        // Add the menu bar
        this.setJMenuBar(this.menuBar);
        
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
//        I18nUtil.getInstance().setLocale(this.getLocale().getLanguage(), this.getLocale().getCountry());
//        I18nUtil.getInstance().setLocale("en", "US");
        I18nUtil.getInstance().setLocale("fr", "FR");
        this.buttonAutoManual.setText(I18nUtil.getInstance().getI18nMsg("fr.davidbrun.wifiautoconnect.views.FrmMain.buttonAutoManual.Manual"));
        this.buttonLaunchPause.setText(I18nUtil.getInstance().getI18nMsg("fr.davidbrun.wifiautoconnect.views.FrmMain.buttonLaunchPause.Pause"));
        this.labelAuthMode.setText(I18nUtil.getInstance().getI18nMsg("fr.davidbrun.wifiautoconnect.views.FrmMain.labelAuthMode.Auto"));
        this.labelAutoConnectState.setText(I18nUtil.getInstance().getI18nMsg("fr.davidbrun.wifiautoconnect.views.FrmMain.labelAutoConnectState.Launched"));
        this.labelConnectionState.setText(I18nUtil.getInstance().getI18nMsg("fr.davidbrun.wifiautoconnect.views.FrmMain.labelConnectionState.Disconnected"));
        this.labelLabelConnectionState.setText(I18nUtil.getInstance().getI18nMsg("fr.davidbrun.wifiautoconnect.views.FrmMain.labelLabelConnectionState"));
        this.labelNotifications.setText(I18nUtil.getInstance().getI18nMsg("fr.davidbrun.wifiautoconnect.views.FrmMain.labelNotifications"));
        this.labelLabelProfile.setText(I18nUtil.getInstance().getI18nMsg("fr.davidbrun.wifiautoconnect.views.FrmMain.labelLabelProfile"));
        this.menuFile.setText((!OSUtil.IS_MAC ? "  " : "") + I18nUtil.getInstance().getI18nMsg("fr.davidbrun.wifiautoconnect.views.FrmMain.menuFile") + (!OSUtil.IS_MAC ? " " : ""));
        this.menuProfiles.setText((!OSUtil.IS_MAC ? " " : "") + I18nUtil.getInstance().getI18nMsg("fr.davidbrun.wifiautoconnect.views.FrmMain.menuProfiles") + (!OSUtil.IS_MAC ? " " : ""));
        this.menuHelp.setText((!OSUtil.IS_MAC ? " " : "") + I18nUtil.getInstance().getI18nMsg("fr.davidbrun.wifiautoconnect.views.FrmMain.menuHelp") + (!OSUtil.IS_MAC ? " " : ""));
        this.menuFile_Parameters.setText(I18nUtil.getInstance().getI18nMsg("fr.davidbrun.wifiautoconnect.views.FrmMain.menuFile_Parameters"));
        this.menuFile_Quit.setText(I18nUtil.getInstance().getI18nMsg("fr.davidbrun.wifiautoconnect.views.FrmMain.menuFile_Quit"));
        this.menuHelp_Update.setText(I18nUtil.getInstance().getI18nMsg("fr.davidbrun.wifiautoconnect.views.FrmMain.menuHelp_Update"));
        this.menuHelp_Doc.setText(I18nUtil.getInstance().getI18nMsg("fr.davidbrun.wifiautoconnect.views.FrmMain.menuHelp_Doc"));
        this.menuHelp_FAQ.setText(I18nUtil.getInstance().getI18nMsg("fr.davidbrun.wifiautoconnect.views.FrmMain.menuHelp_FAQ"));
        this.menuHelp_About.setText(I18nUtil.getInstance().getI18nMsg("fr.davidbrun.wifiautoconnect.views.FrmMain.menuHelp_About"));
        this.setMenuBarMnemonics();
    }
    
    /**
     * Set mnemonics to the menus of the menu bar (with the first letter of the captions)<br/>
     * No mnemonic on Mac OS
     */
    private void setMenuBarMnemonics()
    {
        if (!OSUtil.IS_MAC)
        {
            // Menus
            if (this.menuFile.getText().length() > 0)
                this.menuFile.setMnemonic(this.menuFile.getText().charAt(0));
            if (this.menuProfiles.getText().length() > 0)
                this.menuProfiles.setMnemonic(this.menuProfiles.getText().charAt(0));
            if (this.menuHelp.getText().length() > 0)
                this.menuHelp.setMnemonic(this.menuHelp.getText().charAt(0));
            // File sub menus
            if (this.menuFile_Parameters.getText().length() > 0)
                this.menuFile_Parameters.setMnemonic(this.menuFile_Parameters.getText().charAt(0));
            if (this.menuFile_Quit.getText().length() > 0)
                this.menuFile_Quit.setMnemonic(this.menuFile_Quit.getText().charAt(0));
            // Help sub menus
            if (this.menuHelp_Update.getText().length() > 0)
                this.menuHelp_Update.setMnemonic(this.menuHelp_Update.getText().charAt(0));
            if (this.menuHelp_Doc.getText().length() > 0)
                this.menuHelp_Doc.setMnemonic(this.menuHelp_Doc.getText().charAt(0));
            if (this.menuHelp_FAQ.getText().length() > 0)
                this.menuHelp_FAQ.setMnemonic(this.menuHelp_FAQ.getText().charAt(0));
            if (this.menuHelp_About.getText().length() > 0)
                this.menuHelp_About.setMnemonic(this.menuHelp_About.getText().charAt(0));
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