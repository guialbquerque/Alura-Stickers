package br.com.alura.Programme.Languages;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LanguageController {
    
    @Autowired
    private LanguageRepository repository;

@GetMapping("/languages")
public List<Language> getLanguages() {
    List<Language> languages = repository.findAll();
    return languages;
    }

}