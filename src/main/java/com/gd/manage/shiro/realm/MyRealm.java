package com.gd.manage.shiro.realm;

import com.gd.manage.entity.po.PermissionPO;
import com.gd.manage.entity.po.RolePO;
import com.gd.manage.entity.po.UserPO;
import com.gd.manage.entity.query.PermissionQuery;
import com.gd.manage.enums.SgmsConstants;
import com.gd.manage.service.PermissionService;
import com.gd.manage.service.RoleService;
import com.gd.manage.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

/**
 * @author gq
 * @date 2022/6/16 0016 16:14
 */
public class MyRealm extends AuthorizingRealm {

    private static final Logger log = LoggerFactory.getLogger(MyRealm.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authzInfo = null;
        String username = (String) super.getAvailablePrincipal(principalCollection);
        UserPO user = null;
        try {
            user = userService.getByUserName(username);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 加载角色和权限
        if (null != user) {
            authzInfo = new SimpleAuthorizationInfo();

            //查询出用户角色列表
            List<RolePO> roleList = roleService.getByUserId(user.getId());
            boolean rootRole = false;
            for (RolePO role : roleList) {
                if (role.getRoleType().equals(SgmsConstants.RoleType.ROOT)) { //root角色
                    rootRole = true;
                    break;
                }
            }

            //查询出用户权限列表
            PermissionQuery permissionQuery = new PermissionQuery();
            permissionQuery.setPermissionType(SgmsConstants.PermissionType.INTERFACE);
            List<PermissionPO> permList;
            if (rootRole) {
                permList = permissionService.query(null).getList();  //查询系统所有权限
            } else {
                permList = permissionService.queryByUserId(user.getId());  //查询用户拥有的权限
            }


            //添加角色到授权信息对象
            for (RolePO role : roleList) {
                authzInfo.addRole(role.getCode());
            }

            //添加权限到授权信息对象
            for (PermissionPO perm : permList) {
                if (!perm.getUrl().trim().isEmpty() && !perm.getCode().trim().isEmpty()) {
                    String[] perms = perm.getCode().split(",");
                    for (String permCode : perms) {
                        authzInfo.addStringPermission(permCode);
                    }
                }
            }

            if (log.isDebugEnabled()) {
                Set<String> roles = authzInfo.getRoles();
                if (null != roles) {
                    log.debug("load {} roles {}", user.getUserName(), roles.size());
                }
                Set<String> perms = authzInfo.getStringPermissions();
                if (null != perms) {
                    log.debug("load {} permissions {}", user.getUserName(), perms.size());
                }
            }
        }
        return authzInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        SimpleAuthenticationInfo simpleAuthenticationInfo = null;
        UsernamePasswordToken t = (UsernamePasswordToken) authenticationToken;

        //获取用户名
        String username = (String) authenticationToken.getPrincipal();

        UserPO user = userService.getByUserName(username);

        if(null != user){
            if(SgmsConstants.YesNo.NO.equals(user)){
                throw new AuthenticationException("账号已被禁用");
            }
            simpleAuthenticationInfo = new SimpleAuthenticationInfo(user.getUserName(),user.getPassword(), ByteSource.Util.bytes(user.getSalt()),this.getName());
        }

        if (log.isDebugEnabled()) {
            log.debug("realm {} load authentication info. username:{}",
                    this.getName(), t.getUsername());
        }

        return simpleAuthenticationInfo;

    }
}
