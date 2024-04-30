package com.miage.tekki;

public record Question(int id, String text, String property, String expectedAnswer) {

    public boolean matches(Person person) {
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
        }
        return false;
    }
}
