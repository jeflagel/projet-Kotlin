package fr.isima.views

import kotlinx.html.*
import kotlinx.html.stream.appendHTML
import java.io.StringWriter

fun html(renderer: HTML.() -> Unit) =
        StringWriter().run {
            appendHTML().html(block = renderer)
            buffer.toString()
        }

fun partialDiv(renderer: DIV.() -> Unit) =
        StringWriter().run {
            partialDiv { renderer() }
            buffer.toString()
        }

fun Appendable.partialDiv(renderer: DIV.() -> Unit) =
        appendHTML().div { renderer() }

fun HEAD.imports() {
    script {
        type = "text/javascript"
        src = "/resources/js/jquery-2.2.0.js"
    }
    script {
        type = "text/javascript"
        src = "/resources/js/bootstrap.min.js"
    }
    link {
        rel = "stylesheet"
        type = "text/css"
        href = "/resources/css/bootstrap-theme.min.css"
    }
    link {
        rel = "stylesheet"
        type = "text/css"
        href = "/resources/css/bootstrap.min.css"
    }
}