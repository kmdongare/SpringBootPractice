package com.flower.controller;

import com.flower.exception.UserNotFoundException;
import com.flower.exception.UserServiceException;
import com.flower.model.EndpointDetails;
import com.flower.model.UserDetails;
import com.flower.service.UserService;
import com.flower.util.Constant;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/1008Flower/user")
public class UserController {

    @Autowired
    UserService userService;

    private static Map<String, Integer> counterMap = new HashMap<>();
    private static int updateGlobalEndpointCounter;
    private static int updateUniqueUserEndpointCounter;
    private static int updateUserEndpointCounter;
    private static int updateUserListEndpointCounter;


    @GetMapping("/uniqueUserIds")
    public ResponseEntity<List<UserDetails>> processUniqueUserDetails() throws UserServiceException {
        this.updateEndpointCounter(Constant.UNIQUE_USER_ENDPINT);
        counterMap.put(Constant.UNIQUE_USER_ENDPINT, updateUniqueUserEndpointCounter);
        List<UserDetails> userDetails = userService.findUniqueUsers();
        if (CollectionUtils.isNotEmpty(userDetails)) {
            return new ResponseEntity(userDetails, HttpStatus.OK);
        } else {
            throw new UserNotFoundException("User Details is Not Found");
        }
    }

        @GetMapping("/countEndpoints")
    public ResponseEntity<List<EndpointDetails>> countEndpoint() throws UserServiceException {
        EndpointDetails endpintDetails = userService.updateEndpintDetails(counterMap, updateGlobalEndpointCounter);
        return new ResponseEntity(endpintDetails, HttpStatus.OK);
    }

    @PutMapping("/updateUserDetails")
    public ResponseEntity<UserDetails> updateSpecificUserDetails(@RequestBody List<UserDetails> userDetails) throws UserServiceException {
        this.updateEndpointCounter(Constant.UPDATE_SPECIFIC_USER_ENDPINT);
        counterMap.put(Constant.UPDATE_SPECIFIC_USER_ENDPINT, updateGlobalEndpointCounter - updateUserEndpointCounter);
        List<UserDetails> modifiedUser = userService.modifySpecificUser(userDetails);
        if (CollectionUtils.isNotEmpty(modifiedUser)) {
            return new ResponseEntity(modifiedUser, HttpStatus.OK);
        } else {
            throw new UserNotFoundException("User Details is Not Found");
        }
    }

    @PutMapping("/updateUserList")
    public ResponseEntity updateUserDetails(@RequestBody List<UserDetails> userDetails) throws UserServiceException {
        this.updateEndpointCounter(Constant.UPDATE_USERS_ENDPINT);
        counterMap.put(Constant.UPDATE_USERS_ENDPINT, updateGlobalEndpointCounter - updateUserListEndpointCounter);
        List<UserDetails> modifiedUserList = userService.modifySpecificUserList(userDetails);
        if (CollectionUtils.isNotEmpty(modifiedUserList)) {
            return new ResponseEntity(modifiedUserList, HttpStatus.OK);
        } else {
            throw new UserNotFoundException("User Details is Not Found");
        }
    }

    private void updateEndpointCounter(String endPointName) {
        updateGlobalEndpointCounter = +1;
        switch (endPointName) {
            case Constant.UNIQUE_USER_ENDPINT:
                updateUniqueUserEndpointCounter++;
                break;
            case Constant.UPDATE_SPECIFIC_USER_ENDPINT:
                updateUserEndpointCounter++;
                break;
            case Constant.UPDATE_USERS_ENDPINT:
                updateUserListEndpointCounter++;
                break;
        }
    }
}
