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

public class CsvPeopleRepository {
    private List<Person> people;

    public CsvPeopleRepository() {
        this.people = new ArrayList<>();
    }

    public void loadPeopleFromCSV(String csvFilePath) {
        Path path = Paths.get(csvFilePath);

        try (BufferedReader br = Files.newBufferedReader(path)) {
            // Skip the header line
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
        String profession = metadata[2];
        LocalDate birthday = LocalDate.parse(metadata[3], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        int age = Integer.parseInt(metadata[4]);
        String zodiac = metadata[5];
        String birthPlace = metadata[6];
        int heightInCm = !metadata[7].isEmpty() ? Integer.parseInt(metadata[7].replaceAll("\\D+", "")) : 0;
        String eyeColor = metadata[8];
        String hairColor = metadata[9];

        return new Person(id, name, profession, birthday, age, zodiac, birthPlace, heightInCm, eyeColor, hairColor);
    }

    public List<Person> getFilteredPeople(int questionId) {
        return null;
    }

    public Person getRandomPerson() {
        return null;
    }

    // Other methods like getRandomPerson() remain unchanged...
}
