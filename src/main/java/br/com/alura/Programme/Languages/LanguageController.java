package br.com.alura.Programme.Languages;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LanguageController {
    
    private List<Language> languages = 
        List.of(
            new Language("Java", "https://raw.githubusercontent.com/abrahamcalf/programming-languages-logos/master/src/java/java_256x256.png", 1 ),
            new Language("Python", "https://raw.githubusercontent.com/abrahamcalf/programming-languages-logos/master/src/python/python_256x256.png", 2 ),
            new Language( "JavaScripy", "https://raw.githubusercontent.com/abrahamcalf/programming-languages-logos/master/src/javascript/javascript_256x256.png", 3)
        );


@GetMapping("/languages")
public List<Language> getLanguages() {
    return languages;
    }

}