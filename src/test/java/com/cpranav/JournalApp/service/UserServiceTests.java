package com.cpranav.JournalApp.service;

import com.cpranav.JournalApp.entity.User;
import com.cpranav.JournalApp.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class UserServiceTests {
    @Autowired
    UserRepository userRepository;

//    @BeforeAll      --> before running all  tests this method will run
//    @BeforeEach     --> before running each test this method will run
//    @AfterAll       --> after running all tests this method will run
//    @AfterEach      --> after running each test this method will run
//    @Test           --> if we are not providing any parameters we use this instead of @ParameterizedTest

    @ParameterizedTest
    @Disabled
    @ValueSource(strings = {
            "Pranav",
            "Pranav Chaudhary",
            "Mani"
    })
    public void testFindByUserName(String userName){
        User user = userRepository.findByUserName(userName);
        assertTrue(!user.getJournalEntities().isEmpty(),"failed for: " + userName);
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "Prana,v,Pranav",
            "Ran,aa,Ranaa",
            "Mada,maa,Madam"
    })
    public void test(String a,String b,String expected){
        assertEquals(expected,a+b);
    }
}
