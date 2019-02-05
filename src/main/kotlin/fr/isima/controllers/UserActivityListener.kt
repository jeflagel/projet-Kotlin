package fr.isima.controllers

import fr.isima.business.SessionUtilisation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import org.springframework.web.context.support.ServletRequestHandledEvent

import javax.inject.Provider

@Component
class UserActivityListener @Autowired
constructor(
    private val sessionUtilisationProvider: Provider<SessionUtilisation>
) {

    @EventListener
    fun handleEvent(e: ServletRequestHandledEvent) {
        if (e.requestUrl.contains("quotes")) {
            this.sessionUtilisationProvider.get().addAQuote()
        }
    }
}
