package assignment.services.impl;

import assignment.entity.User;
import assignment.dao.UserRepository;
import assignment.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User save(User user) {
        if (userRepository.findByEmail(user.getEmail()) == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setCreateAt(new Date());
            user.setUpdateAt(new Date());
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public User update(User user) {
        User info = userRepository.findById(user.getId()).get();
        info.setRole(user.getRole());
        info.setFullName(user.getFullName());
        info.setGender(user.getGender());
        info.setPhoneNumber(user.getPhoneNumber());
        info.setAddress(user.getAddress());
        info.setUpdateAt(new Date());
        return userRepository.save(info);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }
}
