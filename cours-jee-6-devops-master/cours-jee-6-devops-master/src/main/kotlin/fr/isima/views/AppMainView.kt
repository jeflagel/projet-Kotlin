package fr.isima.views

import fr.isima.business.IndexedQuote
import kotlinx.html.*


fun IndexedQuote.toCompleteHtml() = html {
    head { imports() }
    body {
        div {
            classes += setOf("container")
            nav {
                classes += setOf("navbar", "navbar-default")
                div {
                    classes += setOf("container-fluid")
                    div {
                        classes += setOf("navbar-header")
                        button {
                            classes += setOf("navbar-toggle", "collapsed")
                            attributes["data-toogle"] = "collapse"
                            attributes["data-target"] = "#bs-example-navbar-collapse-1"
                            span {
                                classes += setOf("sr-only")
                                +"Toogle navigation"
                            }
                            span { classes += setOf("icon-bar") }
                            span { classes += setOf("icon-bar") }
                        }
                        a {
                            classes += setOf("navbar-brand")
                            href = "#"
                            +"Display-quote v3"
                        }
                    }
                    div {
                        id = "bs-example-navbar-collapse-1"
                        classes += setOf("collapse", "navbar-collapse")
                        ul {
                            classes += setOf("nav", "navbar-nav")
                            li {
                                classes += setOf("active")
                                a {
                                    href = "#"
                                    id = "quote-link"
                                    classes += setOf("action-link")
                                    +"Citations"
                                    span {
                                        classes += setOf("sr-only")
                                        +("selectionne")
                                    }

                                }
                            }
                            li {
                                a {
                                    href = "#"
                                    classes += setOf("action-link")
                                    attributes["alt"] = "/stats"
                                    +"Statistiques"
                                }
                            }
                        }
                        ul {
                            classes += setOf("nav", "navbar-nav", "navbar-right")
                            li {
                                a {
                                    href = "login?logout"
                                    +"logout"
                                }
                            }
                        }
                    }
                }
            }
            div {
                id = "content-displayed"
                toPartialHtml(this@toCompleteHtml)
            }
            input(type = InputType.hidden) {
                id = "last-displayed-quote"
                value = "1"
            }
            input(type = InputType.hidden) {
                id = "save-unquoted-url"
                value = "/quotes/"
            }
            script {
                type = "text/javascript"
                src = "/resources/js/main-menu.js"
            }
        }

    }
}
