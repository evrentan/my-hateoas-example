package com.tan.myhateoasexample.impl;

import com.tan.myhateoasexample.controller.LectureController;
import com.tan.myhateoasexample.controller.StudentController;
import com.tan.myhateoasexample.dto.Lecture;
import com.tan.myhateoasexample.dto.LectureRef;
import com.tan.myhateoasexample.dto.Student;
import com.tan.myhateoasexample.dto.StudentRef;
import com.tan.myhateoasexample.entity.LectureEntity;
import com.tan.myhateoasexample.entity.StudentEntity;
import com.tan.myhateoasexample.mapper.LectureMapper;
import com.tan.myhateoasexample.repository.LectureRepository;
import com.tan.myhateoasexample.service.ILectureService;
import com.tan.myhateoasexample.service.IStudentService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LectureServiceImpl implements ILectureService {

  private final LectureRepository lectureRepository;
  private final LectureMapper lectureMapper;

  public LectureServiceImpl(LectureRepository lectureRepository, LectureMapper lectureMapper) {
    this.lectureRepository = lectureRepository;
    this.lectureMapper = lectureMapper;
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED)
  public List<LectureRef> getAllLectureRefList() {
    List<LectureEntity> lectureEntityList = this.lectureRepository.findAll();

    if (CollectionUtils.isNotEmpty(lectureEntityList)) {
      List<LectureRef> lectureRefList = this.lectureMapper.toDtoRefList(lectureEntityList);

      //adding hateoas links to each object in lectureRefList
      lectureRefList.forEach(lectureRef -> {
        final Link selfLink = WebMvcLinkBuilder.linkTo(LectureController.class).slash(lectureRef.getId()).withSelfRel();
        lectureRef.add(selfLink);
      });

      return  lectureRefList;
    }

    return null;
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED)
  public Lecture getLectureById(String lectureId) {

    Optional<LectureEntity> lectureEntity = Optional.of(this.lectureRepository.findById(lectureId)).orElse(null);

    if (lectureEntity.isPresent()) {
      Lecture lecture = this.lectureMapper.toDto(lectureEntity.get());

      //adding hateoas links to student object
      final Link selfLink = WebMvcLinkBuilder.linkTo(StudentController.class).slash(lecture.getId()).withSelfRel();
      lecture.add(selfLink);

      return lecture;
    }

    return null;
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED)
  public Lecture createLecture(Lecture lecture) {
    LectureEntity lectureEntity = this.lectureMapper.toEntity(lecture);
    lectureEntity = this.lectureRepository.save(lectureEntity);
    return this.lectureMapper.toDto(lectureEntity);
  }
}
