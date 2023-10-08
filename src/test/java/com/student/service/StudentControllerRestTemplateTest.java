package com.student.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.student.core.Course;
import com.student.core.Student;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class StudentControllerRestTemplateTest {
    @Test
	void testPost() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("content-type", MediaType.APPLICATION_JSON_VALUE);
		Student student = new Student();
		student.setDept("History");
		student.setFirstName("PARACETAMOL");
		student.setSurname("Flintstone");
		student.setFees(122.00);
		Course course = new Course();
		course.setLocation("University of Miami");
		course.setStudent(student);
		course.setTitle("History of Carthage");
		student.getCourses().add(course);
		ResponseEntity<String> response = new RestTemplate().postForEntity("http://localhost:8080/student",new HttpEntity(student, headers), String.class);
		String url = response.getHeaders().get("location").get(0);
		Student attendee =  new RestTemplate().getForObject("http://localhost:8080/" + url, Student.class);
		System.out.println(attendee);

	}

	@Test
	void testPostNegative() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("content-type", MediaType.APPLICATION_JSON_VALUE);
		Student student = new Student();
		student.setDept("History");
		student.setFirstName("Barney");
		student.setSurname("Rubble");
		student.setFees(201.00);
		Course course = new Course();
		course.setLocation("University of Miami");
		course.setStudent(student);
		course.setTitle("History of Carthage");
		student.getCourses().add(course);
		Assertions.assertThrows(HttpServerErrorException.class, () -> {
			ResponseEntity<String> response = new RestTemplate().postForEntity("http://localhost:8080/student",new HttpEntity(student, headers), String.class);
			assertThat(response.getStatusCodeValue(), equalTo(500));
		});

	}

}
