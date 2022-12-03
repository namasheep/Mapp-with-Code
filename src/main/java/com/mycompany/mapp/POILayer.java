/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mapp;

import java.awt.Graphics;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JPanel;
import java.util.ArrayList;
import javax.imageio.ImageIO;


/**
 *
 * @author DELL
 */
public class POILayer extends JPanel {
    
    ArrayList<POI> navPOIs = new ArrayList<POI>();
    ArrayList<POI> classPOIs = new ArrayList<POI>();
    ArrayList<POI> resPOIs = new ArrayList<POI>();
    ArrayList<POI> labsPOIs = new ArrayList<POI>();
    ArrayList<POI> csPOIs = new ArrayList<POI>();
    ArrayList<POI> ACPOIs = new ArrayList<POI>();
    ArrayList<POI> WRPOIs = new ArrayList<POI>();
    boolean displayUser = true;
    boolean displayNav = true;
    boolean displayClass = true;
    boolean displayRes = true;
    boolean displayLabs = true;
    boolean displayCS = true;
    boolean displayAC = true;
    boolean displayWR = true;
    double sizeMul = 1;
    Image highIm;
    POI highPOI=null;
    POI dragPOI=null;
    int dragX = 0;
    int dragY = 0;
    public POILayer(){
        super();
        try{
            highIm=ImageIO.read(new File("src/main/resources/images/icons/pinHigh.png"));
        }
        catch(Exception e){
            
        }
        
        
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        if(displayClass){
            for(POI i:classPOIs){
                if(((i.userCreated&&displayUser)||!i.userCreated)&&!i.dragging){
                    if(i.highlighted){
                        System.out.println("fdfdfd");
                        g.drawImage(highIm, (int)(i.locX*sizeMul-16), (int)(i.locY*sizeMul-32), this);
                    }
                    else{
                      g.drawImage(i.imIcon, (int)(i.locX*sizeMul-16), (int)(i.locY*sizeMul-32), this);  
                    }
                    
                }
                
            
            }
        }
        if(displayNav){
            for(POI i:navPOIs){
                if(((i.userCreated&&displayUser)||!i.userCreated)&&!i.dragging){
                    if(i.highlighted){
                        System.out.println("fdfdfd");
                        g.drawImage(highIm, (int)(i.locX*sizeMul-16), (int)(i.locY*sizeMul-32), this);
                    }
                    else{
                      g.drawImage(i.imIcon, (int)(i.locX*sizeMul-16), (int)(i.locY*sizeMul-32), this);  
                    }
                }
                
            
            }
        }
        if(displayRes){
            for(POI i:resPOIs){
                if(((i.userCreated&&displayUser)||!i.userCreated)&&!i.dragging){
                    if(i.highlighted){
                        System.out.println("fdfdfd");
                        g.drawImage(highIm, (int)(i.locX*sizeMul-16), (int)(i.locY*sizeMul-32), this);
                    }
                    else{
                      g.drawImage(i.imIcon, (int)(i.locX*sizeMul-16), (int)(i.locY*sizeMul-32), this);  
                    }
                }
                
            
            }
        }
        
        if(displayLabs){
            for(POI i:labsPOIs){
                if(((i.userCreated&&displayUser)||!i.userCreated)&&!i.dragging){
                    if(i.highlighted){
                        System.out.println("fdfdfd");
                        g.drawImage(highIm, (int)(i.locX*sizeMul-16), (int)(i.locY*sizeMul-32), this);
                    }
                    else{
                      g.drawImage(i.imIcon, (int)(i.locX*sizeMul-16), (int)(i.locY*sizeMul-32), this);  
                    }
                }
                
            
            }
        }
        
        if(displayCS){
            for(POI i:csPOIs){
                if(((i.userCreated&&displayUser)||!i.userCreated)&&!i.dragging){
                    if(i.highlighted){
                        System.out.println("fdfdfd");
                        g.drawImage(highIm, (int)(i.locX*sizeMul-16), (int)(i.locY*sizeMul-32), this);
                    }
                    else{
                      g.drawImage(i.imIcon, (int)(i.locX*sizeMul-16), (int)(i.locY*sizeMul-32), this);  
                    }
                }
                
            
            }
        }
        if(displayAC){
            for(POI i:ACPOIs){
                if(((i.userCreated&&displayUser)||!i.userCreated)&&!i.dragging){
                    if(i.highlighted){
                        System.out.println("fdfdfd");
                        g.drawImage(highIm, (int)(i.locX*sizeMul-16), (int)(i.locY*sizeMul-32), this);
                    }
                    else{
                      g.drawImage(i.imIcon, (int)(i.locX*sizeMul-16), (int)(i.locY*sizeMul-32), this);  
                    }
                }
                
            
            }
        }
        if(displayWR){
            for(POI i:WRPOIs){
                if(((i.userCreated&&displayUser)||!i.userCreated)&&!i.dragging){
                    if(i.highlighted){
                        System.out.println("fdfdfd");
                        g.drawImage(highIm, (int)(i.locX*sizeMul-16), (int)(i.locY*sizeMul-32), this);
                    }
                    else{
                      g.drawImage(i.imIcon, (int)(i.locX*sizeMul-16), (int)(i.locY*sizeMul-32), this);  
                    }
                }
                
            
            }
        }
        
        
        if(dragPOI!=null){
            paintDrag(g);
        }
        
        
    
    }
    private void paintDrag(Graphics g){
        g.drawImage(dragPOI.imIcon,(int)(dragX-16), (int)(dragY-32), this);
    }
    public void setSizeMul(double mul){
        sizeMul=mul;
    }
    public void findAndSetHigh(POI m){
        /*
        ArrayList<POI> navPOIs = new ArrayList<POI>();
    ArrayList<POI> classPOIs = new ArrayList<POI>();
    ArrayList<POI> resPOIs = new ArrayList<POI>();
    ArrayList<POI> labsPOIs = new ArrayList<POI>();
    ArrayList<POI> csPOIs
        */
        for(POI i:navPOIs){
            if(i.equals(m)){
                setHigh(i);
            }
        }
        for(POI i:classPOIs){
            if(i.equals(m)){
                setHigh(i);
            }
        }
        for(POI i:resPOIs){
            if(i.equals(m)){
                setHigh(i);
            }
        }
        for(POI i:labsPOIs){
            if(i.equals(m)){
                setHigh(i);
            }
        }
        for(POI i:csPOIs){
            if(i.equals(m)){
                setHigh(i);
            }
        }
        for(POI i:ACPOIs){
            if(i.equals(m)){
                setHigh(i);
            }
        }
        for(POI i:WRPOIs){
            if(i.equals(m)){
                setHigh(i);
            }
        }
        
    }
    
    public void setHigh(POI newHigh){
        if(highPOI!=null){
            highPOI.highlighted=false;
        }
        if(newHigh!=null){
            newHigh.highlighted=true;
        }
        
        highPOI = newHigh;
    }
    public POI clickContain(int x,int y){
        POI ret=null;
        double dist=-1;
        if(displayNav){
            for(POI i:navPOIs){
                if(i.contains(x,y,sizeMul)&&((i.userCreated&&displayUser)||!i.userCreated)){

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
            
        }
        if(displayClass){
            for(POI i:classPOIs){
                if(i.contains(x,y,sizeMul)&&((i.userCreated&&displayUser)||!i.userCreated)){

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
            
        }
        if(displayRes){
            for(POI i:resPOIs){
                if(i.contains(x,y,sizeMul)&&((i.userCreated&&displayUser)||!i.userCreated)){

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
            
        }
        if(displayLabs){
            for(POI i:labsPOIs){
                if(i.contains(x,y,sizeMul)&&((i.userCreated&&displayUser)||!i.userCreated)){

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
            
        }
        if(displayCS){
            for(POI i:csPOIs){
                if(i.contains(x,y,sizeMul)&&((i.userCreated&&displayUser)||!i.userCreated)){

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
            
        }
        if(displayAC){
            for(POI i:ACPOIs){
                if(i.contains(x,y,sizeMul)&&((i.userCreated&&displayUser)||!i.userCreated)){

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
            
        }
        if(displayWR){
            for(POI i:WRPOIs){
                if(i.contains(x,y,sizeMul)&&((i.userCreated&&displayUser)||!i.userCreated)){

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
            
        }
        
        return ret;
    }
    private double calcDist(double x1,double y1,int x2, int y2){
        return Math.sqrt(Math.pow((x2-x1),2)+Math.pow((y2-y1),2));
    }
    public void addPOI(POI newPOI){
        if(newPOI.POIType==POI.CLASSROOMS){
            classPOIs.add(newPOI);
            
        }
        else if(newPOI.POIType==POI.NAVIGATION){
            navPOIs.add(newPOI);
        }
        else if(newPOI.POIType==POI.RESTURANTS){
            resPOIs.add(newPOI);
        }
        else if(newPOI.POIType==POI.LABS){
            labsPOIs.add(newPOI);
        }
        else if(newPOI.POIType==POI.CS){
            csPOIs.add(newPOI);
        }
        else if(newPOI.POIType==POI.AC){
            ACPOIs.add(newPOI);
        }
        else if(newPOI.POIType==POI.WR){
            WRPOIs.add(newPOI);
        }
        
    }
    public void Reset(){
        /*
        save code
        */

        classPOIs.clear();
        navPOIs.clear();
        resPOIs.clear();
        labsPOIs.clear();
        csPOIs.clear();
        ACPOIs.clear();
        WRPOIs.clear();
        highPOI=null;
        dragPOI=null;
        sizeMul = 1;
    }
    public void smallReset(){
        classPOIs.clear();
        navPOIs.clear();
        resPOIs.clear();
        labsPOIs.clear();
        csPOIs.clear();
        ACPOIs.clear();
        WRPOIs.clear();
        highPOI=null;
        dragPOI=null;
    }

    /*public void setPOIs(ArrayList<POI> newList){
        myPOIs = newList;
    }*/
    public void addApplicable(String filePath,String building,int floor){
        try{
          BufferedReader reader = new BufferedReader(new FileReader(filePath));  
          String line = null;
          while((line = reader.readLine()) != null){
              if(!line.trim().isEmpty()){
                  System.out.println(line);
                    String[] POIComp = line.split(",");
                    if(POIComp[7].equals(building)&&floor==Integer.parseInt(POIComp[6])){
                        POI newPOI = new POI(Integer.parseInt(POIComp[0]),Integer.parseInt(POIComp[1]),POIComp[2],POIComp[3],Integer.parseInt(POIComp[4]),Boolean.valueOf(POIComp[5]),floor,POIComp[7]);
                    addPOI(newPOI);
                    }
                    
                }
              }
          
                  
              
              
        }
        catch(IOException e){
            
        }
        
    }
    public void moveingPOI(POI m){
        if(dragPOI!=null){
            dragPOI.dragging=false;
        }
        if(m!=null){
            m.dragging = true;
        }
        
        dragPOI = m;
    }
    
    public void setPOIsFromFile(String filePath,int Floor){
        try{
          BufferedReader reader = new BufferedReader(new FileReader(filePath));  
          String line = null;
          
          while((line = reader.readLine()) != null){
              if(!line.trim().isEmpty()){
                  System.out.println(line);
                    String[] POIComp = line.split(",");
                    POI newPOI = new POI(Integer.parseInt(POIComp[0]),Integer.parseInt(POIComp[1]),POIComp[2],POIComp[3],Integer.parseInt(POIComp[4]),Boolean.valueOf(POIComp[5]),Floor,POIComp[7]);
                    addPOI(newPOI);
                }
              }
          
                  
              
              
        }
        catch(IOException e){
            
        }
        
    }
    
}
