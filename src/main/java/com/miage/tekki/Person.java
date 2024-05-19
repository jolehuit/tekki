package com.miage.tekki;

import java.time.LocalDate;
import java.util.Optional;

public record Person(
        String id,
        String name,
        char sex,
        LocalDate birthday,
        int age,
        String nationality,
        String zodiac,
        String birthPlace,
        Optional<Integer> heightInCm,
        Optional<Integer> weightInKg,
        String eyeColor,
        String hairColor,
        Optional<LocalDate> deathDate,
        Optional<String> particularity1,
        Optional<String> particularity2,
        Optional<String> particularity3
) {

}
