package com.challenge.adm.challengeprojetadata.application.repository;

import com.challenge.adm.challengeprojetadata.domain.Employee;
import jakarta.persistence.NamedNativeQueries;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    @Query("SELECT u FROM Employee  u where u.position = :position")
    List<Employee> findByPosition(@Param("position") String function);

    @Modifying
    @Transactional
    @Query("DELETE FROM Employee u WHERE u.name = :name")
    void deleteByName(@Param("name") String name);
}
