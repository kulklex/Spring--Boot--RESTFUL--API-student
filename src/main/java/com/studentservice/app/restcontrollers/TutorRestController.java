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
import org.springframework.web.bind.annotation.RestController;

import com.studentservice.app.dtos.Response;
import com.studentservice.app.dtos.TutorDTO;
import com.studentservice.app.entity.Tutor;
import com.studentservice.app.service.TutorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*")
@RestController
@Tag(name="Tutors Endpoints", description="This exposes tutor api endpoints")
public class TutorRestController {

	@Autowired
	TutorService tutorService;
	
	
	@PostMapping("/addTutor")
	@Operation(summary = "Add New Tutor", description="This route adds new Tutor")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Tutor Added Successfully"),
	  @ApiResponse(responseCode = "428", description = "Invalid payload supplied"),
	  @ApiResponse(responseCode = "501", description = "An error occured"),
	  @ApiResponse(responseCode = "400", description = "Bad request")})
	public ResponseEntity<?> addTutor(@RequestBody TutorDTO tutordto){
		Tutor returnValue = tutorService.addTutor(tutordto);
		Response response = new Response();
		if (returnValue == null) {
			response.setStatus("NOT_FOUND");
			response.setMessage("Invalid Resource ID supplied");
			return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		}
		response.setStatus("SUCCESS");
		response.setPayload(returnValue);
		response.setMessage("Tutor Added Successfully");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/updateTutor")
	@Operation(summary = "Update Tutor", description="This route updates teachers record")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Tutor Updated Successfully"),
	  @ApiResponse(responseCode = "428", description = "Invalid payload supplied"),
	  @ApiResponse(responseCode = "404", description = "Invalid ID supplied"),
	  @ApiResponse(responseCode = "501", description = "An error occured"),
	  @ApiResponse(responseCode = "400", description = "Bad request")})
	public ResponseEntity<?> updateTutor(@RequestBody Tutor tutor){
		Tutor returnValue = tutorService.updateTutor(tutor);
		Response response = new Response();
		if (returnValue == null) {
			response.setStatus("NOT_FOUND");
			response.setMessage("Invalid Resource ID supplied");
			return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		}
		response.setStatus("SUCCESS");
		response.setPayload(returnValue);
		response.setMessage("Tutor Added Successfully");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/tutor/{id}")
	@Operation(summary = "Get Tutor by ID", description="This route gets tutor record by id")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Tutor fetched Successfully"),
	  @ApiResponse(responseCode = "428", description = "Invalid payload supplied"),
	  @ApiResponse(responseCode = "404", description = "Invalid ID supplied"),
	  @ApiResponse(responseCode = "501", description = "An error occured"),
	  @ApiResponse(responseCode = "400", description = "Bad request")})
	public ResponseEntity<?> getTutor(@PathVariable int id){
		Tutor retValue = tutorService.getTutor(id); 
		Response response = new Response();
		if (retValue == null) {
			response.setStatus("NOT_FOUND");
			response.setMessage("Invalid Resource ID supplied");
			return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		}
		
		response.setStatus("SUCCESS");
		response.setPayload(retValue);
		response.setMessage("Tutor Fetched Successfully");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	@GetMapping("/allTutors")
	@Operation(summary = "Fetch Tutors", description="This route fetches all teachers record")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Teachers Fetched Successfully"),
	  @ApiResponse(responseCode = "428", description = "Invalid payload supplied"),
	  @ApiResponse(responseCode = "404", description = "Invalid ID supplied"),
	  @ApiResponse(responseCode = "501", description = "An error occured"),
	  @ApiResponse(responseCode = "400", description = "Bad request")})
	public ResponseEntity<?> getAllTutors(){
		List<Tutor> retValue =  tutorService.getAllTutors();
		Response response = new Response();
		response.setStatus("SUCCESS");
		response.setPayload(retValue);
		response.setMessage("Fetched all Teachers Successfully");
		return new ResponseEntity<>(response, HttpStatus.OK);
		}
	
	
	@DeleteMapping("/deleteTutor/{id}")
	@Operation(summary = "Tutor School", description="This route deletes a tutor record")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Tutor Deleted Successfully"),
	  @ApiResponse(responseCode = "428", description = "Invalid payload supplied"),
	  @ApiResponse(responseCode = "404", description = "Invalid ID supplied"),
	  @ApiResponse(responseCode = "501", description = "An error occured"),
	  @ApiResponse(responseCode = "400", description = "Bad request")})
public ResponseEntity<?> deleteTutor(@PathVariable int id) {
		
		int retValue = tutorService.deleteTutor(id);
		Response response = new Response();
		
		if (retValue == 1) {
			response.setStatus("SUCCESS");
			response.setPayload(retValue);
			response.setMessage("Tutor Deleted Successfully");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
		
	}
}
