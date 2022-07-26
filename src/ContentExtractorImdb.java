import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ContentExtractorImdb implements ContentExtractor{

    
    public List<Content> contentExtract(String json) {
        var parser = new JsonParser(); //
        List<Map<String, String>> listOfContents = parser.parse(json);

        List<Content> contents = new ArrayList<Content>();
        for (Map<String, String> content : listOfContents) {

            String title = content.get("title");
            String urlImage = content.get("image");

            var contentsImdb = new Content(urlImage, title);
            contents.add(contentsImdb);
        //  }     
    }
        return contents;
    }
}
