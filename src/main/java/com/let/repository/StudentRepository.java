package com.let.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.let.entity.StudentEntity;

@Repository				//this annotation is optional			
public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {

}
