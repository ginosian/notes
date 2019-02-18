package com.margin.disqo.repository;

import com.margin.disqo.entity.ApiAuthAccessToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ApiAuthAccessTokenRepository extends JpaRepository<ApiAuthAccessToken, Long> {

    @Query("SELECT t FROM ApiAuthAccessToken t WHERE t.apiUserDetail.id = (:userDetailId)")
    ApiAuthAccessToken findByUser(@Param("userDetailId") Long userDetailId);

    @Query("SELECT t FROM ApiAuthAccessToken t WHERE t.token = (:token)")
    ApiAuthAccessToken findByToken(@Param("token") String token);
}
