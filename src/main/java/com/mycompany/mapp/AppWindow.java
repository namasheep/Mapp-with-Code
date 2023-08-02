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

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.IntStream;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.commons.io.FileUtils;



import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;



/**
 * Appwindow GUI that handles Map application and file manipulation
 * 
 * @author Group 7
 */
public class AppWindow extends javax.swing.JFrame {
    boolean admin = true;
    boolean navBarON = false;
    int floorIndex=0;
    ArrayList<POI> userPOIs = new ArrayList<POI>();
    ArrayList<String> buildingsAll;
    String buildingName = "MC";
    String buildingPath = "src/main/resources/images/Floors/";
    String buildingLayerPath = "src/main/resources/images/Layers/"; 
    final String POIPath = "src/main/resources/POI info/";
    File buildingLocation = new File(buildingPath+buildingName);
    String[] buildingNameFileList = buildingLocation.list();
    String imgURL = buildingPath+buildingName+"/"+buildingNameFileList[floorIndex];
    double zoomMul = 1;
    ArrayList<POI> buildAllPOIs = new ArrayList<POI>();
    ArrayList<POI> favPOIs = new ArrayList<POI>();
    String username;
    Integer xDrag = null;
    Integer yDrag = null;
    POI draggedPOI = null;
    POI selectedPOI = null;
    boolean editing = false;
    Weathertest2 mm = new Weathertest2();
    String weather;
    
    
    /**
     * Creates new form AppWindow
     * @param admin - whether user is admin or not
     * @param username - username for user
     * @
     */
    public AppWindow(boolean admin,String username) {
        this.admin = admin;
        System.out.println(buildingLayerPath+buildingName+"/"+buildingName+"-ac-"+floorIndex+".png");
        this.username=username;
        buildingsAll = getItemsFromFile("src/main/resources/Building info/buildings.txt");
        changeBuildPOIs();
        weather = mm.temperature;
        initComponents();
        AutoCompleteDecorator.decorate(searchBox);
        
        AutoCompleteDecorator.decorate(jComboBoxFavSearch);
        
        if(!this.admin){
            loadFavouritesData();
        }
        
        
        
        
        
        
        
        
        

    }
    /**
     * checks fvaourites for passed in POI
     * @param temp
     * @return whether temp is in favourites
     */
    private boolean favouriteIncludes(POI temp){
        for(POI m:favPOIs){
            if(m.equals(temp)){
                return true;
            }
        }
        return false;
    }
    /**
     * loads in favourites data from file
     */
    private void loadFavouritesData(){
        favPOIs.clear();
        try{
          BufferedReader reader = new BufferedReader(new FileReader(POIPath+"favourites/"+"favourites.txt"));  
          String line = null;
          
          while((line = reader.readLine()) != null){
              if(!line.trim().isEmpty()){
                  System.out.println(line);
                  String[] POIComp = line.split(",");
                  if(username.equals(POIComp[8])){
                      POI newPOI = new POI(Integer.parseInt(POIComp[0]),Integer.parseInt(POIComp[1]),POIComp[2],POIComp[3],Integer.parseInt(POIComp[4]),Boolean.valueOf(POIComp[5]),Integer.parseInt(POIComp[6]),POIComp[7]);
                      favPOIs.add(newPOI);
                  }
                  
                  
                }
              }
          
                  
              
              
        }
        catch(IOException e){
            
        }
        
    }
    /**
     * loads List of POIs from file
     * @param filePath - path of metafile
     * @return ArrayList of POI from metafile
     */
    private ArrayList<POI> getPOIFromFile(String filePath){
        ArrayList<POI> m = new ArrayList<POI>();
        try{
          BufferedReader reader = new BufferedReader(new FileReader(filePath));  
          String line = null;
          
          while((line = reader.readLine()) != null){
              if(!line.trim().isEmpty()){
                  System.out.println(line);
                  String[] POIComp = line.split(",");
                  POI newPOI = new POI(Integer.parseInt(POIComp[0]),Integer.parseInt(POIComp[1]),POIComp[2],POIComp[3],Integer.parseInt(POIComp[4]),Boolean.valueOf(POIComp[5]),Integer.parseInt(POIComp[6]),POIComp[7]);
                  m.add(newPOI);
                }
              }
          
                  
              
              
        }
        catch(IOException e){
            
        }
        return m;
    }
    /**
     * reloads the POIs for the current building from meta data
     */
    private void changeBuildPOIs(){
        buildAllPOIs.clear();
        
          int lenFiles = new File(buildingPath+buildingName).list().length;
          for(int i = 0;i<lenFiles;i++){
              try{
                BufferedReader reader = new BufferedReader(new FileReader(POIPath+buildingName+"/"+buildingName+"-"+i+".txt"));  
                String line = null;
                while((line = reader.readLine()) != null){
                if(!line.trim().isEmpty()){
                    
                    String[] POIComp = line.split(",");
                    POI newPOI = new POI(Integer.parseInt(POIComp[0]),Integer.parseInt(POIComp[1]),POIComp[2],POIComp[3],Integer.parseInt(POIComp[4]),Boolean.valueOf(POIComp[5]),i,POIComp[7]);
                    buildAllPOIs.add(newPOI);
                  }
                }
              }
              catch(Exception e){
                  
              }
                           
          }
          if(!admin){//gets user POIS as well if not an admin
              ArrayList<POI> userP = new ArrayList<POI>();
              try{
                  BufferedReader reader = new BufferedReader(new FileReader(POIPath+"users"+"/"+username+".txt"));  
                    String line = null;
                    while((line = reader.readLine()) != null){
                    if(!line.trim().isEmpty()){
                        
                        String[] POIComp = line.split(",");
                        POI newPOI = new POI(Integer.parseInt(POIComp[0]),Integer.parseInt(POIComp[1]),POIComp[2],POIComp[3],Integer.parseInt(POIComp[4]),Boolean.valueOf(POIComp[5]),Integer.parseInt(POIComp[6]),POIComp[7]);
                        if(POIComp[7].equals(buildingName)){
                            buildAllPOIs.add(newPOI);
                        }
                        userP.add(newPOI);
                        
                        
                      }
                    }
              }
              catch(Exception e){
                  
              }
              userPOIs = userP;
          }
          
                  
              
              
        
        
    }
    /**
     * gets items line by line from file
     * @param filePath - path for file
     * @return - ArrayList of data from file lines
     */
    private ArrayList<String> getItemsFromFile(String filePath){
        ArrayList<String> temp = new ArrayList<String>();
        try{
          BufferedReader reader = new BufferedReader(new FileReader(filePath));  
          String line = null;
          while((line = reader.readLine()) != null){
              if(!line.trim().isEmpty()){
                  System.out.println(line);
                    temp.add(line);
                }
              }
                  
              
              
        }
        catch(IOException e){
            
        }
        return temp;
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
        jPanelAddPOI = new javax.swing.JPanel();
        jTextFieldPOIAddName = new javax.swing.JTextField();
        jLabelPOIAddName = new javax.swing.JLabel();
        jTextFieldPOIAddDesc = new javax.swing.JTextField();
        jLabelPOIAddDesc = new javax.swing.JLabel();
        BoxPOIAdd = new javax.swing.JComboBox<>();
        jDialogError = new javax.swing.JDialog();
        jFileChooser1 = new javax.swing.JFileChooser();
        jOptionPaneEdit = new javax.swing.JOptionPane();
        jPanelAddBuilding = new javax.swing.JPanel();
        jLabelAddBuildingName = new javax.swing.JLabel();
        jTextFieldAddBuilding = new javax.swing.JTextField();
        jButtonAddBuilding = new javax.swing.JButton();
        jTextFieldAddBuildingPath = new javax.swing.JTextField();
        jPanelAddFloor = new javax.swing.JPanel();
        jLabelAddFloor = new javax.swing.JLabel();
        jComboBoxAddFloor = new javax.swing.JComboBox<>();
        jButtonAddFloor = new javax.swing.JButton();
        jTextFieldAddFloorPath = new javax.swing.JTextField();
        jPanelRemoveFloor = new javax.swing.JPanel();
        jComboBoxRemoveFloorBuild = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jComboBoxRemoveFloorNum = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jPanelEditPOI = new javax.swing.JPanel();
        jLabelEditPOI = new javax.swing.JLabel();
        jTextFieldEditPOIName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldEditPOIDesc = new javax.swing.JTextField();
        jComboBoxEditPOI = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jCheckBoxDeletePOI = new javax.swing.JCheckBox();
        jPanelFavSearch = new javax.swing.JPanel();
        jLabelSearchFav = new javax.swing.JLabel();
        jComboBoxFavSearch = new javax.swing.JComboBox<>();
        jPanelDeleteBuilding = new javax.swing.JPanel();
        jComboBoxDeleteBuild = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jMenuItem1 = new javax.swing.JMenuItem();
        jPanelUserSearch = new javax.swing.JPanel();
        jLabelUserSearch = new javax.swing.JLabel();
        jComboBoxUserSearch = new javax.swing.JComboBox<>();
        jPanelHelp = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        topPanel = new javax.swing.JPanel();
        zoomPanel = new javax.swing.JPanel();
        plusZoomButton = new javax.swing.JButton();
        minusZoomButton = new javax.swing.JButton();
        jPanelLayerButtons = new javax.swing.JPanel();
        jToggleButtonNav = new javax.swing.JToggleButton();
        jToggleButtonClass = new javax.swing.JToggleButton();
        jToggleButtonRes = new javax.swing.JToggleButton();
        jToggleButtonLab = new javax.swing.JToggleButton();
        jToggleButtonCS = new javax.swing.JToggleButton();
        jToggleButtonAC = new javax.swing.JToggleButton();
        jToggleButtonWR = new javax.swing.JToggleButton();
        jToggleButtonUserPOI = new javax.swing.JToggleButton();
        jButtonLayers = new javax.swing.JButton();
        jPanelUpDown = new javax.swing.JPanel();
        jButtonUpFloor = new javax.swing.JButton();
        jButtonDownFloor = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        searchBox = new javax.swing.JComboBox<>();
        jPanelPOIInfo = new javax.swing.JPanel();
        jLabelPOIName = new javax.swing.JLabel();
        jLabelPOIDesc = new javax.swing.JLabel();
        jButtonEditPOI = new javax.swing.JButton();
        jButtonFav = new javax.swing.JButton();
        jLabelFloor = new javax.swing.JLabel();
        jPanelWeather = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabelWeather = new javax.swing.JLabel();
        mapPanel = new javax.swing.JPanel();
        mapImageScrollPane = new javax.swing.JScrollPane();
        jLayeredPane3 = new javax.swing.JLayeredPane();
        jPanel1 = new javax.swing.JPanel();
        mapImage = new javax.swing.JLabel();
        pOILayer1 = new com.mycompany.mapp.POILayer();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        jPanel3 = new javax.swing.JPanel();
        jLabelAC = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabelWC = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu6 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu3.setVisible(this.admin);
        jMenuItemAddBuilding = new javax.swing.JMenuItem();
        jMenuItemAddFloor = new javax.swing.JMenuItem();
        jMenuItemRemoveBuilding = new javax.swing.JMenuItem();
        jMenuItemRemoveFloor = new javax.swing.JMenuItem();
        jMenuLogout = new javax.swing.JMenu();
        jMenuItemLogout = new javax.swing.JMenuItem();
        jMenuUserPOI = new javax.swing.JMenu();
        jMenuHelp = new javax.swing.JMenu();

        jOptionPanePOIOption.setForeground(new java.awt.Color(255, 51, 51));
        jOptionPanePOIOption.setWantsInput(true);
        jOptionPanePOIOption.setAutoscrolls(true);
        jOptionPanePOIOption.setDoubleBuffered(true);
        jOptionPanePOIOption.setVisible(false);

        jLabelPOIAddName.setText("Name:");

        jLabelPOIAddDesc.setText("Description");

        BoxPOIAdd.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Navigation", "Classroom", "Restaurant", "Lab", "CS POI", "Accessibility", "Washroom" }));
        BoxPOIAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoxPOIAddActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jDialogErrorLayout = new javax.swing.GroupLayout(jDialogError.getContentPane());
        jDialogError.getContentPane().setLayout(jDialogErrorLayout);
        jDialogErrorLayout.setHorizontalGroup(
            jDialogErrorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialogErrorLayout.setVerticalGroup(
            jDialogErrorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jFileChooser1.setAcceptAllFileFilterUsed(false);
        jFileChooser1.setFileFilter(new FileNameExtensionFilter("file png","png"));

        jLabelAddBuildingName.setText("Name of Building");

        jButtonAddBuilding.setText("Choose Base Floor file");
        jButtonAddBuilding.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonAddBuildingMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanelAddBuildingLayout = new javax.swing.GroupLayout(jPanelAddBuilding);
        jPanelAddBuilding.setLayout(jPanelAddBuildingLayout);
        jPanelAddBuildingLayout.setHorizontalGroup(
            jPanelAddBuildingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAddBuildingLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanelAddBuildingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelAddBuildingLayout.createSequentialGroup()
                        .addComponent(jLabelAddBuildingName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldAddBuilding, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelAddBuildingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButtonAddBuilding, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextFieldAddBuildingPath)))
                .addContainerGap(96, Short.MAX_VALUE))
        );
        jPanelAddBuildingLayout.setVerticalGroup(
            jPanelAddBuildingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAddBuildingLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanelAddBuildingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelAddBuildingName)
                    .addComponent(jTextFieldAddBuilding, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addComponent(jButtonAddBuilding)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldAddBuildingPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(109, Short.MAX_VALUE))
        );

        jLabelAddFloor.setText("Select Building");

        jComboBoxAddFloor.setModel(new DefaultComboBoxModel(buildingsAll.toArray()));

        jButtonAddFloor.setText("Add Floor Map");
        jButtonAddFloor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonAddFloorMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanelAddFloorLayout = new javax.swing.GroupLayout(jPanelAddFloor);
        jPanelAddFloor.setLayout(jPanelAddFloorLayout);
        jPanelAddFloorLayout.setHorizontalGroup(
            jPanelAddFloorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAddFloorLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanelAddFloorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonAddFloor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelAddFloorLayout.createSequentialGroup()
                        .addComponent(jLabelAddFloor)
                        .addGap(36, 36, 36)
                        .addComponent(jComboBoxAddFloor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextFieldAddFloorPath))
                .addContainerGap(72, Short.MAX_VALUE))
        );
        jPanelAddFloorLayout.setVerticalGroup(
            jPanelAddFloorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAddFloorLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanelAddFloorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelAddFloor)
                    .addComponent(jComboBoxAddFloor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61)
                .addComponent(jButtonAddFloor, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextFieldAddFloorPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(84, Short.MAX_VALUE))
        );

        jComboBoxRemoveFloorBuild.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxRemoveFloorBuild.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxRemoveFloorBuildItemStateChanged(evt);
            }
        });

        jLabel1.setText("Building");

        jComboBoxRemoveFloorNum.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("Floor");

        javax.swing.GroupLayout jPanelRemoveFloorLayout = new javax.swing.GroupLayout(jPanelRemoveFloor);
        jPanelRemoveFloor.setLayout(jPanelRemoveFloorLayout);
        jPanelRemoveFloorLayout.setHorizontalGroup(
            jPanelRemoveFloorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelRemoveFloorLayout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addGroup(jPanelRemoveFloorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelRemoveFloorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBoxRemoveFloorNum, 0, 119, Short.MAX_VALUE)
                    .addComponent(jComboBoxRemoveFloorBuild, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(66, 66, 66))
        );
        jPanelRemoveFloorLayout.setVerticalGroup(
            jPanelRemoveFloorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRemoveFloorLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanelRemoveFloorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxRemoveFloorBuild, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(37, 37, 37)
                .addGroup(jPanelRemoveFloorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxRemoveFloorNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap(165, Short.MAX_VALUE))
        );

        jLabelEditPOI.setText("Name");

        jLabel5.setText("Desc.");

        jComboBoxEditPOI.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Navigation", "Classroom", "Restaurant", "Lab", "CS POI", "Accessibility", "Washroom" }));

        jLabel4.setText("POI Type");

        jCheckBoxDeletePOI.setText("Delete");
        jCheckBoxDeletePOI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxDeletePOIActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelEditPOILayout = new javax.swing.GroupLayout(jPanelEditPOI);
        jPanelEditPOI.setLayout(jPanelEditPOILayout);
        jPanelEditPOILayout.setHorizontalGroup(
            jPanelEditPOILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEditPOILayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanelEditPOILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelEditPOILayout.createSequentialGroup()
                        .addGroup(jPanelEditPOILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabelEditPOI))
                        .addGap(29, 29, 29)
                        .addGroup(jPanelEditPOILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldEditPOIName, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                            .addComponent(jTextFieldEditPOIDesc)))
                    .addGroup(jPanelEditPOILayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanelEditPOILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jComboBoxEditPOI, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBoxDeletePOI))))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanelEditPOILayout.setVerticalGroup(
            jPanelEditPOILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEditPOILayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanelEditPOILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelEditPOI)
                    .addComponent(jTextFieldEditPOIName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanelEditPOILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldEditPOIDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(jComboBoxEditPOI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBoxDeletePOI)
                .addGap(13, 13, 13))
        );

        jLabelSearchFav.setText("Search Favourites");

        javax.swing.GroupLayout jPanelFavSearchLayout = new javax.swing.GroupLayout(jPanelFavSearch);
        jPanelFavSearch.setLayout(jPanelFavSearchLayout);
        jPanelFavSearchLayout.setHorizontalGroup(
            jPanelFavSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFavSearchLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabelSearchFav)
                .addGap(18, 18, 18)
                .addComponent(jComboBoxFavSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanelFavSearchLayout.setVerticalGroup(
            jPanelFavSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFavSearchLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanelFavSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSearchFav, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxFavSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(63, Short.MAX_VALUE))
        );

        jLabel6.setText("Delete Building");

        javax.swing.GroupLayout jPanelDeleteBuildingLayout = new javax.swing.GroupLayout(jPanelDeleteBuilding);
        jPanelDeleteBuilding.setLayout(jPanelDeleteBuildingLayout);
        jPanelDeleteBuildingLayout.setHorizontalGroup(
            jPanelDeleteBuildingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDeleteBuildingLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jComboBoxDeleteBuild, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanelDeleteBuildingLayout.setVerticalGroup(
            jPanelDeleteBuildingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDeleteBuildingLayout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(jPanelDeleteBuildingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxDeleteBuild, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addContainerGap(163, Short.MAX_VALUE))
        );

        jMenuItem1.setText("jMenuItem1");

        jLabelUserSearch.setText("Search User POIs");

        javax.swing.GroupLayout jPanelUserSearchLayout = new javax.swing.GroupLayout(jPanelUserSearch);
        jPanelUserSearch.setLayout(jPanelUserSearchLayout);
        jPanelUserSearchLayout.setHorizontalGroup(
            jPanelUserSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUserSearchLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabelUserSearch)
                .addGap(18, 18, 18)
                .addComponent(jComboBoxUserSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanelUserSearchLayout.setVerticalGroup(
            jPanelUserSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUserSearchLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanelUserSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelUserSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxUserSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(63, Short.MAX_VALUE))
        );

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons/legend.png"))); // NOI18N

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("Use scroll bars and arrows to move through map and between floors. User the list on the left to switch\nBuildings. Search bar on top can be used to find POI in the current building. click on a POI to see more\ninfo on it, or favourite it! Click anywhere on the map to make POIs of your own. User layers tab to \nturn on and off certain POIs. Zoom can be accessed on bottom right. Toolbar on top can be used to\nsearch for your POIs, favourites or Log out");
        jTextArea1.setEditable(false);
        jScrollPane2.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanelHelpLayout = new javax.swing.GroupLayout(jPanelHelp);
        jPanelHelp.setLayout(jPanelHelpLayout);
        jPanelHelpLayout.setHorizontalGroup(
            jPanelHelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHelpLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelHelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelHelpLayout.setVerticalGroup(
            jPanelHelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelHelpLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLayeredPane1.setLayout(new javax.swing.OverlayLayout(jLayeredPane1));

        topPanel.setBackground(new java.awt.Color(255, 255, 255));
        topPanel.setOpaque(false);

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
        jPanelLayerButtons.setVisible(false);
        jPanelLayerButtons.setLayout(new java.awt.GridLayout(1, 0));

        jToggleButtonNav.setSelected(true);
        jToggleButtonNav.setText("Navigation");
        jToggleButtonNav.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jToggleButtonNavItemStateChanged(evt);
            }
        });
        jPanelLayerButtons.add(jToggleButtonNav);

        jToggleButtonClass.setSelected(true);
        jToggleButtonClass.setText("Classrooms");
        jToggleButtonClass.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jToggleButtonClassItemStateChanged(evt);
            }
        });
        jPanelLayerButtons.add(jToggleButtonClass);

        jToggleButtonRes.setSelected(true);
        jToggleButtonRes.setText("Resturants");
        jToggleButtonRes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jToggleButtonResItemStateChanged(evt);
            }
        });
        jPanelLayerButtons.add(jToggleButtonRes);

        jToggleButtonLab.setSelected(true);
        jToggleButtonLab.setText("Labs");
        jToggleButtonLab.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jToggleButtonLabItemStateChanged(evt);
            }
        });
        jPanelLayerButtons.add(jToggleButtonLab);

        jToggleButtonCS.setSelected(true);
        jToggleButtonCS.setText("CS POIs");
        jToggleButtonCS.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jToggleButtonCSItemStateChanged(evt);
            }
        });
        jPanelLayerButtons.add(jToggleButtonCS);

        jToggleButtonAC.setSelected(true);
        jToggleButtonAC.setText("Accessibility");
        jToggleButtonAC.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jToggleButtonACItemStateChanged(evt);
            }
        });
        jPanelLayerButtons.add(jToggleButtonAC);

        jToggleButtonWR.setSelected(true);
        jToggleButtonWR.setText("Washrooms");
        jToggleButtonWR.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jToggleButtonWRItemStateChanged(evt);
            }
        });
        jPanelLayerButtons.add(jToggleButtonWR);

        jToggleButtonUserPOI.setSelected(true);
        jToggleButtonUserPOI.setText("User-POI");
        jToggleButtonUserPOI.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jToggleButtonUserPOIItemStateChanged(evt);
            }
        });
        jPanelLayerButtons.add(jToggleButtonUserPOI);

        jButtonLayers.setText("Layers");
        jButtonLayers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLayersActionPerformed(evt);
            }
        });

        jButtonUpFloor.setText("^");
        jButtonUpFloor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonUpFloorMouseClicked(evt);
            }
        });

        jButtonDownFloor.setText("v");
        jButtonDownFloor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonDownFloorMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanelUpDownLayout = new javax.swing.GroupLayout(jPanelUpDown);
        jPanelUpDown.setLayout(jPanelUpDownLayout);
        jPanelUpDownLayout.setHorizontalGroup(
            jPanelUpDownLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButtonDownFloor, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
            .addComponent(jButtonUpFloor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelUpDownLayout.setVerticalGroup(
            jPanelUpDownLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUpDownLayout.createSequentialGroup()
                .addComponent(jButtonUpFloor, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonDownFloor, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jList1.setModel(new DefaultComboBoxModel(buildingsAll.toArray()));
        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        searchBox.setBackground(new java.awt.Color(213, 213, 213));
        searchBox.setEditable(true);
        searchBox.setModel(new DefaultComboBoxModel(buildAllPOIs.toArray()));
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
        searchBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBoxActionPerformed(evt);
            }
        });

        jPanelPOIInfo.setVisible(false);

        jButtonEditPOI.setText("Edit");
        jButtonEditPOI.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonEditPOIMouseClicked(evt);
            }
        });

        jButtonFav.setText("Favourite");
        jButtonFav.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonFavMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanelPOIInfoLayout = new javax.swing.GroupLayout(jPanelPOIInfo);
        jPanelPOIInfo.setLayout(jPanelPOIInfoLayout);
        jPanelPOIInfoLayout.setHorizontalGroup(
            jPanelPOIInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPOIInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelPOIInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelPOIInfoLayout.createSequentialGroup()
                        .addComponent(jButtonFav, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonEditPOI, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jLabelPOIDesc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPOIInfoLayout.createSequentialGroup()
                .addContainerGap(54, Short.MAX_VALUE)
                .addComponent(jLabelPOIName, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );
        jPanelPOIInfoLayout.setVerticalGroup(
            jPanelPOIInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPOIInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelPOIName, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelPOIDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelPOIInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonEditPOI, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(jButtonFav, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jLabelFloor.setForeground(new java.awt.Color(102, 102, 255));
        jLabelFloor.setText("Floor: "+floorIndex);

        jPanelWeather.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons/weatherIcon.png"))); // NOI18N

        jLabelWeather.setText("It is "+weather+" in London Ontario!");

        javax.swing.GroupLayout jPanelWeatherLayout = new javax.swing.GroupLayout(jPanelWeather);
        jPanelWeather.setLayout(jPanelWeatherLayout);
        jPanelWeatherLayout.setHorizontalGroup(
            jPanelWeatherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
            .addComponent(jLabelWeather, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelWeatherLayout.setVerticalGroup(
            jPanelWeatherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelWeatherLayout.createSequentialGroup()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelWeather, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(topPanelLayout.createSequentialGroup()
                        .addComponent(jButtonLayers, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 244, Short.MAX_VALUE)
                        .addComponent(jPanelLayerButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 245, Short.MAX_VALUE)
                        .addComponent(zoomPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))
                    .addGroup(topPanelLayout.createSequentialGroup()
                        .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(searchBox, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabelFloor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanelUpDown, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addComponent(jPanelWeather, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 853, Short.MAX_VALUE)
                        .addComponent(jPanelPOIInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43))))
        );
        topPanelLayout.setVerticalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(searchBox, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanelWeather, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                .addComponent(jLabelFloor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelUpDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonLayers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelLayerButtons, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(31, 31, 31))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelPOIInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            .addComponent(mapImage, javax.swing.GroupLayout.DEFAULT_SIZE, 1632, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mapImage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE)
        );

        pOILayer1.setOpaque(false);
        pOILayer1.setPOIsFromFile(POIPath+buildingName+"/"+buildingName+"-"+floorIndex+".txt",floorIndex);
        if(!this.admin){
            pOILayer1.addApplicable(POIPath+"users/"+username+".txt",buildingName,floorIndex);
        }
        pOILayer1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                pOILayer1MouseDragged(evt);
            }
        });
        pOILayer1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pOILayer1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pOILayer1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                pOILayer1MouseReleased(evt);
            }
        });
        pOILayer1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                pOILayer1FocusLost(evt);
            }
        });

        javax.swing.GroupLayout pOILayer1Layout = new javax.swing.GroupLayout(pOILayer1);
        pOILayer1.setLayout(pOILayer1Layout);
        pOILayer1Layout.setHorizontalGroup(
            pOILayer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1632, Short.MAX_VALUE)
        );
        pOILayer1Layout.setVerticalGroup(
            pOILayer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 608, Short.MAX_VALUE)
        );

        jPanel3.setOpaque(false);

        jLabelAC.setIcon(new ImageIcon(buildingLayerPath+buildingName+"/"+buildingName+"-ac-"+floorIndex+".png"));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelAC, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1632, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelAC, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE)
        );

        jPanel4.setOpaque(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
        );

        jPanel5.setOpaque(false);

        jLabelWC.setIcon(new ImageIcon(buildingLayerPath+buildingName+"/"+buildingName+"-wc-"+floorIndex+".png"));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelWC, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1626, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelWC, javax.swing.GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
        );

        jLayeredPane2.setLayer(jPanel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jPanel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jPanel5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane2Layout = new javax.swing.GroupLayout(jLayeredPane2);
        jLayeredPane2.setLayout(jLayeredPane2Layout);
        jLayeredPane2Layout.setHorizontalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jLayeredPane2Layout.setVerticalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLayeredPane3.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(pOILayer1, javax.swing.JLayeredPane.MODAL_LAYER);
        jLayeredPane3.setLayer(jLayeredPane2, javax.swing.JLayeredPane.PALETTE_LAYER);

        javax.swing.GroupLayout jLayeredPane3Layout = new javax.swing.GroupLayout(jLayeredPane3);
        jLayeredPane3.setLayout(jLayeredPane3Layout);
        jLayeredPane3Layout.setHorizontalGroup(
            jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1632, Short.MAX_VALUE)
            .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pOILayer1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLayeredPane2))
        );
        jLayeredPane3Layout.setVerticalGroup(
            jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 608, Short.MAX_VALUE)
            .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pOILayer1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLayeredPane2))
        );

        mapImageScrollPane.setViewportView(jLayeredPane3);

        javax.swing.GroupLayout mapPanelLayout = new javax.swing.GroupLayout(mapPanel);
        mapPanel.setLayout(mapPanelLayout);
        mapPanelLayout.setHorizontalGroup(
            mapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mapImageScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1415, Short.MAX_VALUE)
        );
        mapPanelLayout.setVerticalGroup(
            mapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mapImageScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
        );

        jLayeredPane1.add(mapPanel);

        jMenu6.setText("Favourites");
        jMenu6.setVisible(!this.admin);
        jMenu6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu6MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu6);

        jMenu3.setText("Edit");

        jMenuItemAddBuilding.setText("Add Building");
        jMenuItemAddBuilding.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAddBuildingActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItemAddBuilding);

        jMenuItemAddFloor.setText("Add Floor");
        jMenuItemAddFloor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAddFloorActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItemAddFloor);

        jMenuItemRemoveBuilding.setText("Remove Building");
        jMenuItemRemoveBuilding.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItemRemoveBuildingMouseClicked(evt);
            }
        });
        jMenuItemRemoveBuilding.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRemoveBuildingActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItemRemoveBuilding);

        jMenuItemRemoveFloor.setText("Remove Floor");
        jMenuItemRemoveFloor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRemoveFloorActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItemRemoveFloor);

        jMenuBar1.add(jMenu3);

        jMenuLogout.setText("Logout");

        jMenuItemLogout.setText("Logout");
        jMenuItemLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemLogoutActionPerformed(evt);
            }
        });
        jMenuLogout.add(jMenuItemLogout);

        jMenuBar1.add(jMenuLogout);

        jMenuUserPOI.setText("UserPOI Search");
        jMenuUserPOI.setVisible(!this.admin);
        jMenuUserPOI.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuUserPOIMouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenuUserPOI);

        jMenuHelp.setText("Help");
        jMenuHelp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuHelpMouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenuHelp);

        setJMenuBar(jMenuBar1);

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
    /**
     * saves POI to meta data
     * @param poiSave - poi to save
     * @param filePath - filepath for metadata
     */    
    private void savePOIs(POI poiSave,String filePath){
        
        try{
            Files.createDirectories(Paths.get(filePath).getParent());
            FileWriter POIFile = new FileWriter(filePath,true);
            BufferedWriter POIwrite = new BufferedWriter(POIFile);
            
                System.out.println(poiSave.toStringSave());
                POIwrite.write(poiSave.toStringSave());
                POIwrite.newLine();
            

            POIwrite.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    /**
     * saves building data to meta data
     * @param Build
     * @param filePath 
     */
        private void saveBuilding(String Build,String filePath){
            try{
            
            FileWriter POIFile = new FileWriter(filePath,true);
            BufferedWriter POIwrite = new BufferedWriter(POIFile);
            
                System.out.println(Build);
                POIwrite.write(Build);
                POIwrite.newLine();
            

            POIwrite.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        }
        /**
         * removes meta data for building from file
         * @param nameB - name of building to remove
         */
        private void deleteBuilding(String nameB){
            Path path = Paths.get("src/main/resources/Building info/buildings.txt");
            Charset charset = StandardCharsets.UTF_8;

            try{
                String content = new String(Files.readAllBytes(path), charset);


                    
                    content = content.replaceAll(nameB,"");


                Files.write(path, content.getBytes(charset));
            }
            catch(Exception e){
                System.out.println(e+" ChangePOI");

            }
            
        }
    private void mapImageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mapImageMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_mapImageMouseClicked
    /**
     * handles clicking on the POI layer, allows you to add POI if clicked empty
     * area or selects POI if clicked on a POI
     * 
     * @param evt - mouse click event
     */
    private void pOILayer1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pOILayer1MouseClicked
        // TODO add your handling code here:
        POI clickedPOI = pOILayer1.clickContain(evt.getX(), evt.getY());
        System.out.println();
        if(clickedPOI==null&&evt.getX()<mapImage.getIcon().getIconWidth()-16&&evt.getY()<mapImage.getIcon().getIconHeight()){
            resetSelect();
            
            //open POI creation
            int result = jOptionPanePOIOption.showConfirmDialog(null, jPanelAddPOI, 
               "POI Info", jOptionPanePOIOption.OK_CANCEL_OPTION);

            
            //jOptionPanePOIOption.setVisible(true);
            
            if(result==JOptionPane.OK_OPTION){
                //creates POI and saves
                
                POI newPOI = new POI((int)(evt.getX()/zoomMul),(int)(evt.getY()/zoomMul),jTextFieldPOIAddName.getText(),jTextFieldPOIAddDesc.getText(),BoxPOIAdd.getSelectedIndex(),!admin,floorIndex,buildingName);
                
                if(!admin){
                    savePOIs(newPOI,POIPath+"users"+"/"+username+".txt");
                }
                else{
                    savePOIs(newPOI,POIPath+buildingName+"/"+buildingName+"-"+floorIndex+".txt");
                }
                //relaod data and repaint
                changeBuildPOIs();
                editing = true;
                searchBox.setModel(new DefaultComboBoxModel(buildAllPOIs.toArray()));
                searchBox.setSelectedItem(newPOI);
                editing = false;
                System.out.println(evt.getX()+" "+evt.getY());
                pOILayer1.addPOI(newPOI);
                pOILayer1.setHigh(newPOI);
                pOILayer1.repaint();
                
            }
            
            
        }
        //selects POI
        else if(clickedPOI!=null){
            System.out.println("mmmHigh");
            pOILayer1.setHigh(clickedPOI);
            selectedPOI = clickedPOI;
            upDatePOIInfoLabel();
            pOILayer1.repaint();
            
            
        }
        System.out.println(clickedPOI);
        
    }//GEN-LAST:event_pOILayer1MouseClicked
    /**
     * updates POI info label with selected POI
     */
    private void upDatePOIInfoLabel(){
        jPanelPOIInfo.setVisible(true);
        jLabelPOIName.setText(selectedPOI.name);
        jLabelPOIDesc.setText(selectedPOI.desc);
        System.out.println(favPOIs);
        if(selectedPOI.userCreated||admin){
            jButtonFav.setVisible(false);
        }
        else{
            jButtonFav.setVisible(true);
            if(favouriteIncludes(selectedPOI)){
                jButtonFav.setText("Unfavourite");
            }
            else{
                jButtonFav.setText("Favourite");
            }
            
        }
            
        if(selectedPOI.userCreated||(admin&&!selectedPOI.userCreated)){
            jButtonEditPOI.setVisible(true);
        }
        else{
            jButtonEditPOI.setVisible(false);
        }
        
    }
    private void jButtonLayersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLayersActionPerformed
        if (jPanelLayerButtons.isShowing()) {
            jPanelLayerButtons.setVisible(false);
        }
        else {
            jPanelLayerButtons.setVisible(true);
        }
        resetSelect();
    }//GEN-LAST:event_jButtonLayersActionPerformed

    /**
     * zoom out function for map
     * @param evt - mouse click
     */
    private void minusZoomButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minusZoomButtonActionPerformed
        // TODO add your handling code here:
        
        File im = new File(buildingPath+buildingName+"/"+buildingName+"-"+floorIndex+".png");
        
        //rescalses file and reloads
        try{
            System.out.println(im.getPath());
            zoomMul*=0.5;
            Image image = ImageIO.read(im); // transform it
            Image newimg = image.getScaledInstance((int)(image.getWidth(rootPane)*zoomMul), (int)(image.getHeight(rootPane)*zoomMul),  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            mapImage.setIcon(new ImageIcon(newimg));
            
            
            
            Point old = mapImageScrollPane.getViewport().getViewPosition();
            //recenters viewPort
            mapImageScrollPane.getViewport().setViewPosition(new Point(old.x/4,old.y/4));
            pOILayer1.sizeMul=zoomMul;
        }
        catch(IOException imgMiss){
            System.out.println("Missing image");

        }

    }//GEN-LAST:event_minusZoomButtonActionPerformed

    /**
     * zoom in function for map
     * @param evt - mouse click
     */
    private void plusZoomButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plusZoomButtonActionPerformed
        // TODO add your handling code here:
        // load the image to a imageIcon
        
        File im = new File(buildingPath+buildingName+"/"+buildingName+"-"+floorIndex+".png");
        
        //reload and scale image of map
        try{
            System.out.println(buildingPath+buildingName+"/"+buildingName+"-"+floorIndex+".png");
            zoomMul*=2;
            Image image = ImageIO.read(im); // transform it
            Image newimg = image.getScaledInstance((int)(image.getWidth(rootPane)*zoomMul), (int)(image.getHeight(rootPane)*zoomMul),  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            mapImage.setIcon(new ImageIcon(newimg));
            
            
            
            Rectangle view = mapImageScrollPane.getViewport().getViewRect();
            Dimension size = mapImageScrollPane.getViewport().getExtentSize();

            view.setBounds(view.x*2+size.width/2,view.y+size.height/2,view.width,view.height);
            //recenter viewPort
            mapImage.scrollRectToVisible(view);

            pOILayer1.sizeMul=zoomMul;

        }
        catch(IOException imgMiss){
            System.out.println("Missing image");

        }

        
    }//GEN-LAST:event_plusZoomButtonActionPerformed

    private void searchBoxPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_searchBoxPopupMenuWillBecomeVisible
        // TODO add your handling code here:
    }//GEN-LAST:event_searchBoxPopupMenuWillBecomeVisible
    private void reloadImages(){
        File im = new File(buildingPath+buildingName+"/"+buildingName+"-"+floorIndex+".png");
        try{
            Image image = ImageIO.read(im); // transform it
        Image newimg = image.getScaledInstance((int)(image.getWidth(rootPane)*zoomMul), (int)(image.getHeight(rootPane)*zoomMul),  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        mapImage.setIcon(new ImageIcon(newimg));
        
        jLabelFloor.setText("Floor: "+floorIndex);
        }
        catch (Exception e){
            
        }
        
        

    }
    private void jMenuItemAddBuildingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAddBuildingActionPerformed
        // TODO add your handling code here:
        int result = jOptionPaneEdit.showConfirmDialog(null,jPanelAddBuilding,"Adding Building",JOptionPane.OK_CANCEL_OPTION);
        if(result == JOptionPane.OK_OPTION){
            try{
                //buildingsAll.contains(jTextFieldAddBuilding.getText())||
                if(buildingsAll.contains(jTextFieldAddBuilding.getText())||jTextFieldAddBuilding.getText().equals("")){
                    throw new Exception("invalid name");
                }
                else{
                    Path out = Paths.get(buildingPath+jTextFieldAddBuilding.getText()+"/"+jTextFieldAddBuilding.getText()+"-0"+".png");
                    Files.createDirectories(out.getParent());
                    Files.copy(Paths.get(jTextFieldAddBuildingPath.getText()),out);
                    buildingsAll.add(jTextFieldAddBuilding.getText());
                    saveBuilding(jTextFieldAddBuilding.getText(),"src/main/resources/Building info/buildings.txt");
                    buildingsAll = getItemsFromFile("src/main/resources/Building info/buildings.txt");
                    int oldsel = jList1.getSelectedIndex();
                    jList1.setModel(new DefaultComboBoxModel(buildingsAll.toArray()));
                    
                    jList1.setSelectedIndex(jList1.getModel().getSize()-1);
                    buildingName = jList1.getSelectedValue();
                    resetSelect();
                    
                    zoomMul = 1;
                    floorIndex=0;
                    changeBuildPOIs();
                    //searchBox.setModel(new DefaultComboBoxModel(buildAllPOIs.toArray()));
                    reloadImages();

                    pOILayer1.Reset();
                    pOILayer1.setPOIsFromFile(POIPath+buildingName+"/"+buildingName+"-"+floorIndex+".txt",floorIndex);
                    if(!admin){
                          pOILayer1.addApplicable(POIPath+"users"+"/"+username+".txt",buildingName,floorIndex);  
                        }
                    
                    pOILayer1.repaint();
                   
                    
            

            
                }
            }
            catch(Exception e){
                //
                System.out.println(e);
            }
        }
        
        
    }//GEN-LAST:event_jMenuItemAddBuildingActionPerformed
    
    private void jButtonAddBuildingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAddBuildingMouseClicked
        // TODO add your handling code here:
        int result = jFileChooser1.showOpenDialog(jPanelAddBuilding);
        if(result == JFileChooser.APPROVE_OPTION){
            jTextFieldAddBuildingPath.setText(jFileChooser1.getSelectedFile().toPath().toString());
            
        }
        
    }//GEN-LAST:event_jButtonAddBuildingMouseClicked

    private void jMenuItemAddFloorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAddFloorActionPerformed
        // TODO add your handling code here:
        int result = jOptionPaneEdit.showConfirmDialog(null,jPanelAddFloor,"Adding Building",JOptionPane.OK_CANCEL_OPTION);
        if(result == JOptionPane.OK_OPTION){
            try{
                //buildingsAll.contains(jTextFieldAddBuilding.getText())||
                if(jComboBoxAddFloor.getSelectedIndex()<=2){
                    throw new Exception("Cannot Edit Those files");
                }
                else{
                    File buildFloors = new File(buildingPath+jComboBoxAddFloor.getSelectedItem().toString());
                    System.out.println(buildingPath+jComboBoxAddFloor.getSelectedItem().toString());
                    int floorNum = buildFloors.list().length;
                    Path out = Paths.get(buildingPath+jComboBoxAddFloor.getSelectedItem().toString()+"/"+jComboBoxAddFloor.getSelectedItem().toString()+"-"+floorNum+".png");
                    
                    Files.copy(Paths.get(jTextFieldAddFloorPath.getText()),out);
                }
            }
            catch(Exception e){
                //
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_jMenuItemAddFloorActionPerformed

    private void jButtonAddFloorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAddFloorMouseClicked
        // TODO add your handling code here:
        int result = jFileChooser1.showOpenDialog(jPanelAddFloor);
        if(result == JFileChooser.APPROVE_OPTION){
            jTextFieldAddFloorPath.setText(jFileChooser1.getSelectedFile().toPath().toString());
            
        }
    }//GEN-LAST:event_jButtonAddFloorMouseClicked

    private void jButtonUpFloorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonUpFloorMouseClicked
        // TODO add your handling code here:
        resetSelect();
        File floorList = new File(buildingPath+buildingName);
        int maxFloor = floorList.list().length;
        if(floorIndex==maxFloor-1){
            
        }
        else{
            floorIndex+=1;
            zoomMul = 1;
            reloadImages();
            pOILayer1.Reset();
            
            pOILayer1.setPOIsFromFile(POIPath+buildingName+"/"+buildingName+"-"+floorIndex+".txt",floorIndex);
            if(!admin){
              pOILayer1.addApplicable(POIPath+"users"+"/"+username+".txt",buildingName,floorIndex);  
            }
            pOILayer1.repaint();

            
        }
    }//GEN-LAST:event_jButtonUpFloorMouseClicked
    /**
     * on a search for POI, pulls up correct map and floor
     */
    private void searchCenter(){
        
        if(searchBox.getSelectedIndex()==-1){
            return;
        }

        //floorIndex=searchBox.getSelectedIndex();
        POI selP = buildAllPOIs.get(searchBox.getSelectedIndex());
        resetSelect();
        buildingName = selP.building;
        zoomMul = 1;
        floorIndex=selP.floor;
        changeBuildPOIs();
        //searchBox.setModel(new DefaultComboBoxModel(buildAllPOIs.toArray()));
        reloadImages();
        
        pOILayer1.Reset();
        pOILayer1.setPOIsFromFile(POIPath+buildingName+"/"+buildingName+"-"+floorIndex+".txt",floorIndex);
        if(!admin){
              pOILayer1.addApplicable(POIPath+"users"+"/"+username+".txt",buildingName,floorIndex);  
            }
        pOILayer1.findAndSetHigh(selP);
        selectedPOI = pOILayer1.highPOI;
        upDatePOIInfoLabel();
        toggleCorrect(selP);
        
        pOILayer1.repaint();
        
    }
    private void jButtonDownFloorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonDownFloorMouseClicked
        // TODO add your handling code here:
        resetSelect();
        if(floorIndex<=0){
            floorIndex=0;
        }
        else{
            floorIndex-=1;
            zoomMul = 1;
            reloadImages();
            pOILayer1.Reset();
            pOILayer1.setPOIsFromFile(POIPath+buildingName+"/"+buildingName+"-"+floorIndex+".txt",floorIndex);
            if(!admin){
              pOILayer1.addApplicable(POIPath+"users"+"/"+username+".txt",buildingName,floorIndex);  
            }
            pOILayer1.repaint();

            
        }
    }//GEN-LAST:event_jButtonDownFloorMouseClicked

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        // TODO add your handling code here:state
        resetSelect();
        buildingName = jList1.getSelectedValue();
        zoomMul = 1;
        floorIndex=0;
        changeBuildPOIs();
        searchBox.setModel(new DefaultComboBoxModel(buildAllPOIs.toArray()));
        reloadImages();
        
        pOILayer1.Reset();
        pOILayer1.setPOIsFromFile(POIPath+buildingName+"/"+buildingName+"-"+floorIndex+".txt",floorIndex);
        if(!admin){
              pOILayer1.addApplicable(POIPath+"users"+"/"+username+".txt",buildingName,floorIndex);  
            }
        pOILayer1.repaint();

        
    }//GEN-LAST:event_jList1ValueChanged

    private void searchBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchBoxActionPerformed

    private void jMenuItemRemoveFloorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRemoveFloorActionPerformed
        // TODO add your handling code here:
        jComboBoxRemoveFloorBuild.setModel(new DefaultComboBoxModel(buildingsAll.toArray()));
        setRemoveComboFloor();
        int result = jOptionPaneEdit.showConfirmDialog(null,jPanelRemoveFloor,"Remove Floor",JOptionPane.OK_CANCEL_OPTION);
        if(result == JOptionPane.OK_OPTION){
            try{
                
                //buildingsAll.contains(jTextFieldAddBuilding.getText())||
                
                    if(jComboBoxRemoveFloorNum.getItemCount()==1){
                        throw new Exception("cannot delete last floor");
                    }
                    jList1.setSelectedIndex(0);
                    
                    
                    //delete floor items
                    File deleteFloor = new File(buildingPath+jComboBoxRemoveFloorBuild.getSelectedItem().toString()+"/"+jComboBoxRemoveFloorBuild.getSelectedItem().toString()+"-"+jComboBoxRemoveFloorNum.getSelectedIndex()+".png");
                    System.out.println(deleteFloor.getPath());
                    File deleteFloorPOI = new File(POIPath+jComboBoxRemoveFloorBuild.getSelectedItem().toString()+"/"+jComboBoxRemoveFloorBuild.getSelectedItem().toString()+"-"+jComboBoxRemoveFloorNum.getSelectedIndex()+".txt");
                    deleteFloor.delete();
                    deleteFloorPOI.delete();
                    deleteFloorBuildingPOI(jComboBoxRemoveFloorBuild.getSelectedItem().toString(),(""+jComboBoxRemoveFloorNum.getSelectedIndex()),POIPath+"favourites/favourites.txt");
                    File[] userStuff = new File(POIPath+"users").listFiles();
                    //delete floor metadata and re factor other floors for delted floor
                    for(int i = jComboBoxRemoveFloorNum.getSelectedIndex()+1;i<jComboBoxRemoveFloorNum.getItemCount();i++){
                        
                        File newName = new File(buildingPath+jComboBoxRemoveFloorBuild.getSelectedItem().toString()+"/"+jComboBoxRemoveFloorBuild.getSelectedItem()+"-"+(i-1)+".png");
                        File oldName = new File(buildingPath+jComboBoxRemoveFloorBuild.getSelectedItem().toString()+"/"+jComboBoxRemoveFloorBuild.getSelectedItem()+"-"+i+".png");
                        oldName.renameTo(newName);
                        
                        newName = new File(POIPath+jComboBoxRemoveFloorBuild.getSelectedItem().toString()+"/"+jComboBoxRemoveFloorBuild.getSelectedItem().toString()+"-"+(i-1)+".txt");
                        
                        oldName = new File(POIPath+jComboBoxRemoveFloorBuild.getSelectedItem().toString()+"/"+jComboBoxRemoveFloorBuild.getSelectedItem().toString()+"-"+i+".txt");
                        System.out.println(oldName);
                        oldName.renameTo(newName);
                        ArrayList<POI> temp = getPOIFromFile(newName.getAbsolutePath());
                        for(POI k:temp){
                            POI tempPOI = new POI(k.locX,k.locY,k.name,k.desc,k.POIType,k.userCreated,k.floor-1,k.building);
                                if(k.building.equals(jComboBoxRemoveFloorBuild.getSelectedItem())&&k.floor>jComboBoxRemoveFloorNum.getSelectedIndex()){
                                    
                                    changePOIData(k,tempPOI,newName.getAbsolutePath(),"",false);
                                }
                                changePOIData(k,tempPOI,POIPath+"favourites/favourites.txt","",false);
                                    
                        }
                        
                        
                        
                    }
                    for(File j:userStuff){
                            ArrayList<POI> temp = getPOIFromFile(j.getAbsolutePath());
                            for(POI k:temp){
                                if(k.building.equals(jComboBoxRemoveFloorBuild.getSelectedItem())){
                                    if(k.floor>jComboBoxRemoveFloorNum.getSelectedIndex()){
                                        POI tempPOI = new POI(k.locX,k.locY,k.name,k.desc,k.POIType,k.userCreated,k.floor-1,k.building);
                                        changePOIData(k,tempPOI,j.getAbsolutePath(),"",false);
                                    }
                                    else if(k.floor==jComboBoxRemoveFloorNum.getSelectedIndex()){
                                        POI tempPOI = new POI(k.locX,k.locY,k.name,k.desc,k.POIType,k.userCreated,k.floor-1,k.building);
                                        changePOIData(k,tempPOI,j.getAbsolutePath(),"",true);
                                    }
                                  
                                }
                                    
                            }
                   
                    }
                    
                    
                    
                    zoomMul = 1;
                    floorIndex-=1;
                    if(floorIndex<0){
                        floorIndex = 0;
                    }
                    changeBuildPOIs();
                    searchBox.setModel(new DefaultComboBoxModel(buildAllPOIs.toArray()));
                    reloadImages();
                    loadFavouritesData();
                    pOILayer1.Reset();
                    pOILayer1.setPOIsFromFile(POIPath+buildingName+"/"+buildingName+"-"+floorIndex+".txt",floorIndex);
                    if(!admin){
                          pOILayer1.addApplicable(POIPath+"users"+"/"+username+".txt",buildingName,floorIndex);  
                        }
                    pOILayer1.repaint();
                }
                catch(Exception e){
                //
                //System.out.println(e);
                }
        }
    }//GEN-LAST:event_jMenuItemRemoveFloorActionPerformed

    private void jComboBoxRemoveFloorBuildItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxRemoveFloorBuildItemStateChanged
        // TODO add your handling code here:
        setRemoveComboFloor();
        
    }//GEN-LAST:event_jComboBoxRemoveFloorBuildItemStateChanged
    private void setRemoveComboFloor(){
        int floorNums = new File(buildingPath+jComboBoxRemoveFloorBuild.getSelectedItem().toString()).list().length;
        String[] itemBox = new String[floorNums];
        for(int i = 0;i<floorNums;i++){
            itemBox[i] = ""+i;
        }
        jComboBoxRemoveFloorNum.setModel(new DefaultComboBoxModel(itemBox));
        
    }
    private void pOILayer1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pOILayer1MousePressed
        // TODO add your handling code here:
        POI clickedPOI = pOILayer1.clickContain(evt.getX(), evt.getY());
        if(clickedPOI!=null){
            
            System.out.println("DSDS");
            
            draggedPOI = clickedPOI;
            
        }
    }//GEN-LAST:event_pOILayer1MousePressed

    private void pOILayer1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pOILayer1MouseDragged
        // TODO add your handling code here:
        
        System.out.println("DRAGGING MOUSE");
        if(draggedPOI!=null){
            resetSelect();
            pOILayer1.moveingPOI(draggedPOI);
            pOILayer1.setHigh(draggedPOI);
            pOILayer1.dragX = evt.getX();
            pOILayer1.dragY = evt.getY();
            pOILayer1.repaint();
        }
        
    }//GEN-LAST:event_pOILayer1MouseDragged

    private void pOILayer1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pOILayer1MouseReleased
        // TODO add your handling code here:
        
        if(draggedPOI!=null&&pOILayer1.dragPOI!=null){
            if(evt.getX()>mapImage.getIcon().getIconWidth()-16||evt.getY()>mapImage.getIcon().getIconHeight()){
            pOILayer1.moveingPOI(null);
            draggedPOI=null;
            pOILayer1.repaint();
            pOILayer1.setHigh(null);
            
            }
            else{
                POI newPOI = null;
            
            if(admin){
                newPOI = new POI((int)(evt.getX()/zoomMul),(int)(evt.getY()/zoomMul),draggedPOI.name,draggedPOI.desc,draggedPOI.POIType,false,draggedPOI.floor,draggedPOI.building);
                changePOIData(draggedPOI,newPOI,POIPath+buildingName+"/"+buildingName+"-"+floorIndex+".txt","",false);
                changePOIData(draggedPOI,newPOI,POIPath+"favourites/favourites.txt","",false);
                /*
                
                
                
                
                
                
                
                Fav code
                */
                
            }
            else{
               newPOI = new POI((int)(evt.getX()/zoomMul),(int)(evt.getY()/zoomMul),draggedPOI.name,draggedPOI.desc,draggedPOI.POIType,true,draggedPOI.floor,draggedPOI.building); 
               changePOIData(draggedPOI,newPOI,POIPath+"users"+"/"+username+".txt","",false);
            }
            changeBuildPOIs();
            //searchBox.setModel(new DefaultComboBoxModel(buildAllPOIs.toArray()));
            //searchBox.setSelectedItem(newPOI);
            
            
            
            
            
            
            pOILayer1.smallReset();
            pOILayer1.setPOIsFromFile(POIPath+buildingName+"/"+buildingName+"-"+floorIndex+".txt",floorIndex);
            if(!admin){
              pOILayer1.addApplicable(POIPath+"users"+"/"+username+".txt",buildingName,floorIndex);  
            }
            pOILayer1.repaint();
            draggedPOI=null;
            }
            
        }
        
    }//GEN-LAST:event_pOILayer1MouseReleased
    
    private void resetSelect(){
        pOILayer1.setHigh(null);
        jPanelPOIInfo.setVisible(false);
        selectedPOI = null;
        jLabelPOIName.setText("");
        jLabelPOIDesc.setText("");
        pOILayer1.repaint();
                
    }
    private void pOILayer1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pOILayer1FocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_pOILayer1FocusLost

    private void searchBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_searchBoxItemStateChanged
        // TODO add your handling code here:
        if(!editing){
            searchCenter();
        }
        
        
    }//GEN-LAST:event_searchBoxItemStateChanged

    private void jButtonEditPOIMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonEditPOIMouseClicked
        // TODO add your handling code here:
        int result = jOptionPaneEdit.showConfirmDialog(null,jPanelEditPOI,"Edit POI",JOptionPane.OK_CANCEL_OPTION);
        editing = true;
        if(result == JOptionPane.OK_OPTION){
            if(jCheckBoxDeletePOI.isSelected()){
                if(selectedPOI.userCreated){
                   changePOIData(selectedPOI,selectedPOI,POIPath+"users/"+username+".txt","",true); 
                }
                else{
                  removeFav(selectedPOI.toStringSave());
                  changePOIData(selectedPOI,selectedPOI,POIPath+selectedPOI.building+"/"+selectedPOI.building+"-"+selectedPOI.floor+".txt","",true);
                }
                
                loadFavouritesData();
                changeBuildPOIs();
                System.out.println(searchBox);
                searchBox.setModel(new DefaultComboBoxModel(buildAllPOIs.toArray()));
                
                //searchBox.setSelectedIndex();
                
                reloadImages();
                
                
                
            }
            else if(!jTextFieldEditPOIName.getText().equals("")){
                POI newPOI = new POI(selectedPOI.locX,selectedPOI.locY,jTextFieldEditPOIName.getText(),jTextFieldEditPOIDesc.getText(),jComboBoxEditPOI.getSelectedIndex(),selectedPOI.userCreated,selectedPOI.floor,selectedPOI.building);
                if(selectedPOI.userCreated){
                    changePOIData(selectedPOI,newPOI,POIPath+"users/"+username+".txt","",false);
                }
                else{
                    changePOIData(selectedPOI,newPOI,POIPath+newPOI.building+"/"+newPOI.building+"-"+newPOI.floor+".txt","",false);
                    
                }
                System.out.println(zoomMul+" OOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
                changePOIData(selectedPOI,newPOI,POIPath+"favourites"+"/favourites.txt","",false);
                loadFavouritesData();
                changeBuildPOIs();
                System.out.println(searchBox);
                searchBox.setModel(new DefaultComboBoxModel(buildAllPOIs.toArray()));
                System.out.println(buildAllPOIs.indexOf(newPOI));
                //searchBox.setSelectedIndex();
                
                reloadImages();
                System.out.println(zoomMul+" POIPOIDPOSIDPOSIDPOSIDOIPD");

                
            }
        }
        editing = false;
        resetSelect();
        pOILayer1.smallReset();
        pOILayer1.setPOIsFromFile(POIPath+buildingName+"/"+buildingName+"-"+floorIndex+".txt",floorIndex);
        if(!admin){
              pOILayer1.addApplicable(POIPath+"users"+"/"+username+".txt",buildingName,floorIndex);  
            }
        pOILayer1.sizeMul=zoomMul;
        pOILayer1.repaint();
    }//GEN-LAST:event_jButtonEditPOIMouseClicked

    private void jCheckBoxDeletePOIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxDeletePOIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxDeletePOIActionPerformed

    private void jMenu6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu6MouseClicked
        // TODO add your handling code here:
        jComboBoxFavSearch.setModel(new DefaultComboBoxModel(favPOIs.toArray()));
        
        int result = jOptionPaneEdit.showConfirmDialog(null,jPanelFavSearch,"Favourites",JOptionPane.OK_CANCEL_OPTION);
        if(result == JOptionPane.OK_OPTION){
            try{
                if(jComboBoxFavSearch.getSelectedIndex()==-1){
                    throw new Exception("No Favourite Selected");
                }
                else{
                    POI selP = favPOIs.get(jComboBoxFavSearch.getSelectedIndex());
                    
                    resetSelect();
                    buildingName = selP.building;
                    zoomMul = 1;
                    floorIndex=selP.floor;
                    changeBuildPOIs();
                    //searchBox.setModel(new DefaultComboBoxModel(buildAllPOIs.toArray()));
                    reloadImages();

                    pOILayer1.Reset();
                    pOILayer1.setPOIsFromFile(POIPath+buildingName+"/"+buildingName+"-"+floorIndex+".txt",floorIndex);
                    if(!admin){
                          pOILayer1.addApplicable(POIPath+"users"+"/"+username+".txt",buildingName,floorIndex);  
                        }
                    pOILayer1.findAndSetHigh(selP);
                    selectedPOI = pOILayer1.highPOI;
                    upDatePOIInfoLabel();

                    pOILayer1.repaint();
                    toggleCorrect(selP);
                    
                    
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        
        }
    }//GEN-LAST:event_jMenu6MouseClicked
    /**
     * following a search, correctly toggles POI layers so search item will show
     * @param m - POI to search for
     */
    private void toggleCorrect(POI m){
        if(m.POIType==POI.NAVIGATION){
            jToggleButtonNav.setSelected(true);
        }
        else if(m.POIType==POI.CLASSROOMS){
            jToggleButtonClass.setSelected(true);
        }
        else if(m.POIType==POI.RESTURANTS){
            jToggleButtonRes.setSelected(true);
        }
        else if(m.POIType==POI.LABS){
            jToggleButtonLab.setSelected(true);
        }
        else if(m.POIType==POI.CS){
            jToggleButtonNav.setSelected(true);
        }
        else if(m.POIType==POI.AC){
            jToggleButtonAC.setSelected(true);
        }
        else if(m.POIType==POI.WR){
            jToggleButtonWR.setSelected(true);
        }
        if(m.userCreated){
            jToggleButtonUserPOI.setSelected(true);
        }
    }
    private void jButtonFavMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonFavMouseClicked
        // TODO add your handling code here:
        if(jButtonFav.getText().equals("Favourite")){
            saveFav(selectedPOI);
        }
        else{
            removeFav(selectedPOI.toStringSave());
        }
        loadFavouritesData();
        upDatePOIInfoLabel();
        
    
    }//GEN-LAST:event_jButtonFavMouseClicked

    private void jMenuItemRemoveBuildingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItemRemoveBuildingMouseClicked
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_jMenuItemRemoveBuildingMouseClicked

    private void jMenuItemRemoveBuildingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRemoveBuildingActionPerformed
            // TODO add your handling code here:
            jComboBoxDeleteBuild.setModel(new DefaultComboBoxModel(buildingsAll.toArray()));
        
        int result = jOptionPaneEdit.showConfirmDialog(null,jPanelDeleteBuilding,"Remove Building",JOptionPane.OK_CANCEL_OPTION);
        
        if(result == JOptionPane.OK_OPTION){
            try{
                if(jComboBoxDeleteBuild.getSelectedIndex()==-1){
                    throw new Exception("No buildings to delete");
                }
                else{
                    File[] userStuff = new File(POIPath+"users").listFiles();
                    for(File j:userStuff){
                        deleteFloorBuildingPOI(jComboBoxDeleteBuild.getSelectedItem().toString(),".*",j.getAbsolutePath());
                    }
                    
                    deleteFloorBuildingPOI(jComboBoxDeleteBuild.getSelectedItem().toString(),".*",POIPath+"favourites/favourites.txt");
                    FileUtils.deleteDirectory(new File("src/main/resources/images/Floors/"+jComboBoxDeleteBuild.getSelectedItem()));
                    FileUtils.deleteDirectory(new File("src/main/resources/POI info/"+jComboBoxDeleteBuild.getSelectedItem()));
                    deleteBuilding(jComboBoxDeleteBuild.getSelectedItem().toString());
                    buildingsAll = getItemsFromFile("src/main/resources/Building info/buildings.txt");
                    int oldsel = jList1.getSelectedIndex();
                    jList1.setModel(new DefaultComboBoxModel(buildingsAll.toArray()));
                    oldsel-=1;
                    if(oldsel<0){
                        oldsel=0;
                    }
                    jList1.setSelectedIndex(oldsel);
                    buildingName = buildingsAll.get(oldsel);
                    resetSelect();
                    
                    zoomMul = 1;
                    floorIndex=0;
                    changeBuildPOIs();
                    //searchBox.setModel(new DefaultComboBoxModel(buildAllPOIs.toArray()));
                    reloadImages();

                    pOILayer1.Reset();
                    pOILayer1.setPOIsFromFile(POIPath+buildingName+"/"+buildingName+"-"+floorIndex+".txt",floorIndex);
                    if(!admin){
                          pOILayer1.addApplicable(POIPath+"users"+"/"+username+".txt",buildingName,floorIndex);  
                        }
                    
                    pOILayer1.repaint();
                    
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
            
            
        }
    }//GEN-LAST:event_jMenuItemRemoveBuildingActionPerformed

    private void jMenuItemLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemLogoutActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jMenuItemLogoutActionPerformed

    private void jToggleButtonNavItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jToggleButtonNavItemStateChanged
        // TODO add your handling code here:
        pOILayer1.displayNav = jToggleButtonNav.isSelected();
        pOILayer1.repaint();
    }//GEN-LAST:event_jToggleButtonNavItemStateChanged

    private void jToggleButtonClassItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jToggleButtonClassItemStateChanged
        // TODO add your handling code here:
        pOILayer1.displayClass = jToggleButtonClass.isSelected();
        pOILayer1.repaint();
    }//GEN-LAST:event_jToggleButtonClassItemStateChanged

    private void jToggleButtonResItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jToggleButtonResItemStateChanged
        // TODO add your handling code here:
        pOILayer1.displayRes = jToggleButtonRes.isSelected();
        pOILayer1.repaint();
    }//GEN-LAST:event_jToggleButtonResItemStateChanged

    private void jToggleButtonLabItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jToggleButtonLabItemStateChanged
        // TODO add your handling code here:
        pOILayer1.displayLabs = jToggleButtonLab.isSelected();
        pOILayer1.repaint();
    }//GEN-LAST:event_jToggleButtonLabItemStateChanged

    private void jToggleButtonCSItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jToggleButtonCSItemStateChanged
        // TODO add your handling code here:
        pOILayer1.displayCS = jToggleButtonCS.isSelected();
        pOILayer1.repaint();
    }//GEN-LAST:event_jToggleButtonCSItemStateChanged

    private void jToggleButtonACItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jToggleButtonACItemStateChanged
        // TODO add your handling code here:
        pOILayer1.displayAC = jToggleButtonAC.isSelected();
        pOILayer1.repaint();
    }//GEN-LAST:event_jToggleButtonACItemStateChanged

    private void jToggleButtonWRItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jToggleButtonWRItemStateChanged
        // TODO add your handling code here:
        pOILayer1.displayWR = jToggleButtonWR.isSelected();
        pOILayer1.repaint();
    }//GEN-LAST:event_jToggleButtonWRItemStateChanged

    private void BoxPOIAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoxPOIAddActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BoxPOIAddActionPerformed

    private void jToggleButtonUserPOIItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jToggleButtonUserPOIItemStateChanged
        // TODO add your handling code here:
        pOILayer1.displayUser = jToggleButtonUserPOI.isSelected();
        pOILayer1.repaint();
    }//GEN-LAST:event_jToggleButtonUserPOIItemStateChanged

    private void jMenuUserPOIMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuUserPOIMouseClicked
        // TODO add your handling code here:
        jComboBoxUserSearch.setModel(new DefaultComboBoxModel(userPOIs.toArray()));
        
        int result = jOptionPaneEdit.showConfirmDialog(null,jPanelUserSearch,"User Search",JOptionPane.OK_CANCEL_OPTION);
        if(result == JOptionPane.OK_OPTION){
            try{
                if(jComboBoxUserSearch.getSelectedIndex()==-1){
                    throw new Exception("No Favourite Selected");
                }
                else{
                    POI selP = userPOIs.get(jComboBoxUserSearch.getSelectedIndex());
                    
                    resetSelect();
                    buildingName = selP.building;
                    zoomMul = 1;
                    floorIndex=selP.floor;
                    changeBuildPOIs();
                    //searchBox.setModel(new DefaultComboBoxModel(buildAllPOIs.toArray()));
                    reloadImages();

                    pOILayer1.Reset();
                    pOILayer1.setPOIsFromFile(POIPath+buildingName+"/"+buildingName+"-"+floorIndex+".txt",floorIndex);
                    if(!admin){
                          pOILayer1.addApplicable(POIPath+"users"+"/"+username+".txt",buildingName,floorIndex);  
                        }
                    pOILayer1.findAndSetHigh(selP);
                    selectedPOI = pOILayer1.highPOI;
                    upDatePOIInfoLabel();

                    pOILayer1.repaint();
                    toggleCorrect(selP);
                    
                    
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        
        }
        
    }//GEN-LAST:event_jMenuUserPOIMouseClicked

    private void jMenuHelpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuHelpMouseClicked
        // TODO add your handling code here:
        int result = jOptionPaneEdit.showConfirmDialog(null,jPanelHelp,"Help",JOptionPane.OK_CANCEL_OPTION);
        
    }//GEN-LAST:event_jMenuHelpMouseClicked
    /**
     * saves Favourite metadata
     * @param poiSave - POI to save
     */
    private void saveFav(POI poiSave){
        try{
            
            FileWriter POIFile = new FileWriter(POIPath+"favourites/favourites.txt",true);
            BufferedWriter POIwrite = new BufferedWriter(POIFile);
            
                System.out.println(poiSave.toStringSave());
                POIwrite.write(poiSave.toStringSave()+","+username);
                POIwrite.newLine();
                System.out.println("DSDSDSDPPPNAMISAMSOIMSAOISM");
            

            POIwrite.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    /**
     * changes meta data from old POI to new POI
     * @param oldPOI - Old data POI
     * @param newPOI - new data POI
     * @param filePath - metadata file
     * @param mod - mods to make to end of metadata
     * @param delete  - delete old data or replace
     */
    private void changePOIData(POI oldPOI,POI newPOI,String filePath,String mod,boolean delete){
        Path path = Paths.get(filePath);
        Charset charset = StandardCharsets.UTF_8;
        System.out.println(filePath);
        try{
            String content = new String(Files.readAllBytes(path), charset);
            System.out.println(oldPOI.toStringSave()+mod+":old");
            System.out.println(newPOI.toStringSave()+mod+":new");
            if(delete){
                content = content.replaceAll(oldPOI.toStringSave()+mod, "");
            }
            else{
                content = content.replaceAll(oldPOI.toStringSave()+mod, newPOI.toStringSave()+mod);
            }
            
            Files.write(path, content.getBytes(charset));
        }
        catch(Exception e){
            System.out.println(e+" ChangePOI");
            
        }
        
    }
    /**
     * delets POI data from file with corressponding building and floor
     * @param building - building to delete for
     * @param floor - floor to delte for
     * @param filePath  - metadata
     */
    private void deleteFloorBuildingPOI(String building,String floor,String filePath){
        Path path = Paths.get(filePath);
        Charset charset = StandardCharsets.UTF_8;
        
        try{
            String content = new String(Files.readAllBytes(path), charset);
            
                
                String reg = ".*,.*,.*,.*,.*,.*,"+floor+","+building+".*";
                content = content.replaceAll(reg,"");
            
            
            Files.write(path, content.getBytes(charset));
        }
        catch(Exception e){
            System.out.println(e+" ChangePOI");
            
        }
        
        
    }
    /**
     * deletes a POI from Favourites metadata
     * @param poi - POI to delete
     */
    private void removeFav(String poi){
        Path path = Paths.get(POIPath+"favourites/favourites.txt");
        Charset charset = StandardCharsets.UTF_8;
        
        try{
            String content = new String(Files.readAllBytes(path), charset);
            
                
                String reg = poi+",.*";
                content = content.replaceAll(reg,"");
            
            
            Files.write(path, content.getBytes(charset));
        }
        catch(Exception e){
            System.out.println(e+" ChangePOI");
            
        }
        
        
    }

    /**
     *
     * @param args
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
                new AppWindow(true,"namashi").setVisible(true);
            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> BoxPOIAdd;
    private javax.swing.JButton jButtonAddBuilding;
    private javax.swing.JButton jButtonAddFloor;
    private javax.swing.JButton jButtonDownFloor;
    private javax.swing.JButton jButtonEditPOI;
    private javax.swing.JButton jButtonFav;
    private javax.swing.JButton jButtonLayers;
    private javax.swing.JButton jButtonUpFloor;
    private javax.swing.JCheckBox jCheckBoxDeletePOI;
    private javax.swing.JComboBox<String> jComboBoxAddFloor;
    private javax.swing.JComboBox<String> jComboBoxDeleteBuild;
    private javax.swing.JComboBox<String> jComboBoxEditPOI;
    private javax.swing.JComboBox<String> jComboBoxFavSearch;
    private javax.swing.JComboBox<String> jComboBoxRemoveFloorBuild;
    private javax.swing.JComboBox<String> jComboBoxRemoveFloorNum;
    private javax.swing.JComboBox<String> jComboBoxUserSearch;
    private javax.swing.JDialog jDialogError;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelAC;
    private javax.swing.JLabel jLabelAddBuildingName;
    private javax.swing.JLabel jLabelAddFloor;
    private javax.swing.JLabel jLabelEditPOI;
    private javax.swing.JLabel jLabelFloor;
    private javax.swing.JLabel jLabelPOIAddDesc;
    private javax.swing.JLabel jLabelPOIAddName;
    private javax.swing.JLabel jLabelPOIDesc;
    private javax.swing.JLabel jLabelPOIName;
    private javax.swing.JLabel jLabelSearchFav;
    private javax.swing.JLabel jLabelUserSearch;
    private javax.swing.JLabel jLabelWC;
    private javax.swing.JLabel jLabelWeather;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPane3;
    private javax.swing.JList<String> jList1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuHelp;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItemAddBuilding;
    private javax.swing.JMenuItem jMenuItemAddFloor;
    private javax.swing.JMenuItem jMenuItemLogout;
    private javax.swing.JMenuItem jMenuItemRemoveBuilding;
    private javax.swing.JMenuItem jMenuItemRemoveFloor;
    private javax.swing.JMenu jMenuLogout;
    private javax.swing.JMenu jMenuUserPOI;
    private javax.swing.JOptionPane jOptionPaneEdit;
    private javax.swing.JOptionPane jOptionPanePOIOption;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanelAddBuilding;
    private javax.swing.JPanel jPanelAddFloor;
    private javax.swing.JPanel jPanelAddPOI;
    private javax.swing.JPanel jPanelDeleteBuilding;
    private javax.swing.JPanel jPanelEditPOI;
    private javax.swing.JPanel jPanelFavSearch;
    private javax.swing.JPanel jPanelHelp;
    private javax.swing.JPanel jPanelLayerButtons;
    private javax.swing.JPanel jPanelPOIInfo;
    private javax.swing.JPanel jPanelRemoveFloor;
    private javax.swing.JPanel jPanelUpDown;
    private javax.swing.JPanel jPanelUserSearch;
    private javax.swing.JPanel jPanelWeather;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextFieldAddBuilding;
    private javax.swing.JTextField jTextFieldAddBuildingPath;
    private javax.swing.JTextField jTextFieldAddFloorPath;
    private javax.swing.JTextField jTextFieldEditPOIDesc;
    private javax.swing.JTextField jTextFieldEditPOIName;
    private javax.swing.JTextField jTextFieldPOIAddDesc;
    private javax.swing.JTextField jTextFieldPOIAddName;
    private javax.swing.JToggleButton jToggleButtonAC;
    private javax.swing.JToggleButton jToggleButtonCS;
    private javax.swing.JToggleButton jToggleButtonClass;
    private javax.swing.JToggleButton jToggleButtonLab;
    private javax.swing.JToggleButton jToggleButtonNav;
    private javax.swing.JToggleButton jToggleButtonRes;
    private javax.swing.JToggleButton jToggleButtonUserPOI;
    private javax.swing.JToggleButton jToggleButtonWR;
    private javax.swing.JLabel mapImage;
    private javax.swing.JScrollPane mapImageScrollPane;
    private javax.swing.JPanel mapPanel;
    private javax.swing.JButton minusZoomButton;
    private com.mycompany.mapp.POILayer pOILayer1;
    private javax.swing.JButton plusZoomButton;
    private javax.swing.JComboBox<String> searchBox;
    private javax.swing.JPanel topPanel;
    private javax.swing.JPanel zoomPanel;
    // End of variables declaration//GEN-END:variables
    
}
