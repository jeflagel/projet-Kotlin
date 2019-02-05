package fr.isima.controllers

import fr.isima.business.IndexedQuotes
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/rest/quotes")
class QuotesRestController @Autowired constructor(private val indexedQuotes: IndexedQuotes) {

    @GetMapping("{quoteNumber}")
    fun quoteByNumber(@PathVariable quoteNumber: Int) = indexedQuotes.byNumber(quoteNumber).toIndexedQuoteView()

}