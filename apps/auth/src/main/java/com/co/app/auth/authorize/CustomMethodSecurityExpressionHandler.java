/**
 * 
 */
package com.co.app.auth.authorize;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;

import com.co.app.auth.services.role.AuthRoleService;
import com.co.app.auth.services.user.AuthUserService;

/**
 * This class handles the method security context.
 * 
 * @author alobaton
 *
 */
public class CustomMethodSecurityExpressionHandler extends DefaultMethodSecurityExpressionHandler {
	private AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();

	private AuthUserService userService;
	private AuthRoleService roleService;

	public CustomMethodSecurityExpressionHandler(AuthUserService userService, AuthRoleService roleService) {
		super();
		this.userService = userService;
		this.roleService = roleService;
	}

	/**
	 * Build a custom security expression root.
	 */
	@Override
	protected MethodSecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication,
			MethodInvocation invocation) {
		CustomMethodSecurityExpressionRoot root = new CustomMethodSecurityExpressionRoot(authentication, userService,
				roleService);
		root.setPermissionEvaluator(getPermissionEvaluator());
		root.setTrustResolver(this.trustResolver);
		root.setRoleHierarchy(getRoleHierarchy());
		return root;
	}
}