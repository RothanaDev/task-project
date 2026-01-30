package com.chetraseng.taskflow_api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chetraseng.taskflow_api.dto.requests.AddCommentRequest;
import com.chetraseng.taskflow_api.dto.responses.CommentResponse;
import com.chetraseng.taskflow_api.services.CommentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(CommentController.BASE_URL)
@RequiredArgsConstructor
public class CommentController {
  public static final String BASE_URL = "/api/v1/comments";

  private final CommentService commentService;

  @PostMapping("/tasks/{taskId}")
  public ResponseEntity<CommentResponse> addComment(@PathVariable Long taskId,
      @Validated @RequestBody AddCommentRequest request) {
    return ResponseEntity.ok(commentService.addComment(taskId, request));
  }
}
