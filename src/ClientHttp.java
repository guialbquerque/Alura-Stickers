import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class ClientHttp {
    
    public String searchData(String url){
        try {
            
            URI address = URI.create(url); //0- Create a URI instance 
            var client = HttpClient.newHttpClient(); //1- Created the object that will make all process of send requests and retrieve responses
            var request = HttpRequest.newBuilder(address).GET().build(); //2 - This object make all process to search the data and build the api
    
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            //3-bodyhandlers -> class that allow creates differents ways to execute the request
            //4- response is a variable that returns an anwser of the request in a string format
            String body = response.body(); 
            return body;
        } catch (IOException | InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }
}
