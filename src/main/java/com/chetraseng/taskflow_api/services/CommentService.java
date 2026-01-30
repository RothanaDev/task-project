package com.chetraseng.taskflow_api.services;

import com.chetraseng.taskflow_api.dto.requests.AddCommentRequest;
import com.chetraseng.taskflow_api.dto.responses.CommentResponse;

public interface CommentService {
  CommentResponse addComment(Long taskId, AddCommentRequest request);
}
