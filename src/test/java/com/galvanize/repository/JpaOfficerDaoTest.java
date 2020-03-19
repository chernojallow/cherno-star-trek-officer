package com.galvanize.repository;

import com.galvanize.entities.Officer;
import com.galvanize.entities.Rank;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JpaOfficerDaoTest {

    @Autowired
    JpaOfficerDao jpaOfficerDao;

    @Test
    void countOfficerTest(){

        Long count = jpaOfficerDao.count();
        assertEquals(5l, count);

    }

    @Test
    void findAllOfficers(){
        List<Officer> officers = jpaOfficerDao.findAll();
        assertFalse(officers.isEmpty());
        officers.forEach(System.out::println);
    }


    @Test
    void createOfficer() {
        Officer officer = new Officer(Rank.LIEUTENANT, "cherno", "jallow");
        officer =jpaOfficerDao.save(officer);
        assertNotNull(officer.getId());

        System.out.println(officer);

    }

    @Test
    void findByRank(){
        List<Officer> captains = jpaOfficerDao.findAllByRank(Rank.CAPTAIN);
        assertFalse(captains.isEmpty());
        captains.forEach(System.out::println);
    }

}