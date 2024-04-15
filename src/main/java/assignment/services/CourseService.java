package assignment.services;

import assignment.entity.Course;

import java.util.List;

public interface CourseService {
    void save(Course course);
    void deleteById(long id);
    Course getById(long id);
    List<Course> getAll();
}
