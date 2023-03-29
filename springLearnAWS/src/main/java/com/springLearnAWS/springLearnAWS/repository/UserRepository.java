package com.springLearnAWS.springLearnAWS.repository;

import com.springLearnAWS.springLearnAWS.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
