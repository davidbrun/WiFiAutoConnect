/*
 * File:   ResourcesUtil.java
 * Author: David Brun <brundavid@gmail.com>
 *
 * Created on 5 sept. 2011, 10:40:02
 */

package fr.davidbrun.wifiautoconnect.utils;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 * This class provides constants to easily locate resources
 * 
 * @author David Brun <brundavid@gmail.com>
 */
public class ResourcesUtil
{
    /**
     * The path of the resources folder
     */
    public static final String RESOURCES_PATH;
    /**
     * The icon of the application
     */
    public static final Image APPLICATION_IMAGE;
    /**
     * The image of the application used in the system tray
     */
    public static final ImageIcon SYSTEM_TRAY_IMAGE_ICON;
    /**
     * The image of the application used in the about box
     */
    public static final ImageIcon ABOUT_BOX_IMAGE_ICON;
    /**
     * The file where is located the Marker Felt font delivered with the application
     */
    public static final URL MARKER_FELT_FONT;
    
    static
    {
        RESOURCES_PATH = new File(fr.davidbrun.wifiautoconnect.utils.ResourcesUtil.class.getResource("/").getPath()).getParent() + "/resources";
        
        Toolkit kit = Toolkit.getDefaultToolkit();
        // Locate the resources
        URL urlApplicationImage = null;
        URL urlSystemTrayImage = null;
        URL urlAboutBoxImage = null;
        URL urlMarkerFeltFont = null;
        
        try
        {
            urlApplicationImage = new URL("file:" + RESOURCES_PATH + "/images/big_dark_blur_wide_512x512.png");
            urlSystemTrayImage = new URL("file:" + RESOURCES_PATH + "/images/big_dark_blur_wide_512x512.png");
            urlAboutBoxImage = new URL("file:" + RESOURCES_PATH + "/images/big_dark_shadow_blur_200x200.png");
            urlMarkerFeltFont = new URL("file:" + RESOURCES_PATH + "/fonts/MarkerFelt.pfb");
        }
        catch (Exception e)
        { }
        finally
        {
            APPLICATION_IMAGE = kit.createImage(urlApplicationImage);
            SYSTEM_TRAY_IMAGE_ICON = new ImageIcon(urlSystemTrayImage);
            ABOUT_BOX_IMAGE_ICON = new ImageIcon(urlAboutBoxImage);
            MARKER_FELT_FONT = urlMarkerFeltFont;
        }
    }
}