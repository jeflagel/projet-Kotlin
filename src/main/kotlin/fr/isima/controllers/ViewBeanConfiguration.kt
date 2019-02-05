package fr.isima.controllers

import fr.isima.business.SessionUtilisation
import fr.isima.business.Utilisateurs
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope

import javax.inject.Provider

@Configuration
class ViewBeanConfiguration {

    @Bean
    @Autowired
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    fun statsViewBean(utilisateurs: Utilisateurs, sessionUtilisation: Provider<SessionUtilisation>): StatsViewBean {
        return StatsViewBean(utilisateurs, sessionUtilisation.get())
    }
}
