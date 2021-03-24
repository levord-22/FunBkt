package Utility;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.jar.JarFile;

public class ResourceLoader {

    public static Image uploadImage (String path){
        BufferedImage sourceImage = null;
        try{
            URL url;
            final File jarFile = new File(ResourceLoader.class.getProtectionDomain().getCodeSource().getLocation().getPath());
            if(jarFile.isFile()) {  // Run with JAR file

                final JarFile jar = new JarFile(jarFile);


                InputStream fileInputStreamReader = jar.getInputStream(jar.getJarEntry(path));
                sourceImage = ImageIO.read(fileInputStreamReader);
                jar.close();
            } else { // Run with IDE

                url = ResourceLoader.class.getResource( "../" + path);
//                System.out.println("Class.path form IDE: " + ResourceLoader.class.getProtectionDomain().getCodeSource().getLocation().getPath());
//                System.out.println("Path form IDE: " + path);
//                System.out.println("URL form IDE: " + url);

                sourceImage = ImageIO.read(url);
            }

            //URL url = getClass().getResource(path);
//            System.out.println(jarFile);
//            File file = new File(getClass().getResource(path).getFile());

        }catch (IOException e){
            e.printStackTrace();
        }
        return Toolkit.getDefaultToolkit().createImage(sourceImage.getSource());
    }

    public static InputStream uploadAudio (String path){
        InputStream sourceSound = null;
        try{

            final File jarFile = new File(ResourceLoader.class.getProtectionDomain().getCodeSource().getLocation().getPath());
            if(jarFile.isFile()) {  // Run with JAR file

                final JarFile jar = new JarFile(jarFile);

                InputStream fileInputStreamReader =(jar.getInputStream(jar.getEntry(path)));
                byte[] byteArray = new byte[fileInputStreamReader.available()];

                int i = 0;
                int length;
                while ((length = fileInputStreamReader.read()) != -1) {
                    byteArray[i] = (byte) length;
                    i++;
                }

                if (byteArray.length > 500000 ){
                    File fileOut = new File("H:/1.wav");
                    FileOutputStream fos = new FileOutputStream(fileOut);
                    fos.write(byteArray);
                    fos.close();
                }

                InputStream newInputStreamFromArray =  new BufferedInputStream(new ByteArrayInputStream(byteArray));

                sourceSound = newInputStreamFromArray;
                jar.close();

            } else { // Run with IDE

                URL url = ResourceLoader.class.getResource( "../" + path);
                InputStream fileInputStreamReader = new BufferedInputStream(new FileInputStream(url.getPath()));
                sourceSound = fileInputStreamReader;
            }

        }catch (IOException e){
            e.printStackTrace();
        }
        return sourceSound;
    }
}
