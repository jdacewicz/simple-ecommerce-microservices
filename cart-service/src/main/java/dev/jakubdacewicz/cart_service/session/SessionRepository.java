package dev.jakubdacewicz.cart_service.session;

import org.springframework.session.Session;

interface SessionRepository {

    Session findById(String sessionId);
}
