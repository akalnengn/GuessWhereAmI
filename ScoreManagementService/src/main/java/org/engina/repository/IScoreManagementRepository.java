package org.engina.repository;

import org.engina.repository.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IScoreManagementRepository extends JpaRepository<Score, Long> {
    List<Score> findAllByOrderByScoreDesc();
}
