package com.b3i.monsterspring.service;

import com.b3i.monsterspring.domain.Monster;
import com.b3i.monsterspring.exception.MonsterNotFoundException;
import com.b3i.monsterspring.repository.MonsterRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class MonsterService {
    private final MonsterRepository monsterRepository;

    @Autowired
    public MonsterService(MonsterRepository monsterRepository) {
        this.monsterRepository = monsterRepository;
    }

    public Monster addMonster(Monster monster) {
        return monsterRepository.save(monster);
    }

    public List<Monster> findAllMonsters() {
        return monsterRepository.findAll();
    }

    public Monster updateMonster(Monster monster) {
        return monsterRepository.save(monster);
    }

    public Monster findMonsterById(Long id) {
        return monsterRepository.findMonsterById(id)
                .orElseThrow(() -> new MonsterNotFoundException(("Monster by id " + id + " was not found")));
    }

    public void deleteMonster(Long id){
        monsterRepository.deleteMonsterById(id);
    }
}