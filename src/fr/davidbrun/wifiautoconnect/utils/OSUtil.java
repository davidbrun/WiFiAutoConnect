/*
 * File:   OSUtil.java
 * Author: David Brun <brundavid@gmail.com>
 *
 * Created on 5 srpt. 2011, 14:35:39
 */

package fr.davidbrun.wifiautoconnect.utils;

/**
 * This class provides constants to easily detect on which OS the application is running
 * 
 * @author David Brun <brundavid@gmail.com>
 */
public class OSUtil
{
    /**
     * True if the application is running on a mac os
     */
    public static final boolean IS_MAC;
    /**
     * True if the application is running on a windows os
     */
    public static final boolean IS_WINDOWS;
    /**
     * True if the application is running on a linux os
     */
    public static final boolean IS_LINUX;

    static
    {
        String osName = System.getProperty("os.name");

        if (osName.startsWith("Mac"))
        {
            IS_MAC = true;
            IS_WINDOWS = false;
            IS_LINUX = false;
        }
        else if (osName.startsWith("Windows"))
        {
            IS_MAC = false;
            IS_WINDOWS = true;
            IS_LINUX = false;
        }
        else if (osName.startsWith("Linux"))
        {
            IS_MAC = false;
            IS_WINDOWS = false;
            IS_LINUX = true;
        }
        else
        {
            IS_MAC = false;
            IS_WINDOWS = false;
            IS_LINUX = false;
        }
    }
}