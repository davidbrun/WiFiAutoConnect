/*
 * File:   WiFiAnimatedIcon.java
 * Author: David Brun <brundavid@gmail.com>
 *
 * Created on 4 sept. 2011, 16:22:35
 */

package fr.davidbrun.wifiautoconnect.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MultipleGradientPaint.CycleMethod;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import javax.swing.JComponent;

/**
 * Provides a graphical component that displays a WiFi animated icon
 * 
 * @author David Brun <brundavid@gmail.com>
 */
public class WiFiAnimatedIcon extends JComponent
{
    /**
     * Describe the state (actually the color) of a part of the WiFi icon
     */
    public enum State { GREEN, ORANGE, RED, BACKGROUND }
    
    // <editor-fold defaultstate="collapsed" desc="Constants">
    
    /**
     * Represent the offset to add to position the start at 12h00
     */
    private static final int ANGLE_OFFSET = 90;
    
    // The different available sets of colors (typically green, orange, red)
    /**
     * A set of green colors to paint a part of the icon
     */
    private static final Color[] GREEN_COLORS = { new Color(55, 255, 85, 185), new Color(0, 255, 0, 255), new Color(55, 255, 85, 185), new Color(30, 255, 65, 255), new Color(70, 255, 100, 130) };
    /**
     * A set of orange colors to paint a part of the icon
     */
    private static final Color[] ORANGE_COLORS = { new Color(255, 220, 15, 185), new Color(253, 121, 4, 255), new Color(255, 220, 15, 185), new Color(250, 170, 10, 255), new Color(230, 190, 10, 170) };
    /**
     * A set of red colors to paint a part of the icon
     */
    private static final Color[] RED_COLORS = { new Color(230, 40, 40, 185), new Color(255, 0, 0, 255), new Color(230, 40, 40, 185), new Color(255, 0, 0, 255), new Color(255, 15, 15, 170) };
    /**
     * A set of dark gray colors to paint a part of the icon
     */
    private static final Color[] DARK_GRAY_COLORS = { new Color(204, 204, 204, 185), new Color(204, 204, 204, 255), new Color(204, 204, 204, 185), new Color(204, 204, 204, 204), new Color(204, 204, 204, 170) };
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Fields">
    
    // Properties that define the shape of the WiFi icon
    private int radius;
    private int thickness;
    private int openingAngle;
    private Color parentBackground;
    // Individual colors of the part of the WiFi icon
    private State[] iconPartStates;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Overridden methods (from JComponent)">
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        int xOffset = radius / 8;
        int yOffset = radius / 8;
        
        // Try to optimize the brush to have a correct image and to be quick enough
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);

        // Calculate a pseudo radial gradient to make an effect
        float[] dist = {0.0f, 0.05f, 0.65f, 0.9f, 1.0f};
        RadialGradientPaint paintArc1 = new RadialGradientPaint(radius, radius / 6, radius, dist, this.getExternalArcColors(), CycleMethod.REFLECT);
        RadialGradientPaint paintArc2 = new RadialGradientPaint(radius, radius / 6, radius, dist, this.getMiddleArcColors(), CycleMethod.REFLECT);
        RadialGradientPaint paintArc3 = new RadialGradientPaint(radius, radius / 6, radius, dist, this.getInternalArcColors(), CycleMethod.REFLECT);
        RadialGradientPaint paintArc4 = new RadialGradientPaint(radius, radius / 6, radius, dist, this.getCircleColors(), CycleMethod.REFLECT);

        // Paint the first external disk
        g2d.setPaint(paintArc1);
        g2d.fillArc(1 + radius / 2 - xOffset * 7, radius - xOffset * 7 - yOffset, thickness * 7, thickness * 7, ANGLE_OFFSET - (openingAngle / 2), openingAngle);
        
        // Paint the first internal disk
        g2d.setColor(parentBackground);
        g2d.fillArc(1 + radius / 2 - xOffset * 6, radius - xOffset * 6 - yOffset, thickness * 6, thickness * 6, 0, 360);
        
        // Paint the second external disk
        g2d.setPaint(paintArc2);
        g2d.fillArc(1 + radius / 2 - xOffset * 5, radius - xOffset * 5 - yOffset, thickness * 5, thickness * 5, ANGLE_OFFSET - (openingAngle / 2), openingAngle);
        
        // Paint the second internal disk
        g2d.setColor(parentBackground);
        g2d.fillArc(1 + radius / 2 - xOffset * 4, radius - xOffset * 4 - yOffset, thickness * 4, thickness * 4, 0, 360);
        
        // Paint the third external disk
        g2d.setPaint(paintArc3);
        g2d.fillArc(1 + radius / 2 - xOffset * 3, radius - xOffset * 3 - yOffset, thickness * 3, thickness * 3, ANGLE_OFFSET - (openingAngle / 2), openingAngle);
        
        // Paint the third internal disk
        g2d.setColor(parentBackground);
        g2d.fillArc(1 + radius / 2 - xOffset * 2, radius - xOffset * 2 - yOffset, thickness * 2, thickness * 2, 0, 360);
        
        // Paint the middle circle
        g2d.setPaint(paintArc4);
        g2d.fillArc(1 + radius / 2 - xOffset, radius - xOffset - yOffset - 7, thickness, thickness, 0, 360);
    }

    @Override
    public void update(Graphics g)
    {
        this.paintComponent(g);
    }
    
    @Override
    public void setSize(int x, int y)
    {
        this.setRadius(x);
    }
    
    @Override
    public void setSize(Dimension d)
    {
        this.setRadius(d.width);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    /**
     * Create a WiFi animated icon that has a 0 size
     * 
     * @param parentBackground The background color of the parent control
     */
    public WiFiAnimatedIcon(Color parentBackground)
    {
        this(1, parentBackground);
    }
    /**
     * Create a WiFi animated icon that has the specified size
     * 
     * @param radius The radius of the circle that represents the WiFi animated icon
     * @param parentBackground The background color of the parent control
     */
    public WiFiAnimatedIcon(int radius, Color parentBackground)
    {
        // Call the parent constructor
        super();
        // Set physical properties
        this.radius = radius;
        this.parentBackground = parentBackground;
        this.openingAngle = 70;
        super.setSize(radius + 2, radius + 2);
        this.thickness = radius * 2 / 8;
        // Set the default state of the WiFi icon
        this.iconPartStates = new State[] { State.BACKGROUND, State.BACKGROUND, State.BACKGROUND, State.BACKGROUND };
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Private methods">
    
    /**
     * Get the set of colors for the external arc of the WiFi icon
     * 
     * @return The set of colors for the external arc of the WiFi icon
     */
    private Color[] getExternalArcColors()
    {
        return getColorsForState(this.iconPartStates[0]);
    }
    /**
     * Get the set of colors for the middle arc of the WiFi icon
     * 
     * @return The set of colors for the middle arc of the WiFi icon
     */
    private Color[] getMiddleArcColors()
    {
        return getColorsForState(this.iconPartStates[1]);
    }
    /**
     * Get the set of colors for the internal arc of the WiFi icon
     * 
     * @return The set of colors for the internal arc of the WiFi icon
     */
    private Color[] getInternalArcColors()
    {
        return getColorsForState(this.iconPartStates[2]);
    }
    /**
     * Get the set of colors for the bottom circle of the WiFi icon
     * 
     * @return The set of colors for the bottom circle of the WiFi icon
     */
    private Color[] getCircleColors()
    {
        return getColorsForState(this.iconPartStates[3]);
    }
    /**
     * Get a set of colors from a state
     * 
     * @param state The state to determine which is the corresponding set of colors
     * @return A set of colors corresponding to the specified state
     */
    private Color[] getColorsForState(State state)
    {
        switch (state)
        {
            case GREEN:
                return GREEN_COLORS;
            case ORANGE:
                return ORANGE_COLORS;
            case RED:
                return RED_COLORS;
            case BACKGROUND:
                return DARK_GRAY_COLORS;
            default:
                return null;
        }
    }
    
    /**
     * Determine if all the parts of the WiFi icon has the same state
     * 
     * @return True if all the parts of the WiFi icon has the same state, false if not
     */
    private boolean hasUniqueState()
    {
        return (getExternalArcState() == getMiddleArcState() && getExternalArcState() == getInternalArcState() && getExternalArcState() == getCircleState());
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Public methods">
    
    /**
     * Get the state for the external arc of the WiFi icon
     * 
     * @return The state for the external arc of the WiFi icon
     */
    public State getExternalArcState()
    {
        return iconPartStates[0];
    }
    /**
     * Get the state for the middle arc of the WiFi icon
     * 
     * @return The state for the middle arc of the WiFi icon
     */
    public State getMiddleArcState()
    {
        return iconPartStates[1];
    }
    /**
     * Get the state for the internal arc of the WiFi icon
     * 
     * @return The state for the internal arc of the WiFi icon
     */
    public State getInternalArcState()
    {
        return iconPartStates[2];
    }
    /**
     * Get the state for the bottom circle of the WiFi icon
     * 
     * @return The state for the bottom circle of the WiFi icon
     */
    public State getCircleState()
    {
        return iconPartStates[3];
    }
    
    /**
     * Set the state for the external arc of the WiFi icon
     * 
     * @param state The state for the external arc of the WiFi icon
     */
    public void setExternalArcState(State state)
    {
        iconPartStates[0] = state;
        this.paintImmediately(this.getVisibleRect());
    }
    /**
     * Set the state for the middle arc of the WiFi icon
     * 
     * @param state The state for the middle arc of the WiFi icon
     */
    public void setMiddleArcState(State state)
    {
        iconPartStates[1] = state;
        this.paintImmediately(this.getVisibleRect());
    }
    /**
     * Set the state for the internal arc of the WiFi icon
     * 
     * @param state The state for the internal arc of the WiFi icon
     */
    public void setInternalArcState(State state)
    {
        iconPartStates[2] = state;
        this.paintImmediately(this.getVisibleRect());
    }
    /**
     * Set the state for the bottom circle of the WiFi icon
     * 
     * @param state The state for the bottom circle of the WiFi icon
     */
    public void setCircleState(State state)
    {
        iconPartStates[3] = state;
        this.paintImmediately(this.getVisibleRect());
    }
    
    /**
     * Set the state for all parts of the WiFi icon
     * 
     * @param state The state for all parts of the WiFi icon
     */
    public void setComponentState(State state)
    {
        iconPartStates[0] = state;
        iconPartStates[1] = state;
        iconPartStates[2] = state;
        iconPartStates[3] = state;
        this.paintImmediately(this.getVisibleRect());
    }
    
    /**
     * Resize the WiFi icon with the specified radius
     * 
     * @param radius The new radius of the WiFi icon
     */
    public void setRadius(int radius)
    {
        this.radius = radius;
        super.setSize(radius + 2, radius + 2);
        this.paintImmediately(this.getVisibleRect());
    }
    
    /**
     * Get the size of the WiFi icon
     * 
     * @return The radius of the WiFi icon
     */
    public int getRadius()
    {
        return this.radius;
    }
    
    // </editor-fold>
}