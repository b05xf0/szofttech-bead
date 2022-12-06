package resources;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.imageio.ImageIO;

public class ResourceLoader {
    public static InputStream loadResource(String resName){
        return ResourceLoader.class.getClassLoader().getResourceAsStream(resName);
    }
    
    public static Image loadImage(String resName){
        URL url = ResourceLoader.class.getResource(resName);
          try {return ImageIO.read(url); }
        catch(IOException e){ return null;}
    }
}
