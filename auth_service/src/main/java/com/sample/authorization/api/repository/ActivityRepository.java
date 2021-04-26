/**
 * 
 */
package com.sample.authorization.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.authorization.api.entity.Activity;

/**
 * @author ruchira
 *
 */
@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

}
