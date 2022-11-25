
import com.mycompany.mapp.AppWindow;
import java.io.File;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

/**
 *
 * @author DELL
 */
public class Mapp {
    private int floorIndex=0;
    private String buildingPath = "src/main/resources/images/Floors/MC";
    private File buildingLocation = new File(buildingPath);
    private String[] buildingNameFileList = buildingLocation.list();
    private String imgURL = buildingPath+"/"+buildingNameFileList[floorIndex];
    public static void main(String[] args) {
        System.out.println("Hello World!");
        AppWindow ll = new AppWindow();
        
    }
    public void changeIndex(){
        
    }
}
