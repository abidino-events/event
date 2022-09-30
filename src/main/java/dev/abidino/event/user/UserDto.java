package dev.abidino.event.user;

public record UserDto(Long id,
                      String username,
                      String email,
                      Status status) {
}
