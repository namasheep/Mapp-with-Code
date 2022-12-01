/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mapp;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author DELL
 */
public class POI {

    int locX;
    int locY;
    int drawX;
    int drawY;
    String name;
    String desc;
    final static int NAVIGATION = 0;
    Image imIcon;
    final static int CLASSROOMS = 1;
    final static int RESTURANTS = 2;
    final static int LABS = 3;
    final static int CS = 4;
    int floor;
    String building;
    boolean userCreated;
    boolean highlighted = false;
    boolean dragging=false;
    
    int POIType;
    public POI(int x,int y,String name,String desc,int type,boolean userCreated,int floor,String building){
        locX = x;
        locY = y;
        drawX=x-16;
        drawY=y-32;
        this.floor = floor;
        this.building = building;
        System.out.println("X: "+locX+" Y: "+locY+" dX: "+drawX+" dY: "+drawY);
        this.name = name;
        this.desc = desc;
        POIType = type;
        this.userCreated = userCreated;
        try{
            File myIcon = new File("src/main/resources/images/icons/pin.png");
            imIcon = (ImageIO.read(myIcon));
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
    public String toStringSave(){
        return locX+","+locY+","+name+","+desc+","+POIType+","+userCreated+","+floor+","+building;
    }
    public String toString(){
        return name;
    }
    
    
    public boolean equals(POI other){
        if(locX==other.locX&&locY==other.locY&&name.equals(other.name)&&building.equals(other.building)&&floor==other.floor&&desc.equals(other.desc)&&POIType==other.POIType){
            return true;
        }
        return false;
    }
    
    
    
}
