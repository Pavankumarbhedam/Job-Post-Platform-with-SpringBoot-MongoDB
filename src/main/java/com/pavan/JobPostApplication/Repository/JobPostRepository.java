package com.pavan.JobPostApplication.Repository;

import com.pavan.JobPostApplication.model.JobData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobPostRepository extends MongoRepository<JobData,Integer> {
}
