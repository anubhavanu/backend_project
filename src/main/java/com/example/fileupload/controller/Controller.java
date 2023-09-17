package com.example.fileupload.controller;

import com.example.fileupload.dto.Country;
import com.example.fileupload.dto.JobRequest;
import com.example.fileupload.model.infosys.Office;
import com.example.fileupload.model.primary.User;
import com.example.fileupload.repository.primary.FlagImgRepository;
import com.example.fileupload.service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;


@RestController
public class Controller {
    @Autowired
    FileUploadService fileUploadService;
    @Autowired
    UserService userService;

    @Autowired
    CSVUserService csvUserService;

    @Autowired
    FileSystemStorageService fileSytemStorage;

//    @Autowired
//    Country country;

    @Autowired
    CountryService countryService;

    @Autowired
    FlagImgRepository flagImgRepository;

    @Autowired
    FlagImgService flagImgService;

    @Autowired
    SchedulerService schedulerService;

    @Autowired
    JobScheduler jobScheduler;

    @Autowired
    TransactionDetails transactionDetails;

    @Autowired
    TestRepo testRepos;

    @Autowired
    ExamService examServices;

    @Autowired
    OfficeService officeServices;

    @Autowired
    EmployeeService employeeServices;




//    @Autowired
//    Validation validation;

    @PostMapping("/fileupload")
    public void UploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        try {
            fileUploadService.uploadFile(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/userfileupload")
    public void userupload(@RequestPart("user") User json, @RequestParam("file") MultipartFile file) throws IOException {
        try {
            fileUploadService.uploadFile(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/addUser")
    public String addUser(@RequestBody User user) throws UserAlreadyFoundException {
        try {
            userService.addUser(user);
            return ("User added successFully");
        } catch (UserAlreadyFoundException e) {
            return (e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }

    @GetMapping("/download/{filename:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) throws FileNotFoundException {

        Resource resource = fileSytemStorage.loadFile(filename);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping("/userscsv/{fileName:.+}")

    public ResponseEntity<Resource> downloadUser(@PathVariable String fileName) {
        InputStreamResource file = new InputStreamResource(csvUserService.load());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
//    }

    }

    @GetMapping("/country/search")
    public void getcountrydata() throws JsonProcessingException {

         Country[] countryStr = countryService.fetchCountryTableData();
    }

    @GetMapping("/countryFlagImgDownload")

    public String downloadFlagImg() {
        ArrayList<String> webLink = flagImgRepository.getImgUrls();
        String s = null;
        try {
            s = flagImgService.saveImg(webLink);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return (s);
    }

    @GetMapping("/Schedule")
    public String scheduleJob() throws SchedulerException, SchedulerException {
        try {
            schedulerService.schedule();
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
        return ("job ran successfully");
    }

    @PostMapping("/createJob")
    public String userupload(@RequestBody JobRequest jobRequest) throws IOException, SchedulerException {
        try {
            return jobScheduler.scheduling(jobRequest);
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }

    }

    @PostMapping("/pause")
    public String pauseingJob(@RequestBody JobRequest jobRequest) throws SchedulerException {
        return jobScheduler.pause(jobRequest);

    }

    @PostMapping("/resume")
    public String resumeJob(@RequestBody JobRequest jobRequest) throws SchedulerException {
        return jobScheduler.resume(jobRequest);

    }

    @PostMapping("/job/update")
    public String updateJob(@RequestBody JobRequest jobRequest) throws SchedulerException {
        jobScheduler.updateJob(jobRequest);
        return ("job updated");
    }

    @PostMapping("/job/delete/{groupname}/{jobname}")
    public String deleteJob(@PathVariable String groupname, @PathVariable String jobname) throws SchedulerException {
        jobScheduler.deleteJob(groupname, jobname);
        return ("job deleted");
    }

    @PostMapping("/transaction/{user_account_no}/{amount}")
    public ResponseEntity<?> Transaction(@PathVariable String user_account_no, @PathVariable int amount) {
        Long result=transactionDetails.newTransaction(user_account_no, amount);
        String s="{ \"Transaction id\" :"+ String.valueOf(result)+", \"Status\" : "+"Transaction req accepted"+" }";


        return ResponseEntity.ok()

                .contentType(MediaType.parseMediaType("application/json"))
                .body(s);
    }
    @GetMapping("/transactionstatus/{transaction_id}")
    public ResponseEntity<?> Transaction(@PathVariable Long transaction_id) {
        String result=transactionDetails.transactionStatus(transaction_id);
        return ResponseEntity.ok()

                .contentType(MediaType.parseMediaType("application/json"))
                .body(result);
    }
    @GetMapping("/transaction_management")
    public ResponseEntity<?> txn_mgmt() throws JsonProcessingException {
        testRepos.savedata();
        return ResponseEntity.ok()

                .contentType(MediaType.parseMediaType("application/String"))
                .body("Tractional behaviour under imlimentation");
    }
    @GetMapping("/add_exam/{exam_name}/{exam_type}")
    public ResponseEntity<?> adding_exams(@PathVariable String exam_name, @PathVariable String exam_type)  {
        examServices.addExam(exam_name,exam_type);
        return ResponseEntity.ok()

                .contentType(MediaType.parseMediaType("application/String"))
                .body("Exam_added successfully");
    }
    @GetMapping("/add_office/{office_name}/{office_city}/{area}")
    public ResponseEntity<?> adding_office(@PathVariable String office_name, @PathVariable String office_city,@PathVariable int area)  {
        officeServices.add_office(office_name,office_city, area);
        return ResponseEntity.ok()

                .contentType(MediaType.parseMediaType("application/String"))
                .body("OFFICE_added successfully");
    }

    @PostMapping("/add_employee/{employee_name}/{employee_status}/{salary}")
    public ResponseEntity<?> adding_employee(@PathVariable String employee_name, @PathVariable String employee_status,@PathVariable int salary)  {
        employeeServices.add_employee(employee_name,employee_status, salary);
        return ResponseEntity.ok()

                .contentType(MediaType.parseMediaType("application/String"))
                .body("employee added successfully");
    }

    @GetMapping("/find_employee/{id}")
    public ResponseEntity<?> find_employee(@PathVariable int id)  {
        employeeServices.findById(id);
        return ResponseEntity.ok()

                .contentType(MediaType.parseMediaType("application/String"))
                .body("OFFICE_added successfully");
    }


    @GetMapping("/find_office/{id}")
    public Office find_office_by_id(@PathVariable int id)  {
        return officeServices.find_office(id);

    }
    @PostMapping("/update_office/{office_id}/{office_name}/{office_city}/{area}")
    public ResponseEntity<?> updating_office(@PathVariable int office_id,@PathVariable String office_name, @PathVariable String office_city,@PathVariable int area)  {
        officeServices.update_office(office_id,office_name,office_city,area);
        return ResponseEntity.ok()

                .contentType(MediaType.parseMediaType("application/String"))
                .body("office updated successfully");
    }
    @DeleteMapping("/delete_office/{office_id}")
    public ResponseEntity<?> delete_office(@PathVariable int office_id)  {
        officeServices.delete_office(office_id);
        return ResponseEntity.ok()

                .contentType(MediaType.parseMediaType("application/String"))
                .body("office deleted successfully");
    }


}

