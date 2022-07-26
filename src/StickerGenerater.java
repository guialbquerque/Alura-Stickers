import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.util.Scanner;

public class StickerGenerater {
    
    public void create(InputStream inputStream, String nameFile, String nameContent) throws Exception{//Method to create stickers for whatsapp
        //The images will be read, resize, write something below, apply transparency into the text and save them
        //Readying image through an URL  
        //InputStream inputStream = new URL("https://imersao-java-apis.s3.amazonaws.com/TopMovies_1.jpg").openStream(); 
        
        
        
        //1-Readying the images
        BufferedImage originalImage = ImageIO.read(inputStream);
        
        
        //2-Creating a new image in memory with transparency and new size
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        int newHeight = (int) (height + (0.2*height));
        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);
        
        //creating my photo 
        BufferedImage myPhoto = ImageIO.read(new File("myPhoto/approved.jpg"));   
        //3-Copy original images to new images (in memory)
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(originalImage, 0, 0, null);
        Graphics2D graphics2 = (Graphics2D) newImage.getGraphics();
        graphics2.drawImage(myPhoto, (int) (width - 0.3*width), (int) (newHeight -(0.2*newHeight)), (int) (0.3*width), (int) (0.2*newHeight), null);
        
        //Setup the font
       // Font newFont = Font.createFont(Font.LAYOUT_LEFT_TO_RIGHT, new File("impactFont/impact.ttf"));
        Font newFont = new Font(Font.SANS_SERIF, Font.BOLD, 72);
        newFont = newFont.deriveFont(Font.LAYOUT_NO_LIMIT_CONTEXT, (int) (0.15*width));
        
        graphics.setColor(Color.RED);
        graphics.setFont(newFont);
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        FontRenderContext context = graphics.getFontRenderContext();
        //Setup the text
        System.out.println("\u001b[1m \u001b[3m \u001b[33m \u001b[41m" + nameContent + "\u001b[0m");
        Scanner myrating = new Scanner(System.in);
        System.out.println("Classify this language in one word:");
        String text = myrating.nextLine(); 
        
        int textWidth = graphics.getFontMetrics().stringWidth(text);
        graphics.setColor(Color.RED);
        graphics.drawString(text, (width - textWidth) / 2, (int) (newHeight - (0.1*height)));

        //setup the outline  
        TextLayout layout = new TextLayout(text, newFont, context);
        layout.draw(graphics, (width - textWidth) / 2, (int) (newHeight - (0.1*height)));
        AffineTransform transform = graphics.getTransform();
        Shape outline = layout.getOutline(null);
        transform.translate((width - textWidth) / 2, (int) (newHeight - (0.1*height)));
        graphics.transform(transform);
        graphics.setColor(Color.YELLOW);
        graphics.draw(outline);
            
        graphics.setClip(outline);

        graphics.dispose();
        
        ImageIO.write(newImage, "png", new File("whatsAppPackage/"+ nameFile));
    }
}
