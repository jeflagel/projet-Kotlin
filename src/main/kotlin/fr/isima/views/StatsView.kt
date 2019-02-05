package fr.isima.views

import fr.isima.controllers.StatsViewBean
import kotlinx.html.classes
import kotlinx.html.li
import kotlinx.html.ul


fun StatsViewBean.toHtml() = partialDiv {
    classes += setOf("jumbotron")
    ul {
        classes += setOf("list-group")
        li {
            classes += setOf("list-group-item")
            +"Nombre total de citation vue dans l'application : ${nombreCitationVuAuTotal}"
        }
        li {
            classes += setOf("list-group-item")
            +"Nombre de session d'utilisation en cours : ${nombreSessionEnCours}"
        }
        li {
            classes += setOf("list-group-item")
            +"Nombre de citation pour l'utilisateur (toutes sessions confondus) : ${nombreDeCitationPourLUtilisateur}"
        }
        li {
            classes += setOf("list-group-item")
            +"Nombre de citation pour la session en cours : ${nombreCitationVuPourLaSessionEnCours}"
        }
    }
}
