package com.Cloud360.Topics.Mysql.controller;

import com.Cloud360.Topics.Mysql.entity.Course;
import com.Cloud360.Topics.Mysql.repository.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


import java.util.*;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
@Slf4j
public class CourseController {

    @Autowired
    public  CourseRepository courseRepo;

    @GetMapping("/topics")
    public List<Course> getAllCourse()
    {
        log.info("Get all topic details");
        return courseRepo.findAll();
    }

    @PostMapping("/topics")
    public ResponseEntity<?>  createCourse(@Valid @RequestBody Course course)
    {
        log.info("Save new topic details");
        courseRepo.save(course);
        return new ResponseEntity<Void>(HttpStatus.CREATED);

    }

    @GetMapping("/topics/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable(value = "id") Long topicId)  {
        log.info("Get a topic using id");
        Optional<Course> CourseOpt = courseRepo.findById(topicId);

        if (CourseOpt.isPresent())
        {
            return new ResponseEntity<Course>(CourseOpt.get(),HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/topics/{id}")
    public ResponseEntity<?> updateCourse(@RequestBody Course course,
                               @PathVariable("id") Long topicId) {
        log.info("Update a topic using id");
        Optional<Course> CourseOpt = courseRepo.findById(topicId);
        if (CourseOpt.isPresent()) {
            Course newCourse = CourseOpt.get();
            newCourse.setTopicName(course.getTopicName());
            newCourse.setTopicComment(course.getTopicComment());
            courseRepo.save(newCourse);
            return new ResponseEntity<Course>(CourseOpt.get(),HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND) ;
    }
}
