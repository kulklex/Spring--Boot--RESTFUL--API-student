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
import com.studentservice.app.dtos.SubjectDTO;
import com.studentservice.app.entity.Subject;
import com.studentservice.app.service.SubjectService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*")
@RestController
@Tag(name="Subjects Endpoints", description="This exposes subjects in a school api endpoints")
public class SubjectsController {

	
	@Autowired
	SubjectService subjectService;
	
	@PostMapping("/addSubject")
	@Operation(summary = "Add New Subject", description="This route adds new subject")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Subject Added Successfully"),
	  @ApiResponse(responseCode = "428", description = "Invalid payload supplied"),
	  @ApiResponse(responseCode = "501", description = "An error occured"),
	  @ApiResponse(responseCode = "400", description = "Bad request")})
	public ResponseEntity<?> addStudent(@RequestBody SubjectDTO subjectDTO){
		
		Subject retValue = subjectService.addSubject(subjectDTO);
		Response response = new Response();
		if (retValue == null) {
			response.setStatus("EMPTY_TEXTFIELDS");
			response.setMessage("Please Fill Up Required Empty Fields");
			return new ResponseEntity<>(response,HttpStatus.EXPECTATION_FAILED);
		}
		response.setStatus("SUCCESS");
		response.setPayload(retValue);
		response.setMessage("Subject Added Successfully");
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}
	
	
	@PostMapping("/updateSubject")
	@Operation(summary = "Update Subject", description="This route updates subject record")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Subject Updated Successfully"),
	  @ApiResponse(responseCode = "428", description = "Invalid payload supplied"),
	  @ApiResponse(responseCode = "404", description = "Invalid ID supplied"),
	  @ApiResponse(responseCode = "501", description = "An error occured"),
	  @ApiResponse(responseCode = "400", description = "Bad request")})
	public ResponseEntity<?> updateStudent(@RequestBody Subject subjectDTO){
		Subject retValue = subjectService.updateSubject(subjectDTO);
		Response response = new Response();
		if (retValue == null) {
			response.setStatus("EMPTY_TEXTFIELDS");
			response.setMessage("Please Fill Up Required Empty Fields");
			return new ResponseEntity<>(response,HttpStatus.EXPECTATION_FAILED);
		}
		response.setStatus("SUCCESS");
		response.setPayload(retValue);
		response.setMessage("Subject Updated Successfully");
		return new ResponseEntity<>(response, HttpStatus.OK);	
	}
	
	@GetMapping("/subject/{id}")
	@Operation(summary = "Get Subject", description="This route gets subject record by id")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Subject fetched Successfully"),
	  @ApiResponse(responseCode = "428", description = "Invalid payload supplied"),
	  @ApiResponse(responseCode = "404", description = "Invalid ID supplied"),
	  @ApiResponse(responseCode = "501", description = "An error occured"),
	  @ApiResponse(responseCode = "400", description = "Bad request")})
	public ResponseEntity<?> getSubjects(@PathVariable int id){
		Subject retValue = subjectService.getSubjects(id);
		Response response = new Response();
		if (retValue == null) {
			response.setStatus("NOT_FOUND");
			response.setMessage("Invalid Resource ID supplied");
			return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		}
		
		response.setStatus("SUCCESS");
		response.setPayload(retValue);
		response.setMessage("Subject Fetched Successfully");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/allSubjects")
	@Operation(summary = "Fetch Students", description="This route fetches all student record")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Students Fetched Successfully"),
	  @ApiResponse(responseCode = "428", description = "Invalid payload supplied"),
	  @ApiResponse(responseCode = "404", description = "Invalid ID supplied"),
	  @ApiResponse(responseCode = "501", description = "An error occured"),
	  @ApiResponse(responseCode = "400", description = "Bad request")})
	public ResponseEntity<?> getAllSubjects(){
		List<Subject> retValue =  subjectService.getAllSubjects();
		Response response = new Response();
		response.setStatus("SUCCESS");
		response.setPayload(retValue);
		response.setMessage("Fetched all Subjects Successfully");
		return new ResponseEntity<>(response, HttpStatus.OK);
		}
	
	@DeleteMapping("/deleteSubject/{id}")
	@Operation(summary = "Delete Subject", description="This route deletes subject record")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Subject Deleted Successfully"),
	  @ApiResponse(responseCode = "428", description = "Invalid payload supplied"),
	  @ApiResponse(responseCode = "404", description = "Invalid ID supplied"),
	  @ApiResponse(responseCode = "501", description = "An error occured"),
	  @ApiResponse(responseCode = "400", description = "Bad request")})
	public ResponseEntity<?> deleteSubject(@PathVariable int id) {
		
		int retValue = subjectService.deleteSubject(id);
		Response response = new Response();
		
		if (retValue == 1) {
			response.setStatus("SUCCESS");
			response.setPayload(retValue);
			response.setMessage("Subject Deleted Successfully");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}
	
	
	
	
	@PutMapping("/{subjectId}/students/{studentId}")
	@Operation(summary = "Assigning a Subject to a Student", description="This route assignes a subject to a student")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Subject assigned to Student Successfully"),
	  @ApiResponse(responseCode = "428", description = "Invalid payload supplied"),
	  @ApiResponse(responseCode = "404", description = "Invalid ID supplied"),
	  @ApiResponse(responseCode = "501", description = "An error occured"),
	  @ApiResponse(responseCode = "400", description = "Bad request")})
		public ResponseEntity<?> enrollStudentToSubject(@PathVariable int subjectId, @PathVariable int studentId){
		Subject returnValue = subjectService.enrollStudentToSubject(subjectId, studentId);
		Response response = new Response();
		response.setStatus("SUCCESS");
		response.setPayload(returnValue);
		response.setMessage("Assigning a Subject to a Student was Successfull!");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
}
