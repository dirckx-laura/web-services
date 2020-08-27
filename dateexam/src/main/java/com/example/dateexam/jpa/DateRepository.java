package com.example.dateexam.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface DateRepository extends CrudRepository<IntervalDate,Integer> {

}
