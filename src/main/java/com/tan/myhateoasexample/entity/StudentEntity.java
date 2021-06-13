package com.tan.myhateoasexample.entity;

import com.tan.myhateoasexample.dto.Lecture;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Getter @Setter
@ToString
@EqualsAndHashCode
@Document(collection = "student")
public class StudentEntity implements Serializable {
  @Id
  private String id;
  private String name;
  private List<Lecture> lectureList;
}
