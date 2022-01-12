import java.awt.Color;
import java.awt.image.BufferedImage;


public class Fractal {

	static final int WIDTH  = 600;
	static final int HEIGHT = 600;
	
	BufferedImage fractalImage;
	
	static final int MAX_ITER = 1000;
	
	static final double DEFAULT_ZOOM       = 100.0;
	static final double DEFAULT_TOP_LEFT_X = -2.0;
	static final double DEFAULT_TOP_LEFT_Y = 5.0;
	
	double zoomFactor = DEFAULT_ZOOM;
	double topLeftX   = DEFAULT_TOP_LEFT_X;
	double topLeftY   = DEFAULT_TOP_LEFT_Y;


	


	private double getXPos(double x) {
		return x/zoomFactor + topLeftX;
	} // getXPos
	private double getYPos(double y) {
		return y/zoomFactor - topLeftY;
	} // getYPos


	private int computeIterations(double c_r, double c_i) {
		
		/*
		
		Let c = c_r + c_i
		Let z = z_r + z_i
		
		z' = z*z + c
		   = (z_r + z_i)(z_r + z_i) + (c_r + c_i)
		   = z_r² + 2*z_r*z_i - z_i² + c_r + c_i

		     z_r' = z_r² - z_i² + c_r
		     z_i' = 2*z_i*z_r + c_i
		     
		*/

		double z_r = 1;
		double z_i = 0.232;
		
		int iterCount = 0;

		// Modulus (distance) formula:
		// √(a² + b²) <= 2.0
		// a² + b² <= 4.0
		while ( z_r*z_r + z_i*z_i <= 4.0 ) {
			
			double z_r_tmp = z_r;
			
			z_r = z_r*z_r - z_i*z_i + c_r;
			z_i = 2*z_i*z_r_tmp + c_i;
			
			// Point was inside the Mandelbrot set
			if (iterCount >= MAX_ITER) 
				return MAX_ITER;
			
			iterCount++;
			
		}
		
		// Complex point was outside Mandelbrot set
		return iterCount;
		
	} // computeIterations

	private int makeColor( int iterCount ) {
		
		int color = 0b011011100001100101101000; 
		int mask  = 0b000000000000010101110111; 
		int shiftMag = iterCount / 13;
		
		if (iterCount == MAX_ITER) 
			return Color.BLACK.getRGB();
		
		return color | (mask << shiftMag);
		
	} // makeColor

		public BufferedImage getFractal(){
			fractalImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

			for (int x = 0; x < WIDTH; x++ ) {
				for (int y = 0; y < HEIGHT; y++ ) {
					
					double c_r = getXPos(x);
					double c_i = getYPos(y);
					
					int iterCount = computeIterations(c_r, c_i);
					
					int pixelColor = makeColor(iterCount);
					fractalImage.setRGB(x, y, pixelColor);
					
				}
			}
		return fractalImage;
	}

}

