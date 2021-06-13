package com.tan.myhateoasexample.impl;

import com.tan.myhateoasexample.controller.StudentController;
import com.tan.myhateoasexample.dto.Student;
import com.tan.myhateoasexample.dto.StudentRef;
import com.tan.myhateoasexample.entity.StudentEntity;
import com.tan.myhateoasexample.mapper.StudentMapper;
import com.tan.myhateoasexample.repository.StudentRepository;
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
public class StudentServiceImpl implements IStudentService {

  private final StudentRepository studentRepository;
  private final StudentMapper studentMapper;

  public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper) {
    this.studentRepository = studentRepository;
    this.studentMapper = studentMapper;
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED)
  public List<StudentRef> getAllStudentRefList() {
    List<StudentEntity> studentEntityList = this.studentRepository.findAll();

    if (CollectionUtils.isNotEmpty(studentEntityList)) {
      List<StudentRef> studentRefList = this.studentMapper.toDtoRefList(studentEntityList);

      //adding hateoas links to each object in studentRefList
      studentRefList.forEach(studentRef -> {
        final Link selfLink = WebMvcLinkBuilder.linkTo(StudentController.class).slash(studentRef.getId()).withSelfRel();
        studentRef.add(selfLink);
      });

      return  studentRefList;
    }

    return null;
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED)
  public Student getStudentById(String studentId) {

    Optional<StudentEntity> studentEntity = Optional.of(this.studentRepository.findById(studentId)).orElse(null);

    if (studentEntity.isPresent()) {
      Student student = this.studentMapper.toDto(studentEntity.get());

      //adding hateoas links to student object
      final Link selfLink = WebMvcLinkBuilder.linkTo(StudentController.class).slash(student.getId()).withSelfRel();
      student.add(selfLink);

      return student;
    }

    return null;
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED)
  public Student createStudent(Student student) {
    StudentEntity studentEntity = this.studentMapper.toEntity(student);
    studentEntity = this.studentRepository.save(studentEntity);
    return this.studentMapper.toDto(studentEntity);
  }
}