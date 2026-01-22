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
@Table(name = "sub_tasks_tlb")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SubTaskModel extends BasedIdModel {
  @Column(name = "title", columnDefinition = "varchar(100)")
  private String title;

  @Column(name = "completed", columnDefinition = "boolean")
  private Boolean completed;

  @ManyToOne
  @JoinColumn(name = "task_id")
  private TaskModel task;
}
