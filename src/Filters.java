import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class Filters{

    // Every filter has image
    protected BufferedImage filteredImg;

    public Filters(BufferedImage filteredImg) {
        this.filteredImg = filteredImg;
    }
    // Use filter
    abstract void useFilter();

    //  Initialize after creating
    public void initialize(BufferedImage image) throws IOException {
        this.filteredImg = image;
        this.useFilter();
    }
}


