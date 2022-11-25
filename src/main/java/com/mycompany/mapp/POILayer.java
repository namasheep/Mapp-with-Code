/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mapp;

import java.awt.Graphics;
import javax.swing.JPanel;
import java.util.ArrayList;


/**
 *
 * @author DELL
 */
public class POILayer extends JPanel {
    ArrayList<POI> myPOIs = new ArrayList<POI>();
    double sizeMul = 1;
    
    public POILayer(){
        super();
        
        
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(POI i:myPOIs){
            g.drawImage(i.getImage(), (int)(i.locX*sizeMul-16), (int)(i.locY*sizeMul-32), this);
            
        }
    
    }
    public void setSizeMul(double mul){
        sizeMul=mul;
    }
    public POI clickContain(int x,int y){
        POI ret=null;
        double dist=-1;
        for(POI i:myPOIs){
            if(i.contains(x,y,sizeMul)){
               
                if(dist==-1){
                    ret=i;
                    
                }
                else{
                    if(calcDist(i.locX*sizeMul,i.locY*sizeMul-16,x,y)<dist){
                        ret = i;
                    }
                }
            }
        }
        return ret;
    }
    private double calcDist(double x1,double y1,int x2, int y2){
        return Math.sqrt(Math.pow((x2-x1),2)+Math.pow((y2-y1),2));
    }
    public void addPOI(POI newPOI){
        myPOIs.add(newPOI);
        
    }
    public void saveAndReset(){
        /*
        save code
        */
        myPOIs.clear();
        sizeMul = 1;
    }
    
}
