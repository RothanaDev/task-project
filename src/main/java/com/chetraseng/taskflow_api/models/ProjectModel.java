package com.chetraseng.taskflow_api.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "projects_tbl")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProjectModel extends BasedIdModel {
  @Column(name = "name", columnDefinition = "varchar(100)")
  private String name;

  @Column(name = "description", columnDefinition = "text")
  private String description;

  @OneToMany(mappedBy = "project")
  private List<TaskModel> tasks;
}
