package com.cpranav.JournalApp.service;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.cpranav.JournalApp.entity.JournalEntity;
import com.cpranav.JournalApp.entity.User;
import com.cpranav.JournalApp.repository.JournalEntryRepository;
import com.cpranav.JournalApp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Component
public class JournalEntryService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;


    @Transactional
    public void saveEntry(JournalEntity journalEntity, String userName){
        try{
            User user  = userService.findByUserName(userName);
            journalEntity.setDate(LocalDateTime.now());
            JournalEntity saved = journalEntryRepository.save(journalEntity);
            user.getJournalEntities().add(saved);
            userRepository.save(user);
        }
        catch(Exception e){
            log.error("Error ",e);
            throw new RuntimeException("An error occurred while saving the the journal entry",e);
        }

    }

    public void saveEntry(JournalEntity journalEntity){
        journalEntryRepository.save(journalEntity);
    }

    public Optional<JournalEntity> findById(ObjectId id){
           return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id,String userName){
        boolean removed = false;
        try{
            User user  = userService.findByUserName(userName);
            removed = user.getJournalEntities().removeIf(x -> x.getId().equals(id));
            if(removed){
                userRepository.save(user);
                journalEntryRepository.deleteById(id);
            }
        }
        catch(Exception e){
            System.out.println(e);
            throw new RuntimeException("An error occurred while deleting a journal entry",e);
        }
        return removed;
    }

}
