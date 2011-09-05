/*
 * File:   I18nUtil.java
 * Author: David Brun <brundavid@gmail.com>
 *
 * Created on 5 sept. 2011, 10:43:42
 */

package fr.davidbrun.wifiautoconnect.utils;

import java.net.URL;
import java.util.*;

/**
 * This singleton class provides methods to internationalize the application
 * 
 * @author David Brun <brundavid@gmail.com>
 */
public class I18nUtil
{
    // <editor-fold defaultstate="collapsed" desc="Fields">
    
    // Fields for localization and access to the message files
    private String language;
    private String country;
    private ResourceBundle messages;
    // Singleton
    private static I18nUtil instance;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Public static methods">
    
    /**
     * Get the single instance of the internationalization util
     * 
     * @return The single instance of the internationalization util
     */
    public static I18nUtil getInstance()
    {
        // Just one instance of this object
        if (instance == null)
            instance = new I18nUtil();
        
        return instance;
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Public methods">
    
    /**
     * Get the internationalized message for the specified key</br>
     * Use the locale stored in the single instance of the internationalization util
     * 
     * @param key The key of the message to obtain
     * @return The internationalized message for the specified key
     */
    public String getI18nMsg(String key)
    {
        // The key can be absent of the messages files
        try
        {
            return messages.getString(key);
        }
        catch (Exception e)
        {
            return "";
        }
    }
    
    /**
     * Get the locale of the internationalization util
     * 
     * @return The locale of the internationalization util
     */
    public Locale getCurrentLocale()
    {
        return new Locale(language, country);
    }
    
    /**
     * Get the country of the locale of the internationalization util
     * 
     * @return The country of the locale of the internationalization util
     */
    public String getCountry()
    {
        return country;
    }
    
    /**
     * Get the language of the locale of the internationalization util
     * 
     * @return The language of the locale of the internationalization util
     */
    public String getLanguage()
    {
        return language;
    }
    
    /**
     * Set the locale of the internationalization util
     * 
     * @param The locale of the internationalization util
     */
    public void setLocale(String language, String country)
    {
        // Set the locale
        this.language = language;
        this.country = country;
        
        URL urlToMessages;
        try
        {
            if (language.isEmpty() || country.isEmpty())
                urlToMessages = new URL("file:" + ResourcesUtil.RESOURCES_PATH + "/languages/Messages.properties");
            else
                urlToMessages = new URL("file:" + ResourcesUtil.RESOURCES_PATH + "/languages/Messages_" + language + "_" + country + ".properties");
            // Get the correct message file
            messages = new PropertyResourceBundle(urlToMessages.openStream());
        }
        catch (Exception e)
        { }
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Private constructor">
    
    /**
     * A private constructor to make a singleton class<br/>
     * Initialize the internationalization util with "en_US" locale by default
     */
    private I18nUtil()
    {
        this.setLocale("en", "US");
    }
    
    // </editor-fold>
}