package com.nucoders.tablemaker.courses;

import java.time.LocalTime;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="courses")
public class Course {
    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE
    )
    private Long id;
    private String name;
    private String code;
    private Integer type;
    private Integer section;
    private Integer subSection;
    private Integer day;
    private LocalTime startTime;
    private LocalTime endTime;
    private Integer total;
    private Integer registered;
    private ArrayList<String> registeredStudents;
    private Integer toTakeNumber;


    public Course() {
    }


    public Course(Long id, String name, String code, Integer type, Integer section, Integer subSection, Integer day,
            LocalTime startTime, LocalTime endTime, Integer total, Integer registered,
            ArrayList<String> registeredStudents, Integer toTakeNumber) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.type = type;
        this.section = section;
        this.subSection = subSection;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.total = total;
        this.registered = registered;
        this.registeredStudents = registeredStudents;
        this.toTakeNumber = toTakeNumber;
    }


    public Course(String name, String code, Integer type, Integer section, Integer subSection, Integer day,
            LocalTime startTime, LocalTime endTime, Integer total, Integer registered,
            ArrayList<String> registeredStudents, Integer toTakeNumber) {
        this.name = name;
        this.code = code;
        this.type = type;
        this.section = section;
        this.subSection = subSection;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.total = total;
        this.registered = registered;
        this.registeredStudents = registeredStudents;
        this.toTakeNumber = toTakeNumber;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getCode() {
        return code;
    }


    public void setCode(String code) {
        this.code = code;
    }


    public Integer getType() {
        return type;
    }


    public void setType(Integer type) {
        this.type = type;
    }


    public Integer getSection() {
        return section;
    }


    public void setSection(Integer section) {
        this.section = section;
    }


    public Integer getSubSection() {
        return subSection;
    }


    public void setSubSection(Integer subSection) {
        this.subSection = subSection;
    }


    public Integer getDay() {
        return day;
    }


    public void setDay(Integer day) {
        this.day = day;
    }


    public LocalTime getStartTime() {
        return startTime;
    }


    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }


    public LocalTime getEndTime() {
        return endTime;
    }


    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }


    public Integer getTotal() {
        return total;
    }


    public void setTotal(Integer total) {
        this.total = total;
    }


    public Integer getRegistered() {
        return registered;
    }


    public void setRegistered(Integer registered) {
        this.registered = registered;
    }


    public ArrayList<String> getRegisteredStudents() {
        return registeredStudents;
    }


    public void setRegisteredStudents(ArrayList<String> registeredStudents) {
        this.registeredStudents = registeredStudents;
    }


    public Integer getToTakeNumber() {
        return toTakeNumber;
    }


    public void setToTakeNumber(Integer toTakeNumber) {
        this.toTakeNumber = toTakeNumber;
    }


    @Override
    public String toString() {
        return "Course [code=" + code + ", day=" + day + ", endTime=" + endTime + ", id=" + id + ", name=" + name
                + ", registered=" + registered + ", registeredStudents=" + registeredStudents + ", section=" + section
                + ", startTime=" + startTime + ", subSection=" + subSection + ", toTakeNumber=" + toTakeNumber
                + ", total=" + total + ", type=" + type + "]";
    }


    
    
    
    

}
