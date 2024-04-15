package assignment.services;

import assignment.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User save(User user);

    User update(User user);

    List<User> getAll();

    User getById(long id);

    void deleteById(long id);
}
