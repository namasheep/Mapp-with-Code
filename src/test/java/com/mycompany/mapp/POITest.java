/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.mapp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author valeria
 */
public class POITest {
    
    public POITest() {
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

    /**
     * Test of contains method, of class POI.
     */
    @Test
    public void testContainsF() {
        System.out.println("contains");
        int x = 100;
        int y = 100;
        double mul = 2.0;
        POI instance = new POI(100, 100, "POI", "", 1, true,1, "");
        // expecting text to fail 
        boolean expResult = false;
        boolean result = instance.contains(x, y, mul);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
        /**
     * Test of contains method, of class POI.
     */
    @Test
    public void testContainsP() {
        System.out.println("contains");
        int x = 100;
        int y = 100;
        double mul = 1.0;
        POI instance = new POI(100, 100, "POI", "", 1, true,1, "");
        // expecting text to pass 
        boolean expResult = true;
        boolean result = instance.contains(x, y, mul);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of toStringSave method, of class POI.
     */
    @Test
    public void testToStringSave() {
        System.out.println("toStringSave");
        POI instance = new POI(100, 100, "VALERIA", "hola", 1, true,1, "MSEXCOL");
        //locX+","+locY+","+name+","+desc+","+POIType+","+userCreated+","+floor+","+building
        String expResult = "100,100,VALERIA,hola,1,true,1,MSEXCOL";
        String result = instance.toStringSave();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class POI.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        POI instance = new POI(100, 100, "VALERIA", "", 1, true,1, "MSEXC");
        String expResult = "VALERIA";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class POI.
     */
    /*
    /*
    public boolean equals(POI other){
        if(locX==other.locX&&locY==other.locY&&name.equals(other.name)&&building.equals(other.building)&&floor==other.floor&&desc.equals(other.desc)&&POIType==other.POIType){
            return true;
        }
        return false;
    }
    */
    @Test
    public void testEquals() {
        System.out.println("equals");
        POI other = new POI(100, 100, "VALERIA", "", 1, true,1, "MSEXC");
        POI instance = new POI(100, 100, "VALERIA", "", 1, true,1, "MSEXC");
        boolean expResult = true;
        boolean result = instance.equals(other);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
