package com.tan.myhateoasexample.repository;

import com.tan.myhateoasexample.entity.LectureEntity;
import com.tan.myhateoasexample.entity.StudentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureRepository extends MongoRepository<LectureEntity, String> {
}
