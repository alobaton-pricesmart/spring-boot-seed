package com.co.app.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.co.app.commons.domain.AuthUserRole;
import com.co.app.commons.domain.AuthUserRolePk;
import com.co.app.commons.domain.QAuthUserRole;
import com.co.app.commons.repository.BaseRepository;

@Repository
public interface AuthUserRoleRepository extends JpaRepository<AuthUserRole, AuthUserRolePk>,
		QuerydslPredicateExecutor<AuthUserRole>, BaseRepository<QAuthUserRole> {

}
