package com.example.tracker.repositories;

import com.example.tracker.models.Grade;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GradeRepository extends CrudRepository<Grade, Long> {
    List<Grade> findByCourseId(Long courseId);
    List<Grade> findByStudentName(String studentName);
}