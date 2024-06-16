import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.sound.sampled.SourceDataLine;

public class ImageEditorByArjunKshirsagar {

    public static BufferedImage ToGrayScale(BufferedImage inputImage) {

        // Get the width and height of the input image.
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();

        // Create a new BufferedImage to store the grayscale image with the same
        // dimensions as the input.
        // BufferedImage.TYPE_BYTE_GRAY represents a grayscale image.
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

        // Iterate over each pixel in the input image.
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // Get the RGB value of the pixel at (j, i) in the input image.
                int rgb = inputImage.getRGB(j, i);

                // Set the corresponding pixel in the output image to the same grayscale value.
                // This effectively converts the pixel to grayscale.
                outputImage.setRGB(j, i, rgb);
            }
        }
        return outputImage;
    }





    // This function adjusts the brightness of a pixel color by a given value.
    // Positive values increase brightness,
    // and negative values decrease it.
    public static Color getColor(int val, Color pixel) {
        int red = pixel.getRed();
        int blue = pixel.getBlue();
        int green = pixel.getGreen();

        // Adjust each color channel (red, green, and blue) based on the brightness
        // value.
        red += (int) (0.01 * val * red);
        blue += (int) (0.01 * val * blue);
        green += (int) (0.01 * val * green);

        // Ensure that the adjusted color values stay within the valid range [0, 255].
        if (red > 255)
            red = 255;
        if (blue > 255)
            blue = 255;
        if (green > 255)
            green = 255;
        if (red < 0)
            red = 0;
        if (blue < 0)
            blue = 0;
        if (green < 0)
            green = 0;

        // Return a new Color object with the adjusted color channel.
        return new Color(red, green, blue);
    }





    public static BufferedImage Brightness(BufferedImage inputImage, int val) {

        // Get the width and height of the input image.
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();

        // Create a new BufferedImage with the same dimensions and type as the input
        // image.
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

        // Iterate over each pixel in the input image and adjust its brightness.
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color pixel = new Color(inputImage.getRGB(j, i));

                // Get the adjusted pixel color using the getColor method.
                Color newPixel = getColor(val, pixel);

                // Set the adjusted pixel color in the output image.
                outputImage.setRGB(j, i, newPixel.getRGB());
            }
        }
        // Return the resulting adjusted BufferedImage.
        return outputImage;
    }





    // In this function we invert the image.
    // The vertical flip logic(invert) involves reversing the order of rows,and
    // keeping columns constant.
    public static BufferedImage upsideDown(BufferedImage inputImage) {
        // Get the width and height of the input image
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();

        // Create a new BufferedImage to store the horizontally flipped image
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // Get the RGB color value from the original image and set it in the flipped
                // image
                outputImage.setRGB(j, i, inputImage.getRGB(j, height - 1 - i));
            }
        }
        // Return the horizontally flipped image
        return outputImage;
    }





    // In this function we take the mirror image of the input image.
    // Mirroring the image involves reversing the order of columns,and keeping rows
    // constant.
    public static BufferedImage mirrorImage(BufferedImage inputImage) {
        // Get the width and height of the input image
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();

        // Create a new BufferedImage to store the mirror image
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // Get the RGB color value from the original image and set it in the flipped
                // image
                outputImage.setRGB(j, i, inputImage.getRGB(width - 1 - j, i));
            }
        }
        // Return the mirror image
        return outputImage;
    }






    // In this function we rotate the inpute image 90 degrees clockwise.
    public static BufferedImage Rotate_Clockwise(BufferedImage inputImage) {
        // Get the width and height of the input image
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();

        // Create a new BufferedImage to store the rotated image
        BufferedImage outputImage = new BufferedImage(height, width, BufferedImage.TYPE_3BYTE_BGR);

        // Iterate through the pixels of the input image and copy them to the output
        // image with rotation
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // Rotate the pixel from the input image and place it in the output image
                outputImage.setRGB(height - 1 - i, j, inputImage.getRGB(j, i));
            }
        }
        // Return the rotated image
        return outputImage;
        // Menthod 2: We can also solve this by taking the transpose of the
        // image(matrix), and then taking its mirror image.
    }





    // This function rotates the given BufferedImage 90 degrees anti-clockwise.
    public static BufferedImage Rotate_AntiClockwise(BufferedImage inputImage) {
        // Get the width and height of the input image
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();

        // Create a new BufferedImage to store the rotated image with swapped dimensions
        BufferedImage outputImage = new BufferedImage(height, width, BufferedImage.TYPE_3BYTE_BGR);

        // Loop through each pixel of the input image and copy it to the rotated image
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // Swap the x and y coordinates and copy pixel color to the rotated image
                outputImage.setRGB(i, width - 1 - j, inputImage.getRGB(j, i));
            }
        }
        // Return the rotated image
        return outputImage;
    }






    // This function applies a simple blurring effect to a BufferedImage by
    // averaging
    // pixel colors within a specified pixel count window.
    public static BufferedImage blur(BufferedImage inputImage, int pixelCount) {
        // Get the dimensions of the input image.
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();

        // Create a new BufferedImage for the output image with the same dimensions.
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Initialize row and column indices for the pixel window.
        int rowStart = 0;
        int rowEnd = pixelCount - 1;

        // Iterate through rows within the pixel window.
        while (rowEnd < height) {
            int columnStart = 0;
            int columnEnd = pixelCount - 1;

            // Iterate through columns within the pixel window.
            while (columnEnd < width) {
                int sumRed = 0;
                int sumGreen = 0;
                int sumBlue = 0;

                // Calculate the sum of Red, Green, and Blue components within the pixel window.
                for (int i = rowStart; i <= rowEnd; i++) {
                    for (int j = columnStart; j <= columnEnd; j++) {
                        Color pixel = new Color(inputImage.getRGB(j, i));
                        sumRed += pixel.getRed();
                        sumGreen += pixel.getGreen();
                        sumBlue += pixel.getBlue();
                    }
                }

                // Calculate the average Red, Green, and Blue values within the window.
                int avgRed = sumRed / (pixelCount * pixelCount);
                int avgGreen = sumGreen / (pixelCount * pixelCount);
                int avgBlue = sumBlue / (pixelCount * pixelCount);

                // Create a new Color object with the averaged values.
                Color newPixel = new Color(avgRed, avgGreen, avgBlue);

                // Apply the new pixel color to all pixels within the window.
                for (int i = rowStart; i <= rowEnd; i++) {
                    for (int j = columnStart; j <= columnEnd; j++) {
                        outputImage.setRGB(j, i, newPixel.getRGB());
                    }
                }

                // Move the column indices to the next window.
                columnStart += pixelCount;
                columnEnd += pixelCount;
            }
            // Move the row indices to the next window.
            rowStart += pixelCount;
            rowEnd += pixelCount;
        }
        // Return the final blurred image.
        return outputImage;
    }





    // This function performs a circular cropping of a given BufferedImage.
    public static BufferedImage circleCrop(BufferedImage inputImage) {
        // Get the dimensions of the input image
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();

        // Calculate the radius for the circular crop
        int radius = Math.min(height, width) / 2;

        // Create a new BufferedImage with the same dimensions and RGB color type
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Calculate the center offsets for the circle
        int xOffset = (width / 2);
        int yOffset = (height / 2);

        // Iterate through all pixels in the input image
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                // Formula of circle => x^2 + y^2 = r^2 ;

                // Check if the pixel is inside the circular region
                if (((x - xOffset) * (x - xOffset)) + ((y - yOffset) * (y - yOffset)) <= (radius * radius)) {
                    // Copy the pixel color from the input image to the output image
                    outputImage.setRGB(x, y, inputImage.getRGB(x, y));
                }
                
                //  Uncomment the following else block to fill the outside of the circle with white color:
                //  else {
                //  Color c = new Color(255, 255, 255);
                //  outputImage.setRGB(x, y, c.getRGB());
                //  }
                 
            }
        }

        // Return the resulting circular cropped image
        return outputImage;
    }






    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Welcome\nInput your file name: ");

        String input_filename = sc.next();

        // Making file object
        File inputFile = new File(input_filename);

        try {
            // Input image object
            BufferedImage inputImage = ImageIO.read(inputFile);

            System.out.println("SELECT ANY OF THE FOLLOWING OPERATIONS\n" +
                    "1. Convert to GrayScale\n" +
                    "2. Change Brightness\n" +
                    "3. Upside Down(Horizontal Flip)\n" +
                    "4. Mirror Image (Vertical Flip)\n" +
                    "5. Rotate Clockwise\n" +
                    "6. Rotate Anti-Clockwise\n" +
                    "7. Blur the image\n" +
                    "8. Circle crop");

            int operation = sc.nextInt();

            BufferedImage outputImage;

            switch (operation) {
                case 1 -> outputImage = ToGrayScale(inputImage);
                case 2 -> {
                    System.out.println("Enter the brightness change factor in percentage: ");
                    // int val = sc.nextInt();
                    outputImage = Brightness(inputImage, sc.nextInt());
                }

                case 3 -> outputImage = upsideDown(inputImage);

                case 4 -> outputImage = mirrorImage(inputImage);

                case 5 -> outputImage = Rotate_Clockwise(inputImage);

                case 6 -> outputImage = Rotate_AntiClockwise(inputImage);

                case 7 -> {
                    System.out.println("Enter the blur change factor in pixels: ");
                    // int val = sc.nextInt();
                    outputImage = blur(inputImage, sc.nextInt());
                }

                case 8 -> outputImage = circleCrop(inputImage);

                default -> {
                    System.out.println("Invalid input.\n" +
                            "Please provide a valid input in the range of 1 to 8.\n" +
                            "Thank you!");
                    return;
                }
            }

            System.out.println("Enter output path for your file with filename.extension: ");
            File outputFile = new File(sc.next());

            ImageIO.write(outputImage, "jpg", outputFile);

            System.out.println("The code has been executed successfully.\n" +
                    "Output image has been saved by the name: " + outputFile + "\n" +
                    "Thank you for running the program.");
        } catch (IOException e) {
            System.out.println("Invalid image file.\n" +
                    "Please try with valid input & run the program again.\n" +
                    "Thank you!");
        }
    }
}