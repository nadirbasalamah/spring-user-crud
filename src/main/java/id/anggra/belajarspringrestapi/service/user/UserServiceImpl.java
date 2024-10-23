package id.anggra.belajarspringrestapi.service.user;

import id.anggra.belajarspringrestapi.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService
{
    @Override
    public List<User> getAllUsers()
    {
        List<User> userList = new ArrayList<User>();
        User user = new User();
        user.setID(1);
        user.setName("Anggra");
        user.setEmail("anggra@anggra.com");
        user.setPassword("rahasia");

        userList.add(user);

        return userList;
    }
}
