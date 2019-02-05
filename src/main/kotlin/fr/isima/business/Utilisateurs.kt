package fr.isima.business

import java.util.concurrent.atomic.AtomicInteger

/**
 * L'ensemble des utilisateurs de l'application.
 *
 * Le stockage ne se faisant qu'en m�moire, un red�marrage fait perdre toutes
 * les donn�es
 *
 * @author Benjamin
 */
open class Utilisateurs {

    private val nombreTotalCitation = AtomicInteger(0)

    open val nombreCitationVuAuTotal: Int
        get() = nombreTotalCitation.get()

    open fun incrementNombreCitationLu() {
        nombreTotalCitation.getAndIncrement()
    }
}
