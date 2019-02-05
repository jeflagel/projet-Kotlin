package fr.isima.controllers

import fr.isima.business.IndexedQuotes
import fr.isima.views.toCompleteHtml
import fr.isima.views.toPartialHtml
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@RequestMapping("/quotes")
@Controller
class QuotesMVCController @Autowired constructor(private val indexedQuotes: IndexedQuotes) {

    @GetMapping("{quoteNumber}")
    @ResponseBody
    fun quoteByNumber(@PathVariable quoteNumber: Int): String =
        indexedQuotes.byNumber(quoteNumber).toPartialHtml()

    @GetMapping
    @ResponseBody
    fun defaultQuote() = quoteByNumber(quoteNumber = 1)
}

