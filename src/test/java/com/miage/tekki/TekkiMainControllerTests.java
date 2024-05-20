package com.miage.tekki;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.ui.Model;
import org.springframework.web.bind.support.SessionStatus;

import jakarta.servlet.http.HttpSession;

public class TekkiMainControllerTests {
	/*private MainController mainController;
    private QuestionService questionService;
    private PersonService personService;
    private HttpSession session;
    private Model model;
    private CsvPeopleRepository repository;*/
    
    @InjectMocks
    private MainController mainController;

    @Mock
    private QuestionService questionService;
    
    @Mock
    private PersonService personservice;

    @Mock
    private CsvPeopleRepository csvPeopleRepository;

    @Mock
    private Model model;

    @Mock
    private HttpSession session;
    
    @Mock
    private SessionStatus status;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    

    @Test
    public void testShowGamePage() {
        assertEquals(mainController.showGamePage(model, session), "game");
    }
    
    
    @Test
    public void testAskQuestion() {
        // Arrange
        int questionId = 1;
        List<Question> questions = new ArrayList<>();
        Question selectedQuestion = new Question(questionId, "Quelle est la couleur de ses yeux ?", "eyeColor");
        questions.add(selectedQuestion);
        Person selectedPerson = new Person(
                "12345",
                "Alice",
                'F',
                "Footballeur",
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
        ); // Replace with actual Person object
        
        when(session.getAttribute("points")).thenReturn(100);
        when(session.getAttribute("filteredQuestions")).thenReturn(questions);
        when(questionService.getAllQuestions()).thenReturn(questions);
        when(session.getAttribute("selectedPerson")).thenReturn(selectedPerson);
        when(csvPeopleRepository.getPropertyByQuestion(selectedPerson, selectedQuestion)).thenReturn("Sample property value");

        String result = mainController.askQuestion(questionId, model, session);

        assertEquals("game", result);
        
        
        when(session.getAttribute("questions")).thenReturn(null);
        when(questionService.getAllQuestions()).thenReturn(questions);
        when(session.getAttribute("selectedPerson")).thenReturn(selectedPerson);
        when(csvPeopleRepository.getPropertyByQuestion(selectedPerson, selectedQuestion)).thenReturn("Sample property value");
        
        String result2 = mainController.askQuestion(questionId, model, session);

        assertEquals("game", result2);
    }
    
    @Test
    public void testStartGuessing() {
    	assertEquals(mainController.startGuessing(model, session), "redirect:/");
    	
    	when(session.getAttribute("gamePageVisited")).thenReturn(true);
    	when(session.getAttribute("randomPeople")).thenReturn(null);
    	String result = mainController.startGuessing(model, session);
    	
    	assertEquals("guess", result);
    }
    
    @Test
    public void testSubmitGuess() {
    	when(session.getAttribute("selectedPersonId")).thenReturn(null);
    	String result = mainController.submitGuess("", model, session);
    	assertEquals(result, "redirect:/");
    	
    	//when(session.getAttribute("selectedPersonId")).thenReturn("taylor-swift");
    	String result2 = mainController.submitGuess("taylor-swift", model, session);
    	verify(model, times(1)).addAttribute("guessResult", "Bravo ! Tu as deviné correctement !");
    	assertEquals(result2, "redirect:/game");
    	
    	
    }
    
    @Test
    public void testshowHomePage() {
    	assertEquals(mainController.showHomePage(session, status), "tekki");
    }

}
