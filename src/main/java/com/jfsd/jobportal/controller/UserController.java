package com.jfsd.jobportal.controller;

import com.jfsd.jobportal.config.JwtTokenUtil;
import com.jfsd.jobportal.exception.ResourceNotFoundException;
import com.jfsd.jobportal.models.*;
import com.jfsd.jobportal.repository.AdminRepository;
import com.jfsd.jobportal.repository.ApplicationRepository;
import com.jfsd.jobportal.repository.JobRepository;
import com.jfsd.jobportal.repository.RecruiterRepository;
import com.jfsd.jobportal.service.*;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
//@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000",allowedHeaders = "*")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RecruiterService recruiterService;
    @Autowired
    private JobService jobService;
    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private RecruiterRepository recruiterRepository;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private EmailService emailService;

    //create user Rest API
    @PostMapping("/user")
    public ResponseEntity<User> saveEmployee(@Valid @RequestBody User user) {
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    //get user Rest API
    @GetMapping("/user/mobile/{mobile}")
    public ResponseEntity<User> getUser(@PathVariable String mobile) {
        return ResponseEntity.ok(userService.getUser(mobile));
    }

    @GetMapping("/user/id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @PutMapping("/user/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User userDetails) {
        User existingUser = userService.getUserById(id);
        if (existingUser == null) {
            return ResponseEntity.notFound().build();
        }
        // Update only the allowed fields
        existingUser.setFirstName(userDetails.getFirstName());
        existingUser.setLastName(userDetails.getLastName());
        existingUser.setAge(userDetails.getAge());
        User updatedUser = userService.saveUser(existingUser);
        return ResponseEntity.ok(updatedUser);
    }


    @PostMapping("/userlogin/{email}/{password}")
    public ResponseEntity<LoginResponse> checkUser(@PathVariable String email, @PathVariable String password) {
        User user = userService.checkUser(email, password);
        if (user != null) {
            // Create an instance of JwtTokenUtil
            JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();

            // Generate JWT token using user ID
            String token = jwtTokenUtil.generateToken(String.valueOf(user.getId()));

            // Prepare the response with the token and user details
            LoginResponse loginResponse = new LoginResponse(user.getId(), user.getEmail(), token);

            return ResponseEntity.ok(loginResponse);  // Send response with token
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }


    @PostMapping("/recruiter")
    public ResponseEntity<Recruiter> saveRecruiter(@Valid @RequestBody Recruiter recruiter) {
        return new ResponseEntity<>(recruiterService.saveRecruiter(recruiter), HttpStatus.CREATED);
    }

    @GetMapping("/recruiter/{mobile}")
    public ResponseEntity<Recruiter> getRecruiter(@PathVariable String mobile) {
        return ResponseEntity.ok(recruiterService.getRecruiter(mobile));
    }

    @GetMapping("/recruiter/id/{id}")
    public ResponseEntity<Recruiter> getRecruiterById(@PathVariable int id) {
        Recruiter recruiter = recruiterRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recruiter not found"));
        return ResponseEntity.ok(recruiter);
    }

    @PutMapping("/recruiter/update/{id}")
    public ResponseEntity<Recruiter> updateRecruiter(@PathVariable int id, @RequestBody User userDetails) {
        Recruiter existingRecruiter = recruiterRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recruiter not found"));
        if (existingRecruiter == null) {
            return ResponseEntity.notFound().build();
        }
        // Update only the allowed fields
        existingRecruiter.setFirstName(userDetails.getFirstName());
        existingRecruiter.setLastName(userDetails.getLastName());
        existingRecruiter.setAge(userDetails.getAge());
        Recruiter updatedRecruiter = recruiterService.saveRecruiter(existingRecruiter);
        return ResponseEntity.ok(updatedRecruiter);
    }

    @GetMapping("/recruiters")
    public ResponseEntity<List<Recruiter>> getRecruiters() {
        return ResponseEntity.ok(recruiterService.getRecruiters());
    }

    @PostMapping("/recruiterlogin/{email}/{password}")
    public ResponseEntity<?> checkRecruiter(@PathVariable String email, @PathVariable String password) {
        System.out.println("Received email: " + email + ", password: " + password);

        String user = recruiterService.checkRecruiter(email, password);
        if (user != null) {
            // Return recruiter object if credentials are valid
            Recruiter recruiter = recruiterRepository.findByEmail(email);
            if (recruiter != null) {
                return ResponseEntity.ok(recruiter); // Returns the recruiter details
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recruiter not found");
            }
        } else {
            // Return unauthorized status with a message
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @PostMapping("/job")
    public ResponseEntity<Job> saveJob(@RequestBody Job job) {
        // Retrieve the recruiter from the database by ID
        System.out.println(job.getRecruiter().getId());
        if (job.getRecruiter() == null) {
            System.out.println("No Job");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Invalid recruiter ID
        }

        Recruiter recruiter = recruiterRepository.findById(job.getRecruiter().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Recruiter not found"));

        // Set the recruiter to the job
        job.setRecruiter(recruiter);

        // Save the job with the recruiter
        return new ResponseEntity<>(jobService.saveJob(job), HttpStatus.CREATED);
    }


    @GetMapping("/job/{id}")
    public ResponseEntity<JobDTO> getJob(@PathVariable int id) {

        Job job = jobRepository.findByid(id);
        ;
        JobDTO jobDTO = new JobDTO(
                job.getId(),
                job.getName(),
                job.getCompany(),
                job.getSalary(),
                job.getLocation(),
                job.getShift(),
                job.getDescription(),
                job.getRecruiter()
        );
        return new ResponseEntity<>(jobDTO, HttpStatus.OK);
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<JobDTO>> getAllJobs() {
        List<Job> jobs = jobRepository.findAll();
        List<JobDTO> jobDTOs = jobs.stream()
                .map(job -> {
                    JobDTO dto = new JobDTO(
                            job.getId(),
                            job.getName(),
                            job.getCompany(),
                            job.getSalary(),
                            job.getLocation(),
                            job.getShift(),
                            job.getDescription(),
                            job.getRecruiter()
                    );

                    return dto;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(jobDTOs);
    }

    @GetMapping("/jobsByRec/{id}")
    public ResponseEntity<List<JobDTO>> getJobsByRec(@PathVariable int id) {
        List<Job> jobs = jobRepository.findByRecruiter_Id(id);
        List<JobDTO> jobDTOs = jobs.stream()
                .map(job -> {
                    JobDTO dto = new JobDTO(
                            job.getId(),
                            job.getName(),
                            job.getCompany(),
                            job.getSalary(),
                            job.getLocation(),
                            job.getShift(),
                            job.getDescription(),
                            job.getRecruiter()
                    );

                    return dto;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(jobDTOs);
    }

    @PostMapping("/application")
    public ResponseEntity<Application> saveApplication(@RequestBody Application application) {
        System.out.println("Received application: " + application);

        // Validate job_id
        if (application.getJob() == null || application.getJob().getId() <= 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Find the Job entity from the database by job ID
        Job job = jobRepository.findById(application.getJob().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Job not found"));

        // Set the job in the application and other details
        application.setJob(job);
        application.setJobName(job.getName());
        application.setStatus("pending");
        System.out.println("Application with job set: " + application);

        return new ResponseEntity<>(applicationService.saveApplication(application), HttpStatus.CREATED);
    }


    @PutMapping("/application/{id}/status")
    public ResponseEntity<Application> updateApplicationStatus(
            @PathVariable int id,
            @RequestBody Map<String, String> status) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found with id: " + id));

        // Update the status
        application.setStatus(status.get("status"));
        applicationRepository.save(application);

        // Send email notification
        emailService.sendStatusEmail(
                application.getEmail(),          // Applicant's email
                application.getFirstName(),      // Applicant's first name
                application.getJobName(),        // Job name
                application.getStatus()          // New status
        );

        return ResponseEntity.ok(application);
    }


    @GetMapping("/userApplications/{userEmail}")
    public ResponseEntity<List<Application>> getApplicationsByUserId(@PathVariable String userEmail) {
        List<Application> applications = applicationService.getApplicationsByEmail(userEmail);
        if (applications.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(applications, HttpStatus.OK);
    }


    //    @GetMapping("/applications/{mobile}")
//    public ResponseEntity<Application> getApplication(@PathVariable String mobile){
//        return ResponseEntity.ok(applicationService.getApplication(mobile));
//    }
    @GetMapping("/applications/{recruiterId}")
    public List<Application> getApplicationsByRecruiter(@PathVariable int recruiterId) {
        return applicationService.getApplicationsByRecruiterId(recruiterId);
    }



    //    @GetMapping("/applications/all")
//    public ResponseEntity<List<Application>> getApplications(){
//        return ResponseEntity.ok(applicationService.getApplications());
//    }
    @PostMapping("/createAdmin")
    public ResponseEntity<Admin> saveAdmin(@RequestBody Admin admin) {
        return new ResponseEntity<>(adminService.saveAdmin(admin), HttpStatus.CREATED);
    }

    @PostMapping("/adminLogin/{username}/{password}")
    public ResponseEntity<Admin> checkAdmin(@PathVariable String username, @PathVariable String password, HttpSession session) {

        Admin admin = adminService.checkAdmin(username, password);

        if (admin != null) {

            return ResponseEntity.ok(admin); // Returns the recruiter details

        } else
            System.out.println("no data");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

//    @GetMapping("/admin")
//    public ResponseEntity<String> admin(){
//        return ResponseEntity.ok("Hello admin");
//    }
//    @PostMapping("/admin/logout")
//    public ResponseEntity<String> logoutAdmin(HttpSession session){
//        session.invalidate();
//
//        return ResponseEntity.status(HttpStatus.FOUND)
//                .header("Location", "/api/adminLogin")
//                .build();
//    }
//    @GetMapping("/admin/users")
//    public ResponseEntity<List<User>> viewUsers(HttpSession session){
//        boolean adminLoggedIn = (Boolean)session.getAttribute("adminLoggedIn");
//        if(adminLoggedIn){
//            return ResponseEntity.ok(adminService.viewUsers());
//        }else{
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
//        }
//
//    }

}

