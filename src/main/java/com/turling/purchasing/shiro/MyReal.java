package com.turling.purchasing.shiro;

import com.turling.purchasing.dao.SysUsersMapper;
import com.turling.purchasing.entity.SysUsers;
import com.turling.purchasing.entity.SysUsersExample;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.web.session.HttpServletSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.util.List;

public class MyReal extends AuthorizingRealm {
    @Autowired
    private SysUsersMapper sysUsersMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权");
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("做认证");
        //1.强转token
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        SysUsersExample sysUsersExample = new SysUsersExample();
        sysUsersExample.createCriteria().andLoginIdEqualTo(token.getUsername());
        List<SysUsers> list = sysUsersMapper.selectByExample(sysUsersExample);
        if(list.size()<1){
            return null;
        }else {
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(list.get(0),list.get(0).getPassword(),getName());
            info.setCredentialsSalt(ByteSource.Util.bytes(list.get(0).getLoginId()));
            return info;
        }
    }
}
