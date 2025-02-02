package com.pavan.JobPostApplication.Controllers;


import com.pavan.JobPostApplication.model.JobData;
import com.pavan.JobPostApplication.services.JobPostDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "https://job-post-platform-with-springboot-rxrb.onrender.com")
public class JobPostController {

    @Autowired
    JobPostDao data;

    @GetMapping("/posts")
    public List<JobData> getAllPosts()
    {
        return data.getAll();
    }

    @GetMapping("posts/search/{text}")
    public List<JobData> searchPosts(@PathVariable String text)
    {
        return data.findJobs(text);
    }

    @PostMapping("/addJobPost")
    public void postJob(@RequestBody JobData job)
    {
        data.jobPost(job);
    }


}
