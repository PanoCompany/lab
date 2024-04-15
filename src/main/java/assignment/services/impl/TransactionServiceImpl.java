package assignment.services.impl;

import assignment.dao.CourseRepository;
import assignment.dao.TransactionRepository;
import assignment.entity.Course;
import assignment.entity.CourseRevenue;
import assignment.entity.Transaction;
import assignment.entity.User;
import assignment.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    CourseRepository courseRepository;

    @Override
    public void save(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> findAllByBuyer(User user) {
        return transactionRepository.findAllByBuyer(user);
    }

    @Override
    public List<Object[]> getRevenueByMonthInYear(int year) {
        return transactionRepository.sumPriceByMonthAndYear(year);
    }

    @Override
    public List<Object[]> getRevenueByQuarterInYear(int year) {
        return transactionRepository.sumPriceByQuarterAndYear(year);
    }

    @Override
    public List<CourseRevenue> getRevenueByCourse() {
        List<CourseRevenue> revenueByCourse = new ArrayList<>();
        List<Course> courses = courseRepository.findAll();
        for (Course course : courses) {
            double revenue = transactionRepository.sumPriceByCourse(course.getId());
            CourseRevenue courseRevenue = new CourseRevenue();
            courseRevenue.setCourse(course);
            courseRevenue.setRevenue(revenue);

            revenueByCourse.add(courseRevenue);
        }
        return revenueByCourse;
    }
}
