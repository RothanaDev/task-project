package com.chetraseng.taskflow_api.specs;

import java.util.Arrays;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.chetraseng.taskflow_api.enums.TaskStatus;
import com.chetraseng.taskflow_api.models.ProjectModel_;
import com.chetraseng.taskflow_api.models.TaskModel;
import com.chetraseng.taskflow_api.models.TaskModel_;

public class TaskSpecification {

  public static Specification<TaskModel> equalStatus(TaskStatus status) {
    return (root, query, cb) -> {
      return cb.equal(root.get(TaskModel_.STATUS), status);
    };
  }

  public static Specification<TaskModel> equalProjectId(Long projectId) {
    return (root, query, cb) -> {
      return cb.equal(root.get(TaskModel_.PROJECT).get(ProjectModel_.ID), projectId);
    };
  }

  public static Specification<TaskModel> containsName(String name) {
    List<String> nameParts = Arrays.stream(name.split(" "))
        .filter(part -> !part.isBlank())
        .toList();

    if (nameParts.isEmpty()) {
      return (root, query, cb) -> cb.conjunction();
    }

    return (root, query, cb) -> {
      return nameParts.stream()
          .map(namePart -> cb.like(
              cb.lower(root.get(TaskModel_.NAME)),
              "%" + namePart.toLowerCase() + "%"))
          .reduce(cb::or)
          .orElse(cb.conjunction());
    };
  }

}
