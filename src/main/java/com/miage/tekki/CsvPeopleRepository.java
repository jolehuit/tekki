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
import java.util.Random;

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
        char sex = metadata[10].charAt(0);

        return new Person(id, name, profession, birthday, age, zodiac, birthPlace, heightInCm, eyeColor, hairColor, sex);
    }

    public List<Person> getFilteredPeople(int questionId) {
        return null;
    }

    public Person getRandomPerson() {
        if (!this.people.isEmpty()) {
            Random random = new Random();
            int randomIndex = random.nextInt(people.size());
            return people.get(randomIndex);
        }
        return null;
    }

    public List<Person> selectRandomPeople() {
        List<Person> randomPeople = new ArrayList<>();

        Person personToGuess = this.getRandomPerson();
        randomPeople.add(personToGuess);

        List<Person> shuffledPeople = new ArrayList<>(people);
        java.util.Collections.shuffle(shuffledPeople);

        for (int i = 0; i < 19; i++) {
            randomPeople.add(shuffledPeople.get(i));
        }

        return randomPeople;
    }
    
    public List<Person> getPeople() {
    	return people; 
    }

    // Other methods like getRandomPerson() remain unchanged...
}
