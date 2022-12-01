/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.mapp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import com.mycompany.mapp.navBar;



import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author DELL
 */


public class AppWindow extends javax.swing.JFrame {
    boolean admin = true;
    boolean navBarON = false;
    int floorIndex=0;
    String buildingName = "STAB";
    String buildingPath = "src/main/resources/images/Floors/"+buildingName;
    final String POIPath = "src/main/resources/POI info/";
    File buildingLocation = new File(buildingPath);
    String[] buildingNameFileList = buildingLocation.list();
    String imgURL = buildingPath+"/"+buildingNameFileList[floorIndex];
    double zoomMul = 1;
    int xCirc = 0;
    int yCirc = 0;
    /**
     * Creates new form AppWindow
     */
    public AppWindow(boolean admin) {
        initComponents();
        AutoCompleteDecorator.decorate(searchBox);
        AutoCompleteDecorator.decorate(BoxPOIAdd);
        this.admin = admin;
        
        

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jOptionPanePOIOption = new javax.swing.JOptionPane();
        jDialog1 = new javax.swing.JDialog();
        jPanelAddPOI = new javax.swing.JPanel();
        jTextFieldPOIAddName = new javax.swing.JTextField();
        jLabelPOIAddName = new javax.swing.JLabel();
        jTextFieldPOIAddDesc = new javax.swing.JTextField();
        jLabelPOIAddDesc = new javax.swing.JLabel();
        BoxPOIAdd = new javax.swing.JComboBox<>();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        topPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jSeparator1 = new javax.swing.JSeparator();
        searchBox = new javax.swing.JComboBox<>();
        zoomPanel = new javax.swing.JPanel();
        plusZoomButton = new javax.swing.JButton();
        minusZoomButton = new javax.swing.JButton();
        jPanelLayerButtons = new javax.swing.JPanel();
        jButtonAccessibility = new javax.swing.JButton();
        jButtonWashrooms = new javax.swing.JButton();
        jButtonClassrooms = new javax.swing.JButton();
        jButtonRestaurants = new javax.swing.JButton();
        jButtonLabs = new javax.swing.JButton();
        jButtonUserPOIs = new javax.swing.JButton();
        jButtonLegend = new javax.swing.JButton();
        jButtonNavigation = new javax.swing.JButton();
        jButtonCSPOIs = new javax.swing.JButton();
        jButtonLayers = new javax.swing.JButton();
        mapPanel = new javax.swing.JPanel();
        mapImageScrollPane = new javax.swing.JScrollPane();
        jLayeredPane3 = new javax.swing.JLayeredPane();
        jPanel1 = new javax.swing.JPanel();
        mapImage = new javax.swing.JLabel();
        pOILayer1 = new com.mycompany.mapp.POILayer();
        navBarMenu1 = new com.mycompany.mapp.NavBarMenu();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        jOptionPanePOIOption.setForeground(new java.awt.Color(255, 51, 51));
        jOptionPanePOIOption.setWantsInput(true);
        jOptionPanePOIOption.setAutoscrolls(true);
        jOptionPanePOIOption.setDoubleBuffered(true);
        jOptionPanePOIOption.setVisible(false);

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jLabelPOIAddName.setText("Name:");

        jLabelPOIAddDesc.setText("Description");

        BoxPOIAdd.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Navigation", "Classroom", "Resturant", "Lab" }));

        javax.swing.GroupLayout jPanelAddPOILayout = new javax.swing.GroupLayout(jPanelAddPOI);
        jPanelAddPOI.setLayout(jPanelAddPOILayout);
        jPanelAddPOILayout.setHorizontalGroup(
            jPanelAddPOILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAddPOILayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelPOIAddName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelAddPOILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelAddPOILayout.createSequentialGroup()
                        .addGroup(jPanelAddPOILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelPOIAddDesc)
                            .addComponent(BoxPOIAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelAddPOILayout.createSequentialGroup()
                        .addGroup(jPanelAddPOILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextFieldPOIAddDesc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                            .addComponent(jTextFieldPOIAddName))
                        .addGap(55, 55, 55))))
        );
        jPanelAddPOILayout.setVerticalGroup(
            jPanelAddPOILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAddPOILayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanelAddPOILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldPOIAddName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelPOIAddName))
                .addGap(18, 18, 18)
                .addComponent(BoxPOIAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelPOIAddDesc)
                .addGap(18, 18, 18)
                .addComponent(jTextFieldPOIAddDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLayeredPane1.setLayout(new javax.swing.OverlayLayout(jLayeredPane1));

        topPanel.setBackground(new java.awt.Color(255, 255, 255));
        topPanel.setOpaque(false);

        jPanel2.setBackground(new java.awt.Color(213, 213, 213));

        jToggleButton1.setBackground(new java.awt.Color(213, 213, 213));
        jToggleButton1.setForeground(new java.awt.Color(213, 213, 213));
        jToggleButton1.setBorder(null);
        jToggleButton1.setBorderPainted(false);
        jToggleButton1.setContentAreaFilled(false);
        jToggleButton1.setFocusPainted(false);

        searchBox.setBackground(new java.awt.Color(213, 213, 213));
        searchBox.setEditable(true);
        searchBox.setModel(new DefaultComboBoxModel(buildingNameFileList));
        searchBox.setBorder(null);
        searchBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                searchBoxItemStateChanged(evt);
            }
        });
        searchBox.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                searchBoxPopupMenuWillBecomeVisible(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(searchBox, 0, 283, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(searchBox, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                    .addComponent(jToggleButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        zoomPanel.setLayout(new javax.swing.BoxLayout(zoomPanel, javax.swing.BoxLayout.PAGE_AXIS));

        plusZoomButton.setText("+");
        plusZoomButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plusZoomButtonActionPerformed(evt);
            }
        });
        zoomPanel.add(plusZoomButton);

        minusZoomButton.setText("-");
        minusZoomButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minusZoomButtonActionPerformed(evt);
            }
        });
        zoomPanel.add(minusZoomButton);

        jPanelLayerButtons.setBackground(new java.awt.Color(204, 204, 255));

        jButtonAccessibility.setText("Accessibility");

        jButtonWashrooms.setText("Washrooms");

        jButtonClassrooms.setText("Classrooms");
        jButtonClassrooms.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonClassroomsMouseClicked(evt);
            }
        });

        jButtonRestaurants.setText("Restaurants");
        jButtonRestaurants.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonRestaurantsMouseClicked(evt);
            }
        });

        jButtonLabs.setText("Labs");
        jButtonLabs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonLabsMouseClicked(evt);
            }
        });

        jButtonUserPOIs.setText("User POIs");
        jButtonUserPOIs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonUserPOIsMouseClicked(evt);
            }
        });

        jButtonLegend.setText("Legend");

        jButtonNavigation.setText("Navigation");
        jButtonNavigation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonNavigationMouseClicked(evt);
            }
        });

        jButtonCSPOIs.setText("CS POIs");
        jButtonCSPOIs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonCSPOIsMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanelLayerButtonsLayout = new javax.swing.GroupLayout(jPanelLayerButtons);
        jPanelLayerButtons.setLayout(jPanelLayerButtonsLayout);
        jPanelLayerButtonsLayout.setHorizontalGroup(
            jPanelLayerButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayerButtonsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonLegend, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonAccessibility)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonWashrooms, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonNavigation, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonClassrooms)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonRestaurants, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonLabs, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonUserPOIs, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonCSPOIs, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanelLayerButtonsLayout.setVerticalGroup(
            jPanelLayerButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLayerButtonsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelLayerButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButtonCSPOIs, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                    .addComponent(jButtonLegend, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonUserPOIs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonLabs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonRestaurants, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonClassrooms, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonAccessibility, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonWashrooms, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonNavigation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jButtonLayers.setText("Layers");
        jButtonLayers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLayersActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(topPanelLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(topPanelLayout.createSequentialGroup()
                        .addComponent(jButtonLayers, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                        .addComponent(jPanelLayerButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 204, Short.MAX_VALUE)
                        .addComponent(zoomPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))))
        );
        topPanelLayout.setVerticalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 230, Short.MAX_VALUE)
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonLayers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelLayerButtons, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(31, 31, 31))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(zoomPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        jLayeredPane1.setLayer(topPanel, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.add(topPanel);

        mapImageScrollPane.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        mapImage.setIcon(new ImageIcon(imgURL));
        mapImage.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        mapImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mapImageMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mapImage, javax.swing.GroupLayout.DEFAULT_SIZE, 1348, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mapImage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
        );

        pOILayer1.setOpaque(false);
        pOILayer1.setPOIsFromFile(POIPath+buildingName+"/"+buildingName+"-"+floorIndex+".txt");
        pOILayer1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pOILayer1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pOILayer1Layout = new javax.swing.GroupLayout(pOILayer1);
        pOILayer1.setLayout(pOILayer1Layout);
        pOILayer1Layout.setHorizontalGroup(
            pOILayer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1348, Short.MAX_VALUE)
        );
        pOILayer1Layout.setVerticalGroup(
            pOILayer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 537, Short.MAX_VALUE)
        );

        jLayeredPane3.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(pOILayer1, javax.swing.JLayeredPane.PALETTE_LAYER);

        javax.swing.GroupLayout jLayeredPane3Layout = new javax.swing.GroupLayout(jLayeredPane3);
        jLayeredPane3.setLayout(jLayeredPane3Layout);
        jLayeredPane3Layout.setHorizontalGroup(
            jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1348, Short.MAX_VALUE)
            .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pOILayer1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane3Layout.setVerticalGroup(
            jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 537, Short.MAX_VALUE)
            .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pOILayer1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mapImageScrollPane.setViewportView(jLayeredPane3);

        javax.swing.GroupLayout mapPanelLayout = new javax.swing.GroupLayout(mapPanel);
        mapPanel.setLayout(mapPanelLayout);
        mapPanelLayout.setHorizontalGroup(
            mapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mapImageScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1360, Short.MAX_VALUE)
        );
        mapPanelLayout.setVerticalGroup(
            mapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mapImageScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
        );

        jLayeredPane1.add(mapPanel);

        jMenu1.setText("File");
        navBarMenu1.add(jMenu1);

        jMenu2.setText("Edit");
        navBarMenu1.add(jMenu2);

        setJMenuBar(navBarMenu1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
        private void savePOIs(POI poiSave,String filePath){
        
        try{
            FileWriter POIFile = new FileWriter(filePath,true);
            BufferedWriter POIwrite = new BufferedWriter(POIFile);
            
                System.out.println(poiSave.toString());
                POIwrite.write(poiSave.toString());
                POIwrite.newLine();
            

            POIwrite.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    private void mapImageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mapImageMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_mapImageMouseClicked

    private void pOILayer1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pOILayer1MouseClicked
        // TODO add your handling code here:
        POI clickedPOI = pOILayer1.clickContain(evt.getX(), evt.getY());
        if(clickedPOI==null && admin){
            int result = jOptionPanePOIOption.showConfirmDialog(null, jPanelAddPOI, 
               "POI Info", jOptionPanePOIOption.OK_CANCEL_OPTION);

            
            //jOptionPanePOIOption.setVisible(true);
            
            if(result==JOptionPane.OK_OPTION){
                POI newPOI = new POI((int)(evt.getX()/zoomMul),(int)(evt.getY()/zoomMul),jTextFieldPOIAddName.getText(),jTextFieldPOIAddDesc.getText(),BoxPOIAdd.getSelectedIndex(),true);
                savePOIs(newPOI,POIPath+buildingName+"/"+buildingName+"-"+floorIndex+".txt");
                System.out.println(evt.getX()+" "+evt.getY());
                pOILayer1.addPOI(newPOI);
                pOILayer1.repaint();
                
            }
            
            
        }
        else{
            
        }
        System.out.println(clickedPOI);
        
    }//GEN-LAST:event_pOILayer1MouseClicked

    private void jButtonLayersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLayersActionPerformed
        if (jPanelLayerButtons.isShowing()) {
            jPanelLayerButtons.setVisible(false);
        }
        else {
            jPanelLayerButtons.setVisible(true);
        }
    }//GEN-LAST:event_jButtonLayersActionPerformed

    private void minusZoomButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minusZoomButtonActionPerformed
        // TODO add your handling code here:
        File im = new File(imgURL);
        try{
            zoomMul*=0.5;

            System.out.println("Before");
            System.out.println(mapImageScrollPane.getViewport().getViewPosition());
            System.out.println(mapImageScrollPane.getViewport().getExtentSize());
            Image image = ImageIO.read(im); // transform it
            Image newimg = image.getScaledInstance((int)(image.getWidth(rootPane)*zoomMul), (int)(image.getHeight(rootPane)*zoomMul),  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            mapImage.setIcon(new ImageIcon(newimg));
            Point old = mapImageScrollPane.getViewport().getViewPosition();

            mapImageScrollPane.getViewport().setViewPosition(new Point(old.x/4,old.y/4));
            System.out.println("AFter");
            System.out.println(mapImageScrollPane.getViewport().getViewPosition());
            System.out.println(mapImageScrollPane.getViewport().getExtentSize());
            pOILayer1.sizeMul=zoomMul;
        }
        catch(IOException imgMiss){
            System.out.println("Missing image");

        }

    }//GEN-LAST:event_minusZoomButtonActionPerformed

    /*private ArrayList<POI> getPOIsFromFile(String filePath){
        ArrayList<POI> POIRead = new ArrayList<POI>();
        try{
          BufferedReader reader = new BufferedReader(new FileReader(filePath));  
          String line = null;
          while((line = reader.readLine()) != null){
              String[] POIComp = line.split(",");
              POI newPOI = new POI(Integer.parseInt(POIComp[1]),Integer.parseInt(POIComp[2]),POIComp[0]);
              POIRead.add(newPOI);
          }
        }
        catch(IOException e){
            
        }
        return POIRead;
    }*/
    private void plusZoomButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plusZoomButtonActionPerformed
        // TODO add your handling code here:
        // load the image to a imageIcon
        File im = new File(imgURL);

        try{
            zoomMul*=2;
            /* System.out.println("Before");
            System.out.println(mapImageScrollPane.getViewport().getViewPosition());

            System.out.println(mapImageScrollPane.getViewport().getExtentSize());*/
            Image image = ImageIO.read(im); // transform it
            Image newimg = image.getScaledInstance((int)(image.getWidth(rootPane)*zoomMul), (int)(image.getHeight(rootPane)*zoomMul),  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            mapImage.setIcon(new ImageIcon(newimg));
            Rectangle view = mapImageScrollPane.getViewport().getViewRect();
            Dimension size = mapImageScrollPane.getViewport().getExtentSize();

            view.setBounds(view.x*2+size.width/2,view.y+size.height/2,view.width,view.height);

            mapImage.scrollRectToVisible(view);

            /*System.out.println("After");
            System.out.println(mapImageScrollPane.getViewport().getViewPosition());

            System.out.println(mapImageScrollPane.getViewport().getExtentSize());*/
            pOILayer1.sizeMul=zoomMul;

        }
        catch(IOException imgMiss){
            System.out.println("Missing image");

        }

        // transform it back
    }//GEN-LAST:event_plusZoomButtonActionPerformed

    private void searchBoxPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_searchBoxPopupMenuWillBecomeVisible
        // TODO add your handling code here:
    }//GEN-LAST:event_searchBoxPopupMenuWillBecomeVisible

    private void searchBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_searchBoxItemStateChanged
        // TODO add your handling code here:

        if(searchBox.getSelectedIndex()==-1){
            return;
        }

        floorIndex=searchBox.getSelectedIndex();
        imgURL = buildingPath+"/"+buildingNameFileList[floorIndex];
        mapImage.setIcon(new ImageIcon(imgURL));

        pOILayer1.setPOIsFromFile(POIPath+buildingName+"/"+buildingName+"-"+floorIndex+".txt");
        pOILayer1.saveAndReset();

        zoomMul = 1;

    }//GEN-LAST:event_searchBoxItemStateChanged

    private void jButtonClassroomsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonClassroomsMouseClicked
        // TODO add your handling code here:
        pOILayer1.displayClass = !pOILayer1.displayClass;
        pOILayer1.repaint();
    }//GEN-LAST:event_jButtonClassroomsMouseClicked

    private void jButtonRestaurantsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonRestaurantsMouseClicked
        // TODO add your handling code here:
        pOILayer1.displayRes = !pOILayer1.displayRes;
        pOILayer1.repaint();
    }//GEN-LAST:event_jButtonRestaurantsMouseClicked

    private void jButtonLabsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonLabsMouseClicked
        // TODO add your handling code here:
        pOILayer1.displayLabs = !pOILayer1.displayLabs;
        pOILayer1.repaint();
    }//GEN-LAST:event_jButtonLabsMouseClicked

    private void jButtonUserPOIsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonUserPOIsMouseClicked
        // TODO add your handling code here:
        pOILayer1.displayUser = !pOILayer1.displayUser;
        pOILayer1.repaint();
    }//GEN-LAST:event_jButtonUserPOIsMouseClicked

    private void jButtonNavigationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonNavigationMouseClicked
        // TODO add your handling code here:
        pOILayer1.displayNav = !pOILayer1.displayNav;
        pOILayer1.repaint();
    }//GEN-LAST:event_jButtonNavigationMouseClicked

    private void jButtonCSPOIsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonCSPOIsMouseClicked
        // TODO add your handling code here:
        pOILayer1.displayCS = !pOILayer1.displayCS;
        pOILayer1.repaint();
    }//GEN-LAST:event_jButtonCSPOIsMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AppWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AppWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AppWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AppWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AppWindow(true).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> BoxPOIAdd;
    private javax.swing.JButton jButtonAccessibility;
    private javax.swing.JButton jButtonCSPOIs;
    private javax.swing.JButton jButtonClassrooms;
    private javax.swing.JButton jButtonLabs;
    private javax.swing.JButton jButtonLayers;
    private javax.swing.JButton jButtonLegend;
    private javax.swing.JButton jButtonNavigation;
    private javax.swing.JButton jButtonRestaurants;
    private javax.swing.JButton jButtonUserPOIs;
    private javax.swing.JButton jButtonWashrooms;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabelPOIAddDesc;
    private javax.swing.JLabel jLabelPOIAddName;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JOptionPane jOptionPanePOIOption;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelAddPOI;
    private javax.swing.JPanel jPanelLayerButtons;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextFieldPOIAddDesc;
    private javax.swing.JTextField jTextFieldPOIAddName;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JLabel mapImage;
    private javax.swing.JScrollPane mapImageScrollPane;
    private javax.swing.JPanel mapPanel;
    private javax.swing.JButton minusZoomButton;
    private com.mycompany.mapp.NavBarMenu navBarMenu1;
    private com.mycompany.mapp.POILayer pOILayer1;
    private javax.swing.JButton plusZoomButton;
    private javax.swing.JComboBox<String> searchBox;
    private javax.swing.JPanel topPanel;
    private javax.swing.JPanel zoomPanel;
    // End of variables declaration//GEN-END:variables
    
}
