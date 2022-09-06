package com.nucoders.tablemaker.courses;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    //@Query("SELECT c FROM Course c WHERE c.code =?1")
    ArrayList<Course> findByCode(String code);
    ArrayList<Course> findBySection(Integer section);
    ArrayList<Course> findByCodeAndSection(String code, Integer section);
}
