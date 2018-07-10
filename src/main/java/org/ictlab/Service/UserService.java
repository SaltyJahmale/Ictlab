package org.ictlab.Service;

import org.ictlab.domain.Group;
import org.ictlab.domain.User;
import org.ictlab.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * @param user
     */
    public void saveUser(User user) {
        if(getUser(user.getUsername()) == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userRepository.save(user);
    }

    /**
     * @param user
     */
    public void updateUser(User user) {
        userRepository.save(user);
    }

    /**
     * @param username
     * @return User
     */
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * @return List<User>
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * @param user
     */
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    /**
     * @param groupName
     * @return List<User>
     */
    public List<User> getAllByClass(Group groupName) {
        return userRepository.findAllByClassName(groupName);
    }
}
