package com.miage.tekki;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class TekkiQuestionServiceTests {
	private QuestionService questionService;
    private List<Question> questions;

    public Resource csvResource() {
        return new ClassPathResource("people.csv");
    }
    
    @BeforeEach
    void setUp() {
        // Initialisez le service et les questions ici (par exemple, avec un mock CsvPeopleRepository)
        CsvPeopleRepository mockRepository = new CsvPeopleRepository(csvResource());
        questionService = new QuestionService(mockRepository);
        questions = questionService.getAllQuestions();
    }

    @Test
    void testGetAllQuestions() {
        // Vérifiez que la liste de questions n'est pas vide
        assertNotNull(questions);
        assertFalse(questions.isEmpty());
    }
    
    @Test
    void testRemoveQuestion() {
        // Supprimez une question (par exemple, la première question)
        int questionIdToRemove = 1;
        questionService.removeQuestion(questions, questionIdToRemove);

        // Vérifiez que la question a été supprimée
        assertTrue(questions.stream().noneMatch(q -> q.id() == questionIdToRemove));
    }

}
