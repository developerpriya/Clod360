package com.Cloud360.Topics.Mysql.repository;

import com.Cloud360.Topics.Mysql.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    
}
