/*
 * File:   FrmMain.java
 * Author: David Brun <brundavid@gmail.com>
 *
 * Created on 4 sept. 2011, 16:20:28
 */

package fr.davidbrun.wifiautoconnect.views;

import com.apple.eawt.AboutHandler;
import com.apple.eawt.AppEvent.AboutEvent;
import com.apple.eawt.AppEvent.PreferencesEvent;
import com.apple.eawt.Application;
import com.apple.eawt.PreferencesHandler;
import fr.davidbrun.wifiautoconnect.controllers.FrmMainController;
import fr.davidbrun.wifiautoconnect.utils.I18nUtil;
import fr.davidbrun.wifiautoconnect.utils.OSUtil;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
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
        // Attributes of the window
        int windowWidth = 450;
        
        // Get the content pane
        Container pane = this.getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        
        // The first row: wifi animated icon
        JPanel row0 = new JPanel();
        row0.setLayout(new BoxLayout(row0, BoxLayout.X_AXIS));
        wifiAnimatedIcon = new WiFiAnimatedIcon(250, FrmMainController.WINDOW_TOP_BACKGROUND);
        wifiAnimatedIcon.setPreferredSize(new Dimension(152, 252));
        row0.add(Box.createHorizontalGlue());
        row0.add(wifiAnimatedIcon);
        row0.add(Box.createHorizontalGlue());
        
        // The second row: notifications
        labelNotifications = new JLabel("Notifications ici", SwingConstants.CENTER);
        labelNotifications.setFont(new Font(labelNotifications.getFont().getFontName(), Font.ITALIC, labelNotifications.getFont().getSize()));
        labelNotifications.setForeground(Color.GRAY);
        labelNotifications.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelNotifications.setAlignmentY(Component.TOP_ALIGNMENT);
        Dimension labelDimension = new Dimension(windowWidth, 15);
        labelNotifications.setMinimumSize(labelDimension);
        labelNotifications.setMaximumSize(labelDimension);
        labelNotifications.setPreferredSize(labelDimension);
        labelNotifications.setSize(labelDimension);
        
        // The third row: profile + connection state
        JPanel row2 = new JPanel();
        row2.setLayout(new BoxLayout(row2, BoxLayout.X_AXIS));
        labelProfile = new JLabel("SFR WiFi Public");
        labelConnectionState = new JLabel("Connecté");
        labelProfile.setFont(new Font(labelProfile.getFont().getName(), Font.BOLD, labelProfile.getFont().getSize()));
        labelConnectionState.setFont(new Font(labelConnectionState.getFont().getName(), Font.BOLD, labelConnectionState.getFont().getSize()));
        row2.add(Box.createHorizontalGlue());
        row2.add(labelProfile);
        row2.add(Box.createHorizontalGlue());
        row2.add(Box.createHorizontalGlue());
        row2.add(labelConnectionState);
        row2.add(Box.createHorizontalGlue());
        row2.setBorder(BorderFactory.createEtchedBorder());
        // Set the sizes
        Dimension row2Dimension = new Dimension(windowWidth - 40, 45);
        row2.setMinimumSize(row2Dimension);
        row2.setMaximumSize(row2Dimension);
        row2.setPreferredSize(row2Dimension);
        row2.setSize(row2Dimension);
        
        // Last row: buttons
        JPanel row3 = new JPanel();
        row3.setLayout(new BoxLayout(row3, BoxLayout.X_AXIS));
        // The last row, left column
        JPanel row3_col0 = new JPanel();
        row3_col0.setLayout(new BoxLayout(row3_col0, BoxLayout.Y_AXIS));
        buttonLaunchPause = new JButton("Marche");
        Dimension buttonLaunchPauseDimension = new Dimension(140, 35);
        buttonLaunchPause.setSize(buttonLaunchPauseDimension);
        buttonLaunchPause.setMinimumSize(buttonLaunchPauseDimension);
        buttonLaunchPause.setMaximumSize(buttonLaunchPauseDimension);
        buttonLaunchPause.setPreferredSize(buttonLaunchPauseDimension);
        buttonLaunchPause.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelAutoConnectState = new JLabel("Auto Connect en pause", SwingConstants.CENTER);
        labelAutoConnectState.setForeground(Color.GRAY);
        labelAutoConnectState.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelAutoConnectState.setMinimumSize(new Dimension(windowWidth / 2, 15));
        // Add components
        row3_col0.add(Box.createVerticalGlue());
        row3_col0.add(buttonLaunchPause);
        row3_col0.add(Box.createRigidArea(new Dimension(0, 3)));
        row3_col0.add(labelAutoConnectState);
        row3_col0.add(Box.createVerticalGlue());
        // The last row, right column
        JPanel row3_col1 = new JPanel();
        row3_col1.setLayout(new BoxLayout(row3_col1, BoxLayout.Y_AXIS));
	buttonAutoManual = new JButton("Automatique");
        Dimension buttonAutoManualDimension = new Dimension(140, 35);
        buttonAutoManual.setSize(buttonAutoManualDimension);
        buttonAutoManual.setMinimumSize(buttonAutoManualDimension);
        buttonAutoManual.setMaximumSize(buttonAutoManualDimension);
        buttonAutoManual.setPreferredSize(buttonAutoManualDimension);
        buttonAutoManual.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelAuthMode = new JLabel("Authentification manuelle", SwingConstants.CENTER);
        labelAuthMode.setForeground(Color.GRAY);
        labelAuthMode.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelAuthMode.setMinimumSize(new Dimension(windowWidth / 2, 15));
        // Add components
        row3_col1.add(Box.createVerticalGlue());
        row3_col1.add(buttonAutoManual);
        row3_col1.add(Box.createRigidArea(new Dimension(0, 3)));
        row3_col1.add(labelAuthMode);
        row3_col1.add(Box.createVerticalGlue());
        
        // Add components
        row3.add(row3_col0);
        row3.add(row3_col1);
        // Set sizes
        Dimension colDimension = new Dimension(windowWidth / 2, 70);
        row3_col0.setPreferredSize(colDimension);
        row3_col1.setPreferredSize(colDimension);
        row3_col0.setSize(colDimension);
        row3_col1.setSize(colDimension);
        row3_col0.setMinimumSize(colDimension);
        row3_col1.setMinimumSize(colDimension);
        row3_col0.setMaximumSize(colDimension);
        row3_col1.setMaximumSize(colDimension);
        Dimension row3Dimension = new Dimension(windowWidth, row3.getHeight());
        row3.setMinimumSize(row3Dimension);
        row3.setSize(row3Dimension);
        row3.setMaximumSize(row3Dimension);
        // Set the color
        pane.setBackground(FrmMainController.WINDOW_TOP_BACKGROUND);
        row0.setBackground(FrmMainController.WINDOW_TOP_BACKGROUND);
        row2.setBackground(FrmMainController.WINDOW_TOP_BACKGROUND);
        row3_col0.setBackground(FrmMainController.WINDOW_BOTTOM_BACKGROUND);
        row3_col1.setBackground(FrmMainController.WINDOW_BOTTOM_BACKGROUND);
        
        // Add components
        pane.add(Box.createRigidArea(new Dimension(0, 15)));
        pane.add(row0);
        pane.add(Box.createRigidArea(new Dimension(0, 0)));
        pane.add(labelNotifications);
        pane.add(Box.createRigidArea(new Dimension(0, 12)));
        pane.add(row2);
        pane.add(Box.createRigidArea(new Dimension(0, 18)));
        pane.add(row3);
        
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
        
        // Full integration of the application in Mac OS
        if (OSUtil.IS_MAC)
        {
            Application.getApplication().setAboutHandler(new AboutHandler()
            {
                @Override
                public void handleAbout(AboutEvent arg0)
                {
                    new FrmAbout(FrmMain.this, true).setVisible(true);
                }
            });
            Application.getApplication().setPreferencesHandler(new PreferencesHandler()
            {
                @Override
                public void handlePreferences(PreferencesEvent pe)
                {
                    // TODO: show the preferences window
                }
            });
        }
        
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
        menuHelp_About.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                new FrmAbout(FrmMain.this, true).setVisible(true);
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
        this.setLocale(Locale.FRANCE);
//        this.setLocale(Locale.UK);
        I18nUtil.getInstance().setLocale(this.getLocale().getLanguage(), this.getLocale().getCountry());
        this.buttonAutoManual.setText(I18nUtil.getInstance().getI18nMsg("fr.davidbrun.wifiautoconnect.views.FrmMain.buttonAutoManual.Manual"));
        this.buttonLaunchPause.setText(I18nUtil.getInstance().getI18nMsg("fr.davidbrun.wifiautoconnect.views.FrmMain.buttonLaunchPause.Pause"));
        this.labelAuthMode.setText(I18nUtil.getInstance().getI18nMsg("fr.davidbrun.wifiautoconnect.views.FrmMain.labelAuthMode.Auto"));
        this.labelAutoConnectState.setText(I18nUtil.getInstance().getI18nMsg("fr.davidbrun.wifiautoconnect.views.FrmMain.labelAutoConnectState.Launched"));
        this.labelConnectionState.setText(I18nUtil.getInstance().getI18nMsg("fr.davidbrun.wifiautoconnect.views.FrmMain.labelConnectionState.Disconnected"));
        this.labelNotifications.setText(I18nUtil.getInstance().getI18nMsg("fr.davidbrun.wifiautoconnect.views.FrmMain.labelNotifications"));
        this.labelProfile.setForeground(Color.decode(I18nUtil.getInstance().getI18nMsg("fr.davidbrun.wifiautoconnect.views.FrmMain.labelProfile.Color")));
        this.labelConnectionState.setForeground(Color.decode(I18nUtil.getInstance().getI18nMsg("fr.davidbrun.wifiautoconnect.views.FrmMain.labelConnectionState.Color")));
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