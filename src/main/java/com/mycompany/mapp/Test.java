
import com.mycompany.mapp.AppWindow;
import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

/**
 *
 * @author DELL
 */
public class Test {
    
    public static void main(String[] args) {
        System.out.println("Hello World!");
       // AppWindow ll = new AppWindow(true);
       deleteFavourites("234,602,jTextField1,jTextField2,0,false,0");
        
    }
    public void changeIndex(){
        
    }
    private static void deleteFavourites(String delete){
        Path path = Paths.get("src/main/resources/POI info/favourites/favourites.txt");
        Charset charset = StandardCharsets.UTF_8;
        
        try{
            String content = new String(Files.readAllBytes(path), charset);
            
            
                String reg = delete+",.*";
                content = content.replaceAll(reg,"");
            
            
            Files.write(path, content.getBytes(charset));
        }
        catch(Exception e){
            System.out.println(e+" ChangePOI");
            
        }
        
        
    }
    
}
