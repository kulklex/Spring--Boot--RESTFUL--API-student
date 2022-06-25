package com.studentservice.app.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.studentservice.app.dtos.CoursesDTO;
import com.studentservice.app.dtos.Response;
import com.studentservice.app.services.CourseService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*")
@RestController
@Tag(name="Courses Endpoints", description="This exposes courses api endpoints")
public class CoursesRestController {

	@Autowired
	CourseService courseService;
	
	@PostMapping("/addCourse")
	@Operation(summary = "Add New Course", description="This route adds a new course")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Course Added Successfully"),
	  @ApiResponse(responseCode = "428", description = "Invalid payload supplied"),
	  @ApiResponse(responseCode = "501", description = "An error occured"),
	  @ApiResponse(responseCode = "400", description = "Bad request")})
	public ResponseEntity<?> addCourse(@RequestBody CoursesDTO courseDTO){
		int returnValue = courseService.addCourse(courseDTO);
		Response response = new Response();
		if(returnValue == 3) {
			response.setStatus("EMPTY_TEXTFIELDS");
			response.setMessage("Please Fill Up Required Textfields");
			return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
		} else if (returnValue == 2) {
			response.setStatus("ALREADY_EXISTS");
			response.setMessage("CourseName or CourseCode Already Exists");
			return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
		} else if (returnValue == 1){
			response.setStatus("SUCCESS");
			response.setPayload(returnValue);
			response.setMessage("Course Added Successfully");
			return new ResponseEntity<>(returnValue, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
