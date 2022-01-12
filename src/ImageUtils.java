import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;


//TODO: BufferedImage to FXImage?
//TODO: BufferedImage to Image?
//TODO: Image to FXImage?

public class ImageUtils {

   public static BufferedImage imageToBufferedImage(Image im) {
      BufferedImage bi = new BufferedImage(im.getWidth(null), im.getHeight(null), BufferedImage.TYPE_INT_RGB);
      Graphics bg = bi.getGraphics();
      bg.drawImage(im, 0, 0, null);
      bg.dispose();
      return bi;
   }

   public static BufferedImage readImageFromFile(File file)
         throws IOException {
      return ImageIO.read(file);
   }

   public static void writeImageToPNG(File file, BufferedImage bufferedImage)
         throws IOException {
      ImageIO.write(bufferedImage, "png", file);
   }

   public static void writeImageToJPG(File file, BufferedImage bufferedImage)
         throws IOException {
      ImageIO.write(bufferedImage, "jpg", file);
   }
}