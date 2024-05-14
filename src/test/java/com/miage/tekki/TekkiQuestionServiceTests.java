package com.miage.tekki;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TekkiQuestionServiceTests {
	private QuestionService questionService;
    private List<Question> questions;

    @BeforeEach
    void setUp() {
        questionService = new QuestionService();
        questions = questionService.getAllQuestions();
    }

    @Test
    void testGetAllQuestions() {
        assertEquals(2, questions.size());
        // Vérifiez que les questions sont correctement initialisées
        assertEquals("Est-ce que votre personnage a des lunettes ?", questions.get(0).text());
        assertEquals("eyeColor", questions.get(0).property());
        assertEquals("lunettes", questions.get(0).expectedAnswer());

        assertEquals("Est-ce que votre personnage a des cheveux blonds ?", questions.get(1).text());
        assertEquals("hairColor", questions.get(1).property());
        assertEquals("blond", questions.get(1).expectedAnswer());
    }

    @Test
    void testRemoveQuestion() {
        int initialSize = questions.size();
        int questionIdToRemove = 1; // ID de la première question
        questionService.removeQuestion(questions, questionIdToRemove);
        assertEquals(initialSize - 1, questions.size());
        // Vérifiez que la question a été supprimée
        for (Question question : questions) {
            assertNotEquals(questionIdToRemove, question.id());
        }
    }
}
