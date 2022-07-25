
import java.net.URL;
import java.util.List;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;


public class App {
    public static void main(String[] args) throws Exception {


        
        //Make a HTTP conection and search for the 250 movies
        
        FileInputStream fis = new FileInputStream("/mnt/c/Users/guial/OneDrive/Documents/Alura-Cursos/Imersao-Java/Alura-Stickers/src/resources/config.properties");
        Properties props = new Properties();
        props.load(fis);
        
        String url = props.getProperty("api");
        ContentExtractor extractorImdb = new ContentExtractorImdb();
        var http = new ClientHttp();
        String json = http.searchData(url);
        
        List<Content> contents = extractorImdb.contentExtract(json);

        var stickerNasa = new StickerGenerater();

        for(int i = 0; i <= 5; i++){

            Content content = contents.get(i);

            InputStream inputStream = new URL(content.getUrlImage()).openStream();
            String nameFile = content.getTitle() + ".png";

            stickerNasa.create(inputStream, nameFile);

            System.out.println(content.getTitle());
            System.out.println();

        }

       //5-The body of my request send

        //System.out.println(body); 
        
        //Extract only the file that interest (title, post, classification)
        
        
        
        
        //display and manipulate the data
        //String emoji = "\uD83C\uDF1F";
        
        
        }
    }



