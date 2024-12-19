package com.cpranav.JournalApp.repository;

import com.cpranav.JournalApp.entity.JournalEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface JournalEntryRepository  extends  MongoRepository<JournalEntity, ObjectId> {
}
