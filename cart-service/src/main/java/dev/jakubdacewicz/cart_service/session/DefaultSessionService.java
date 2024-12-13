package dev.jakubdacewicz.cart_service.session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.session.Session;
import org.springframework.stereotype.Service;

@Service
class DefaultSessionService implements SessionService {

    private static final Logger logger = LoggerFactory.getLogger(DefaultSessionService.class);

    private final SessionRepository sessionRepository;

    DefaultSessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public Session getSession(String sessionId) {
        logger.debug("Attempt to get session '{}'", sessionId);

        Session session = sessionRepository.findById(sessionId);

        logger.info("Successfully got session '{}'", sessionId);
        return session;
    }
}
