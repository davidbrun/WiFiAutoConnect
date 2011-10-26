/*
 * File:   TabbedPanel.java
 * Author: David Brun <brundavid@gmail.com>
 *
 * Created on 12 sept. 2011, 11:43:30
 */

package fr.davidbrun.wifiautoconnect.views;

import fr.davidbrun.wifiautoconnect.controllers.FrmMainController;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Provide a graphical component that represents a custom tabbed panel with a custom header
 * 
 * @author David Brun <brundavid@gmail.com>
 */
public class TabbedPanel extends JPanel
{
    // <editor-fold defaultstate="collapsed" desc="Constants">
    
    /**
     * Represent the width of the columns of the header
     */
    private int HEADER_COLUMN_WIDTH = 100;
    /**
     * Represent the height of the columns of the header
     */
    private int HEADER_COLUMN_HEIGHT = 52;
    /**
     * Represent the thickness of the borders in the header
     */
    private int HEADER_SEPARATOR_THICKNESS = 1;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Fields">
    
    // Swing components
    private JPanel header;
    private JPanel verticalSeparator;
    private JPanel body;
    private ArrayList<HeaderColumn> headerListColumns;
    private ArrayList<JPanel> listTabPanels;
    
    // Remember the last selected column
    private int previousSelectedColumnIndex = -1;
    
    
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    /**
     * Create a tabbed panel that contains 0 tab panels
     * 
     * @param d The size of the tabbed panel
     */
    public TabbedPanel(Dimension d)
    {
        this(d, 0);
    }
    /**
     * Create a tabbed panel that contains the specified number of tab panels
     * 
     * @param d The size of the tabbed panel
     * @param numberOfTabPanels The number of desired tab panels
     */
    public TabbedPanel(Dimension d, int numberOfTabPanels)
    {
        super();
        
        // Initialize the arrays
        this.header = new JPanel();
        this.verticalSeparator = new JPanel();
        this.body = new JPanel();
        this.listTabPanels = new ArrayList<JPanel>(numberOfTabPanels);
        this.headerListColumns = new ArrayList<HeaderColumn>(numberOfTabPanels);
        this.previousSelectedColumnIndex = -1;
        // Initialize the swing components
        this.initComponents(d);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Public methods">
    
    /**
     * Add a tab panel with the specified properties
     * 
     * @param tabName The name of the tab panel
     * @param tabIcon The icon of the tab panel
     * @param tabPanel The panel that contains components
     */
    public void addTabPanel(String tabName, ImageIcon tabIcon, JPanel tabPanel)
    {
        listTabPanels.add(tabPanel);
        
        // Add a column in the header
        addHeaderSubPanel(tabName, tabIcon);
    }
    
    /**
     * Set the tab panel at the specified index, or do nothing if the index is not valid
     * 
     * @param index The index of the tab panel to set
     * @param tabName The name of the tab panel
     * @param tabIcon The icon of the tab panel
     * @param tabPanel The panel that contains components
     */
    public void setTabPanel(int index, String tabName, ImageIcon tabIcon, JPanel tabPanel)
    {
        if (index >= 0 && index < listTabPanels.size())
        {
            listTabPanels.set(index, tabPanel);
            
            // Update the header
            updateHeader(index, tabName, tabIcon);
        }
    }
    
    /**
     * Get the tab panel at the specified index, or null if the index is not valid
     * 
     * @param index The index of the desired tab panel
     * @return The desired tab panel
     */
    public JPanel getTabPanel(int index)
    {
        if (index >= 0 && index < listTabPanels.size())
            return listTabPanels.get(index);
        else
            return null;
    }
    
    /**
     * Select the specified tab panel
     * 
     * @param index The index of the tab panel to select
     */
    public void selectTabPanel(int index)
    {
        if (index >= 0 && index < listTabPanels.size())
        {
            // Deselect the previous column
            if (previousSelectedColumnIndex >= 0)
            {
                body.remove(listTabPanels.get(previousSelectedColumnIndex));
                headerListColumns.get(previousSelectedColumnIndex).setColumnBackground(FrmMainController.WINDOW_HEADER_BACKGROUND);
                headerListColumns.get(previousSelectedColumnIndex).setBordersBackground(FrmMainController.WINDOW_HEADER_BACKGROUND);
            }
            // Change the body
            JPanel tmp = listTabPanels.get(index);
            body.add(tmp);
            tmp.setLocation(body.getWidth() / 2 - tmp.getWidth() / 2, body.getHeight() / 2 - tmp.getHeight() / 2);
            body.repaint(tmp.getBounds());
            // Select the new column
            headerListColumns.get(index).setColumnBackground(FrmMainController.WINDOW_BOTTOM_BACKGROUND);
            headerListColumns.get(index).setBordersBackground(Color.gray);
            // Remember the column index
            previousSelectedColumnIndex = index;
        }
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Private methods">
    
    /**
     * Initialize the swing components
     * 
     * @param d The size of the tabbed panel
     */
    private void initComponents(Dimension d)
    {   
        setSize(this, d);
        
        // Initialize the header
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));
        setSize(header, new Dimension(d.width, HEADER_COLUMN_HEIGHT));
        header.setBackground(FrmMainController.WINDOW_HEADER_BACKGROUND);
        
        // Initialize the vertical separator
        setSize(verticalSeparator, new Dimension(d.width, HEADER_SEPARATOR_THICKNESS));
        verticalSeparator.setBackground(Color.gray);
        
        // Initialize the body
        body.setLayout(null);
        setSize(body, new Dimension(d.width, d.height - HEADER_COLUMN_HEIGHT - HEADER_SEPARATOR_THICKNESS));
        body.setBackground(FrmMainController.WINDOW_TOP_BACKGROUND);
        
        // Add the components
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        header.setVisible(true);
        verticalSeparator.setVisible(true);
        body.setVisible(true);
        header.setAlignmentY(Component.TOP_ALIGNMENT);
        header.setAlignmentX(Component.CENTER_ALIGNMENT);
        verticalSeparator.setAlignmentY(Component.CENTER_ALIGNMENT);
        verticalSeparator.setAlignmentX(Component.CENTER_ALIGNMENT);
        body.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        body.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(header);
        this.add(verticalSeparator);
        this.add(body);
    }
    
    /**
     * Add a column in the header panel
     * 
     * @param tabName The name of the tab panel
     * @param tabIcon The icon of the tab panel 
     */
    private void addHeaderSubPanel(String tabName, ImageIcon tabIcon)
    {
        // Create the column
        HeaderColumn toAdd = createHeaderColumn(tabName, tabIcon);
        toAdd.getColumn().setAlignmentX(Component.LEFT_ALIGNMENT);
        toAdd.getColumn().setAlignmentY(Component.CENTER_ALIGNMENT);
        header.add(toAdd.getColumn());
        
        // Store the column
        headerListColumns.add(toAdd);
    }
    
    /**
     * Update the specified column in the header panel
     * 
     * @param index The index of the column to update
     * @param tabName The name of the tab panel
     * @param tabIcon The icon of the tab panel 
     */
    private void updateHeader(int index, String tabName, ImageIcon tabIcon)
    {
        HeaderColumn tmp = headerListColumns.get(index);
        tmp = null;
        
        // Re-create the column
        headerListColumns.get(index).setLabelCaption(tabName);
        headerListColumns.get(index).setLabelIcon(tabIcon);
    }
    
    /**
     * Create a column to the header
     * 
     * @param tabName The name of the tab panel
     * @param tabIcon The icon of the tab panel 
     */
    private HeaderColumn createHeaderColumn(String tabName, ImageIcon tabIcon)
    {
        // Create the content of the column
        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        JLabel labelIcon = new JLabel(tabIcon);
        JLabel labelCaption = new JLabel(tabName);
        labelIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelIcon.setAlignmentY(Component.TOP_ALIGNMENT);
        labelCaption.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelCaption.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        center.add(Box.createVerticalGlue());
        center.add(Box.createVerticalGlue());
        center.add(labelIcon);
        center.add(Box.createVerticalGlue());
        center.add(labelCaption);
        setSize(center, new Dimension(HEADER_COLUMN_WIDTH - 2 * HEADER_SEPARATOR_THICKNESS, HEADER_COLUMN_HEIGHT));
        // Create the column with borders
        JPanel result = new JPanel();
        result.setLayout(new BoxLayout(result, BoxLayout.X_AXIS));
        JLabel leftBorder = new JLabel();
        setSize(leftBorder, new Dimension(HEADER_SEPARATOR_THICKNESS, HEADER_COLUMN_HEIGHT));
        JLabel rightBorder = new JLabel();
        setSize(rightBorder, new Dimension(HEADER_SEPARATOR_THICKNESS, HEADER_COLUMN_HEIGHT));
        leftBorder.setAlignmentX(Component.LEFT_ALIGNMENT);
        leftBorder.setAlignmentY(Component.CENTER_ALIGNMENT);
        center.setAlignmentX(Component.CENTER_ALIGNMENT);
        center.setAlignmentY(Component.CENTER_ALIGNMENT);
        rightBorder.setAlignmentX(Component.RIGHT_ALIGNMENT);
        rightBorder.setAlignmentY(Component.CENTER_ALIGNMENT);
        result.add(leftBorder);
        result.add(center);
        result.add(rightBorder);
        setSize(result, new Dimension(HEADER_COLUMN_WIDTH, HEADER_COLUMN_HEIGHT));
        center.setBackground(FrmMainController.WINDOW_HEADER_BACKGROUND);
        result.setBackground(FrmMainController.WINDOW_HEADER_BACKGROUND);
        // The click listener
        result.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                int newIndex = getIndexFromJPanel((JPanel)e.getSource());
                if (newIndex != previousSelectedColumnIndex)
                    selectTabPanel(newIndex);
            }
        });
        // Return the result
        return new HeaderColumn(result, center, labelIcon, labelCaption);
    }
    
    /**
     * Fix the size of a swing component
     * 
     * @param target The component to fix the size
     * @param d The new size of the component
     */
    private void setSize(JComponent target, Dimension d)
    {
        target.setMinimumSize(d);
        target.setMaximumSize(d);
        target.setPreferredSize(d);
        target.setSize(d);
    }
    
    /**
     * Find the index of a panel in the list of the header columns
     * 
     * @param panel The panel we want to know the index
     * @return The index of the specified panel
     */
    private int getIndexFromJPanel(JPanel panel)
    {
        int result = -1;
        for (int i = 0; i < headerListColumns.size(); i++)
            if (headerListColumns.get(i).getColumn().equals(panel))
            {
                result = i;
                break;
            }
        
        return result;
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Private class">
    
    /**
     * Represent a holder to store data about header columns
     */
    private class HeaderColumn
    {
        // Fields
        private JPanel column;
        private JPanel columnCenter;
        private JLabel labelIcon;
        private JLabel labelCaption;
        
        /**
         * Create a holder with the required data
         * 
         * @param column The panel that represents the column
         * @param columnCenter The content of the column (without the borders)
         * @param labelIcon The label used to display the icon
         * @param labelCaption The label used to display the caption
         */
        public HeaderColumn(JPanel column, JPanel columnCenter, JLabel labelIcon, JLabel labelCaption)
        {
            this.column = column;
            this.columnCenter = columnCenter;
            this.labelIcon = labelIcon;
            this.labelCaption = labelCaption;
        }
        
        /**
         * Return the panel that represents the column
         * 
         * @return The panel that represents the column
         */
        public JPanel getColumn()
        {
            return column;
        }
        /**
         * Set the caption of the column
         * 
         * @param caption The new caption of the column
         */
        public void setLabelCaption(String caption)
        {
            this.labelCaption.setText(caption);
        }
        /**
         * Set the icon of the column
         * 
         * @param icon The new icon of the column
         */
        public void setLabelIcon(ImageIcon icon)
        {
            this.labelIcon.setIcon(icon);
        }
        /**
         * Set the background color of the column
         * 
         * @param backgroundColor The new background color of the column
         */
        public void setColumnBackground(Color backgroundColor)
        {
            this.columnCenter.setBackground(backgroundColor);
        }
        /**
         * Set the background color of the borders of the column
         * 
         * @param backgroundColor The new background color of the borders of the column
         */
        public void setBordersBackground(Color backgroundColor)
        {
            this.column.setBackground(backgroundColor);
        }
    }
    
    // </editor-fold>
}