/*
 * File:   JSeparatorExt.java
 * Author: David Brun <brundavid@gmail.com>
 *
 * Created on 27 oct. 2011, 15:21:47
 */

package fr.davidbrun.wifiautoconnect.views;

import java.awt.Dimension;
import javax.swing.JSeparator;

/**
 * This class is an extension of JSeparator. It displays properly on GTK Linux.
 * 
 * @author David Brun <brundavid@gmail.com>
 */
public class JSeparatorExt extends JSeparator
{
    @Override
    public Dimension getPreferredSize()
    {
        Dimension d = super.getPreferredSize();
        if (d.height == 0)
            d.height = 4;
        
        return d;
    }
}