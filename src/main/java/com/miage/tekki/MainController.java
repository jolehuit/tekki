package com.miage.tekki;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@SessionAttributes({"points", "questions", "selectedPerson", "selectedQuestion"})
public class MainController {
    private final QuestionService questionService;
    private final PersonService personService;
    private final CsvPeopleRepository csvPeopleRepository;

    public MainController(QuestionService questionService, PersonService personService, CsvPeopleRepository csvPeopleRepository) {
        this.questionService = questionService;
        this.personService = personService;
        this.csvPeopleRepository = csvPeopleRepository;
    }

    @GetMapping("/")
    public String showHomePage(HttpSession session, SessionStatus status) {
        status.setComplete();
        session.invalidate();
        return "tekki";
    }

    @GetMapping("/game")
    public String showGamePage(Model model, HttpSession session) {
        Boolean gamePageVisited = (Boolean) session.getAttribute("gamePageVisited");
        if (gamePageVisited == null || !gamePageVisited) {
            session.setAttribute("gamePageVisited", true);
            session.setAttribute("points", 150);
        }

        Integer points = (Integer) session.getAttribute("points");
        if (points == null) {
            points = 150;
            session.setAttribute("points", points);
        }
        model.addAttribute("points", points);

        List<Question> filteredQuestions = (List<Question>) session.getAttribute("filteredQuestions");
        if (filteredQuestions == null || filteredQuestions.isEmpty()) {
            List<Question> allQuestions = questionService.getAllQuestions();
            Person selectedPerson = personService.getRandomPerson();
            session.setAttribute("selectedPerson", selectedPerson);

            filteredQuestions = allQuestions.stream()
                    .filter(question -> {
                        String propertyValue = csvPeopleRepository.getPropertyByQuestion(selectedPerson, question);
                        return propertyValue != null && !propertyValue.isEmpty() && !propertyValue.equals("–");
                    })
                    .collect(Collectors.toList());

            session.setAttribute("filteredQuestions", filteredQuestions);
        }

        model.addAttribute("questions", filteredQuestions);
        return "game";
    }

    @PostMapping("/askQuestion")
    public String askQuestion(@RequestParam("questionId") int questionId,
                              Model model, HttpSession session) {
        int points = (int) session.getAttribute("points");
        points -= 10;
        if (points < 0) {
            points = 0;
        }
        session.setAttribute("points", points);

        if (points == 0) {
            model.addAttribute("guessResult", "Désolé, tu as perdu. Ton score est de zéro.");
            model.addAttribute("points", points);
            return "result";
        }

        model.addAttribute("points", points);

        List<Question> questions = (List<Question>) session.getAttribute("filteredQuestions");

        if (questions == null || questions.isEmpty()) {
            return "redirect:/guess";
        }

        Question selectedQuestion = questions.stream().filter(q -> q.id() == questionId).findFirst().orElse(null);

        if (selectedQuestion != null) {
            Person selectedPerson = (Person) session.getAttribute("selectedPerson");

            if (selectedPerson != null) {
                String propertyValue = csvPeopleRepository.getPropertyByQuestion(selectedPerson, selectedQuestion);

                if (propertyValue != null) {
                    model.addAttribute("selectedQuestion", selectedQuestion);
                    model.addAttribute("selectedPersonProperty", propertyValue);
                }
            }

            model.addAttribute("selectedQuestionText", selectedQuestion.text());
            questionService.removeQuestion(questions, questionId);
            model.addAttribute("questions", questions);

            if (questions.isEmpty()) {
                return "redirect:/guess";
            }
        }
        return "game";
    }

    @GetMapping("/guess")
    public String startGuessing(Model model, HttpSession session) {
        Boolean gamePageVisited = (Boolean) session.getAttribute("gamePageVisited");
        if (gamePageVisited == null || !gamePageVisited) {
            return "redirect:/";
        }

        List<Person> randomPeople = (List<Person>) session.getAttribute("randomPeople");
        if (randomPeople == null) {
            Person selectedPerson = (Person) session.getAttribute("selectedPerson");
            randomPeople = csvPeopleRepository.selectRandomPeopleIncluding(selectedPerson);

            if (!randomPeople.contains(selectedPerson)) {
                randomPeople.add(selectedPerson);
            }

            Collections.shuffle(randomPeople);
            session.setAttribute("randomPeople", randomPeople);
        }

        model.addAttribute("randomPeople", randomPeople);
        return "guess";
    }

    @PostMapping("/submitGuess")
    public String submitGuess(@RequestParam("selectedPersonId") String selectedPersonId,
                              Model model, HttpSession session) {
        Person personToGuess = (Person) session.getAttribute("selectedPerson");

        if (personToGuess == null) {
            return "redirect:/";
        }

        List<Question> questions = (List<Question>) session.getAttribute("filteredQuestions");

        boolean guessedCorrectly = personToGuess.id().equals(selectedPersonId);

        if (guessedCorrectly) {
            model.addAttribute("guessResult", "Bravo ! Tu as deviné correctement !");
        } else {
            int points = (int) session.getAttribute("points");
            points -= 20;
            if (points < 0) {
                points = 0;
            }
            session.setAttribute("points", points);
            model.addAttribute("points", points);

            if (points == 0) {
                model.addAttribute("guessResult", "Désolé, tu as perdu. Ton score est de zéro.");
                return "result";
            }

            if (questions.isEmpty()) {
                model.addAttribute("guessResult", "Désolé, tu as perdu. Il ne reste plus de questions.");
            } else {
                List<Person> randomPeople = (List<Person>) session.getAttribute("randomPeople");
                randomPeople.removeIf(person -> person.id().equals(selectedPersonId));

                List<Question> filteredQuestions = (List<Question>) session.getAttribute("filteredQuestions");
                filteredQuestions = filteredQuestions.stream()
                        .filter(question -> {
                            String propertyValue = csvPeopleRepository.getPropertyByQuestion(personToGuess, question);
                            return propertyValue != null && !propertyValue.isEmpty() && !propertyValue.equals("–");
                        })
                        .collect(Collectors.toList());
                session.setAttribute("filteredQuestions", filteredQuestions);

                session.setAttribute("randomPeople", randomPeople);
                return "redirect:/game";
            }
        }

        return "result";
    }
}
