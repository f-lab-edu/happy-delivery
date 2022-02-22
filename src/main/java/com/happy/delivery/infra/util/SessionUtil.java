package com.happy.delivery.infra.util;

import javax.servlet.http.HttpSession;

public class SessionUtil {
    private static final String LOGIN_ID = "LOGIN_ID";

    public static void setLoginId(HttpSession httpSession, Long id) {
        httpSession.setAttribute(LOGIN_ID, id);
    }

    public static Long getLoginId(HttpSession httpSession) {
        return (Long) httpSession.getAttribute(LOGIN_ID);
    }

    public static void clear(HttpSession httpSession) {
        httpSession.invalidate();
    }
}
