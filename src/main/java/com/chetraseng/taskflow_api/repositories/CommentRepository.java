package com.chetraseng.taskflow_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chetraseng.taskflow_api.models.CommentModel;

@Repository
public interface CommentRepository extends JpaRepository<CommentModel, Long> {

}
