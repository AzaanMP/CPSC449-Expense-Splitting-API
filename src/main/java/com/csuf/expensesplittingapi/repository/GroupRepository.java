package com.csuf.expensesplittingapi.repository;

import com.csuf.expensesplittingapi.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    // Finds all groups that a specific user belongs to
    List<Group> findByUsersId(Long userId);
}