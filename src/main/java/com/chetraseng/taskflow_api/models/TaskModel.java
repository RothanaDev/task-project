package com.chetraseng.taskflow_api.models;

import java.time.Instant;
import java.util.List;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.chetraseng.taskflow_api.enums.TaskPriority;
import com.chetraseng.taskflow_api.enums.TaskStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tasks_tbl")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskModel extends BasedIdModel {
  @Column(name = "name", columnDefinition = "varchar(255)")
  private String name;

  @Column(name = "description", columnDefinition = "text")
  private String description;

  @Column(name = "priority", columnDefinition = "varchar(100)")
  @Enumerated(value = EnumType.STRING)
  private TaskPriority priority;

  @Column(name = "due_date", columnDefinition = "timestamp")
  private Instant dueDate;

  @Column(name = "status", columnDefinition = "varchar(100)")
  @Enumerated(value = EnumType.STRING)
  private TaskStatus status;

  @JdbcTypeCode(SqlTypes.ARRAY)
  @Column(name = "tags", columnDefinition = "varchar(100)[]")
  private List<String> tags;

  @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
  private List<CommentModel> comments;

  @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
  private List<SubTaskModel> subTasks;
}
