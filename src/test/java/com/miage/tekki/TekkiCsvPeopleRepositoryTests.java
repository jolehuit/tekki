package com.miage.tekki;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.BeforeClass;
import org.mockito.Mockito;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class TekkiCsvPeopleRepositoryTests {
    private CsvPeopleRepository repository;

    @Before
    public void setUp() throws Exception {
        repository = new CsvPeopleRepository();
    }
    
    @Test
    public void testLoadPeopleFromCSV() {
    	repository.loadPeopleFromCSV("C:/Users/Elouan/Downloads/DATASET_TOP_100_EN_V1.csv");
    	
    	assertFalse(repository.getPeople().isEmpty());
    }
}