package com.payudon.base.sys.util;

import com.payudon.base.sys.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class ShiroUtils {
   /* @Autowired
    private static SessionDAO sessionDAO;*/

    public static Subject getSubjct() {
        return SecurityUtils.getSubject();
    }
    public static User getUser() {
        Object object = getSubjct().getPrincipal();
        return (User)object;
    }
    public static int getUserId() {
        return getUser().getId();
    }
    
    public static int getUserRoleId() {
        return getUser().getRoleId();
    }
    
    public static void logout() {
        getSubjct().logout();
    }

   /* public static List<Principal> getPrinciples() {
        List<Principal> principals = null;
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        return principals;
    }*/
}
