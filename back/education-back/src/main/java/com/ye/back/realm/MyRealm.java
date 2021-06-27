package com.ye.back.realm;

import com.ye.back.entity.User;
import com.ye.back.service.PermissionService;
import com.ye.back.service.RoleService;
import com.ye.back.service.UserService;
import com.ye.back.token.JwtToken;
import com.ye.back.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @PackageName:com.ye.shiro.realm
 * @ClassName:MyRealm
 * @Description:
 * @author:何进业
 * @date:2021/4/30 10:58
 */
@Component
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    @Override
    //获得自己定义的token
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String)principalCollection.iterator().next();
        Set<String> roleNames = roleService.getAllRoleNamesByUsername(username);
        Set<String> permission = permissionService.getAllPermissionByUsername(username);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roleNames);
        info.setStringPermissions(permission);
        return info;
    }

    //使用自定义token
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        JwtToken jwtToken = (JwtToken) authenticationToken;
        String token = (String) jwtToken.getPrincipal();
        Claims claims = JwtUtil.parseJWT(token);
        String username = claims.getId();
        User user = userService.getUserByUsername(username);
        if (user == null){
            return null;
        }
        return new SimpleAuthenticationInfo(username, user.getPassword(), getName());
    }

//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
//        String username = token.getUsername();
//        User user = userService.getUserByUsername(username);
//        if (user == null){
//            return null;
//        }
//        return new SimpleAuthenticationInfo(username, user.getPassword(), getName());
//    }

}
