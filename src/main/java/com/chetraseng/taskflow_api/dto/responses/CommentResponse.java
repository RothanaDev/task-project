package com.chetraseng.taskflow_api.dto.responses;

import java.time.Instant;

import lombok.Data;

@Data
public class CommentResponse {
  private Long id;
  private String content;
  private Instant createdAt;
}
