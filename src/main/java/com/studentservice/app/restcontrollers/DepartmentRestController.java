package com.studentservice.app.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.studentservice.app.dtos.DepartmentDTO;
import com.studentservice.app.dtos.Response;
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
	
	@PostMapping("addDepartment")
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
		} else {
			response.setStatus("SUCCESS");
			response.setPayload(returnValue);
			response.setMessage("Department Added Successfully");
			return new ResponseEntity<>(returnValue, HttpStatus.OK);
		}
	}
}
