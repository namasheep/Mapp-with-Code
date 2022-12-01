/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mapp;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import com.mycompany.mapp.AppWindow;
     

/**
 *
 * @author valeria
 */
public class navBar extends JMenuBar{
    JTextArea output;
    JScrollPane scrollPane;
    public navBar(){
        createMenuBar();
    }
    
    public JMenuBar createMenuBar() {
        JMenuBar menuBar;
        JMenu menu, submenu;
        JMenuItem menuItem;
        //JRadioButtonMenuItem rbMenuItem;
        JCheckBoxMenuItem cbMenuItem;
 
        //Create the menu bar.
        menuBar = new JMenuBar();
        
        //create menu button 
 
        //Build the first menu.
        ImageIcon icon = createImageIcon("images.icons/pin.gif");
        menu = new JMenu("MApp");
        //menu.setMnemonic(KeyEvent.VK_A);
        //menu.getAccessibleContext().setAccessibleDescription(
                //"");
        menuBar.add(menu);
        
        //layers 
        //a group of JMenuItems
        menuItem = new JMenuItem("Layers");
        menu.add(menuItem);
        menu.addSeparator();
 
        //a group of check box menu items
        //menu.addSeparator();
        cbMenuItem = new JCheckBoxMenuItem("Personal POIs");
        cbMenuItem.setMnemonic(KeyEvent.VK_C);
        menu.add(cbMenuItem);
 
        cbMenuItem = new JCheckBoxMenuItem("Favourites");
        cbMenuItem.setMnemonic(KeyEvent.VK_H);
        menu.add(cbMenuItem);
 
        // submenu - Middlesex collge 
        menu.addSeparator();
        submenu = new JMenu("Middlesex College");
        submenu.setMnemonic(KeyEvent.VK_S);
        //building = "MC";
 
        menuItem = new JMenuItem("First Floor");
        submenu.add(menuItem);
        //floorIndex = 1;
 
        menuItem = new JMenuItem("Second Floor");
        submenu.add(menuItem);
        menu.add(submenu);
        //floorIndex = 2;
        
        menuItem = new JMenuItem("Third Floor");
        submenu.add(menuItem);
        //floorIndex = 3;
        
        menuItem = new JMenuItem("Fourth Floor");
        submenu.add(menuItem);
        menu.add(submenu);
        //floorIndex = 4;
        
        // sub menu - staging building 
        menu.addSeparator();
        submenu = new JMenu("Staging Building");
        //building = "STAB";
        
        menuItem = new JMenuItem("First Floor");
        submenu.add(menuItem);
        //floorIndex = 1;
 
        menuItem = new JMenuItem("Second Floor");
        submenu.add(menuItem);
        menu.add(submenu);
        //floorIndex = 2;
        
        menuItem = new JMenuItem("Third Floor");
        submenu.add(menuItem);
        menu.add(submenu);
        //floorIndex = 3;
        
        // sub menu - TD Stadium 
        menu.addSeparator();
        submenu = new JMenu("TD Stadium");
        //building = 'STADIUM';
        
        menuItem = new JMenuItem("First Floor");
        submenu.add(menuItem);
        //floorIndex = 1;
 
        menuItem = new JMenuItem("Second Floor");
        submenu.add(menuItem);
        menu.add(submenu);
        //floorIndex = 2;
        
        menuItem = new JMenuItem("Third Floor");
        submenu.add(menuItem);
        menu.add(submenu);
        //floorIndex = 3;
 
        return menuBar;
    }
 
    public Container createContentPane() {
        //Create the content-pane-to-be.
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setOpaque(true);
 
        //Create a scrolled text area.
        output = new JTextArea(5, 30);
        output.setEditable(false);
        scrollPane = new JScrollPane(output);
 
        //Add the text area to the content pane.
        contentPane.add(scrollPane, BorderLayout.CENTER);
 
        return contentPane;
    }
 
    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = navBar.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
 
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("MApp");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Create and set up the content pane.
        navBar demo = new navBar();
        frame.setJMenuBar(demo.createMenuBar());
        frame.setContentPane(demo.createContentPane());
 
        //Display the window.
        frame.setSize(450, 260);
        frame.setVisible(true);
    }
 
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}


