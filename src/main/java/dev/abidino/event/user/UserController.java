package dev.abidino.event.user;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
record UserController(UserService userService, UserMapper userMapper) {

    @PostMapping
    UserDto save(@RequestBody UserDto userDto) {
        User user = userMapper.ToUser(userDto);
        User savedUser = userService.save(user);
        return userMapper.ToUserDto(savedUser);
    }

    @PutMapping("/{id}")
    UserDto update(@RequestBody UserDto userDto, Long id) {
        User user = userMapper.ToUser(userDto);
        User updatedUser = userService.update(id, user);
        return userMapper.ToUserDto(updatedUser);
    }

    @GetMapping("/{id}")
    UserDto get(@PathVariable Long id) {
        User user = userService.findById(id);
        return userMapper.ToUserDto(user);
    }

    @GetMapping("/all")
    List<UserDto> get() {
        List<User> userList = userService.findAll();
        return userMapper.ToUserDto(userList);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
