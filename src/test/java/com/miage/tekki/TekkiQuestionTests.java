package com.miage.tekki;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

public class TekkiQuestionTests {
	Question qEye, qHair, qAge, qAge2, qFeetSize;
	Person p1, p2;
	
	@Test
    public void testMatches() {
        Person person = new Person(
            "12345",
            "Alice",
            'F',
            LocalDate.of(1990, 5, 15),
            31,
            "French",
            "Taurus",
            "Paris",
            Optional.of(165),
            Optional.of(60),
            "Brown",
            "Blonde",
            Optional.of(LocalDate.of(2024, 5, 15)),
            Optional.of("Scar on left cheek"),
            Optional.of("Tattoo on right arm"),
            Optional.of("Manchot")
        );
        
        Person person2 = new Person(
                "12345",
                "Alice",
                'F',
                LocalDate.of(1990, 5, 15),
                0,
                "French",
                "Taurus",
                "Paris",
                Optional.empty(),
                Optional.empty(),
                "Brown",
                "Blonde",
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty()
            );

        Question question = new Question(1, "Couleur des yeux ?", "eyeColor");
        assertTrue(question.matches(person, "Brown"));
        
        question = new Question(2, "Quel age ?", "age");
        assertTrue(question.matches(person, "31"));
        
        question = new Question(2, "Quel age ?", "age");
        assertFalse(question.matches(person2, "31"));

        question = new Question(4, "Quel sexe ?", "sex");
        assertTrue(question.matches(person, "F"));

        question = new Question(5, "Couleur des cheveux ?", "hairColor");
        assertTrue(question.matches(person, "Blonde"));
        
        question = new Question(6, "", "nationality");
        assertTrue(question.matches(person, "French"));
        
        question = new Question(7, "", "zodiac");
        assertTrue(question.matches(person, "Taurus"));
        
        question = new Question(8, "", "birthPlace");
        assertTrue(question.matches(person, "Paris"));
        
        question = new Question(9, "", "heightInCm");
        assertTrue(question.matches(person, "165"));
        
        question = new Question(10, "", "heightInCm");
        assertFalse(question.matches(person2, null));
        
        question = new Question(11, "", "weightInKg");
        assertTrue(question.matches(person, "60"));
        
        question = new Question(12, "", "weightInKg");
        assertFalse(question.matches(person2, null));
        
        question = new Question(13, "", "deathDate");
        assertTrue(question.matches(person, "15/05/2024"));
        
        question = new Question(14, "", "deathDate");
        assertFalse(question.matches(person2, null));
        
        question = new Question(15, "", "particularity1");
        assertTrue(question.matches(person, "Scar on left cheek"));
        
        question = new Question(16, "", "particularity1");
        assertTrue(question.matches(person2, ""));
        
        question = new Question(17, "", "particularity2");
        assertTrue(question.matches(person, "Tattoo on right arm"));
        
        question = new Question(18, "", "particularity2");
        assertTrue(question.matches(person2, ""));
        
        question = new Question(19, "", "particularity3");
        assertTrue(question.matches(person, "Manchot"));
        
        question = new Question(20, "", "particularity3");
        assertTrue(question.matches(person2, ""));
        
        question = new Question(20, "", "particularity4");
        assertFalse(question.matches(person, "Beau"));
    }

}
