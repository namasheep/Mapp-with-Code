/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.mapp;

import java.awt.Graphics;
import java.io.File;
import javax.swing.JPanel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author DELL
 */
public class POILayerTest {
    POI testPOI;
    public POILayerTest() {
        testPOI = new POI(80,80,"kk","dd",0,true,0,"Test");
    }
    
    @BeforeAll
    public static void setUpClass() {
        
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /*
     * Test of paintComponent method, of class POILayer.
     * Test Case for overridden paintComponent untestable (not useful test)
    @Test
    public void testPaintComponent() {
        System.out.println("paintComponent");
        
       
        POILayer instance = new POILayer();
        Graphics g = instance.getGraphics();
        JPanel p = new JPanel();
        Graphics g2 = p.getGraphics();
        POI i = new POI(80,80,"kk","dd",0,true,0,"Test");
        instance.ACPOIs.add(i);
        g2.drawImage(i.imIcon, (int)(i.locX*1-16), (int)(i.locY*1-32), p);
        instance.paintComponent(g);
        assertEquals(instance.getGraphics(), p.getGraphics());
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    

    /**
     * Test of setSizeMul method, of class POILayer.
     */
    @Test
    public void testSetSizeMul() {
        System.out.println("setSizeMul");
        double mul = 1.0;
        POILayer instance = new POILayer();
        instance.setSizeMul(mul);
        double result = instance.sizeMul;
        System.out.println(result+", "+mul);
        assertEquals(mul,result,0);
        
    }

    /**
     * Test of findAndSetHigh method, of class POILayer.
     */
    @Test
    public void testFindAndSetHigh() {
        System.out.println("findAndSetHigh");
        
        POILayer instance = new POILayer();
        instance.ACPOIs.add(testPOI);
        instance.findAndSetHigh(testPOI);
        // TODO review the generated test code and remove the default call to fail.
        assertTrue(instance.highPOI.equals(testPOI));
        
    }

    /**
     * Test of setHigh method, of class POILayer.
     */
    @Test
    public void testSetHigh() {
        System.out.println("setHigh");
        
        POILayer instance = new POILayer();
        instance.ACPOIs.add(testPOI);
        instance.setHigh(testPOI);
        assertTrue(instance.highPOI.equals(testPOI));
    }

    /**
     * Test of clickContain method, of class POILayer.
     */
    @Test
    public void testClickContain() {
        System.out.println("clickContain");
        int x = 80;
        int y = 75;
        
        POILayer instance = new POILayer();
        instance.ACPOIs.add(testPOI);
        instance.sizeMul = 1;
        POI expResult = testPOI;
        POI result = instance.clickContain(x,y);
        assertTrue(expResult.equals(result));
        
    }

    /**
     * Test of addPOI method, of class POILayer.
     */
    @Test
    public void testAddPOI() {
        System.out.println("addPOI");
        
        
        POILayer instance = new POILayer();
        
        instance.addPOI(testPOI);
        assertTrue(instance.navPOIs.contains(testPOI));
        
    }

    /**
     * Test of Reset method, of class POILayer.
     */
    @Test
    public void testReset() {
        System.out.println("Reset");
        POILayer instance = new POILayer();
        instance.addPOI(testPOI);
        instance.highPOI = testPOI;
        instance.Reset();
        boolean resetWork = (instance.navPOIs.isEmpty()&&instance.classPOIs.isEmpty()&&instance.labsPOIs.isEmpty()&&instance.csPOIs.isEmpty()&&instance.resPOIs.isEmpty()&&instance.ACPOIs.isEmpty()&&instance.WRPOIs.isEmpty());
        assertTrue(resetWork);
    }

    /**
     * Test of smallReset method, of class POILayer.
     */
    @Test
    public void testSmallReset() {
        System.out.println("smallReset");
        POILayer instance = new POILayer();
        instance.addPOI(testPOI);
        instance.highPOI = testPOI;
        double newMul = 88;
        instance.sizeMul = newMul;
        
        instance.smallReset();
        boolean resetWork = (instance.navPOIs.isEmpty()&&instance.classPOIs.isEmpty()&&instance.labsPOIs.isEmpty()&&instance.csPOIs.isEmpty()&&instance.resPOIs.isEmpty()&&instance.ACPOIs.isEmpty()&&instance.WRPOIs.isEmpty());
        // TODO review the generated test code and remove the default call to fail.
        assertTrue(resetWork);
    }

    /**
     * Test of addApplicable method, of class POILayer.
     */
    @Test
    public void testAddApplicable() {
        System.out.println("addApplicable");
        String filePath = "mockData.txt";
        String building = "opfipsd";
        int floor = 0;
        POILayer instance = new POILayer();
        instance.addApplicable(filePath, building, floor);
        // TODO review the generated test code and remove the default call to fail.
        assertTrue(instance.navPOIs.isEmpty());
    }

    /**
     * Test of moveingPOI method, of class POILayer.
     */
    @Test
    public void testMoveingPOI() {
        System.out.println("moveingPOI");
        POI m = null;
        POILayer instance = new POILayer();
        instance.moveingPOI(m);
        // TODO review the generated test code and remove the default call to fail.
        assertNull(instance.dragPOI);
    }

    /**
     * Test of setPOIsFromFile method, of class POILayer.
     */
    @Test
    public void testSetPOIsFromFile() {
        System.out.println("setPOIsFromFile");
        String filePath = "mockData.txt";
        
        
        int Floor = 0;
        POILayer instance = new POILayer();
        
        instance.setPOIsFromFile(filePath, Floor);
        // TODO review the generated test code and remove the default call to fail.
        assertTrue(instance.navPOIs.isEmpty());
    }
    
}
