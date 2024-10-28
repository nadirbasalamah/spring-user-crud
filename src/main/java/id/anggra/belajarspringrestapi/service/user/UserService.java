package id.anggra.belajarspringrestapi.service.user;

import id.anggra.belajarspringrestapi.model.User;
import id.anggra.belajarspringrestapi.model.UserRequest;

import java.util.List;
import java.util.Optional;

public interface UserService
{
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    User updateUser(UserRequest request, Long id);
    boolean deleteUser(Long id);
}
