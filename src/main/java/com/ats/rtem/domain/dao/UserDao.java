package com.ats.rtem.domain.dao;

import com.ats.rtem.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.ats.rtem.domain.query.UserQueryConstants.*;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

    Optional<User> findByUserNameOrEmail(String userName, String email);

    Boolean existsByEmail(String email);

    Boolean existsByUserName(String userName);

    @Query(value = SEARCH_USER_BY_KEYWORD_AND_DATE_RANGE, nativeQuery = true)
    Page<User> searchUsersByKeywordAndDateRange(
            @Param("keyword") String keyword,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            Pageable pageable
    );

    @Query(value = SEARCH_USER_BY_DATE_RANGE,
            countQuery = COUNT_USER_BY_DATE_RANGE,
            nativeQuery = true)
    Page<User> searchByDateRange(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            Pageable pageable
    );
}
