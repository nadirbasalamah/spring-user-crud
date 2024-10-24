package id.anggra.belajarspringrestapi.service.job;

import id.anggra.belajarspringrestapi.model.Job;
import id.anggra.belajarspringrestapi.model.JobRequest;

import java.util.List;
import java.util.Optional;

public interface JobService {
    List<Job> getAllJobs();
    Optional<Job> getJobById(Long id);
    Job createJob(JobRequest request);
}
