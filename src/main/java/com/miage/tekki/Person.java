package com.miage.tekki;
import java.time.LocalDate;
import java.util.Optional;

public record Person(String id, String name, String profession, LocalDate birthday, int age, String zodiac, String birthPlace, Optional<Integer> heightInCm, String eyeColor, String hairColor, char sex) {

}
