package com.galvanize.repository;

import com.galvanize.entities.Officer;
import com.galvanize.entities.Rank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcOfficerDao {




     @Autowired
    JdbcTemplate jdbcTemplate;

    private SimpleJdbcInsert insertOfficer;




    public JdbcOfficerDao(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
        insertOfficer = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("OFFICERS")
                .usingGeneratedKeyColumns("id");

    }


    public int countOfficer() {
        return jdbcTemplate.queryForObject("select count(*)from OFFICERS ", Integer.class);
    }


    public List<Officer> findAllOfficers() {
        return jdbcTemplate.query("select *  from OFFICERS",
                (rs,rowNum) -> new Officer(rs.getLong("id"),
                        Rank.valueOf(rs.getString("officer_rank")),
                        rs.getString("first_name"),
                        rs.getString("last_name"))


        );


    }

    public boolean officerExistBy(Long id) {

        boolean found = false;

        try{
            found = jdbcTemplate.queryForObject("Select 1 from Officers where id = 1", Boolean.class);
        } catch (EmptyResultDataAccessException e){
            //ignore
        }

        return found;

    }

    public Optional<Officer> findOfficerById(long id) {
        if(!officerExistBy(id)) return Optional.empty();
        return Optional.ofNullable(jdbcTemplate.queryForObject("select * from OFFICERS where id =?",
                ((rs,rowNum) -> new Officer(rs.getLong("id"),
                        Rank.valueOf(rs.getString("officer_rank")),
                        rs.getString("first_name"),
                        rs.getString("last_name"))),id)
        );
    }


    public Officer save(Officer officer) {

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("officer_rank", officer.getRank());
        parameters.put("first_name", officer.getFirst());
        parameters.put("last_name", officer.getLast());

        long newId = insertOfficer.executeAndReturnKey(parameters).longValue();
        officer.setId(newId);
        return officer;

    }

    public void delete(Long id) {
        jdbcTemplate.update("delete from OFFICERS where id = ? ", id);


    }
}
