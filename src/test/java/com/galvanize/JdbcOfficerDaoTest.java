package com.galvanize;


import com.galvanize.entities.Officer;
import com.galvanize.entities.Rank;
import com.galvanize.repository.JdbcOfficerDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class JdbcOfficerDaoTest {

    @Autowired
    JdbcOfficerDao jdbcOfficerDao;

    @Test
    void countOfficersTest(){
      int count = jdbcOfficerDao.count();
      assertEquals(5, count);
    }


    @Test
    void findAllOfficersTest(){

        List<Officer> officers = jdbcOfficerDao.findAllOfficers();
        assertFalse(officers.isEmpty());
        officers.forEach(System.out::println);

    }

    @Test
    void officerExistByIdTest(){

        boolean exists = jdbcOfficerDao.officerExistBy(3l);
        assertTrue(exists);
    }



    @Test
    void existsById_doesnt_existsTest(){

        boolean exists = jdbcOfficerDao.officerExistBy(20l );
        assertTrue(exists);

    }

@Test
    void findOfficerByIdTest() {
        Optional<Officer> officer = jdbcOfficerDao.findOfficerById(3l);
        assertTrue(officer.isPresent());
        assertNotNull(officer.get().getId());
        System.out.println(officer);

    }


    @Test
    void createOfficer() {
        Officer officer = new Officer(Rank.LIEUTENANT, "cherno", "jallow");
        officer = jdbcOfficerDao.save(officer);
        assertNotNull(officer);
        assertNotNull(officer.getId());

        System.out.println(officer);

    }


     @Test
    void deleteOfficer(){

        Officer officer = new Officer(Rank.LIEUTENANT, "cherno", "jallow");
        officer = jdbcOfficerDao.save(officer);
        Long id = officer.getId();
        assertNotNull(id);

        jdbcOfficerDao.delete(id);
        assertFalse(jdbcOfficerDao.officerExistBy(id));
    }


}
