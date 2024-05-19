package com.miage.tekki;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
public class AppConfig {

    @Bean
    public Resource csvResource() {
        return new ClassPathResource("people.csv");
    }

    @Bean
    public CsvPeopleRepository databaseSimulator(Resource csvResource) {
        return new CsvPeopleRepository(csvResource);
    }

    @Bean
    public PersonService personService(CsvPeopleRepository csvPeopleRepository) {
        return new PersonService(csvPeopleRepository);
    }

    @Bean
    public QuestionService questionService(CsvPeopleRepository csvPeopleRepository) {
        return new QuestionService(csvPeopleRepository);
    }
}
