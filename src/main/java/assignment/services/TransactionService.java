package assignment.services;

import assignment.entity.CourseRevenue;
import assignment.entity.Transaction;
import assignment.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionService {
    void save(Transaction transaction);

    List<Transaction> findAllByBuyer(User user);

    List<Object[]> getRevenueByMonthInYear(int year);

    List<Object[]> getRevenueByQuarterInYear(int year);

    List<CourseRevenue> getRevenueByCourse();

}
