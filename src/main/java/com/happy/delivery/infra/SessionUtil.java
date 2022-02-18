package com.happy.delivery.infra;

import javax.servlet.http.HttpSession;

public class SessionUtil {
    private static final String LOGIN_EMAIL_ADDRESS = "LOGIN_EMAIL_ADDRESS";

    public static void setEmailAddress(HttpSession httpSession, String email) {
        httpSession.setAttribute(LOGIN_EMAIL_ADDRESS, email);
    }

    public static String getEmailAddress(HttpSession httpSession) {
        return (String) httpSession.getAttribute(LOGIN_EMAIL_ADDRESS);
    }

    public static void clear(HttpSession httpSession) {
        httpSession.invalidate();
    }
}
