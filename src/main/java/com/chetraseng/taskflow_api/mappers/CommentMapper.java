package com.chetraseng.taskflow_api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

import com.chetraseng.taskflow_api.dto.responses.CommentResponse;
import com.chetraseng.taskflow_api.models.CommentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface CommentMapper {
  CommentResponse toCommentResponse(CommentModel comment);
}
