package com.margin.disqo.repository;

import com.margin.disqo.entity.ApiUserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiUserDetailRepository extends JpaRepository<ApiUserDetail, Long> {

    ApiUserDetail findByUsername (String username);
}
