package fr.isima.controllers

import fr.isima.business.IndexedQuote

data class IndexedQuoteView(val author: String, val content: String, val number: Int, val directions: List<DirectionView>)
data class DirectionView(val id: String, val enabled: Boolean, val targetNumber: Int)
fun IndexedQuote.toIndexedQuoteView() = IndexedQuoteView(
        author = quote.author,
        content = quote.content,
        number = number,
        directions = directions.map { DirectionView(id = it.id, enabled = it.enabled, targetNumber = it.targetNumber) }
)

