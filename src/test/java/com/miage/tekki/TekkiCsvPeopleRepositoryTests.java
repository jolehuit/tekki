package com.miage.tekki;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.BeforeClass;
import org.mockito.Mockito;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class TekkiCsvPeopleRepositoryTests {
    private CsvPeopleRepository repository;
    private Person personNI;

    public Resource csvResource() {
        return new ClassPathResource("people.csv");
    }
    
    @Before
    public void setUp() throws Exception {
        repository = new CsvPeopleRepository(csvResource());
    }
    
    @Test
    public void testGetFilteredPeople() {
    	assertFalse(repository.getFilteredPeople("sex", "F").isEmpty());
    }
    
    @Test
    public void testGetRandomPerson() {
    	assertEquals(repository.getRandomPerson().getClass(), Person.class);
    }
    
    @Test
    public void testGetPropertyByQuestion() {
    	Person person = new Person(
                "12345",
                "Alice",
                'F',
                "Prof",
                LocalDate.of(1990, 5, 15),
                31,
                "French",
                "Taurus",
                "Paris",
                Optional.of(165),
                Optional.of(60),
                Optional.of("Brown"),
                Optional.of("Blonde"),
                Optional.empty(),
                Optional.of("Scar on left cheek"),
                Optional.of("Tattoo on right arm"),
                Optional.empty()
        );
    	
    	Question question = new Question(0 , "", "eyeColor");
        assertEquals("Brown", repository.getPropertyByQuestion(person, question));

        question = new Question(1, "", "age");
        assertEquals("31", repository.getPropertyByQuestion(person, question));

        question = new Question(2, "", "heightInCm");
        assertEquals("165", repository.getPropertyByQuestion(person, question));

        question = new Question(3, "", "weightInKg");
        assertEquals("60", repository.getPropertyByQuestion(person, question));

        question = new Question(4, "", "sex");
        assertEquals("F", repository.getPropertyByQuestion(person, question));
        
        question = new Question(5, "", "nationality");
        assertEquals("French", repository.getPropertyByQuestion(person, question));

        question = new Question(6, "", "zodiac");
        assertEquals("Taurus", repository.getPropertyByQuestion(person, question));

        question = new Question(7, "", "birthPlace");
        assertEquals("Paris", repository.getPropertyByQuestion(person, question));

        question = new Question(8, "", "hairColor");
        assertEquals("Blonde", repository.getPropertyByQuestion(person, question));

        question = new Question(9, "", "deathDate");
        assertNull(repository.getPropertyByQuestion(person, question));

        question = new Question(10, "", "particularity1");
        assertEquals("Scar on left cheek", repository.getPropertyByQuestion(person, question));
        
        question = new Question(11, "", "particularity2");
        assertEquals("Tattoo on right arm", repository.getPropertyByQuestion(person, question));

        question = new Question(12, "", "particularity3");
        assertNull(repository.getPropertyByQuestion(person, question));
        
        question = new Question(13, "", "profession");
        assertEquals("Prof", repository.getPropertyByQuestion(person, question));
        
        question = new Question(11, "", "birthday");
        assertEquals("1990-05-15", repository.getPropertyByQuestion(person, question));
        
        question = new Question(11, "", "name");
        assertEquals("Alice", repository.getPropertyByQuestion(person, question));
        
        question = new Question(11, "", "id");
        assertEquals("12345", repository.getPropertyByQuestion(person, question));
        
        question = new Question(13, "", "particularity4");
        assertNull(repository.getPropertyByQuestion(person, question));

    }
    
    @Test
    public void testSelectRandomPeopleIncluding() {
    	Person person = new Person(
                "12345",
                "Alice",
                'F',
                "Prof",
                LocalDate.of(1990, 5, 15),
                31,
                "French",
                "Taurus",
                "Paris",
                Optional.of(165),
                Optional.of(60),
                Optional.of("Brown"),
                Optional.of("Blonde"),
                Optional.empty(),
                Optional.of("Scar on left cheek"),
                Optional.of("Tattoo on right arm"),
                Optional.empty()
        );
    	
    	assertFalse(repository.selectRandomPeopleIncluding(person).isEmpty());
    	
    	assertTrue(repository.selectRandomPeopleIncluding(personNI).isEmpty());
    }
    
    
}