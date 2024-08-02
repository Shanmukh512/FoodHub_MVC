package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.login;

@Repository
public interface loginrepository extends CrudRepository<login, Integer>{

}
