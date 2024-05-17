package com.example.tracker.controllers;

import com.example.tracker.models.Course;
import com.example.tracker.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/courses")
    public String getCoursesPage(Model model) {
        model.addAttribute("course", new Course()); // Ensure a new Course object is added to the model
        model.addAttribute("courses", courseService.getAllCourses()); // Assuming you have a method to fetch all courses
        return "courses";
    }


    @PostMapping("/courses")
    public String addCourse(@ModelAttribute Course course, Model model) {
        courseService.addCourse(course);
        model.addAttribute("message", "Course added successfully!");
        return "redirect:/courses";
    }

    @PostMapping("courses/delete/{id}")
    public String deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return "redirect:/courses";
    }
    @GetMapping("/courses/{id}/grades")
    public String viewCourseGrades(@PathVariable Long id) {
        return "redirect:/grades/course/" + id;
    }
}
