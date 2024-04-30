package com.miage.tekki;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public record Person(String id,String name, String profession, LocalDate birthday,int age, String zodiac, String birthPlace, int heightInCm,String eyeColor, String hairColor, char sexe) {

}
