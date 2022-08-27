package com.nucoders.tablemaker.courses;

import java.time.LocalTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.ArrayList;

@Configuration
public class CourseConfig {
    
    @Bean
    CommandLineRunner commandLineRunner(CourseRepository courseRepository) {
        return args -> {
            ArrayList<String> st = new ArrayList<String>();
            Course csci2051 = new Course("Introduction to Computer Systems", "CSCI205", 0, 1, 0,2, LocalTime.of(12,30), LocalTime.of(14,30), 60, 0,st,2);
            Course csci205TB1 = new Course("Introduction to Computer Systems", "CSCI205", 1, 1, 1,2, LocalTime.of(8,30), LocalTime.of(10,30), 60, 0,st,2);
            Course csci205siTA1 = new Course("Introduction to Computer Systems", "CSCI205", 1, 1, 2,4, LocalTime.of(14,30), LocalTime.of(16,30), 60, 0,st,2);

            Course csci2052 = new Course("Introduction to Computer Systems", "CSCI205", 0, 2, 0,3, LocalTime.of(11,30), LocalTime.of(13,30), 60, 0,st,2);
            Course csci205TA2 = new Course("Introduction to Computer Systems", "CSCI205", 1, 2, 1,1, LocalTime.of(9,30), LocalTime.of(11,30), 60, 0,st,2);
            Course csci205siTB2 = new Course("Introduction to Computer Systems", "CSCI205", 1, 2, 2,3, LocalTime.of(10,30), LocalTime.of(12,30), 60, 0,st,2);

            Course math2031 = new Course("Differential Equations", "MATH203", 0, 1, 0,5, LocalTime.of(14,30), LocalTime.of(16,30), 60, 0,st,2);
            Course math203TA1 = new Course("Differential Equations", "MATH203", 1, 1, 1,2, LocalTime.of(11,30), LocalTime.of(13,30), 60, 0,st,2);
            Course math203TB1 = new Course("Differential Equations", "MATH203", 1, 1, 2,3, LocalTime.of(10,30), LocalTime.of(12,30), 60, 0,st,2);

            Course math2032 = new Course("Differential Equations", "MATH203", 0, 2, 0,4, LocalTime.of(9,30), LocalTime.of(11,30), 60, 0,st,2);
            Course math203TA2 = new Course("Differential Equations", "MATH203", 1, 2, 1,1, LocalTime.of(13,30), LocalTime.of(15,30), 60, 0,st,2);
            Course math203TB2 = new Course("Differential Equations", "MATH203", 1, 2, 2,2, LocalTime.of(12,30), LocalTime.of(14,30), 60, 0,st,2);
            Course csci2081 = new Course("Algorithms", "CSCI208", 0, 1, 0,2, LocalTime.of(7,30), LocalTime.of(9,30), 60, 0,st,2);
            Course csci208TB1 = new Course("Algorithms", "CSCI208", 1, 1, 1,2, LocalTime.of(3,30), LocalTime.of(5,30), 60, 0,st,2);
            Course csci208siTA1 = new Course("Algorithms", "CSCI208", 1, 1, 2,4, LocalTime.of(16,30), LocalTime.of(18,30), 60, 0,st,2);

            Course csci2082 = new Course("Algorithms", "CSCI208", 0, 2, 0,3, LocalTime.of(17,30), LocalTime.of(19,30), 60, 0,st,2);
            Course csci208TA2 = new Course("Algorithms", "CSCI208", 1, 2, 1,3, LocalTime.of(9,30), LocalTime.of(11,30), 60, 0,st,2);
            Course csci208siTB2 = new Course("Algorithms", "CSCI208", 1, 2, 2,1, LocalTime.of(1,30), LocalTime.of(3,30), 60, 0,st,2);
            Course ais2011 = new Course("Introduction to artificial intelligence", "AIS201", 0, 1, 0,2, LocalTime.of(13,30), LocalTime.of(15,30), 60, 0,st,2);
            Course ais201TB1 = new Course("Introduction to artificial intelligence", "AIS201", 1, 1, 1,3, LocalTime.of(8,30), LocalTime.of(10,30), 60, 0,st,2);
            Course ais201siTA1 = new Course("Introduction to artificial intelligence", "AIS201", 1, 1, 2,3, LocalTime.of(16,30), LocalTime.of(18,30), 60, 0,st,2);

            Course ais2012 = new Course("Introduction to artificial intelligence", "AIS201", 0, 2, 0,2, LocalTime.of(7,30), LocalTime.of(9,30), 60, 0,st,2);
            Course ais201TA2 = new Course("Introduction to artificial intelligence", "AIS201", 1, 2, 1,1, LocalTime.of(14,30), LocalTime.of(16,30), 60, 0,st,2);
            Course ais201siTB2 = new Course("Introduction to artificial intelligence", "AIS201", 1, 2, 2,0, LocalTime.of(1,30), LocalTime.of(3,30), 60, 0,st,2);
            
            // courseRepository.saveAll(List.of(csci2081,csci208TB1,csci208siTA1,csci2082,csci208TA2,csci208siTB2,ais2011,ais201TB1,ais201siTA1,ais2012,ais201TA2,ais201siTB2,csci2051,csci205TB1,csci205siTA1,csci2052,csci205TA2,csci205siTB2,math2031,math203TA1,math203TA2,math203TB1,math203TB2,math2032));
             };
            
    }
}
