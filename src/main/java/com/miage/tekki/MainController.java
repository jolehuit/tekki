package com.miage.tekki;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MainController {
    @GetMapping
    public String showTekkiPage() {
        return "tekki";
    }
    @GetMapping("/game")
    public String showGamePage() {
        return "game"; // Assurez-vous que "game.html" est bien dans le répertoire des templates.
    }


}
