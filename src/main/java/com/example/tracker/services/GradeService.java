package com.example.tracker.services;

import com.example.tracker.models.Grade;
import com.example.tracker.repositories.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    public List<Grade> getGradesByCourseId(Long courseId) {
        return gradeRepository.findByCourseId(courseId);
    }

    public void addGrade(Grade grade) {
        gradeRepository.save(grade);
    }

    public void deleteGrade(Long id) {
        gradeRepository.deleteById(id);
    }

    public List<Grade> getGradesByStudentName(String studentName) {
        return gradeRepository.findByStudentName(studentName);
    }

    public double calculateCGPA(String studentName) {
        List<Grade> grades = getGradesByStudentName(studentName);
        if (grades.isEmpty()) {
            return 0.0;
        }

        double totalGrades = 0;
        for (Grade grade : grades) {
            totalGrades += grade.getGrade();
        }

        return totalGrades / grades.size();
    }
}
