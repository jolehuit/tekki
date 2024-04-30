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
    public PersonService personService () {
        return new PersonService();
    }

    @Bean
    public QuestionService questionService() {
        return new QuestionService();
    }
}
