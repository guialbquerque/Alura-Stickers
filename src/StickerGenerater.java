import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;

public class StickerGenerater {
    
    public void create(InputStream inputStream, String nameFile) throws Exception{//Method to create stickers for whatsapp
        //The images will be read, resize, write something below, apply transparency into the text and save them

        //Readying image through an URL  
        //InputStream inputStream = new URL("https://imersao-java-apis.s3.amazonaws.com/TopMovies_1.jpg").openStream(); 
        
        //1-Readying the images
        BufferedImage originalImage = ImageIO.read(inputStream);

        //2-Creating a new image in memory with transparency and new size
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        int newHeight = height + 200;
        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

        //3-Copy original images to new images (in memory)
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(originalImage, 0, 0, null);

        //Setup the font

        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 72);
        graphics.setColor(Color.RED);
        graphics.setFont(font);

        String text = "TOPZERA";
        int textWidth = graphics.getFontMetrics().stringWidth(text);
        //4-Write a phrase in each image
        graphics.drawString(text, (width - textWidth) / 2, newHeight - 100);
        //5-Write the new images in a file
        ImageIO.write(newImage, "png", new File("output/"+nameFile));

    }

}
