import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;
import java.lang.Math;
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

        String url = props.getProperty("api");
        URI address = URI.create(url); //0- Create a URI instance 
        var client = HttpClient.newHttpClient(); //1- Created the object that will make all process of send requests and retrieve responses
        var request = HttpRequest.newBuilder(address).GET().build(); //2 - This object make all process  to search the data and build the api

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        //3-bodyhandlers -> class that allow creates differents ways to execute the request
        //4- response is a variable that returns an anwser of the request in a string format
        String body = response.body(); //5-The body of my request send

        //System.out.println(body); 
        
        //Extract only the file that interest (title, post, classification)
        
        
        var parser = new JsonParser(); //
        List<Map<String, String>> listOfMovies = parser.parse(body);
        
        //display and manipulate the data
        String emoji = "\uD83C\uDF1F";
        
        for (Map<String, String> movie : listOfMovies) {

            String urlImage = movie.get("image");
            String title = movie.get("title");
            InputStream inputStream = new URL(urlImage).openStream();    
            String nameFile = title + ".png";
            StickerGenerater stickerGenerater = new StickerGenerater();
            stickerGenerater.create(inputStream, nameFile);


            System.out.println("\u001b[1m \u001b[3m \u001b[33m \u001b[41m" + title + "\u001b[0m");
            System.out.println();
            System.out.println("\u001b[1m \u001b[35m \u001b[44m" + movie.get("image") + "\u001b[0m");
            System.out.println();
            

            long rating = (Math.round(Double.parseDouble(movie.get("imDbRating"))));
            int finalRating = (int)rating;
            System.out.println("\u001b[1m \u001b[37m \u001b[45m Rating:" + emoji.repeat(finalRating) + "\u001b[0m");
            
            System.out.println();
            System.out.println("-------------------------------------------------------------------");

         }System.out.println("Users avaliation");
         int count = 0;
         for (Map<String, String> movie : listOfMovies){
            count ++;
           
            Scanner myrating = new Scanner(System.in);
            System.out.println("\u001b[1m \u001b[3m \u001b[33m \u001b[41m" + movie.get("title") + "\u001b[0m");
            System.out.println("Movie: " + count + ", How do you classify?");
    
            String value = myrating.nextLine(); 
            long ratingUsers = (Math.round(Double.parseDouble(value)));  
            int finalRatingUsers = (int)ratingUsers;
            System.out.println("\u001b[1m \u001b[37m \u001b[45m User Rating:" + emoji.repeat(finalRatingUsers) + "\u001b[0m");
            

         }
    }
}
