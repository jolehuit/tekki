package com.miage.tekki;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TekkiPersonServiceTests {
	private PersonService personService;
    private CsvPeopleRepository csvPeopleRepository;

    public Resource csvResource() {
        return new ClassPathResource("people.csv");
    }
    
    @BeforeEach
    public void setUp() {
        csvPeopleRepository = new CsvPeopleRepository(csvResource());
        personService = new PersonService(csvPeopleRepository);
    }

    @Test
    public void testGetRandomPerson() {
        assertEquals(personService.getRandomPerson().getClass(), Person.class);
    }
    
    @Test
    public void testGetFilteredPeople() {
    	assertFalse(personService.getFilteredPeople("sex", "F").isEmpty());
    }

}
