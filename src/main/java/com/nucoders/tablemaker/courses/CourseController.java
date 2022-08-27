package com.nucoders.tablemaker.courses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;




@RestController
@CrossOrigin
@RequestMapping(path="api/v1/")
public class CourseController {

    private final CourseService courseService;   
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping()
    public String welcome() {
        return "Welcome to our API v1 for Auto Table Maker by NU Coders";
	}

    @GetMapping(path="/getcourses")
    public ArrayList<Course> getCourses() {
        return courseService.getCourses();
	}

    @GetMapping(path="/getcoursecodes")
    public ArrayList<String> getCourseCodes() {
        return courseService.getCourseCodes();
	}

    @GetMapping(path="findcourse")
    public ArrayList<ArrayList<Course>> findCourse(@RequestParam String wantedCourse){
        ArrayList<String> wantedCourses = new ArrayList<String>();
        wantedCourses.add(wantedCourse);
        return courseService.findCourses(wantedCourses);
    }

    @GetMapping(path="findcourses")
    public ArrayList<ArrayList<Course>> findCourses(@RequestParam ArrayList<String> wantedCourses){
        return courseService.findCourses(wantedCourses);
    }

    @GetMapping(path="createtable")
    public ArrayList<ArrayList<ArrayList<Course>>> createTable(@RequestParam ArrayList<String> wantedCourses){
        return courseService.createTables(wantedCourses);
    }

    
    
}
