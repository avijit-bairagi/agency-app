package com.avijit.agencyapp.repository;

import com.avijit.agencyapp.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {

    Page<PostEntity> findByPrivacyType(Pageable pageable, String privacyType);

    @Query(value = "SELECT * FROM posts p " +
            "WHERE p.user_id = ?1 ORDER BY p.updated_at desc", nativeQuery = true)
    Page<PostEntity> findAllCurrentUserPost(Pageable pageable, Long id);
}
