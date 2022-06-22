package com.studentservice.app.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.studentservice.app.dtos.Response;
import com.studentservice.app.dtos.StuDTO;
import com.studentservice.app.dtos.StudentDTO;
import com.studentservice.app.dtos.StudentRecord;
import com.studentservice.app.entity.Courses;
import com.studentservice.app.entity.Student;
import com.studentservice.app.services.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.*;

@CrossOrigin(origins = "*")
@RestController
@Tag(name="Students Endpoints", description="This exposes student api endpoints")
public class StudentRestController {
	
	
	StudentService studentService;
	
	@PostMapping("/add")
	@Operation(summary = "Add New Student", description="This route adds new student")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Student Added Successfully"),
	  @ApiResponse(responseCode = "428", description = "Invalid payload supplied"),
	  @ApiResponse(responseCode = "501", description = "An error occured"),
	  @ApiResponse(responseCode = "400", description = "Bad request")})
	public ResponseEntity<?> addStudent(@RequestBody StudentDTO studentDTO){
		
		int retValue = studentService.addStudent(studentDTO);
		Response response = new Response();
		if (retValue == 3) {
			response.setStatus("EMPTY_TEXTFIELDS");
			response.setMessage("Please Fill Up Required Empty Fields");
			return new ResponseEntity<>(response,HttpStatus.EXPECTATION_FAILED);
		}
		else if (retValue == 2) {
			response.setStatus("ALREADY_EXISTS");
			response.setMessage("Student Already Exist");
			return new ResponseEntity<>(response,HttpStatus.EXPECTATION_FAILED);
		}
		response.setStatus("SUCCESS");
		response.setPayload(retValue);
		response.setMessage("Student Added Successfully");
		return new ResponseEntity<>(retValue, HttpStatus.OK);
		
	}
	
	@PostMapping("/update")
	@Operation(summary = "Update Student", description="This route updates student record")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Student Updated Successfully"),
	  @ApiResponse(responseCode = "428", description = "Invalid payload supplied"),
	  @ApiResponse(responseCode = "404", description = "Invalid ID supplied"),
	  @ApiResponse(responseCode = "501", description = "An error occured"),
	  @ApiResponse(responseCode = "400", description = "Bad request")})
	public ResponseEntity<?> updateStudent(@RequestBody StuDTO student){
		int retValue = studentService.updateStudent(student);
		Response response = new Response();
		if (retValue == 2) {
			response.setStatus("NOT_FOUND");
			response.setMessage("Invalid Resource ID supplied");
			return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		}
		else if (retValue == 1) {
			response.setStatus("SUCCESS");
			response.setPayload(retValue);
			response.setMessage("Student Updated Successfully");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Get Student", description="This route gets student record by id")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Student fetched Successfully"),
	  @ApiResponse(responseCode = "428", description = "Invalid payload supplied"),
	  @ApiResponse(responseCode = "404", description = "Invalid ID supplied"),
	  @ApiResponse(responseCode = "501", description = "An error occured"),
	  @ApiResponse(responseCode = "400", description = "Bad request")})
	public ResponseEntity<?> getStudent(@PathVariable int id){
		StudentRecord retValue = studentService.getStudent(id);
		Response response = new Response();
		if (retValue == null) {
			response.setStatus("NOT_FOUND");
			response.setMessage("Invalid Resource ID supplied");
			return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		}
		
		response.setStatus("SUCCESS");
		response.setPayload(retValue);
		response.setMessage("Student Fetched Successfully");
		return new ResponseEntity<>(response, HttpStatus.OK);
	
	}
	
	
	@GetMapping("/students/department/{deptID}")
	@Operation(summary = "Fetch Students", description="This route fetches all student record")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Students Fetched Successfully"),
	  @ApiResponse(responseCode = "428", description = "Invalid payload supplied"),
	  @ApiResponse(responseCode = "404", description = "Invalid ID supplied"),
	  @ApiResponse(responseCode = "501", description = "An error occured"),
	  @ApiResponse(responseCode = "400", description = "Bad request")})
	public ResponseEntity<?> getStudentsInDepartment(@PathVariable int deptID){
		List<Student> retValue =  studentService.getStudentsByDepartmentID(deptID);
		Response response = new Response();
		response.setStatus("SUCCESS");
		response.setPayload(retValue);
		response.setMessage("Fetched all Students Successfully");
		return new ResponseEntity<>(response, HttpStatus.OK);
		}
	
	@GetMapping("/students/courses/{studentID}")
	@Operation(summary = "Fetch Students", description="This route fetches all student record")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Students Fetched Successfully"),
	  @ApiResponse(responseCode = "428", description = "Invalid payload supplied"),
	  @ApiResponse(responseCode = "404", description = "Invalid ID supplied"),
	  @ApiResponse(responseCode = "501", description = "An error occured"),
	  @ApiResponse(responseCode = "400", description = "Bad request")})
	public ResponseEntity<?> getCoursesByStudentID(@PathVariable int studentID){
		List<Courses>  retValue =  studentService.getCoursesByStudentID(studentID);
		Response response = new Response();
		response.setStatus("SUCCESS");
		response.setPayload(retValue);
		response.setMessage("Fetched all Students Successfully");
		return new ResponseEntity<>(response, HttpStatus.OK);
		}
	
	
}
