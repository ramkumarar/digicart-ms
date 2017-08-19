package com.wilson.retail.repository;


import com.wilson.retail.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by ramkumar on 19-08-2017.
 */
public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByLastName(String lastName);
}
