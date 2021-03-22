package com.co.app.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.co.app.commons.domain.AuthRolePermission;
import com.co.app.commons.domain.AuthRolePermissionPk;

@Repository
public interface AuthRolePermissionRepository
		extends JpaRepository<AuthRolePermission, AuthRolePermissionPk>, QuerydslPredicateExecutor<AuthRolePermission> {

}
