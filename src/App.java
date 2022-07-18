import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class App {
    public static void main(String[] args) throws Exception {

        //Make a HTTP conection and search for the 250 movies

        String url = "https://api.mocki.io/v2/549a5d8b";
        URI address = URI.create(url); //0- Create a URI instance 
        var client = HttpClient.newHttpClient(); //1- Created the object that will make all process of send requests and retrieve responses
        var request = HttpRequest.newBuilder(address).GET().build(); //2 - This object make all process  to search the data and build the api

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        //3-bodyhandlers -> class that allow creates differents ways to execute the request
        //4- response is a variable that returns an anwser of the request in a string format
        String body = response.body(); //5-The body of my request send

        System.out.println(body); 
        
        //Extract only the file that interest (title, post, classification)
        
        //display and manipulate the data
    }
}
