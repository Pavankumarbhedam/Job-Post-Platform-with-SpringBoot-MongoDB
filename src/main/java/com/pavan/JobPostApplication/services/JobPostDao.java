package com.pavan.JobPostApplication.services;


import com.pavan.JobPostApplication.Repository.JobPostRepository;
import com.pavan.JobPostApplication.Repository.JobPostSearch;
import com.pavan.JobPostApplication.model.JobData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JobPostDao {
    @Autowired
    JobPostRepository repo;
    @Autowired
    JobPostSearch jobsearch;

    public List<JobData> getAll()
    {

        return repo.findAll();
    }

    public List<JobData> findJobs(String text) {
        return jobsearch.findByText(text);
    }

    public void jobPost(JobData job)
    {
        repo.save(job);
    }

    public void delete(long id) {

    }
}
