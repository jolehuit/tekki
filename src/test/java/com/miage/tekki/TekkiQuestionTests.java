package com.miage.tekki;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TekkiQuestionTests {
	Question qEye, qHair, qAge, qAge2, qFeetSize;
	Person p1, p2;
	
	@Test
	void contextLoads() {
		p1 = new Person(
	            "123456789",
	            "John Doe",
	            "Software Engineer",
	            LocalDate.of(1990, 5, 15),
	            34,
	            "Taurus",
	            "New York City",
	            180,
	            "Brown",
	            "Black",
	            'M'
	        );
		
		p2 = new Person(
	            "123456789",
	            "John Doe",
	            "Software Engineer",
	            LocalDate.of(1990, 5, 15),
	            -5,
	            "Taurus",
	            "New York City",
	            180,
	            "Brown",
	            "Black",
	            'M'
	        );
		qEye = new Question(1, "Quelle est sa couleur des yeux", "eyeColor", p1.eyeColor());
		qHair = new Question(2, "Quelle est sa couleur de cheveux", "hairColor", p1.hairColor());
		qAge = new Question(3, "Quelle est son age ?", "age", String.valueOf(p1.age()));
		qAge2 = new Question(4, "Quelle est son age ?", "age", String.valueOf(5.5));
		qFeetSize = new Question(5, "Quelle est sa taille de pied ?", "feet", String.valueOf(45));
		
		
		assertTrue(qEye.matches(p1));
		assertTrue(qHair.matches(p1));
		assertTrue(qAge.matches(p1));
		assertFalse(qAge2.matches(p2));
		assertFalse(qFeetSize.matches(p1));
		
	}

}
