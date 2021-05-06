package com.aws.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.aws.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public Student save(Student student){
        dynamoDBMapper.save(student);
        return student;
    }

    public Student getStudentById(String studentId){
        return dynamoDBMapper.load(Student.class, studentId);
    }

    public Student updateStudentById(String studentId, Student student){
        try{
            dynamoDBMapper.save(student,
                    new DynamoDBSaveExpression()
                            .withExpectedEntry("studentId", new ExpectedAttributeValue(
                                    new AttributeValue().withS(studentId)
                            ))
            );
            return student;
        } catch (ConditionalCheckFailedException e){
            System.err.println("Error while Updating student details");
            throw new RuntimeException(e);
        }
    }

    public String deleteStudentById(String studentId){
        Student student = dynamoDBMapper.load(Student.class, studentId);
        dynamoDBMapper.delete(student);
        return "Student Deleted";
    }



}
