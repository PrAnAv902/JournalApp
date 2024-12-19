package com.cpranav.JournalApp.service;

import com.cpranav.JournalApp.entity.User;
import com.cpranav.JournalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.when;

public class UserDetailsServiceImplTests {
    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepository userRepository;

    private AutoCloseable closeable;

    @BeforeEach public void openMocks() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @Test
    void loadUserByUsernameTest(){
        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("Pranav").password("1234").roles(new ArrayList<>(Arrays.asList("USER","ADMIN"))).id(new ObjectId("675dadea1214ea432a0f14df")).build());
        UserDetails user = userDetailsService.loadUserByUsername("Pranav");
        Assertions.assertNotNull(user);
    }

    @AfterEach public void releaseMocks() throws Exception {
        closeable.close();
    }

}
