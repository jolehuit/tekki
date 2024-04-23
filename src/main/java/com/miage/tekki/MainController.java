package com.miage.tekki;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import java.util.List;

@Controller
@SessionAttributes({"score", "questions", "selectedPerson"})
public class MainController {

    private final DatabaseSimulator databaseSimulator;
    private final QuestionService questionService;

    public MainController(DatabaseSimulator databaseSimulator, QuestionService questionService) {
        this.databaseSimulator = databaseSimulator;
        this.questionService = questionService;
    }

    @GetMapping
    public String showTekkiPage() {
        return "tekki";
    }

    @GetMapping("/game")
    public String showGamePage(Model model, HttpSession session) {
        session.setAttribute("score", 0);
        List<Question> questions = questionService.getAllQuestions();
        model.addAttribute("questions", questions);
        Person selectedPerson = databaseSimulator.getRandomPerson();
        session.setAttribute("selectedPerson", selectedPerson);
        return "game";
    }

    @PostMapping("/askQuestion")
    public String askQuestion(@RequestParam("questionId") int questionId, Model model, HttpSession session) {
        int score = (int) session.getAttribute("score");
        session.setAttribute("score", score + 1);

        List<Question> questions = (List<Question>) session.getAttribute("questions");
        questions.removeIf(q -> q.getId() == questionId);
        model.addAttribute("questions", questions);

        List<Person> filteredPeople = databaseSimulator.getFilteredPeople(questionId);
        model.addAttribute("people", filteredPeople); // Ajoutez les personnes filtrées au modèle.

        return "game";
    }

}
