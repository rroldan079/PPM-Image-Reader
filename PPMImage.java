package hw02; 

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * This class implements a constructor that reads a ppm image file and reads
 * its bytes.
 * <br />
 * Additionally, the class also implements methods that read a ppm file, writes
 * a ppm output file located in a folder containing output
 * files, and changes the image to a greyscale, sepia, or negative.
 * 
 * @author Roger Roldan, 400986323, CS 2013- 01/02
 * 
 * 
 */
public class PPMImage {
	/** this value is the magic number of the file*/
	String magicNumber;
	/** this value is the width of the image file*/
	int width;
	/** this value is the height of the image file*/
	int height;
	/** this value is the maximum color value of each pixel channel of the file*/
	int maxColorValue;
	/** this value represents the pixel data, or raster*/
	char[] raster = new char[width * height * 3];
	
	
	/**
	 * 
	 * Constructor takes a ppm image as a parameter and uses the (@code readImage) method to read 
	 * the image. An exception is thrown when the file is not a ppm image.
	 * 
	 * @param imageFileName image file that is being read
	 * @throws FileNotFoundException exception type that prints a message
	 * states to use a ppm image file as your parameter. 
	 * 
	 * 
	 *
	 */
	public PPMImage(String imageFileName) throws FileNotFoundException{
		if(imageFileName.endsWith(".ppm")) {
			readImage(imageFileName);
		}
		else {
			throw new FileNotFoundException("Please enter a ppm image");
		}
	}
	
	
	
	/**
	 * This method writes the read ppm image file into another file.
	 * @param outputImageFileName ppm image file that is being written on.
	 * @throws IOException exception that is thrown when the parameter file
	 * type is not ".ppm".
	 */
	
	@SuppressWarnings("unused")
	public void writeImage(String outputImageFileName) throws IOException {
		
		if(outputImageFileName.endsWith(".ppm")) {
		 
		 /**
		  * New file relative file path is created. 
		  */
			File output = new File("src/outputFiles/" + outputImageFileName);
		/** 
		 * FileOutputStream is used to write data onto the new output file type.
		 */
			FileOutputStream stream;
			stream = new FileOutputStream(output);
		/**
		 * DataOutputStream is then used to read primitive data types, in this case bytes.
		 * It takes BufferedOutputStream in order to capture the bytes in the file using the buffer.
		 * This is used to improve efficiency.
		 */
			DataOutputStream file = new DataOutputStream(new BufferedOutputStream(stream));
	/**
	 * All of the data from the image is then written as a sequence of bytes to the output stream.
	 * All integer data types are converted to a string and seperated by spaces represented as bytes,
	 * which is much like how a .ppm image appears when viewed in a text editor.
	 */
			file.writeBytes(magicNumber);
			file.writeByte('\n');
			file.writeBytes(Integer.toString(this.width));
			file.writeByte('\n');
			file.writeBytes(Integer.toString(this.height));
			file.writeByte('\n');
			file.writeBytes(Integer.toString(this.maxColorValue));
			file.writeByte('\n');
			
	/**
	 * each of the pixel data (elements) of raster are written onto the file.
	 */
			int i = 0;
			for(char elements : raster) {
				file.write((int) raster[i]);
				i++;
			}
	/**
	 * Any buffered bytes are then flushed and the file is then closed.
	 */
			file.flush();
			file.close();
		}
		else {
			throw new IOException("File must be a ppm file type");
		}
		
	}
	
	/**
	 * This method parses the data of the given file and reads it.
	 * 
	 * @param imageFileName file that is being read. 
	 */
	private void readImage(String imageFileName){
		File input = new File("src/files/" + imageFileName);
		/** 
		 * Code is surrounded by a try-catch in order to catch any {@code FilenotFoundException} and {@code IOException}
		 */
		try {
			/**
			 * File is read using FileInputStream {@code stream}, which reads streams of raw bytes from the {@code input} 
			 * image file.
			 */
		
			FileInputStream stream;
			stream = new FileInputStream(input);
			/**
			 * {@code raster} represents the size of the image file in pixels.
			 */
			this.raster = new char[this.width * this.height * 3];
			/**
			 * {@code file} is used to read any primitive data types, and take a new {@code BufferedInputStream(stream)} as
			 * a parameter in order to read the primitive data types from a stream, which improves performance.
 			 */
			DataInputStream file = new DataInputStream(new BufferedInputStream(stream));
			
			/**
			 * {@code ppmPixels} represents the content of the image file's pixels.
			 */
			String[] ppmPixels = new String[3];
			/**
			 * Each element of {@code ppmPixels} is read, and the loop prints a message stating that the data has been read
			 * once it has ended.
			 */
			int count = 0;
			for(int i = 0; i < 3; i++) {
				ppmPixels[i] = file.readLine();
				count++;
				
				if(count == 3) {
					System.out.println("Byte data has been read");
					break;
				}
			}
			/**
			 * Each element of the {@code ppmImage} array (from 0 to 2) is represented in the array:
			 * the first element (ppmPixels[0]) is represented as the magic number.
			 * the second element (ppmPixels[1]) is split and represented as the {@code resultion} String array.
			 * the first and second elements of {@code resolution} are parsedm along with the second element
			 * of {@code ppmPixels}, which represents the {@code maxColorValue}.
			 * <br>
			 * <br>
			 * This is done in order to part/organize the different data values seperately (as they appear in a text file).
			 * 
			 */
			this.magicNumber = ppmPixels[0];
			String[] resolution = ppmPixels[1].split(" ");
			
			this.width = Integer.parseInt(resolution[0]);
			this.height = Integer.parseInt(resolution[1]);
			this.raster = new char[this.width * this.height * 3];
			this.maxColorValue = Integer.parseInt(ppmPixels[2]);
			
			/**
			 * each of the elements of {@code raster} are also read throigh iteratively as characters.
			 */
			for(int i = 0; i < raster.length; i++) {
				raster[i] = (char) file.read();
			}
			
		} 
		catch(FileNotFoundException e) {
			e.printStackTrace();
		} 
		catch(IOException e) {
			e.printStackTrace();
		}
		
		
		
		
	}
	
	/**
	 * This method turns a .ppm image into a gray scale version of itself. 
	 */
	public void grayScale(){
		/**
		 * raster array is iterated, and each raster pixel is converted into it's new color to turn the image to a gray scale.
		 *  */ 
		for(int i = 0; i < raster.length; i++) {
			char formerRed = raster[i];
			char formerBlue = raster[i + 2];
			char formerGreen = raster[i + 1];
			
			char newRed = (char)( (formerRed * 0.229) + (formerBlue * 0.114) + (formerGreen * 0.587));
			char newBlue = (char)( (formerRed * 0.229) + (formerBlue * 0.114) + (formerGreen * 0.587));
			char newGreen = (char)( (formerRed * 0.229) + (formerBlue * 0.114) + (formerGreen * 0.587));
			
			raster[i] = newRed;
			raster [i + 2] = newBlue;
			raster[i + 1] = newGreen;
			
			i += 2;
		}
	}
	
	/**
	 * Thsi method turns the ppm image into a sepia version of itself.
	 */
	public void sepia() {
		/**
		 * Each original pixel is iterated in the raster array and changed if the raster element is less than 255.
		 * This is done to change the image to a sepia versopm of itself.
		 */
		for(int i = 0; i < raster.length; i++) {
			char formerRed = raster[i];
			char formerBlue = raster[i + 2];
			char formerGreen = raster[i + 1];
			
			char newRed = (char)( (formerRed * 0.393) + (formerBlue * 0.769) + (formerGreen * 0.189));
			raster[i] = newRed;
			if((int) raster[i] > 255) {
				raster[i] = (char) 255;
			}
			
			char newBlue = (char)( (formerRed * 0.272) + (formerBlue * 0.534) + (formerGreen * 0.131));
			raster[i + 2] = newBlue;
			if((int) raster[i + 2] > 255) {
				raster[i + 2] = (char) 255;
		}
			
			char newGreen = (char)( (formerRed * 0.349) + (formerBlue * 0.686) + (formerGreen * 0.168));
			raster[i + 1] = newGreen;
			if((int) raster[i + 1] > 255) {
				raster[i + 1] = (char) 255;
		}
			i += 2;
	}
 }
	/**
	 * This method turns the ppm image into a negative version of itself.
	 */
	public void negative() {
		
		for(int i = 0; i < raster.length; i++) {
			/**
			 * representation of original red, blue, and green pixels as elements in raster (the whole image represented in pixels)
			 */
			char formerRed = raster[i];
			char formerBlue = raster[i + 2];
			char formerGreen = raster[i + 1];
			/**
			 * each pixel is converted to a new color shade in order to turn whole image into a negative.
			 */
			char newRed = (char)(255 - formerRed);
			raster[i] = newRed;
			if((int) raster[i] > 255) {
				raster[i] = (char) 255;
			}
			
			char newBlue = (char)(255 - formerBlue);
			raster[i + 2] = newBlue;
			if((int) raster[i + 2] > 255) {
				raster[i + 2] = (char) 255;
			}
			
			char newGreen = (char)(255 - formerGreen);
			raster[i + 1] = newGreen;
			if((int) raster[i + 1] > 255) {
				raster[i + 1] = (char) 255;
			}
			
			i += 2;
		}
	}
	
}
