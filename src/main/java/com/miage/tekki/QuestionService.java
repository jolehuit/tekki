package com.miage.tekki;

import java.util.ArrayList;
import java.util.List;

public class QuestionService {

    public List<Question> getAllQuestions() {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question(1, "Est-ce que votre personnage a des lunettes ?", "eyeColor", "lunettes"));
        questions.add(new Question(2, "Est-ce que votre personnage a des cheveux blonds ?", "hairColor", "blond"));
        return questions;
    }
}