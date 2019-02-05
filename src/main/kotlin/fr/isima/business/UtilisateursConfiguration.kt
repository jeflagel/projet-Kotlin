package fr.isima.business

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.context.annotation.ApplicationScope
import org.springframework.web.context.annotation.SessionScope

import javax.servlet.http.HttpSession

@Configuration
open class UtilisateursConfiguration {

    @Bean
    @ApplicationScope
    open fun utilisateurs(): Utilisateurs {
        return Utilisateurs()
    }

    @Bean
    @SessionScope
    open fun sessionUtilisation(utilisateur: Utilisateur): SessionUtilisation {
        return utilisateur.ouvrirSessionUtilisation()
    }

    @Bean
    @SessionScope
    @Autowired
    open fun utilisateur(httpSession: HttpSession, quotes: IndexedQuotes): Utilisateur {
        return Utilisateur(httpSession.getAttribute("username") as String, quotes, utilisateurs())
    }
}
