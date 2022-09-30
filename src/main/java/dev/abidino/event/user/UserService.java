package dev.abidino.event.user;

import dev.abidino.event.exception.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record UserService(UserRepository userRepository) {
    public User save(User user) {
        return userRepository.save(user);
    }

    public User update(Long id, User user) {
        User oldUser = findById(id);
        user.setId(oldUser.getId());
        return userRepository.save(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new BadRequestException("User not found"));
    }

    public void delete(Long id) {
        User user = findById(id);
        user.setStatus(Status.PASSIVE);
        save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User authenticateUser() {
        //TODO: @abidino security baglayinca burayi kaldir
        return findById(1L);
    }
}
