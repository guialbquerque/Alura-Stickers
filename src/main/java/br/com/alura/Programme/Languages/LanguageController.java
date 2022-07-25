package br.com.alura.Programme.Languages;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LanguageController {
    
    @GetMapping(value="/languages")
    public String getLanguages(){
        return "Hello, Java!";
    }
}
