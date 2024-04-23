package com.miage.tekki;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DatabaseSimulator {
    private List<Person> people;

    public DatabaseSimulator() {
        this.people = new ArrayList<>();
    }

    public void loadPeopleFromCSV(String csvFilePath) {
        Path path = Paths.get(csvFilePath);

        try (BufferedReader br = Files.newBufferedReader(path)) {
            // Sauter la ligne d'en-tête
            String line = br.readLine();

            while ((line = br.readLine()) != null) {
                String[] attributes = line.split(";");
                Person person = createPerson(attributes);
                people.add(person);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private Person createPerson(String[] metadata) {
        String id = metadata[0];
        String name = metadata[1];
        String profession = metadata[3];
        LocalDate birthday = LocalDate.parse(metadata[4], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        int age = Integer.parseInt(metadata[5]);
        String zodiac = metadata[6];
        String birthPlace = metadata[7];
        int heightInCm = !metadata[8].isEmpty() ? Integer.parseInt(metadata[8].replaceAll("\\D+", "")) : 0;
        String eyeColor = metadata[13];
        String hairColor = metadata[14];
        // ... créez une Personne avec ces données

        // Notez que vous devez gérer les données manquantes et les conversions nécessaires
        return new Person(id, name, profession, birthday, age, zodiac, birthPlace, heightInCm, eyeColor, hairColor);
    }

    // Les autres méthodes comme getRandomPerson() restent les mêmes...
}