package fr.isima.business

import java.util.concurrent.atomic.AtomicInteger

/**
 * Represente une session d'utilisation.
 *
 *
 * Un [Utilisateur] peut avoir plusieurs sessions d'utilisation en m�me
 * temps. La session d'utilisation est donc relier � un utilisateur. L'objet
 * permet de compter, le nombre de citation vu dans la session d'utilisation en
 * cours.
 *
 *
 * @author Benjamin Kuchcik
 */
open class SessionUtilisation internal constructor(
    /**
     * L'utilisateur utilisant utilis� pour se connecter � cette session
     */
    open val utilisateur: Utilisateur
) {

    /**
     * Le nombre de citation vu pour cette session
     */
    private val nombreDeCitation = AtomicInteger()

    open val nombreDeCitationVue: Int
        get() = nombreDeCitation.get()

    /**
     * Permet d'ajouter une quote � la session d'utilisation. Appel la methode d
     * [Utilisateur] pour pouvoir incr�menter le nombre de citation vu par
     * l'utilisateur.
     *
     * @see Utilisateur.incrementNombreCitationLu
     */
    open fun addAQuote() {
        nombreDeCitation.incrementAndGet()
        utilisateur.incrementNombreCitationLu()
    }

    open fun terminer() {
        utilisateur.terminerSession(this)
    }

}
