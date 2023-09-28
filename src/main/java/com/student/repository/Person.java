package com.student.repository;

import com.student.core.Student;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "person", types = Student.class)
public interface Person {
    String getFirstName();
    String getSurname();
    String getId();
}
