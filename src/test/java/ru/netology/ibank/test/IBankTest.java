package ru.netology.ibank.test;

import org.junit.jupiter.api.Test;
import ru.netology.ibank.data.DataGenerator;

public class IBankTest {

    @Test
    void shouldEnter(){
        var validUser = DataGenerator.Authorization.Registration.generateUser("active");

    }

    @Test
    void shouldNotEnterBlocked(){

    }

    @Test
    void shouldNotEnterNotRegistered(){

    }
}
