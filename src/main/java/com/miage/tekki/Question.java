package com.miage.tekki;

public class Question {
    private int id;
    private String text;
    private String property;
    private String expectedAnswer;

    public Question(int id, String text, String property, String expectedAnswer) {
        this.id = id;
        this.text = text;
        this.property = property;
        this.expectedAnswer = expectedAnswer;
    }

    // Méthode pour obtenir la question posée
    public String getText() {
        return text;
    }

    // Méthode pour obtenir l'ID de la question
    public int getId() {
        return id;
    }

    // Méthode pour obtenir la propriété à laquelle la question se rapporte
    public String getProperty() {
        return property;
    }

    // Méthode pour obtenir la réponse attendue à la question
    public String getExpectedAnswer() {
        return expectedAnswer;
    }

    // Méthode pour vérifier si une personne correspond à la réponse attendue à la question
    public boolean matches(Person person) {
        // Implémentez la logique de correspondance ici en fonction de la propriété et de la réponse attendue
        // Par exemple:
        switch (property) {
            case "eyeColor":
                return person.getEyeColor().equalsIgnoreCase(expectedAnswer);
            case "hairColor":
                return person.getHairColor().equalsIgnoreCase(expectedAnswer);
            case "age":
                // Dans ce cas, expectedAnswer devrait être un intervalle ou une valeur spécifique.
                // Vous devez implémenter la logique pour interpréter correctement expectedAnswer.
                break;
            // Ajoutez des cas supplémentaires pour les autres propriétés.
        }
        return false;
    }
}
