package vn.hoidanit.jobhunter.controller;

import java.net.ResponseCache;

import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties.Http;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import vn.hoidanit.jobhunter.domain.Job;
import vn.hoidanit.jobhunter.service.JobService;
import vn.hoidanit.jobhunter.util.annotation.ApiMessage;

@RestController
@RequestMapping("/api/v1")
public class JobController {

  private final JobService jobService;

  public JobController(JobService jobService) {
    this.jobService = jobService;
  }

  @PostMapping("/jobs")
  @ApiMessage("Create a job")
  public ResponseEntity<Job> createJob( Job reqJob) {
    Job thanhJob = this.jobService.handleCreateJob(reqJob);
    return ResponseEntity.status(HttpStatus.CREATED).body(thanhJob);
  }
}
