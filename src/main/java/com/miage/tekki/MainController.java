package com.miage.tekki;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@SessionAttributes({"score", "questions", "selectedPerson", "selectedQuestion"})
public class MainController {

    private final QuestionService questionService;
    private final PersonService personService;
    private final CsvPeopleRepository csvPeopleRepository;

    public MainController(QuestionService questionService, PersonService personService, CsvPeopleRepository csvPeopleRepository) {
        this.questionService = questionService;
        this.personService = personService;
        this.csvPeopleRepository = csvPeopleRepository;
    }

    @GetMapping("/game")
    public String showGamePage(Model model, HttpSession session) {
        session.setAttribute("score", 0);
        List<Question> allQuestions = questionService.getAllQuestions();
        Person selectedPerson = personService.getRandomPerson();
        session.setAttribute("selectedPerson", selectedPerson);

        List<Question> filteredQuestions = allQuestions.stream()
                .filter(question -> {
                    String propertyValue = csvPeopleRepository.getPropertyByQuestion(selectedPerson, question);
                    return propertyValue != null && !propertyValue.isEmpty() && !propertyValue.equals("–");
                })
                .collect(Collectors.toList());

        model.addAttribute("questions", filteredQuestions);
        return "game";
    }

    @PostMapping("/askQuestion")
    public String askQuestion(@RequestParam("questionId") int questionId,
                              Model model, HttpSession session) {

        List<Question> questions = (List<Question>) session.getAttribute("questions");

        // Vérifier si la liste des questions est null
        if (questions == null) {
            // Si c'est le cas, initialisez-la avec toutes les questions disponibles
            questions = questionService.getAllQuestions();
            session.setAttribute("questions", questions);
        }

        // Récupérer la question sélectionnée
        Question selectedQuestion = questions.stream().filter(q -> q.id() == questionId).findFirst().orElse(null);

        if (selectedQuestion != null) {
            System.out.println("Selected Question: " + selectedQuestion);
            Person selectedPerson = (Person) session.getAttribute("selectedPerson");

            if (selectedPerson != null) {
                // Obtenir la réponse à partir du CSV
                String propertyValue = csvPeopleRepository.getPropertyByQuestion(selectedPerson, selectedQuestion);

                if (propertyValue != null) {
                    // Afficher la réponse
                    model.addAttribute("selectedQuestion", selectedQuestion);
                    model.addAttribute("selectedPersonProperty", propertyValue);
                }
            }

            // Conserver la question sélectionnée dans le modèle pour qu'elle soit disponible lors du rendu de la page
            model.addAttribute("selectedQuestionText", selectedQuestion.text());

            // Supprimer la question de la liste des questions
            questionService.removeQuestion(questions, questionId);
            model.addAttribute("questions", questions);
        }
        return "game";
    }

    @PostMapping("/guess")
    public String startGuessing(Model model, HttpSession session) {
        Person selectedPerson = (Person) session.getAttribute("selectedPerson");

        List<Person> randomPeople = csvPeopleRepository.selectRandomPeopleIncluding(selectedPerson);
        session.setAttribute("randomPeople", randomPeople);

        model.addAttribute("randomPeople", randomPeople);
        return "guess";
    }

    @PostMapping("/submitGuess")
    public String submitGuess(@RequestParam("selectedPersonId") String selectedPersonId,
                              Model model, HttpSession session) {
        Person personToGuess = (Person) session.getAttribute("selectedPerson");
        List<Question> questions = (List<Question>) session.getAttribute("questions");

        boolean guessedCorrectly = personToGuess.id().equals(selectedPersonId);

        if (guessedCorrectly) {
            model.addAttribute("guessResult", "Bravo ! Tu as deviné correctement !");
        } else {
            if (questions.isEmpty()) {
                model.addAttribute("guessResult", "Désolé, tu as perdu. Il ne reste plus de questions.");
            } else {
                model.addAttribute("guessResult", "Désolé, tu as choisi la mauvaise personne. Essaye encore !");
                return "redirect:/game";
            }
        }

        return "result";
    }
}