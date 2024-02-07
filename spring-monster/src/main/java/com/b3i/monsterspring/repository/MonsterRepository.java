package com.b3i.monsterspring.repository;

import com.b3i.monsterspring.domain.Monster;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface MonsterRepository extends JpaRepository<Monster, Long> {
    void deleteMonsterById(Long id);

    Optional<Monster> findMonsterById(Long id);
}