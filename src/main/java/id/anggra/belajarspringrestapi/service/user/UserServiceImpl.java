package id.anggra.belajarspringrestapi.service.user;

import id.anggra.belajarspringrestapi.model.Job;
import id.anggra.belajarspringrestapi.model.User;
import id.anggra.belajarspringrestapi.model.UserRequest;
import id.anggra.belajarspringrestapi.repository.UserRepository;
import id.anggra.belajarspringrestapi.service.job.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService
{
    private final UserRepository userRepository;
    private final JobService jobService;

    @Override
    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User createUser(UserRequest request) {
        Optional<Job> jobData = jobService.getJobById(request.getJobId());

        if (jobData.isEmpty()) {
            return null;
        }

        Job job = jobData.get();

        User userData = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .job(job)
                .build();

        return userRepository.save(userData);
    }

    @Override
    public User updateUser(UserRequest request, Long id) {
        Optional<User> userData = getUserById(id);

        if (userData.isEmpty()) {
            return null;
        }

        Optional<Job> jobData = jobService.getJobById(request.getJobId());

        if (jobData.isEmpty()) {
            return null;
        }

        Job job = jobData.get();
        User updatedUser = userData.get();

        updatedUser.setName(request.getName());
        updatedUser.setEmail(request.getEmail());
        updatedUser.setPassword(request.getPassword());
        updatedUser.setJob(job);

        return userRepository.save(updatedUser);
    }

    @Override
    public boolean deleteUser(Long id) {
       Optional<User> userData = getUserById(id);

       if (userData.isEmpty()) {
           return false;
       }

       userRepository.delete(userData.get());

       return true;
    }
}
