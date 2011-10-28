/*
 * File:   GenericPopupMenu.java
 * Author: David Brun <brundavid@gmail.com>
 *
 * Created on 28 oct. 2011, 00:45:08
 */

package fr.davidbrun.wifiautoconnect.structs;

import fr.davidbrun.wifiautoconnect.utils.OSUtil;
import fr.davidbrun.wifiautoconnect.views.JSeparatorExt;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 * Represent a generic popup menu: either a AWT or a SWING popup menu
 * 
 * @author David Brun <brundavid@gmail.com>
 */
public class GenericPopupMenu
{
    /**
     * The type of the popup menu: either a AWT or a SWING popup menu
     * 
     * @author David Brun <brundavid@gmail.com>
     */
    public enum PopupMenuType { /** Represent a AWT popup menu */ AWT, /** Represent a SWING popup menu */ SWING };
    
    // <editor-fold defaultstate="collapsed" desc="Fields">
    
    // To store the real popup menu and its type
    private PopupMenu awtPopupMenu;
    private JPopupMenu swingPopupMenu;
    private PopupMenuType popupMenuType;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    
    /**
     * Create a generic popup menu of the specfified type, with the specified caption
     * 
     * @param popupMenuType The type of the popup menu
     * @param caption The caption to display on the popup menu
     */
    public GenericPopupMenu(PopupMenuType popupMenuType, String caption)
    {
        this.popupMenuType = popupMenuType;
        
        if (popupMenuType == PopupMenuType.AWT)
            awtPopupMenu = new PopupMenu(caption);
        else
            swingPopupMenu = new JPopupMenu(caption);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Public methods">
    
    /**
     * Add a menu item to the generic popup menu
     * 
     * @param menuItem The menu item to add to the popup menu
     * @return true if the operation succeeded, false otherwise
     */
    public boolean addMenuItem(GenericMenuItem menuItem)
    {
        try
        {
            if (popupMenuType == PopupMenuType.AWT)
                awtPopupMenu.add((MenuItem)menuItem.getCurrentMenuItem());
            else
                swingPopupMenu.add((JMenuItem)menuItem.getCurrentMenuItem());
        }
        catch (Exception e)
        {
            return false;
        }
        
        return true;
    }
    
    /**
     * Add a menu item separator to the generic popup menu
     * 
     * @param menuItemSeparator The menu item separator to add to the popup menu
     * @return true if the operation succeeded, false otherwise
     */
    public boolean addMenuItemSeparator(GenericMenuItemSeparator menuItemSeparator)
    {
        try
        {
            if (popupMenuType == PopupMenuType.AWT)
                awtPopupMenu.add(new MenuItem("-"));
            else
                if (OSUtil.IS_WINDOWS)
                    swingPopupMenu.addSeparator();
                else
                    swingPopupMenu.add(new JSeparatorExt());
        }
        catch (Exception e)
        {
            return false;
        }
        
        return true;
    }
    
    /**
     * Get the real popup menu behind this generic
     * 
     * @return Either a java.awt.PopupMenu or a javax.swing.JPopupMenu
     */
    public Object getCurrentPopupMenu()
    {
        return (popupMenuType == PopupMenuType.AWT ? awtPopupMenu : swingPopupMenu);
    }
    
    // </editor-fold>
}