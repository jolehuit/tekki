package com.miage.tekki;

import java.util.List;

public class PersonService {
    private final CsvPeopleRepository csvPeopleRepository;

    public PersonService(CsvPeopleRepository csvPeopleRepository) {
        this.csvPeopleRepository = csvPeopleRepository;
    }

    public Person getRandomPerson() {
        return csvPeopleRepository.getRandomPerson();
    }

    public List<Person> getFilteredPeople(String property, String expectedAnswer) {
        return csvPeopleRepository.getFilteredPeople(property, expectedAnswer);
    }
}
