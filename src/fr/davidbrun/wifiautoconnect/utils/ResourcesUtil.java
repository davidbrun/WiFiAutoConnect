/*
 * File:   ResourcesUtil.java
 * Author: David Brun <brundavid@gmail.com>
 *
 * Created on 5 sept. 2011, 10:40:02
 */

package fr.davidbrun.wifiautoconnect.utils;

import java.io.File;

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

    static
    {
        RESOURCES_PATH = new File(fr.davidbrun.wifiautoconnect.utils.ResourcesUtil.class.getResource("/").getPath()).getParent() + "/resources";
    }
}