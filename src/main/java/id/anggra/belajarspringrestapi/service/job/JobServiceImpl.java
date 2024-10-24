package id.anggra.belajarspringrestapi.service.job;

import id.anggra.belajarspringrestapi.model.Job;
import id.anggra.belajarspringrestapi.model.JobRequest;
import id.anggra.belajarspringrestapi.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService{
    private final JobRepository repository;

    @Override
    public List<Job> getAllJobs() {
        return repository.findAll();
    }

    @Override
    public Optional<Job> getJobById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Job createJob(JobRequest request) {
        Job job = Job.builder()
                .title(request.getTitle())
                .build();

        return repository.save(job);
    }
}
