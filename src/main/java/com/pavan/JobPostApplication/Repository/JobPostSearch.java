package com.pavan.JobPostApplication.Repository;

import com.pavan.JobPostApplication.model.JobData;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface JobPostSearch {
    List<JobData> findByText(String text);
}
