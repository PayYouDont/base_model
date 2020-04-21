package com.payudon.base.sys.config;

import com.payudon.base.sys.entity.User;
import com.payudon.base.sys.service.MenuRoleRelationService;
import com.payudon.base.sys.service.UserService;
import com.payudon.base.sys.util.ShiroUtils;
import com.payudon.base.common.config.ApplicationContextRegister;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Set;

public class UserRealm extends AuthorizingRealm {
	// 权限的实现
	@Override
	public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		int roleId = ShiroUtils.getUserRoleId();
		MenuRoleRelationService mrrService = ApplicationContextRegister.getBean(MenuRoleRelationService.class);
		Set<String> perms = mrrService.getRolePerms(roleId);
        /*RoleService roleService = ApplicationContextRegister.getBean(RoleService.class);
        Role role = roleService.findById (roleId);
        Set<String> perms = new HashSet<> ();
        role.getMenus ().forEach (menu -> perms.add (menu.getPermissions ()));*/
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setStringPermissions(perms);
		return info;
	}

	// 登录认证实现
	@Override
	public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 获取用户的输入的账号.
		String account = (String) token.getPrincipal();
		// 通过name从数据库中查找 User对象，如果找到，没找到.
		// 这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
		/*
		 * Map<String, Object> map = new HashMap<>(16); map.put("name", name);
		 */
		String password = new String((char[]) token.getCredentials());
		// 获取容器中的userMapper
		UserService userService = ApplicationContextRegister.getBean(UserService.class);
		// 查询用户信息
		User user = userService.findByAccount(account);

		// 账号不存在
		if (user == null) {
			throw new UnknownAccountException("账号或密码不正确");
		}

		// 密码错误
		if (!password.equals(user.getPassword())) {
			throw new IncorrectCredentialsException("账号或密码不正确");
		}

		/*
		 * // 账号锁定 if (user.getStatus() == 0) { throw new
		 * LockedAccountException("账号已被锁定,请联系管理员"); }
		 */
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
		return info;
	}
}
