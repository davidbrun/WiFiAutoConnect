/*
 * File:   GenericMenuItemSeparator.java
 * Author: David Brun <brundavid@gmail.com>
 *
 * Created on 28 oct. 2011, 01:48:44
 */

package fr.davidbrun.wifiautoconnect.structs;

/**
 * Represent a generic menu item separator: either a AWT or a SWING menu item separator.<br/>
 * Currently the class does nothing because the GenericPopupMenu can add separators.
 * 
 * @see fr.davidbrun.wifiautoconnect.structs.GenericPopupMenu
 * @author David Brun <brundavid@gmail.com>
 */
public class GenericMenuItemSeparator
{
    /**
     * The type of the menu item separator: either a AWT or a SWING menu item separator
     * 
     * @author David Brun <brundavid@gmail.com>
     */
    public enum MenuItemSeparatorType { /** Represent a AWT menu item separator */ AWT, /** Represent a SWING menu item separator */ SWING };
    
    // <editor-fold defaultstate="uncollapsed" desc="Fields">
    
    private MenuItemSeparatorType menuItemSeparatorType;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="uncollapsed" desc="Constructor">
    
    public GenericMenuItemSeparator(MenuItemSeparatorType menuItemSeparatorType)
    { }
    
    // </editor-fold>
}