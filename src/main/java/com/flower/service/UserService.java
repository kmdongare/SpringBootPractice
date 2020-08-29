package com.flower.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.flower.config.UserProperties;
import com.flower.exception.UserServiceException;
import com.flower.model.EndPoint;
import com.flower.model.EndpointDetails;
import com.flower.model.UserDetails;
import com.flower.util.Constant;
import com.flower.util.FlowerCommonUtility;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    FlowerCommonUtility flowerCommonUtility;

    @Autowired
    private UserProperties userProperties;

    @Autowired
    private Constant constant;

    public UserService(UserProperties userProperties, Constant constant) {
        this.userProperties = userProperties;
        this.constant = constant;
    }

    public List<UserDetails> findUniqueUsers() throws UserServiceException {
        List<UserDetails> distinctElements = null;
        List<UserDetails> userDetailsList = flowerCommonUtility.getUserObjectFromJson();
        if (CollectionUtils.isNotEmpty(userDetailsList)) {
            distinctElements = userDetailsList.stream()
                    .filter(FlowerCommonUtility.distinctByKey(UserDetails::getUserId))
                    .collect(Collectors.toList());
        }
        return distinctElements;
    }

    public List<UserDetails> modifySpecificUser(List<UserDetails> userList) throws UserServiceException {
        List<UserDetails> updateUserList = null;
        /** we can read json from Path as well*/
        if (CollectionUtils.isNotEmpty(userList)) {
            updateUserList = userList.stream().
                    map((user) -> {
                        if (user.getId() == userProperties.getModifiedUserId()) {
                            user.setTitle(userProperties.getTitle());
                            user.setBody(userProperties.getTitle());
                        }
                        return user;
                    }).collect(Collectors.toList());
        }
        return updateUserList;
    }

    public List<UserDetails> modifySpecificUserList(List<UserDetails> modifiedUserList) throws UserServiceException {
        List<UserDetails> updatedUserList;
        //populate userdata from existing json payload from path.
        List<UserDetails> existingUserDetailsList = flowerCommonUtility.getUserObjectFromJson();

        //following code is use to update user data with existing user data.
        if (CollectionUtils.isNotEmpty(modifiedUserList)) {
            updatedUserList = modifiedUserList.stream()
                    .filter(existUser -> existingUserDetailsList.stream()
                            .noneMatch(newUser ->
                                    newUser.getTitle().equals(existUser.getTitle()) &&
                                            newUser.getBody().equals(existUser.getBody())))
                    .collect(Collectors.toList());
        } else {
            throw new UserServiceException("Existing user list is empty.");
        }
        return updatedUserList;
    }

    public EndpointDetails updateEndpintDetails(Map<String, Integer> counterMap, int globalEndpintCounter) {
        EndpointDetails endpointsDetails = new EndpointDetails();
        List<EndPoint> endPoints = new ArrayList<>();
        if (!counterMap.isEmpty()) {
            if (counterMap.get(constant.UNIQUE_USER_ENDPINT) != null) {
                endPoints.add(new EndPoint(counterMap.get(constant.UNIQUE_USER_ENDPINT), constant.UNIQUE_USER_ENDPINT, "find unique user lsit"));
            }
            if (counterMap.get(constant.UPDATE_USERS_ENDPINT) != null) {
                endPoints.add(new EndPoint(counterMap.get(constant.UPDATE_USERS_ENDPINT), constant.UPDATE_USERS_ENDPINT, "update given user list"));
            }
            if (counterMap.get(constant.UPDATE_SPECIFIC_USER_ENDPINT) != null) {
                endPoints.add(new EndPoint(counterMap.get(constant.UPDATE_SPECIFIC_USER_ENDPINT), constant.UPDATE_SPECIFIC_USER_ENDPINT, "update specific user"));
            }
            endpointsDetails.setTotalEndpoint(globalEndpintCounter);
            endpointsDetails.setEndPoints(endPoints);
        }
        return endpointsDetails;
    }
}
