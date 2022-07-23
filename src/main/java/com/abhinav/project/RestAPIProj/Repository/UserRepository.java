package com.abhinav.project.RestAPIProj.Repository;

import com.abhinav.project.RestAPIProj.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Repository interface that extends JpaRepository to store user data (name, password)
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
