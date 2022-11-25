/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mapp;

import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author DELL
 */
public class POI extends ImageIcon {

    int locX;
    int locY;
    int drawX;
    int drawY;
    final String desc;
    public POI(int x,int y,String name){
        locX = x;
        locY = y;
        drawX=x-16;
        drawY=y-32;
        System.out.println("X: "+locX+" Y: "+locY+" dX: "+drawX+" dY: "+drawY);
        desc=name;
        
        try{
            File myIcon = new File("src/main/resources/images/icons/pin.png");
            this.setImage(ImageIO.read(myIcon));
        }
        catch(IOException imgloss){
            
        }
        
    }
    public boolean contains(int x, int y,double mul){
        
        if(x>=locX*mul-16 && x<=(locX*mul+16)&& y>=locY*mul-32 && y<=(locY*mul)){
            return true;
        }
        return false;
        
    }
    public String toString(){
        return desc;
    }
    
    
}
