package com.miage.tekki;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class TekkiPersonServiceTests {
	private PersonService personService;
    private CsvPeopleRepository csvPeopleRepository;

    @BeforeEach
    void setUp() {
        personService = new PersonService();
        csvPeopleRepository = new CsvPeopleRepository();
    }

    @Test
    void testGetRandomPerson() {
        assertEquals(csvPeopleRepository.getRandomPerson().getClass(), Person.class);
    }

}
