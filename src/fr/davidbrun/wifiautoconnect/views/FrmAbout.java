/*
 * File:   FrmAbout.java
 * Author: David Brun <brundavid@gmail.com>
 *
 * Created on 5 sept. 2011, 17:16:43
 */

package fr.davidbrun.wifiautoconnect.views;

import fr.davidbrun.wifiautoconnect.utils.ResourcesUtil;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

/**
 * Represent the about window of the application
 * 
 * @author David Brun <brundavid@gmail.com>
 */
public class FrmAbout extends JDialog
{
    // <editor-fold defaultstate="collapsed" desc="Fields">
    
    // Swing components
    JPanel panelMain;
    JLabel labelLogo;
    JLabel labelAppName;
    JLabel labelAppVersion;
    JLabel labelUpdates;
    JTextArea labelDescription;
    JLabel labelCopyright;
    JLabel hyperlinkCreatorWebSite;
    JLabel hyperlinkDocumentation;
    JButton buttonOk;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    
    public FrmAbout(JFrame parent, boolean modal)
    {
        // The JDialog enable the frame to be modal
        super(parent, modal);
        
        //First make sure the parent frame is visible
        parent.setVisible(true);
        
        // Initialize the SWING components
        this.initComponents(parent);
        
        // Add listeners to the SWING components
        this.addListeners();
        
        // Center the about box in the main frame
        this.centerFrameInParent(parent);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Private methods">
    
    /**
     * Initialize the Swing components
     */
    private void initComponents(JFrame parent)
    {
        // Disable the ability to resize the frame
        this.setResizable(false);
        // Set a correct size to display all the content
        this.setSize(650, 310);
        // Set a title to the window
        this.setTitle("À propos de WiFi Auto Connect...");
        // Defaut operation on close
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(parent);
        // Create the main panel of the window
        panelMain = new JPanel(null);
        // The swing components
        JPanel row0 = new JPanel(null);
        JPanel row0_col0 = new JPanel(null);
        JPanel row0_col1 = new JPanel();
        row0_col1.setLayout(new BoxLayout(row0_col1, BoxLayout.Y_AXIS));
        JPanel row1 = new JPanel();
        row1.setLayout(new BoxLayout(row1, BoxLayout.X_AXIS));
        row0_col0.setSize(270, 235);
        row0_col1.setSize(650 - 270, 235);
        row0.add(row0_col0);
        row0.add(row0_col1);
        row0_col0.setLocation(0, 0);
        row0_col1.setLocation(270, 0);
        panelMain.add(row0);
        panelMain.add(row1);
        row0.setSize(650, 235);
        row1.setSize(650, this.getHeight() - 255);
        row0.setLocation(0, 0);
        row1.setLocation(0, row0.getHeight());
        row0.setBackground(new Color(237, 237, 237));
        row1.setBackground(new Color(215, 215, 215));
        // The application icon
        labelLogo = new JLabel(ResourcesUtil.ABOUT_BOX_IMAGE_PATH);
        row0_col0.add(labelLogo);
        labelLogo.setSize(200, 200);
        labelLogo.setLocation(row0_col0.getWidth() / 2 - labelLogo.getWidth() / 2, row0_col0.getHeight() / 2 - labelLogo.getHeight() / 2);
        // Set texts
        labelAppName = new JLabel("WiFi Auto Connect");
        labelAppVersion = new JLabel("Version alpha");
        labelUpdates = new JLabel("Wifi Auto Connect est à jour !");
        labelDescription = new JTextArea("Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.");
        labelCopyright = new JLabel("Copyright © 2011 David Brun");
        try
        {
            labelAppName.setFont(Font.createFont(Font.BOLD, ResourcesUtil.MARKER_FELT_FONT).deriveFont(32f));
        }
        catch (Exception e)
        {
            labelAppName.setFont(new Font(labelCopyright.getFont().getFontName(), Font.BOLD, 32));
        }
        labelUpdates.setFont(new Font(labelCopyright.getFont().getFontName(), Font.ITALIC, 12));
        labelUpdates.setForeground(Color.GRAY);
        labelDescription.setFont(new Font(labelCopyright.getFont().getFontName(), Font.PLAIN, 11));
        labelDescription.setMaximumSize(new Dimension(650 - 270 - 40, labelDescription.getHeight()));
        labelDescription.setLineWrap(true);
        labelDescription.setWrapStyleWord(true);
        labelDescription.setEditable(false);
        labelDescription.setFocusable(false);
        labelDescription.setDragEnabled(false);
        labelDescription.setBackground(this.getBackground());
        // Set the alignment
        labelAppName.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelAppVersion.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelDescription.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelCopyright.setAlignmentX(Component.LEFT_ALIGNMENT);
        row0_col1.add(Box.createRigidArea(new Dimension(0, 15)));
        row0_col1.add(labelAppName);
        row0_col1.add(Box.createRigidArea(new Dimension(0, 5)));
        row0_col1.add(labelAppVersion);
        row0_col1.add(Box.createRigidArea(new Dimension(0, 10)));
        row0_col1.add(labelUpdates);
        row0_col1.add(Box.createRigidArea(new Dimension(0, 10)));
        row0_col1.add(labelDescription);
        row0_col1.add(Box.createVerticalGlue());
        row0_col1.add(labelCopyright);
        row0_col1.add(Box.createVerticalGlue());
        // The bottom row
        hyperlinkCreatorWebSite = new JLabel("Site de l'éditeur");
        hyperlinkCreatorWebSite.setForeground(new Color(20, 79, 174));
        hyperlinkCreatorWebSite.setBackground(Color.red);
        buttonOk = new JButton("Fermer");
        buttonOk.setMargin(new Insets(6, 0, 4, 0));
        buttonOk.setPreferredSize(new Dimension(120, 20));
        hyperlinkDocumentation = new JLabel("Documentation");
        hyperlinkDocumentation.setForeground(new Color(20, 79, 174));
        hyperlinkCreatorWebSite.setAlignmentY(Component.CENTER_ALIGNMENT);
        buttonOk.setAlignmentY(Component.CENTER_ALIGNMENT);
        hyperlinkDocumentation.setAlignmentY(Component.CENTER_ALIGNMENT);
        row1.add(Box.createHorizontalGlue());
        row1.add(hyperlinkCreatorWebSite);
        row1.add(Box.createHorizontalGlue());
        row1.add(buttonOk);
        row1.add(Box.createHorizontalGlue());
        row1.add(hyperlinkDocumentation);
        row1.add(Box.createHorizontalGlue());
        // Add swing components
        this.setContentPane(panelMain);
        // Close the frame when typing ESC
        panelMain.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Exit");
        panelMain.getActionMap().put("Exit", new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                setVisible(false);
            }
        });
    }
    
    /**
     * Add listeners to the components
     */
    private void addListeners()
    {
        this.hyperlinkCreatorWebSite.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseReleased(MouseEvent arg0)
            {
                browseToWebSite();
            }

            @Override
            public void mouseExited(MouseEvent arg0)
            {
                hyperlinkCreatorWebSite.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mouseEntered(MouseEvent arg0)
            {
                hyperlinkCreatorWebSite.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
        
        this.hyperlinkDocumentation.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseReleased(MouseEvent arg0)
            {
                // TODO: open the documentation
            }

            @Override
            public void mouseExited(MouseEvent arg0)
            {
                hyperlinkDocumentation.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mouseEntered(MouseEvent arg0)
            {
                hyperlinkDocumentation.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
    }
    /**
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
     * Open the web site of the creator of the application
     */
    private void browseToWebSite()
    {
        Desktop desktop = Desktop.getDesktop();
        try
        {
            desktop.browse(new URI("http://welcom5.free.fr/"));
        }
        catch (Exception e)
        {}
    }
    
    // </editor-fold>
}