package com.co.app.cache.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.co.app.cache.domain.Cache;

/**
 * @author luis.colmenarez
 *
 */
public interface CacheRepository extends JpaRepository<Cache, String> {

}
