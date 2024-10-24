package id.anggra.belajarspringrestapi.repository;

import id.anggra.belajarspringrestapi.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
