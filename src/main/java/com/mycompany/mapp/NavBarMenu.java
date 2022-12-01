/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mapp;

import static com.mycompany.mapp.navBar.createImageIcon;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author DELL
 */
public class NavBarMenu extends JMenuBar {
    public NavBarMenu(){
       
        
        JMenu menu, submenu;
        JMenuItem menuItem;
        //JRadioButtonMenuItem rbMenuItem;
        JCheckBoxMenuItem cbMenuItem;
 
        //Create the menu bar.
        
        //create menu button 
 
        //Build the first menu.
        ImageIcon icon = createImageIcon("images.icons/pin.gif");
        menu = new JMenu("MApp");
        //menu.setMnemonic(KeyEvent.VK_A);
        //menu.getAccessibleContext().setAccessibleDescription(
                //"");
        this.add(menu);
        
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
        //jButtonSetVisible(True);jButtonCSPOI 
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
        //CS false
        
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
 

    
    }
}
