package com.miage.tekki;

import java.util.List;

public class PersonService {
    private CsvPeopleRepository csvPeopleRepository;

    public Person getRandomPerson() {
        return csvPeopleRepository.getRandomPerson();
    }

    public List<Person> getFilteredPeople(int questionId) {
        return csvPeopleRepository.getFilteredPeople(questionId);
    }
}
