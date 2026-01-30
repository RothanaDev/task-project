package com.chetraseng.taskflow_api.services.impl;

import org.springframework.stereotype.Service;

import com.chetraseng.taskflow_api.dto.requests.AddCommentRequest;
import com.chetraseng.taskflow_api.dto.responses.CommentResponse;
import com.chetraseng.taskflow_api.mappers.CommentMapper;
import com.chetraseng.taskflow_api.models.CommentModel;
import com.chetraseng.taskflow_api.models.TaskModel;
import com.chetraseng.taskflow_api.repositories.CommentRepository;
import com.chetraseng.taskflow_api.repositories.TaskRepository;
import com.chetraseng.taskflow_api.services.CommentService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
  private final CommentRepository commentRepository;
  private final TaskRepository taskRepository;
  private final CommentMapper commentMapper;

  @Override
  public CommentResponse addComment(Long taskId, AddCommentRequest request) {
    TaskModel task = taskRepository.findById(taskId)
        .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + taskId));

    CommentModel comment = new CommentModel();
    comment.setContent(request.content());
    comment.setTask(task);

    CommentModel savedComment = commentRepository.save(comment);

    return commentMapper.toCommentResponse(savedComment);
  }
}
