/*
 * File:   ResourcesUtil.java
 * Author: David Brun <brundavid@gmail.com>
 *
 * Created on 5 sept. 2011, 10:40:02
 */

package fr.davidbrun.wifiautoconnect.utils;

import java.io.File;
import java.net.URI;
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
     * The path of the image used in the about box
     */
    public static final ImageIcon ABOUT_BOX_IMAGE_PATH;
    /**
     * The file where is located the Marker Felt font delivered with the application
     */
    public static final File MARKER_FELT_FONT;
    
    static
    {
        RESOURCES_PATH = new File(fr.davidbrun.wifiautoconnect.utils.ResourcesUtil.class.getResource("/").getPath()).getParent() + "/resources";
        URI urlFont = null;
        URL urlAboutBoxImage = null;
        try
        {
            urlAboutBoxImage = new URL("file:" + RESOURCES_PATH + "/images/big_dark_shadow_blur_200x200.png");
            urlFont = new URL("file:" + RESOURCES_PATH + "/fonts/MarkerFelt.pfb").toURI();
        }
        catch (Exception e)
        { }
        finally
        {
            ABOUT_BOX_IMAGE_PATH = new ImageIcon(urlAboutBoxImage);
            MARKER_FELT_FONT = new File(urlFont);
        }
    }
}