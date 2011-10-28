/*
 * File:   GenericTrayIcon.java
 * Author: David Brun <brundavid@gmail.com>
 *
 * Created on 28 oct. 2011, 01:06:00
 */

package fr.davidbrun.wifiautoconnect.structs;

import fr.davidbrun.wifiautoconnect.views.JTrayIcon;
import java.awt.Image;
import java.awt.PopupMenu;
import java.awt.TrayIcon;
import java.awt.event.ActionListener;
import javax.swing.JPopupMenu;

/**
 * Represent a generic system tray icon: either the default or the custom one located in the views package
 * 
 * @see fr.davidbrun.wifiautoconnect.views.JTrayIcon
 * @author David Brun <brundavid@gmail.com>
 */
public class GenericTrayIcon
{
    /**
     * The type of the tray icon: either the DEFAULT or the CUSTOM one located in the views package
     * 
     * @see fr.davidbrun.wifiautoconnect.views.JTrayIcon
     * @author David Brun <brundavid@gmail.com>
     */
    public enum TrayIconType { /** Represent the DEFAULT tray icon */ DEFAULT, /** Represent the CUSTOM tray icon */ CUSTOM };
    
    // <editor-fold defaultstate="uncollapsed" desc="Fields">
    
    // To store the real tray icon and its type
    private TrayIcon defaultTrayIcon;
    private JTrayIcon customTrayIcon;
    private TrayIconType trayIconType;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="uncollapsed" desc="Constructor">
    
    /**
     * Create a generic tray icon of the specfified type, with the specified image, tooltip and popup menu
     * 
     * @param trayIconType The type of the tray icon
     * @param image The image to display in the tray area
     * @param tooltip The tooltip to display in the tray area
     * @param popupMenu The popup menu attached to the tray icon
     */
    public GenericTrayIcon(TrayIconType trayIconType, Image image, String tooltip, Object popupMenu)
    {
        this.trayIconType = trayIconType;
        
        if (trayIconType == TrayIconType.DEFAULT)
            defaultTrayIcon = new TrayIcon(image, tooltip, (PopupMenu)popupMenu);
        else
        {
            customTrayIcon = new JTrayIcon(image);
            customTrayIcon.setJPopupMenu((JPopupMenu)popupMenu);
            customTrayIcon.setToolTip(tooltip);
        }
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="uncollapsed" desc="Public methods">
    
    /**
     * Set the auto-size property of the tray icon
     * 
     * @param autosize true to auto-size the image, false otherwise
     */
    public void setImageAutoSize(boolean autosize)
    {
        if (trayIconType == TrayIconType.DEFAULT)
            defaultTrayIcon.setImageAutoSize(autosize);
        else
            customTrayIcon.setImageAutoSize(autosize);
    }
    
    /**
     * Add the specified action listener to the tray icon
     * 
     * @param actionListener The action listener to add to the tray icon
     */
    public void addActionListener(ActionListener actionListener)
    {
        if (trayIconType == TrayIconType.DEFAULT)
            defaultTrayIcon.addActionListener(actionListener);
        else
            customTrayIcon.addActionListener(actionListener);
    }
    
    /**
     * Set the tooltip to display in the tray area
     * 
     * @param tooltip The tooltip to display in the tray area
     */
    public void setToolTip(String tooltip)
    {
        if (trayIconType == TrayIconType.DEFAULT)
            defaultTrayIcon.setToolTip(tooltip);
        else
            customTrayIcon.setToolTip(tooltip);
    }
    
    /**
     * Get the real tray icon behind this generic
     * 
     * @return Either a java.awt.TrayIcon or a fr.davidbrun.wifiautoconnect.views.JTrayIcon
     */
    public Object getCurrentTrayIcon()
    {
        return (trayIconType == TrayIconType.DEFAULT ? defaultTrayIcon : customTrayIcon);
    }
    
    // </editor-fold>
}