package com.margin.disqo.repository;

import com.margin.disqo.entity.ApiUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<ApiUser, Long> {
}
