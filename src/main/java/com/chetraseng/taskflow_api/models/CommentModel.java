package com.chetraseng.taskflow_api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "comments_tbl")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentModel extends BasedIdModel {
  @Column(name = "content", columnDefinition = "text")
  private String content;

  @ManyToOne
  @JoinColumn(name = "task_id")
  private TaskModel task;
}
