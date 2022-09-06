package com.nucoders.tablemaker.courses;

import java.util.ArrayList;
import java.util.Set;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nucoders.tablemaker.courses.Dialogflow.DialogflowService;




@RestController
@CrossOrigin
@RequestMapping(path="api/v1/")
public class CourseController {

    private final CourseService courseService;
    private final DialogflowService dialogflowService;
    public CourseController(CourseService courseService, DialogflowService dialogflowService) {
        this.courseService = courseService;
        this.dialogflowService = dialogflowService;
    }

    @GetMapping()
    public String welcome() {
        return "Welcome to our API for Auto Table Maker by NU Coders";
	}

    @GetMapping(path="/getcourses")
    public ArrayList<Course> getCourses() {
        return courseService.getCourses();
	}

    @GetMapping(path="/getcoursecodes")
    public ArrayList<String> getCourseCodes() {
        return courseService.getCourseCodes();
	}

    @GetMapping(path="/findcourse")
    public ArrayList<Course> findCourse(@RequestParam String wantedCourse){
        ArrayList<String> wantedCourses = new ArrayList<String>();
        wantedCourses.add(wantedCourse);
        System.out.println(courseService.findCourses(wantedCourses).get(0));
        return courseService.findCourses(wantedCourses).get(0);
    }
    @GetMapping(path="/findcoursesections")
    public Set<Integer> findCourseSections(@RequestParam String wantedCourse){
        return courseService.findCourseSections(wantedCourse);
    }
    @GetMapping(path="/findcourses")
    public ArrayList<ArrayList<Course>> findCourses(@RequestParam ArrayList<String> wantedCourses){
        return courseService.findCourses(wantedCourses);
    }

    @GetMapping(path="/createtable")
    public ArrayList<ArrayList<ArrayList<Course>>> createTable(@RequestParam ArrayList<String> wantedCourses){
        return courseService.createTables(wantedCourses);
    }
    @GetMapping(path="/constraineddayscreatetable")
    public ArrayList<ArrayList<ArrayList<Course>>> constrainedDaysCreateTables(@RequestParam ArrayList<String> wantedCourses, @RequestParam ArrayList<Integer> constraints){
        return courseService.constrainedDaysCreateTables(wantedCourses,constraints);
    }

    @GetMapping(path="/constrainedcreatetable")
    public ArrayList<ArrayList<ArrayList<Course>>> constrainedCreateTables(@RequestParam ArrayList<String> wantedCourses, @RequestParam ArrayList<String> constraints){
        return courseService.constrainedCreateTables(wantedCourses,constraints);
    }
    //Dialogflow API

    @PostMapping(path="/dialogflow")
    @ResponseBody
    public String addCourse(@RequestBody String req) {
        return dialogflowService.fullfilmentResponse(req);
    }

        
        
    
}
