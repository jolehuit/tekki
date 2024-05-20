package com.miage.tekki;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.springframework.core.io.Resource;

public class CsvPeopleRepository {
    private final List<Person> people;

    public CsvPeopleRepository(Resource csvResource) {
        this.people = new ArrayList<>();
        loadPeopleFromResource(csvResource);
    }

    private void loadPeopleFromResource(Resource csvResource) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(csvResource.getInputStream()))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                try {
                    String[] attributes = line.split(";");
                    Person person = createPerson(attributes);
                    if (person != null) {
                        people.add(person);
                    }
                } catch (Exception e) {
                    System.err.println("Error parsing line: " + line);
                    e.printStackTrace();
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private Person createPerson(String[] metadata) {
        try {
            String id = getValue(metadata, 0);
            String name = getValue(metadata, 1);
            char sex = getValue(metadata, 2).charAt(0);
            String profession = getValue(metadata, 3);
            LocalDate birthday = LocalDate.parse(getValue(metadata, 4), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            int age = Integer.parseInt(getValue(metadata, 5));
            String nationality = getValue(metadata, 6);
            String zodiac = getValue(metadata, 7);
            String birthPlace = getValue(metadata, 8);
            Optional<Integer> heightInCm = parseInteger(getValue(metadata, 9));
            Optional<Integer> weightInKg = parseInteger(getValue(metadata, 10));
            String eyeColor = getValue(metadata, 11);
            String hairColor = getValue(metadata, 12);
            Optional<LocalDate> deathDate = parseDate(getValue(metadata, 13));
            Optional<String> particularity1 = parseOptional(getValue(metadata, 14));
            Optional<String> particularity2 = parseOptional(getValue(metadata, 15));
            Optional<String> particularity3 = parseOptional(getValue(metadata, 16));

            return new Person(id, name, sex, profession, birthday, age, nationality, zodiac, birthPlace,
                    heightInCm, weightInKg, Optional.ofNullable(eyeColor), Optional.ofNullable(hairColor), deathDate,
                    particularity1, particularity2, particularity3);
        } catch (Exception e) {
            System.err.println("Error creating person from metadata: " + String.join(";", metadata));
            e.printStackTrace();
            return null;
        }
    }

    private String getValue(String[] metadata, int index) {
        return index < metadata.length ? metadata[index] : "–";
    }

    private Optional<Integer> parseInteger(String value) {
        try {
            return value.equals("–") ? Optional.empty() : Optional.of(Integer.parseInt(value));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    private Optional<LocalDate> parseDate(String value) {
        try {
            return value.equals("–") ? Optional.empty() : Optional.of(LocalDate.parse(value, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private Optional<String> parseOptional(String value) {
        return value.equals("–") ? Optional.empty() : Optional.of(value);
    }

    public List<Person> getFilteredPeople(String property, String expectedAnswer) {
        List<Person> filteredPeople = new ArrayList<>();
        for (Person person : people) {
            if (new Question(0, "", property).matches(person, expectedAnswer)) {
                filteredPeople.add(person);
            }
        }
        return filteredPeople;
    }

    public Person getRandomPerson() {
        if (!this.people.isEmpty()) {
            Random random = new Random();
            int randomIndex = random.nextInt(people.size());
            return people.get(randomIndex);
        }
        return null;
    }

    public List<Person> selectRandomPeopleIncluding(Person personToGuess) {
        List<Person> randomPeople = new ArrayList<>();

        if (personToGuess != null) {
            randomPeople.add(personToGuess);

            List<Person> shuffledPeople = new ArrayList<>(people);
            Collections.shuffle(shuffledPeople);

            for (Person person : shuffledPeople) {
                if (!person.equals(personToGuess) && randomPeople.size() < 10) {
                    randomPeople.add(person);
                }
            }
        }

        return randomPeople;
    }

    public String getPropertyByQuestion(Person person, Question question) {
        switch (question.property()) {
            case "eyeColor":
                return person.eyeColor().orElse(null);
            case "age":
                return String.valueOf(person.age());
            case "heightInCm":
                return person.heightInCm().map(String::valueOf).orElse(null);
            case "weightInKg":
                return person.weightInKg().map(String::valueOf).orElse(null);
            case "sex":
                return String.valueOf(person.sex());
            case "nationality":
                return person.nationality();
            case "zodiac":
                return person.zodiac();
            case "birthPlace":
                return person.birthPlace();
            case "hairColor":
                return person.hairColor().orElse(null);
            case "deathDate":
                return person.deathDate().map(Object::toString).orElse(null);
            case "particularity1":
                return person.particularity1().orElse(null);
            case "particularity2":
                return person.particularity2().orElse(null);
            case "particularity3":
                return person.particularity3().orElse(null);
            case "profession":
                return person.profession();
            case "birthday":
                return person.birthday().toString();
            case "name":
                return person.name();
            case "id":
                return person.id();
            default:
                return null;
        }
    }

    public List<Person> getPeople() {
        return people;
    }

    public boolean verifyGuess(String idGuess, Person personToGuess){
        return personToGuess.id().equals(idGuess);
    }
}
