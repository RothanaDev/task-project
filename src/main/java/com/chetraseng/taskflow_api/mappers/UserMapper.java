package com.chetraseng.taskflow_api.mappers;

import com.chetraseng.taskflow_api.dto.requests.UserRequest;
import com.chetraseng.taskflow_api.dto.responses.AuthResponse;
import com.chetraseng.taskflow_api.dto.responses.UserResponse;
import com.chetraseng.taskflow_api.models.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    UserResponse toUserResponse(UserModel user);
    List<UserResponse> toUserResponseList(List<UserModel > users);
    UserModel  fromUserCreateRequest(UserRequest userRequest);
}
