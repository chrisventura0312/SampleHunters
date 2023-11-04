package com.codingdojo.samplehunters.repositories;

//spring imports
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

//java util imports
import java.util.List;
import java.util.Optional;

//local imports
import com.codingdojo.samplehunters.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

Optional<User> findByEmail(String email);

boolean existsByEmail(String email);

}
