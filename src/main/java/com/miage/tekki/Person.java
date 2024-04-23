package com.miage.tekki;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Person {
    private String id;
    private String name;
    private String profession;
    private LocalDate birthday;
    private int age;
    private String zodiac;
    private String birthPlace;
    private int heightInCm;
    private String eyeColor;
    private String hairColor;

    public Person(String id, String name, String profession, String birthday, int age,
                  String zodiac, String birthPlace, int heightInCm, String eyeColor, String hairColor) {
        this.id = id;
        this.name = name;
        this.profession = profession;
        this.birthday = LocalDate.parse(birthday, DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH));
        this.age = age;
        this.zodiac = zodiac;
        this.birthPlace = birthPlace;
        this.heightInCm = heightInCm;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getProfession() { return profession; }
    public LocalDate getBirthday() { return birthday; }
    public int getAge() { return age; }
    public String getZodiac() { return zodiac; }
    public String getBirthPlace() { return birthPlace; }
    public int getHeightInCm() { return heightInCm; }
    public String getEyeColor() { return eyeColor; }
    public String getHairColor() { return hairColor; }

    // Setters (si nécessaire)
    // ...
}
