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
 *Object to hold location data for map manipulation
 * @author Group 7
 * @version 1.0
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
    final static int AC = 5;
    final static int WR = 6;
    int floor;
    String building;
    boolean userCreated;
    boolean highlighted = false;
    boolean dragging=false;

    int POIType;

    /**
     *
     * @param x - X coordinate
     * @param y - y coordinate
     * @param name - name of poi
     * @param desc - description of poi
     * @param type - poi type
     * @param userCreated - lean if poi is user created
     * @param floor - floor poi is on
     * @param building - building poi is in
     *
     *
     * POI Constructor creates new POI
     * @Throws IO
     */
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
          String fpath = "src/main/resources/images/icons/pin.png";
          switch(type){
              case(0):
                  fpath = "src/main/resources/images/icons/pinNavigation.png";

              break;
              case(1):
                  fpath = "src/main/resources/images/icons/pinClassroom.png";

              break;
              case(2):
                  fpath = "src/main/resources/images/icons/pinRestaurant.png";

              break;
              case(3):
                  fpath = "src/main/resources/images/icons/pinLab.png";

              break;
              case(4):
                  fpath = "src/main/resources/images/icons/pinCSPOI.png";

              break;
              case(5):
                  fpath = "src/main/resources/images/icons/pinAccessibility.png";

              break;
              case(6):
                  fpath = "src/main/resources/images/icons/pinWashroom.png";

              break;

          }

          File myIcon = new File(fpath);
          imIcon = (ImageIO.read(myIcon));
      }
      catch(IOException imgloss){

      }

  }

    
    /**
     *
     * @param x - x coordinate
     * @param y - y coordinate
     * @param mul - multiple for coordinates
     * @return returns boolean based on if poi is contained
     *
     * boolean to return if the POI is where selected, the size mul allows for scaling
     * @throws IOException
     */
    public boolean contains(int x, int y,double mul){

        if(x>=locX*mul-16 && x<=(locX*mul+16)&& y>=locY*mul-32 && y<=(locY*mul)){
            return true;
        }
        return false;

    }
    /**
     *
     * @return string of all related POI params
     */
    public String toStringSave(){
        return locX+","+locY+","+name+","+desc+","+POIType+","+userCreated+","+floor+","+building;
    }

    /**
     *
     * @return POI name
     */
    public String toString(){
        return name;
    }

    /**
     *
     * @param other - other POI for comparison
     * @return
     *
     * Compares POIs against each other
     */
    public boolean equals(POI other){
        if(locX==other.locX&&locY==other.locY&&name.equals(other.name)&&building.equals(other.building)&&floor==other.floor&&desc.equals(other.desc)&&POIType==other.POIType){
            return true;
        }
        return false;
    }



}
