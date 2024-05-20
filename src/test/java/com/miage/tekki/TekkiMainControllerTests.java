package com.miage.tekki;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;

public class TekkiMainControllerTests {
	private MainController mainController;
    private QuestionService questionService;
    private PersonService personService;
    private HttpSession session;
    private Model model;
    private CsvPeopleRepository repository;
    
    public Resource csvResource() {
        return new ClassPathResource("people.csv");
    }

    @BeforeEach
    public void setUp() {
    	repository = new CsvPeopleRepository(csvResource());
    	questionService = new QuestionService(repository);
        personService = new PersonService(repository);
        session = mock(HttpSession.class);
        model = mock(Model.class);

        mainController = new MainController(questionService, personService, repository);
    }

    @Test
    public void testShowGamePage() {
        assertEquals(mainController.showGamePage(model, session), "game");
    }

}
