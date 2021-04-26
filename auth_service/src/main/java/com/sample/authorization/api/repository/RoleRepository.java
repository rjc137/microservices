/**
 * 
 */
package com.sample.authorization.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.authorization.api.entity.Role;
import com.sample.authorization.api.entity.Roles;

/**
 * @author ruchira
 *
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(Roles name);
}
