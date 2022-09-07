package com.nucoders.tablemaker.courses;


import java.lang.reflect.Array;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;


@Service
public class CourseService {

    private final CourseRepository courseRepository;
    

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    
    public ArrayList<Course> getCourses() {
        return new ArrayList<Course>(courseRepository.findAll());
    }

    public ArrayList<String> getCourseCodes() { 
        ArrayList<Course> courses =  new ArrayList<Course>(courseRepository.findAll());
        Iterator<Course> itr = courses.iterator();
        ArrayList<String> courseCodes =  new ArrayList<String>();
        while(itr.hasNext()){
            Course curr = itr.next();
            if(!courseCodes.contains(curr.getCode())){
                courseCodes.add(curr.getCode());
            }
        }
        return courseCodes;
    }
    
    public ArrayList<Course> findCourse(String courseCode){
        ArrayList<Course> courses = new ArrayList<Course>(courseRepository.findAll());
        ArrayList<Course> found = new ArrayList<Course>();
        Iterator<Course> iterate = courses.iterator();
        while(iterate.hasNext()){
            Course c = iterate.next();
            if(c.getCode().equals(courseCode)){
                found.add(c);
            }
        }
        return found;
    }
    public Set<Integer> findCourseSections(String courseCode){
        ArrayList<Course> found = new ArrayList<Course>(courseRepository.findByCode(courseCode));
        Iterator<Course> iterate = found.iterator();
        Set<Integer> sections = new HashSet<Integer>();
        while(iterate.hasNext()){
            Course c = iterate.next();
            sections.add(c.getSection());
        }
        return sections;
    }
    public ArrayList<Course> constrainedFindCourse(String courseCode, Integer constraint){
        ArrayList<Course> courses = new ArrayList<Course>(courseRepository.findAll());
        ArrayList<Course> found = new ArrayList<Course>();
        Iterator<Course> iterate = courses.iterator();
        while(iterate.hasNext()){
            Course c = iterate.next();
            if(c.getCode().equals(courseCode) && c.getSection().equals(constraint)){
                found.add(c);
            }
        }
        return found;
    }
    public ArrayList<ArrayList<Course>> findCourses(ArrayList<String> coursesWanted){
        ArrayList<ArrayList<Course>> available = new ArrayList<ArrayList<Course>>();
        for(String course : coursesWanted){
            available.add(findCourse(course));
        }
        return available;
    
    }

    public ArrayList<ArrayList<ArrayList<Course>>> createTables(ArrayList<String> coursesWanted){
        ArrayList<ArrayList<Course>> available = new ArrayList<ArrayList<Course>>();
        for(String course : coursesWanted){
            available.add(courseRepository.findByCode(course));
        }
        return createOptions(available);
    }
    
    public ArrayList<ArrayList<ArrayList<Course>>> constrainedCreateTables(ArrayList<String> coursesWanted,ArrayList<String> constraints){
        ArrayList<ArrayList<Course>> available = new ArrayList<ArrayList<Course>>();
        for(int i=0;i<coursesWanted.size();i++){
            if(constraints.get(i).equals("0")){
                available.add(findCourse(coursesWanted.get(i)));
            }else{
                available.add(courseRepository.findByCodeAndSection(coursesWanted.get(i), Integer.valueOf(constraints.get(i))));
            }
        }
        return createOptions(available);
    }
    
    public ArrayList<ArrayList<ArrayList<Course>>> constrainedDaysCreateTables(ArrayList<String> coursesWanted, ArrayList<Integer> daysWanted){
        ArrayList<ArrayList<Course>> available = new ArrayList<ArrayList<Course>>();
        for(String course : coursesWanted){
            available.add(findCourse(course));
        }
        
        return constraintTableDays(createOptions(available),daysWanted);
    }
    public ArrayList<ArrayList<ArrayList<Course>>> constraintTableDays(ArrayList<ArrayList<ArrayList<Course>>> tables, ArrayList<Integer> daysWanted){
        ArrayList<ArrayList<ArrayList<Course>>> tablesConstrained = new ArrayList<ArrayList<ArrayList<Course>>>();
        for(ArrayList<ArrayList<Course>> table:tables){
            Set<Integer> daysInTable = new HashSet<Integer>();
            for(ArrayList<Course> courseFull : table){
                for(Course coursePart : courseFull){
                    daysInTable.add(coursePart.getDay());
                }
            }
            if(daysWanted.contains(daysInTable.size())){
                tablesConstrained.add(table);
            }
        }
        return tablesConstrained;
    }
/////////////////////////
    public ArrayList<Course> searchCourse(String courseCode,String studentId){
        ArrayList<Course> courses = new ArrayList<Course>(courseRepository.findAll());
        ArrayList<Course> found = new ArrayList<Course>();
        Iterator<Course> iterate = courses.iterator();
        while(iterate.hasNext()){
            Course c = iterate.next();
            if(c.getCode().equals(courseCode)){
                found.add(c);
                //c.getRegisteredStudents().add(studentId);

            }

        }
        return found;
    }
    
    public ArrayList<ArrayList<ArrayList<Course>>> createOptions(ArrayList<ArrayList<Course>> available){
        ArrayList<ArrayList<ArrayList<Course>>> options = new ArrayList<ArrayList<ArrayList<Course>>>();
        ArrayList<String> courseNames = new ArrayList<String>();
        Set<Integer> coursesSectionNumbersSet = new HashSet<Integer>();
        for(ArrayList<Course> currCourseList : available){
            ArrayList<Integer> courseSections = new ArrayList<Integer>(); 
            Integer indexOfCourse = available.indexOf(currCourseList);
            for(Course currCoursePart : currCourseList){
                Integer currCourseSection = currCoursePart.getSection();
                String currCourseName = currCoursePart.getCode();
                courseNames.add(indexOfCourse,currCourseName);
                courseSections.add(currCourseSection);
            }
            coursesSectionNumbersSet.add(courseSections.size());
        }
        ArrayList<Integer> coursesSectionNumbers = new ArrayList<Integer>();
        coursesSectionNumbers.addAll(coursesSectionNumbersSet);
        for(ArrayList<Course> currCourseList : available){
            ArrayList<ArrayList<Course>> currCourseSection = new ArrayList<ArrayList<Course>>();
            Integer indexOfCourse = available.indexOf(currCourseList);
            for (int i=0;i<coursesSectionNumbers.get(indexOfCourse)*2;i++){
                ArrayList<Course> newCourse = new ArrayList<Course>();
                currCourseSection.add(newCourse);
                System.out.println("This is i for currCourseSection");
                System.out.println(i);
            }
            for(Course currCoursePart : currCourseList){
                Integer currCourseSectionNow = currCoursePart.getSection();
                Integer currCourseSubSectionNow = currCoursePart.getSubSection();

                if(currCourseSubSectionNow.equals(0)){
                    Integer i = (currCourseSectionNow-1)*2;
                    Integer j = i+1;
                    currCourseSection.get(i).add(currCoursePart);
                    currCourseSection.get(j).add(currCoursePart);
                }
                if(currCourseSubSectionNow.equals(1)){
                    Integer i = (currCourseSectionNow-1)*2;
                    Integer j = i+1;
                    currCourseSection.get(i).add(currCoursePart);
                }
                if(currCourseSubSectionNow.equals(2)){
                    Integer i = (currCourseSectionNow-1)*2;
                    Integer j = i+1;
                    currCourseSection.get(j).add(currCoursePart);
                }
            }
            options.add(currCourseSection);
        }
       return getCombination(0,options);
    }

    public ArrayList<ArrayList<ArrayList<Course>>> getCombination(int currentIndex, ArrayList<ArrayList<ArrayList<Course>>> containers) {
        if (currentIndex == containers.size()) {
            // Skip the items for the last container
            ArrayList<ArrayList<ArrayList<Course>>> combinations = new ArrayList<ArrayList<ArrayList<Course>>>();
            combinations.add(new ArrayList<ArrayList<Course>>());
            return combinations;
        }
        ArrayList<ArrayList<ArrayList<Course>>> combinations = new ArrayList<ArrayList<ArrayList<Course>>>();
        ArrayList<ArrayList<Course>> container = containers.get(currentIndex);
        ArrayList<ArrayList<Course>> containerItemList = container;
        // Get combination from next index
        ArrayList<ArrayList<ArrayList<Course>>> suffixList = getCombination(currentIndex + 1, containers);
        int size = containerItemList.size();
        for (int ii = 0; ii < size; ii++) {
            ArrayList<Course> containerItem = containerItemList.get(ii);
            if (suffixList != null) {
                for (ArrayList<ArrayList<Course>> suffix : suffixList) {
                    ArrayList<ArrayList<Course>> nextCombination = new ArrayList<ArrayList<Course>>();
                    nextCombination.add(containerItem);
                    nextCombination.addAll(suffix);
                    combinations.add(nextCombination);
                }
            }
        }
        return reduceCombinations(combinations);
    }

    public ArrayList<ArrayList<ArrayList<Course>>> reduceCombinations( ArrayList<ArrayList<ArrayList<Course>>> options) {
        ArrayList<ArrayList<ArrayList<Course>>> reducedOptions = new ArrayList<ArrayList<ArrayList<Course>>>();
        ArrayList<ArrayList<ArrayList<Course>>> newList =new ArrayList<ArrayList<ArrayList<Course>>>(options.stream().distinct().collect(Collectors.toList()));
        Iterator<ArrayList<ArrayList<Course>>> iter = newList.iterator();
        while(iter.hasNext()){
            ArrayList<ArrayList<Course>> array11 = iter.next();
            Iterator<ArrayList<Course>> iter2 = array11.iterator();
            Boolean emptyArray = false;
            while(iter2.hasNext()){
                ArrayList<Course> array22 = iter2.next();
                Integer sizeOfArr = Integer.valueOf(array22.size());
                if(sizeOfArr>=1){
                    if(!sizeOfArr.equals(array22.get(0).getToTakeNumber())){
                        emptyArray = true;
                    }
                }else{
                    emptyArray=true;
                }
                
            }
            if(!emptyArray){
                reducedOptions.add(array11);
            }
        }
        return nonClashOptions(reducedOptions);
    }

    public ArrayList<ArrayList<ArrayList<Course>>> nonClashOptions( ArrayList<ArrayList<ArrayList<Course>>> options) {
        ArrayList<ArrayList<ArrayList<Course>>> nonClashing = new ArrayList<ArrayList<ArrayList<Course>>>();
        Iterator<ArrayList<ArrayList<Course>>> iter = options.iterator();
        while(iter.hasNext()){
            ArrayList<ArrayList<Course>> array11 = iter.next();
            Iterator<ArrayList<Course>> iter2 = array11.iterator();
            ArrayList<Course> currTableCourses = new ArrayList<Course>(); 
            Boolean clashingCourses = false;
            while(iter2.hasNext()){
                ArrayList<Course> array22 = iter2.next();
                Iterator<Course> iter3 = array22.iterator();
                while(iter3.hasNext()){
                    Course currCourse = iter3.next();
                    currTableCourses.add(currCourse);
                }                
            }
            Iterator<Course> iter4 = currTableCourses.iterator();
            while(iter4.hasNext()){
                Course currCourse = iter4.next();
                for(Integer i = 0; i<currTableCourses.size();i++){
                    Course nextCourse = currTableCourses.get(i);
                    if(currCourse.getRegistered().equals(currCourse.getTotal())){
                        clashingCourses = true;
                    }
                    if (!Integer.valueOf(currTableCourses.indexOf(currCourse)).equals(i)){
                        if (currCourse.getDay().equals(nextCourse.getDay())){
                            if (isOverlapping(currCourse.getStartTime(), currCourse.getEndTime(), nextCourse.getStartTime(), nextCourse.getEndTime())){
                                clashingCourses = true;
                            }
                        }
                        
                    }
                }
            }
            if(!clashingCourses){
                nonClashing.add(array11);
            }
        }
        
        return nonClashing;
    }

    public boolean isOverlapping(LocalTime start1, LocalTime end1, LocalTime start2, LocalTime end2) {
        return start1.isBefore(end2) && end1.isAfter(start2);
    }


    //for v2 of api
    @Transactional// saves the changes to the database
    public void registerInCourse(String studentId, Integer courseId) {
        Course course = courseRepository.findById(Long.valueOf(courseId)).orElseThrow(()-> new IllegalStateException("Not Found"));
        if (course.getRegistered()<course.getTotal() && !course.getRegisteredStudents().contains(studentId)){
            course.setRegistered(course.getRegistered()+1);
            course.getRegisteredStudents().add(studentId);
        }else{
            throw new IllegalStateException("Course is Full or you are already enrolled");
        }
        
    }

    //@Transactional
    // public void dropCourse(String courseCode, Integer drop,String studentId) {
    //     Course course = courseRepository.findByCode(courseCode).orElseThrow(()-> new IllegalStateException("Not Found"));
    //     if (drop ==1 && course.getRegisteredStudents().contains(studentId)){
    //         course.setRegistered(course.getRegistered()-1);
    //         course.getRegisteredStudents().remove(studentId);
    //     }else {
    //         throw new IllegalStateException("Course is not found or you are nor enrolled");

    //     }
        
    // }

    @Transactional
    public ArrayList<Course> getTable(String studentId) {//returns your regisstered courses
        ArrayList<Course> courses = new ArrayList<Course>(courseRepository.findAll());
        Iterator<Course> iterate = courses.iterator();
        ArrayList<Course> reg = new ArrayList<Course>();
        while(iterate.hasNext()){
            Course c = iterate.next();
            if(c.getRegisteredStudents().contains(studentId)){
                reg.add(c);
            }

        }
        return reg;

    }

    public void searchCourseId(Integer courseId,String studentId){

        Course course = courseRepository.findById(Long.valueOf(courseId)).orElseThrow(()-> new IllegalStateException("Not Found"));
        if (course.getRegistered()<course.getTotal() && !course.getRegisteredStudents().contains(studentId)){
            System.out.println("testing");
            course.setRegistered(course.getRegistered()+1);
            course.getRegisteredStudents().add(studentId);
        }
    }


    public void deleteCourse(Long id) {
        boolean exists = courseRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Not Found");
        }
        courseRepository.deleteById(id);
    }

}
