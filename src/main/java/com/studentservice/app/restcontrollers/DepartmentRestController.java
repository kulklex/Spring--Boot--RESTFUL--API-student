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

import com.studentservice.app.dtos.DepartmentDTO;
import com.studentservice.app.dtos.Response;
import com.studentservice.app.entity.Courses;
import com.studentservice.app.services.DepartmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*")
@RestController
@Tag(name="Department Endpoints", description="This exposes department api endpoints")
public class DepartmentRestController {

	@Autowired
	DepartmentService departmentService;
	
	@PostMapping("/addDepartment")
	@Operation(summary = "Add New Department", description="This route adds a new department")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Department Added Successfully"),
	  @ApiResponse(responseCode = "428", description = "Invalid payload supplied"),
	  @ApiResponse(responseCode = "501", description = "An error occured"),
	  @ApiResponse(responseCode = "400", description = "Bad request")})
	public ResponseEntity<?> addDepartment(@RequestBody DepartmentDTO departmentDTO){
		int returnValue = departmentService.addDepartment(departmentDTO);	
		Response response = new Response();
		if(returnValue == 3) {
			response.setStatus("EMPTY_TEXTFIELDS");
			response.setMessage("Please Fill Up Required Textfields");
			return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
		} else if (returnValue == 2) {
			response.setStatus("ALREADY_EXISTS");
			response.setMessage("Department Already Exists");
			return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
		} else if (returnValue == 1){
			response.setStatus("SUCCESS");
			response.setMessage("Department Added Successfully");
			return new ResponseEntity<>(returnValue, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/department/courses/{deptID}/{courseIDs}")
	@Operation(summary = "Add Courses To Department", description="This route adds all courses associated with department")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Courses Added Successfully"),
	  @ApiResponse(responseCode = "428", description = "Invalid payload supplied"),
	  @ApiResponse(responseCode = "501", description = "An error occured"),
	  @ApiResponse(responseCode = "400", description = "Bad request")})
	public ResponseEntity<?> addCoursesToDepartment(@PathVariable int deptID, @PathVariable List<String> courseIDs){
		int returnValue = departmentService.addCoursesToDepartment(deptID, courseIDs);	
		Response response = new Response();
		if(returnValue == 1) {
			response.setStatus("SUCCESS");
			response.setMessage("Department Added Successfully");
			return new ResponseEntity<>(returnValue, HttpStatus.OK);
		} else if (returnValue == 2) {
			response.setStatus("NOT_FOUND");
			response.setMessage("Department Resource Not Found! INVALID ID SUPPLIED");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		} else {
			
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/department/courses/{deptID}")
	@Operation(summary = "Get Courses In Department", description="This route fetches all courses associated with department")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Courses Fetched Successfully"),
	  @ApiResponse(responseCode = "428", description = "Invalid payload supplied"),
	  @ApiResponse(responseCode = "501", description = "An error occured"),
	  @ApiResponse(responseCode = "400", description = "Bad request")})
	public ResponseEntity<?> getCoursesInDepartment(@PathVariable int deptID){
		List<Courses> returnValue = departmentService.fetchAllCoursesInDepartment(deptID);	
		Response response = new Response();
		response.setStatus("SUCCESS");
		response.setMessage("Courses Fetched Successfully");
		response.setPayload(returnValue);
		return new ResponseEntity<>(returnValue, HttpStatus.OK);
		
	}
	
}
