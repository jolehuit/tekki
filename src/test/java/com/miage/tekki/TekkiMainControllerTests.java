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
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;

public class TekkiMainControllerTests {
	private MainController mainController;
    private QuestionService questionService;
    private PersonService personService;
    private HttpSession session;
    private Model model;

    @BeforeEach
    public void setUp() {
    	questionService = mock(QuestionService.class);
        personService = mock(PersonService.class);
        session = mock(HttpSession.class);
        model = mock(Model.class);

        mainController = new MainController(questionService, personService);
    }

    @Test
    public void testShowGamePage() {
        assertEquals(mainController.showGamePage(model, session), "game");
    }

}
