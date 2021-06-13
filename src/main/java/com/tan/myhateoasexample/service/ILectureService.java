package com.tan.myhateoasexample.service;

import com.tan.myhateoasexample.dto.Lecture;
import com.tan.myhateoasexample.dto.LectureRef;
import com.tan.myhateoasexample.dto.Student;
import com.tan.myhateoasexample.dto.StudentRef;

import java.util.List;

public interface ILectureService {

  /**
   * get all lecture refs in the database
   *
   * @return List<LectureRef>
   */
  List<LectureRef> getAllLectureRefList();


  /**
   * get a specific lecture from the database
   *
   * @param lectureId id of the queried lecture
   * @return Lecture
   */
  Lecture getLectureById(String lectureId);

  /**
   * create a lecture instance in the database
   *
   * @param lecture lecture to be created
   * @return Lecture
   */
  Lecture createLecture(Lecture lecture);
}
