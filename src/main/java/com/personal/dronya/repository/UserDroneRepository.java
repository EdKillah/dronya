package com.personal.dronya.repository;

import com.personal.dronya.model.entity.UserDrone;
import com.personal.dronya.model.entity.UserDroneID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDroneRepository extends JpaRepository<UserDrone, UserDroneID> {
}
