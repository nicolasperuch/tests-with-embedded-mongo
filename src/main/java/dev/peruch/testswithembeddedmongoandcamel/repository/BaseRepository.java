package dev.peruch.testswithembeddedmongoandcamel.repository;

import dev.peruch.testswithembeddedmongoandcamel.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseRepository extends MongoRepository<User, String> {}
