package com.student.repository;

import com.student.core.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT student FROM Student student WHERE student.dept = :dept")
    public Collection<Student> getByDept(@Param("dept") String department);
}
