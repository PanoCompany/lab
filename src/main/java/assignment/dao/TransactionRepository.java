package assignment.dao;

import assignment.entity.Course;
import assignment.entity.Transaction;
import assignment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByBuyer(User user);

    @Query("SELECT MONTH(t.createAt) AS month, SUM(t.price) AS totalRevenue " +
            "FROM Transaction t " +
            "WHERE YEAR(t.createAt) = :year " +
            "GROUP BY MONTH(t.createAt)" +
            "ORDER BY MONTH(t.createAt) ASC")
    List<Object[]> sumPriceByMonthAndYear(@Param("year") int year);

    @Query("SELECT QUARTER(t.createAt) AS quarter, SUM(t.price) AS totalRevenue " +
            "FROM Transaction t " +
            "WHERE YEAR(t.createAt) = :year " +
            "GROUP BY QUARTER(t.createAt)" +
            "ORDER BY QUARTER(t.createAt) ASC")
    List<Object[]> sumPriceByQuarterAndYear(@Param("year") int year);

    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN SUM(t.price) ELSE 0 END " +
            "FROM Transaction t " +
            "WHERE t.course.id = :courseId " +
            "ORDER BY SUM(t.price)")
    Double sumPriceByCourse(@Param("courseId") Long courseId);
}
