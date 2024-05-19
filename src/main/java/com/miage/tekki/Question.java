package com.miage.tekki;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public record Question(int id, String text, String property) {
    public boolean matches(Person person, String expectedAnswer) {
        switch (property) {
            case "eyeColor":
                return person.eyeColor().equalsIgnoreCase(expectedAnswer);
            case "hairColor":
                return person.hairColor().equalsIgnoreCase(expectedAnswer);
            case "age":
                try {
                    int expectedAge = Integer.parseInt(expectedAnswer);
                    return person.age() == expectedAge;
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    return false;
                }
            case "sex":
                return person.sex() == expectedAnswer.charAt(0);
            case "nationality":
                return person.nationality().equalsIgnoreCase(expectedAnswer);
            case "zodiac":
                return person.zodiac().equalsIgnoreCase(expectedAnswer);
            case "birthPlace":
                return person.birthPlace().equalsIgnoreCase(expectedAnswer);
            case "heightInCm":
                try {
                    int expectedHeight = Integer.parseInt(expectedAnswer);
                    return person.heightInCm().orElse(-1) == expectedHeight;
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    return false;
                }
            case "weightInKg":
                try {
                    int expectedWeight = Integer.parseInt(expectedAnswer);
                    return person.weightInKg().orElse(-1) == expectedWeight;
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    return false;
                }
            case "deathDate":
                try {
                    LocalDate expectedDeathDate = LocalDate.parse(expectedAnswer, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    return person.deathDate().orElse(null).equals(expectedDeathDate);
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            case "particularity1":
                return person.particularity1().orElse("").equalsIgnoreCase(expectedAnswer);
            case "particularity2":
                return person.particularity2().orElse("").equalsIgnoreCase(expectedAnswer);
            case "particularity3":
                return person.particularity3().orElse("").equalsIgnoreCase(expectedAnswer);
            default:
                return false;
        }
    }
}
