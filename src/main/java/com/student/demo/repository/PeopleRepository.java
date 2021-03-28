package com.student.demo.repository;

import com.student.demo.models.Person;
import org.springframework.data.repository.CrudRepository;

public interface PeopleRepository extends CrudRepository<Person,Long> {
}
