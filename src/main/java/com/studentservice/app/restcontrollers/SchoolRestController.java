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
import com.studentservice.app.dtos.SchoolDTO;
import com.studentservice.app.entity.School;
import com.studentservice.app.service.SchoolService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*")
@RestController
@Tag(name="Schools Endpoints", description="This exposes schools api endpoints")
public class SchoolRestController {
	
	@Autowired
	SchoolService schoolService;
	
	@PostMapping("/addSchool")
	@Operation(summary = "Add New School", description="This route adds new school")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "School Added Successfully"),
	  @ApiResponse(responseCode = "428", description = "Invalid payload supplied"),
	  @ApiResponse(responseCode = "501", description = "An error occured"),
	  @ApiResponse(responseCode = "400", description = "Bad request")})
	public ResponseEntity<?> addSchool(@RequestBody SchoolDTO schooldto){
		School returnValue = schoolService.addSchool(schooldto);
		Response response = new Response();
		if (returnValue == null) {
			response.setStatus("NOT_FOUND");
			response.setMessage("Invalid Resource ID supplied");
			return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		}
		response.setStatus("SUCCESS");
		response.setPayload(returnValue);
		response.setMessage("School Added Successfully");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/updateSchool")
	@Operation(summary = "Update School", description="This route updates school record")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Student Updated Successfully"),
	  @ApiResponse(responseCode = "428", description = "Invalid payload supplied"),
	  @ApiResponse(responseCode = "404", description = "Invalid ID supplied"),
	  @ApiResponse(responseCode = "501", description = "An error occured"),
	  @ApiResponse(responseCode = "400", description = "Bad request")})
	public ResponseEntity<?> updateSchool(@RequestBody School schooldto){
		School returnValue = schoolService.updateSchool(schooldto);
		Response response = new Response();
		if (returnValue == null) {
			response.setStatus("NOT_FOUND");
			response.setMessage("Invalid Resource ID supplied");
			return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		}
		response.setStatus("SUCCESS");
		response.setPayload(returnValue);
		response.setMessage("School updated Successfully");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/school/{id}")
	@Operation(summary = "Get School", description="This route gets school record by id")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "School fetched Successfully"),
	  @ApiResponse(responseCode = "428", description = "Invalid payload supplied"),
	  @ApiResponse(responseCode = "404", description = "Invalid ID supplied"),
	  @ApiResponse(responseCode = "501", description = "An error occured"),
	  @ApiResponse(responseCode = "400", description = "Bad request")})
	public ResponseEntity<?> findOneSchool(@PathVariable int id){
		School retValue = schoolService.findOneSchool(id);
		Response response = new Response();
		if (retValue == null) {
			response.setStatus("NOT_FOUND");
			response.setMessage("Invalid Resource ID supplied");
			return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		}
		
		response.setStatus("SUCCESS");
		response.setPayload(retValue);
		response.setMessage("School Fetched Successfully");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/allSchools")
	@Operation(summary = "Fetch Schools", description="This route fetches all student record")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Schools Fetched Successfully"),
	  @ApiResponse(responseCode = "428", description = "Invalid payload supplied"),
	  @ApiResponse(responseCode = "404", description = "Invalid ID supplied"),
	  @ApiResponse(responseCode = "501", description = "An error occured"),
	  @ApiResponse(responseCode = "400", description = "Bad request")})
	public ResponseEntity<?> getAllAvaliableSchools(){
		List<School> retValue =  schoolService.getAllAvaliableSchools();
		Response response = new Response();
		response.setStatus("SUCCESS");
		response.setPayload(retValue);
		response.setMessage("Fetched all Schools Successfully");
		return new ResponseEntity<>(response, HttpStatus.OK);
		}
	
	@DeleteMapping("/deleteSchool/{id}")
	@Operation(summary = "Delete School", description="This route deletes school record")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "School Deleted Successfully"),
	  @ApiResponse(responseCode = "428", description = "Invalid payload supplied"),
	  @ApiResponse(responseCode = "404", description = "Invalid ID supplied"),
	  @ApiResponse(responseCode = "501", description = "An error occured"),
	  @ApiResponse(responseCode = "400", description = "Bad request")})
	public ResponseEntity<?> deleteSchool(@PathVariable int id) {
		
		int retValue = schoolService.deleteSchool(id);
		Response response = new Response();
		
		if (retValue == 1) {
			response.setStatus("SUCCESS");
			response.setPayload(retValue);
			response.setMessage("School Deleted Successfully");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
		
	}
}
