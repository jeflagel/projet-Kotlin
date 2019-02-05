package fr.isima.business

import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.atomic.AtomicInteger

/**
 * Un utilisateur est inform� des consultations pour pouvoir incrementer son
 * nombre de citation
 *
 * @author Benjamin Kuchcik
 */
open class Utilisateur(val pseudonyme: String, val quotes: IndexedQuotes, private val utilisateurs: Utilisateurs) {

    private val sessionsUtilisations = CopyOnWriteArrayList<SessionUtilisation>()
    private val nombreTotalDeCitationEnregistree = AtomicInteger()

    open  val nombreSessionUtilisation: Int
        get() = sessionsUtilisations.size

    open val nombreCitationVue: Int
        get() = nombreTotalDeCitationEnregistree.get()

    /**
     * Cree une nouvelle session d'utilisation, celle-ci est liee fortement a
     * l'utilisateur
     *
     * @return la session d'utilisation
     */
    open  fun ouvrirSessionUtilisation(): SessionUtilisation =
        SessionUtilisation(this).apply { sessionsUtilisations.add(this) }

    /**
     * Appel� depuis la [SessionUtilisation.addAQuote]. Il incr�mente le
     * nombre de citation vu par l'utilisateur et appelle la methode
     * [Utilisateurs.incrementNombreCitationLu] pour incr�menter les
     * citations de toute l'application
     */
    internal open fun incrementNombreCitationLu() {
        nombreTotalDeCitationEnregistree.incrementAndGet()
        utilisateurs.incrementNombreCitationLu()
    }

    internal open fun terminerSession(sessionUtilisation: SessionUtilisation) {
        sessionsUtilisations.remove(sessionUtilisation)
    }
}
