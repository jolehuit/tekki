package com.miage.tekki;

import java.util.ArrayList;
import java.util.List;

public class QuestionService {

    public QuestionService(CsvPeopleRepository csvPeopleRepository) {
    }

    public List<Question> getAllQuestions() {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question(1, "Quelle est la couleur de ses yeux ?", "eyeColor"));
        questions.add(new Question(2, "Quel est son âge ?", "age"));
        questions.add(new Question(3, "Quelle est sa taille en cm ?", "heightInCm"));
        questions.add(new Question(4, "Quel est son poids en kg ?", "weightInKg"));
        questions.add(new Question(5, "Quel est son sexe ?", "sex"));
        questions.add(new Question(6, "Quelle est sa nationalité ?", "nationality"));
        questions.add(new Question(7, "Quel est son signe du zodiaque ?", "zodiac"));
        questions.add(new Question(8, "Quel est son lieu de naissance ?", "birthPlace"));
        questions.add(new Question(9, "Quelle est la couleur de ses cheveux ?", "hairColor"));
        questions.add(new Question(10, "Quelle est sa date de décès ?", "deathDate"));
        questions.add(new Question(11, "Quelle est sa première particularité ?", "particularity1"));
        questions.add(new Question(12, "Quelle est sa deuxième particularité ?", "particularity2"));
        questions.add(new Question(13, "Quelle est sa troisième particularité ?", "particularity3"));
        return questions;
    }

    public void removeQuestion(List<Question> questions, int questionId) {
        questions.removeIf(q -> q.id() == questionId);
    }
}
// Path: src/main/java/com/miage/tekki/PersonService.java