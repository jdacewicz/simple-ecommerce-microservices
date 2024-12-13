package dev.jakubdacewicz.cart_service.session;

import org.springframework.session.Session;

public interface SessionService {

    Session getSession(String sessionId);
}
