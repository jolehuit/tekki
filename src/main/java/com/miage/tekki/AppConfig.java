package com.miage.tekki;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public CsvPeopleRepository databaseSimulator() {
        return new CsvPeopleRepository();
    }

    @Bean
    public QuestionService questionService() {
        // Initialisez votre QuestionService ici selon vos besoins
        return new QuestionService();
    }
}
