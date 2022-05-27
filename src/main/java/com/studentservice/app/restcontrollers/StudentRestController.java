package com.studentservice.app.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.studentservice.app.dtos.Response;
import com.studentservice.app.dtos.StuDTO;
import com.studentservice.app.dtos.StudentDTO;
import com.studentservice.app.entity.Student;
import com.studentservice.app.service.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.*;

@CrossOrigin(origins = "*")
@RestController
@Tag(name="Students Endpoints", description="This exposes student api endpoints")
public class StudentRestController {
	
	@Autowired
	StudentService studentService;
	
	@PostMapping("/add")
	@Operation(summary = "Add New Student", description="This route adds new student")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Student Added Successfully"),
	  @ApiResponse(responseCode = "428", description = "Invalid payload supplied"),
	  @ApiResponse(responseCode = "501", description = "An error occured"),
	  @ApiResponse(responseCode = "400", description = "Bad request")})
	public ResponseEntity<?> addStudent(@RequestBody StudentDTO studentDTO){
		
		Student retValue = studentService.addStudent(studentDTO);
		Response response = new Response();
		if (retValue == null) {
			response.setStatus("EMPTY_TEXTFIELDS");
			response.setMessage("Please Fill Up Required Empty Fields");
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
	public ResponseEntity<?> updateStudent(@RequestBody Student student){
		Student retValue = studentService.updateStudent(student);
		Response response = new Response();
		if (retValue == null) {
			response.setStatus("NOT_FOUND");
			response.setMessage("Invalid Resource ID supplied");
			return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		}
		
		response.setStatus("SUCCESS");
		response.setPayload(retValue);
		response.setMessage("Student Updated Successfully");
		return new ResponseEntity<>(response, HttpStatus.OK);
	
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
		Student retValue = studentService.getStudent(id);
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
	
	
	@GetMapping("/fetchAll")
	@Operation(summary = "Fetch Students", description="This route fetches all student record")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Students Fetched Successfully"),
	  @ApiResponse(responseCode = "428", description = "Invalid payload supplied"),
	  @ApiResponse(responseCode = "404", description = "Invalid ID supplied"),
	  @ApiResponse(responseCode = "501", description = "An error occured"),
	  @ApiResponse(responseCode = "400", description = "Bad request")})
	public ResponseEntity<?> getAllStudents(){
		List<Student> retValue =  studentService.getAllStudents();
		Response response = new Response();
		response.setStatus("SUCCESS");
		response.setPayload(retValue);
		response.setMessage("Fetched all Students Successfully");
		return new ResponseEntity<>(response, HttpStatus.OK);
		}
	
	@DeleteMapping("/deleteByID/{id}")
	@Operation(summary = "Delete Student", description="This route deletes student record")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Student Deleted Successfully"),
	  @ApiResponse(responseCode = "428", description = "Invalid payload supplied"),
	  @ApiResponse(responseCode = "404", description = "Invalid ID supplied"),
	  @ApiResponse(responseCode = "501", description = "An error occured"),
	  @ApiResponse(responseCode = "400", description = "Bad request")})
	public ResponseEntity<?> deleteStudent(@PathVariable int id) {
		
		int retValue = studentService.deleteStudent(id);
		Response response = new Response();
		
		if (retValue == 1) {
			response.setStatus("SUCCESS");
			response.setPayload(retValue);
			response.setMessage("Student Deleted Successfully");
			return new ResponseEntity<>(retValue, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
		
	}
	
	@PutMapping("/update/{userID}")
	@Operation(summary = "Update Student", description="This route updates student record")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Student Updated Successfully"),
	  @ApiResponse(responseCode = "428", description = "Invalid payload supplied"),
	  @ApiResponse(responseCode = "404", description = "Invalid ID supplied"),
	  @ApiResponse(responseCode = "501", description = "An error occured"),
	  @ApiResponse(responseCode = "400", description = "Bad request")})
	public ResponseEntity<?> updateStudentByID(@PathVariable int userID,@RequestBody StuDTO student){
		Student retValue = studentService.updateFirstName(userID, student);
		Response response = new Response();
		if (retValue == null) {
			response.setStatus("NOT_FOUND");
			response.setMessage("Invalid Resource ID supplied");
			return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		}
		
		response.setStatus("SUCCESS");
		response.setPayload(retValue);
		response.setMessage("Student Updated Successfully");
		return new ResponseEntity<>(response, HttpStatus.OK);
	
	}

}
