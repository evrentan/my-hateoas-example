package com.tan.myhateoasexample.mapper;

import com.tan.myhateoasexample.dto.LectureRef;
import com.tan.myhateoasexample.dto.Student;
import com.tan.myhateoasexample.dto.StudentRef;
import com.tan.myhateoasexample.entity.LectureEntity;
import com.tan.myhateoasexample.entity.StudentEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Objects;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
@MapperConfig(unmappedTargetPolicy = ReportingPolicy.ERROR, unmappedSourcePolicy = ReportingPolicy.ERROR)
public interface StudentMapper {

  StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

  @Mappings({
      @Mapping(target = "id", ignore = true)
  })
  StudentEntity toEntity(Student student);

  Student toDto(StudentEntity studentEntity);

  List<StudentEntity> toEntityList(List<Student> studentList);

  List<Student> toDtoList(List<StudentEntity> studentEntityList);

  @BeanMapping(ignoreUnmappedSourceProperties = {"name", "lectureList"})
  StudentRef toDtoRef(StudentEntity studentEntity);

  List<StudentRef> toDtoRefList(List<StudentEntity> studentEntityList);

  @AfterMapping
  default void setEntityId(Student student, @MappingTarget StudentEntity studentEntity) {
    if(Objects.nonNull(student.getId()))
      studentEntity.setId(student.getId());
  }

}