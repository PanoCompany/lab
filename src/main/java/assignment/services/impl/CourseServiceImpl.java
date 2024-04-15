package assignment.services.impl;

import assignment.entity.Course;
import assignment.dao.CourseRepository;
import assignment.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseRepository courseRepository;

    @Override
    public void save(Course course) {
        courseRepository.save(course);
    }

    @Override
    public void deleteById(long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public Course getById(long id) {
        return courseRepository.findById(id).get();
    }

    @Override
    public List<Course> getAll() {
        return courseRepository.findAll();
    }
}
