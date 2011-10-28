/*
 * File:   GenericMenuItem.java
 * Author: David Brun <brundavid@gmail.com>
 *
 * Created on 28 oct. 2011, 00:53:36
 */

package fr.davidbrun.wifiautoconnect.structs;

import java.awt.Font;
import java.awt.MenuItem;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;

/**
 * Represent a generic menu item: either a AWT or a SWING menu item
 * 
 * @author David Brun <brundavid@gmail.com>
 */
public class GenericMenuItem
{
    /**
     * The type of the menu item: either a AWT or a SWING menu item
     * 
     * @author David Brun <brundavid@gmail.com>
     */
    public enum MenuItemType { /** Represent a AWT menu item */ AWT, /** Represent a SWING menu item */ SWING };
    
    // <editor-fold defaultstate="collapsed" desc="Fields">
    
    // To store the real menu item and its type
    private MenuItem awtMenuItem;
    private JMenuItem swingMenuItem;
    private MenuItemType menuItemType;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    
    /**
     * Create a generic menu item of the specfified type, with the specified caption
     * 
     * @param menuItemType The type of the menu item
     * @param caption The caption to display on the menu item
     */
    public GenericMenuItem(MenuItemType menuItemType, String caption)
    {
        this.menuItemType = menuItemType;
        
        if (menuItemType == MenuItemType.AWT)
            awtMenuItem = new MenuItem(caption);
        else
            swingMenuItem = new JMenuItem(caption);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Public methods">
    
    /**
     * Add the specified action listener to the menu item
     * 
     * @param actionListener The action listener to add to the menu item
     * @return true if the operation succeeded, false otherwise
     */
    public boolean addActionListener(ActionListener actionListener)
    {
        try
        {
            if (menuItemType == MenuItemType.AWT)
                awtMenuItem.addActionListener(actionListener);
            else
                swingMenuItem.addActionListener(actionListener);
        }
        catch (Exception e)
        {
            return false;
        }
        
        return true;
    }
    
    /**
     * Set the label of the menu item
     * 
     * @param label The label to set to the menu item
     * @return true if the operation succeeded, false otherwise
     */
    public boolean setLabel(String label)
    {
        try
        {
            if (menuItemType == MenuItemType.AWT)
                awtMenuItem.setLabel(label);
            else
                swingMenuItem.setText(label);
        }
        catch (Exception e)
        {
            return false;
        }
        
        return true;
    }
    
    /**
     * Set the font of the menu item, if possible
     * 
     * @param font The font to set to the menu item
     * @return true if the operation succeeded, false otherwise
     */
    public boolean setFont(Font font)
    {
        if (menuItemType == MenuItemType.AWT)
            return false;
        else
        {
            swingMenuItem.setFont(font);
            return true;
        }
    }
    
    /**
     * Get the label of the menu item
     * 
     * @return The label of the menu item
     */
    public String getLabel()
    {
        if (menuItemType == MenuItemType.AWT)
            return awtMenuItem.getLabel();
        else
            return swingMenuItem.getText();
    }
    
    /**
     * Get the real menu item behind this generic
     * 
     * @return Either a java.awt.MenuItem or a javax.swing.JMenuItem
     */
    public Object getCurrentMenuItem()
    {
        return (menuItemType == MenuItemType.AWT ? awtMenuItem : swingMenuItem);
    }
    
    // </editor-fold>
}