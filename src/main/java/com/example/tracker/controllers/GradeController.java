package com.example.tracker.controllers;

import com.example.tracker.models.Grade;
import com.example.tracker.services.GradeService;
import com.example.tracker.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @Autowired
    private CourseService courseService;

    @GetMapping("/grades/course/{courseId}")
    public String getGradesPage(@PathVariable Long courseId, Model model) {
        model.addAttribute("grades", gradeService.getGradesByCourseId(courseId));
        model.addAttribute("course", courseService.getCourseById(courseId));
        model.addAttribute("grade", new Grade());
        return "grade-list";
    }

    @PostMapping("/grades")
    public String addGrade(@ModelAttribute Grade grade, @RequestParam("courseId") Long courseId){
        System.out.println("Received courseId: " + courseId); // Add this line for logging
        grade.setCourse(courseService.getCourseById(courseId));
        gradeService.addGrade(grade);
        return "redirect:/grades/course/" + courseId;
    }

    @PostMapping("/grades/delete/{id}")
    public String deleteGrade(@PathVariable Long id, @RequestParam Long courseId) {
        gradeService.deleteGrade(id);
        return "redirect:/grades/course/" + courseId;
    }

    @GetMapping("/grades/student/{studentName}")
    public String getStudentGrades(@PathVariable String studentName, Model model) {
        List<Grade> grades = gradeService.getGradesByStudentName(studentName);
        double cgpa = gradeService.calculateCGPA(studentName);
        model.addAttribute("grades", grades);
        model.addAttribute("studentName", studentName);
        model.addAttribute("cgpa", cgpa);
        return "cgpa";
    }
}
