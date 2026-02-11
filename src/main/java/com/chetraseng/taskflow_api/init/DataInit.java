package com.chetraseng.taskflow_api.init;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.chetraseng.taskflow_api.enums.TaskPriority;
import com.chetraseng.taskflow_api.enums.TaskStatus;
import com.chetraseng.taskflow_api.models.ProjectModel;
import com.chetraseng.taskflow_api.models.TaskModel;
import com.chetraseng.taskflow_api.repositories.ProjectRepository;
import com.chetraseng.taskflow_api.repositories.TaskRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Profile({"dev", "prod"})
public class DataInit implements CommandLineRunner {

    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    @Override
    public void run(String... args) {
        // prevent duplicate seeding
        if (projectRepository.count() > 0) return;

        // ---------- Projects ----------
        ProjectModel project1 = new ProjectModel();
        project1.setName("TaskFlow API");
        project1.setDescription("Backend API for task management app");
        project1 = projectRepository.save(project1);

        ProjectModel project2 = new ProjectModel();
        project2.setName("School Project");
        project2.setDescription("Assignments and homework tasks");
        project2 = projectRepository.save(project2);

        // ---------- Tasks ----------
        TaskModel t1 = new TaskModel();
        t1.setName("Create Project CRUD");
        t1.setDescription("Add endpoints for creating, updating, deleting projects");
        t1.setPriority(TaskPriority.HIGH);
        t1.setStatus(TaskStatus.TODO);
        t1.setDueDate(Instant.now().plus(7, ChronoUnit.DAYS));
        t1.setTags(List.of("backend", "spring", "api"));
        t1.setProject(project1);

        TaskModel t2 = new TaskModel();
        t2.setName("Create Task CRUD");
        t2.setDescription("Add endpoints for tasks, include filtering");
        t2.setPriority(TaskPriority.MEDIUM);
        t2.setStatus(TaskStatus.IN_PROGRESS);
        t2.setDueDate(Instant.now().plus(10, ChronoUnit.DAYS));
        t2.setTags(List.of("tasks", "jpa"));
        t2.setProject(project1);

        TaskModel t3 = new TaskModel();
        t3.setName("Finish Math Homework");
        t3.setDescription("Complete chapter 3 exercises");
        t3.setPriority(TaskPriority.LOW);
        t3.setStatus(TaskStatus.TODO);
        t3.setDueDate(Instant.now().plus(2, ChronoUnit.DAYS));
        t3.setTags(List.of("school", "math"));
        t3.setProject(project2);

        taskRepository.saveAll(List.of(t1, t2, t3));
    }
}
