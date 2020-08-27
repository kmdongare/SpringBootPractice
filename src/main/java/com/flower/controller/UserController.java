package com.flower.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flower.model.UserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/1008FLower/user")
public class UserController {

	@PostMapping("/userIds")
	public ResponseEntity<List<UserDetails>> processUniqueUserDetails(@RequestBody List<UserDetails> userDetails) throws JsonProcessingException {

			//return new ResponseEntity(userDetails, HttpStatus.OK);
			//Object mapper instance
			ObjectMapper mapper = new ObjectMapper();
			String json = "";
			List<UserDetails> userDetailsList = (List<UserDetails>) mapper.readValue(json, UserDetails.class);
			if(CollectionUtils.isEmpty(userDetailsList)){

		}




		return new ResponseEntity(userDetails, HttpStatus.OK);
	}

	@PutMapping("/{id}/updateUserDetails")
	public ResponseEntity<UserDetails> updateSpecificUserDetails(@RequestBody List<UserDetails> userDetails) {
     /*   System.out.print(cricketer);
        Cricketer cCricketer = new Cricketer();
        cCricketer.setCountry(cricketer.getCountry());x
        cCricketer.setName(cricketer.getName());
        cCricketer.setHighestScore(cricketer.getHighestScore());
        cricketerRepository.save(cCricketer);*/
		return new ResponseEntity<UserDetails>((UserDetails) userDetails, HttpStatus.OK);
	}

	@PostMapping("/updateUserDetails")
	public ResponseEntity<UserDetails> updateUserDetails(@RequestBody UserDetails userDetails) {
     /*   System.out.print(cricketer);
        Cricketer cCricketer = new Cricketer();
        cCricketer.setCountry(cricketer.getCountry());x
        cCricketer.setName(cricketer.getName());
        cCricketer.setHighestScore(cricketer.getHighestScore());
        cricketerRepository.save(cCricketer);*/
		return new ResponseEntity<UserDetails>(userDetails, HttpStatus.OK);
	}
	
}
