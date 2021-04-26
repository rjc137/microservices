/**
 * 
 */
package com.sample.authorization.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.authorization.api.entity.Permission;

/**
 * @author ruchira
 *
 */
@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

}
