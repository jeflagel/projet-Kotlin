package fr.isima.controllers

import fr.isima.business.IndexedQuotes
import fr.isima.views.toCompleteHtml
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.servlet.ModelAndView

@Controller
@RequestMapping("/main")
class AppMainMVCController @Autowired constructor(private val indexedQuotes: IndexedQuotes) {

    @GetMapping
    @ResponseBody
    fun main() = indexedQuotes.byNumber(1).toCompleteHtml()
}
