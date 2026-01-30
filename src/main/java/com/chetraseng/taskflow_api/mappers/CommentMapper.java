package com.chetraseng.taskflow_api.mappers;

import org.mapstruct.Mapper;

import com.chetraseng.taskflow_api.dto.responses.CommentResponse;
import com.chetraseng.taskflow_api.models.CommentModel;

@Mapper
public interface CommentMapper {
  CommentResponse toCommentResponse(CommentModel comment);
}
