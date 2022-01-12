import java.awt.Color;
import java.awt.image.BufferedImage;


public class NegativeFilter extends Filters {


    public NegativeFilter(BufferedImage filteredImg) {
        super(filteredImg);
        //TODO Auto-generated constructor stub
    }

    @Override
    void useFilter() {
        for (int x = 0; x < this.filteredImg.getWidth(); x++) {
            for (int y = 0; y < this.filteredImg.getHeight(); y++) {
                int rgba = this.filteredImg.getRGB(x, y);
                Color color = new Color(rgba, true);
                //Negate every pixel
                color = new Color(255 - color.getRed(), 255 - color.getGreen(), 255 - color.getBlue());
                this.filteredImg.setRGB(x, y, color.getRGB());
            }
        }
    }



}
