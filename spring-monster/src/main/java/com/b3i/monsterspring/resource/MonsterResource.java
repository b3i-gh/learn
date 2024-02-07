package com.b3i.monsterspring.resource;

import com.b3i.monsterspring.domain.Monster;
import com.b3i.monsterspring.service.MonsterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/monster/api/v1.1/")
public class MonsterResource {
    private final MonsterService monsterService;

    public MonsterResource(MonsterService monsterService) {
        this.monsterService = monsterService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Monster>> getAllMonsters () {
        List<Monster> monsters = monsterService.findAllMonsters();
        return new ResponseEntity<>(monsters, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Monster> getMonsterById (@PathVariable("id") Long id) {
        Monster monster = monsterService.findMonsterById(id);
        return new ResponseEntity<>(monster, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Monster> addMonster(@RequestBody Monster monster) {
        Monster newMonster = monsterService.addMonster(monster);
        return new ResponseEntity<>(newMonster, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Monster> updateMonster(@RequestBody Monster monster) {
        Monster updateMonster = monsterService.updateMonster(monster);
        return new ResponseEntity<>(updateMonster, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMonster(@PathVariable("id") Long id) {
        monsterService.deleteMonster(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}