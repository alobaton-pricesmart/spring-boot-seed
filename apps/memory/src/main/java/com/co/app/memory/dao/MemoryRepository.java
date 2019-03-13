package com.co.app.memory.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.co.app.memory.domain.Memory;

/**
 * @author luis.colmenarez
 *
 */
public interface MemoryRepository extends JpaRepository<Memory, String> {

}
