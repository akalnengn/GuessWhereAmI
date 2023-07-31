package org.engina.repository;

import org.engina.repository.entity.Guess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGuessManagementRepository extends JpaRepository<Guess,Long> {
}
