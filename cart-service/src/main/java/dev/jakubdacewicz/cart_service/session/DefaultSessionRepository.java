package dev.jakubdacewicz.cart_service.session;

import dev.jakubdacewicz.cart_service.shared.exception.SessionNotFoundException;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.stereotype.Repository;

@Repository
class DefaultSessionRepository implements SessionRepository {

    private final FindByIndexNameSessionRepository<?> findByIndexNameSessionRepository;

    DefaultSessionRepository(FindByIndexNameSessionRepository<?> findByIndexNameSessionRepository) {
        this.findByIndexNameSessionRepository = findByIndexNameSessionRepository;
    }

    @Override
    public Session findById(String sessionId) {
        Session session = findByIndexNameSessionRepository.findById(sessionId);
        if (session == null) {
            throw new SessionNotFoundException("Session with id " + sessionId + " not found");
        }
        return session;
    }
}
