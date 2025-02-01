package com.pavan.JobPostApplication.Repository;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.pavan.JobPostApplication.model.JobData;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class JobPostSerachImple implements JobPostSearch{

    @Autowired
    MongoClient mongoClient;
    @Autowired
    MongoConverter converter;
    @Override
    public List<JobData> findByText(String text)
    {
        List<JobData> data=new ArrayList<>();
        MongoDatabase database = mongoClient.getDatabase("JobPostDataBase");
        MongoCollection<Document> collection = database.getCollection("JobPostCollection");
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                        new Document("text",
                                new Document("query", text)
                                        .append("path", Arrays.asList("des", "profile", "tech")))),
                new Document("$sort",
                        new Document("exp", -1L)),
                new Document("$limit", 5L)));



        result.forEach(doc->data.add(converter.read(JobData.class,doc)));
        return  data;
    }
}
