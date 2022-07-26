
import java.net.URL;
import java.util.List;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;


public class App {
    public static void main(String[] args) throws Exception {


        
        //Make a HTTP conection and search for the 250 movies
        
        FileInputStream fis = new FileInputStream("/mnt/c/Users/guial/OneDrive/Documents/Alura-Cursos/Imersao-Java/Alura-Stickers/src/resources/config.properties");
        Properties props = new Properties();
        props.load(fis);
        
        String url = props.getProperty("api5");
        ContentExtractor extractorImdb = new ContentExtractorImdb();
        var http = new ClientHttp();
        String json = http.searchData(url);

        String emoji = "\uD83C\uDF1F";
        
        List<Content> contents = extractorImdb.contentExtract(json);
        
        var stickerImdb = new StickerGenerater();
        int count = 0;
        for(int i = 0; i < contents.size(); i++){
            count ++;
            Content content = contents.get(i);
            String urlImage = content.getUrlImage();
            String title = content.getTitle();
            //System.out.println(urlImage);
            //System.out.println(title);
            InputStream inputStream = new URL(urlImage).openStream();
            String nameFile = title + ".png";
            
            stickerImdb.create(inputStream, nameFile, title);
            
            System.out.println();
            Scanner myrating = new Scanner(System.in);
            System.out.println("Note Language "+ count + ":");
            String value = myrating.nextLine();
            long ratingUsers = (Math.round(Double.parseDouble(value)));
            int finalRatingUsers = (int)ratingUsers;
            System.out.println("\u001b[1m \u001b[37m \u001b[45m User Rating:" + emoji.repeat(finalRatingUsers) + "\u001b[0m");
            System.out.println();
            System.out.println("\u001b[1m \u001b[35m \u001b[44m" + urlImage + "\u001b[0m");
            System.out.println();


        }

       //5-The body of my request send

        //System.out.println(body); 
        
        //Extract only the file that interest (title, post, classification)
        
        
        
        
        //display and manipulate the data
        //String emoji = "\uD83C\uDF1F";
        
        
        }
    }



