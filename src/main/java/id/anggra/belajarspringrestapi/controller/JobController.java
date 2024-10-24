package id.anggra.belajarspringrestapi.controller;

import id.anggra.belajarspringrestapi.model.Job;
import id.anggra.belajarspringrestapi.model.JobRequest;
import id.anggra.belajarspringrestapi.model.Response;
import id.anggra.belajarspringrestapi.service.job.JobService;
import id.anggra.belajarspringrestapi.util.Util;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/jobs")
@RequiredArgsConstructor
public class JobController {
    private final JobService jobService;

    @GetMapping
    public ResponseEntity<Response<List<Job>>> getAllJobs() {
        List<Job> jobs = jobService.getAllJobs();
        Response<List<Job>> response = new Response<>("all jobs", jobs);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Response<Job>> createJob(@Valid @RequestBody JobRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String validationErrors = Util.getValidationErrors(bindingResult);

            return new ResponseEntity<>(new Response<>(validationErrors,null), HttpStatus.BAD_REQUEST);
        }
        Job job = jobService.createJob(request);
        Response<Job> response = new Response<>("job created",job);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
