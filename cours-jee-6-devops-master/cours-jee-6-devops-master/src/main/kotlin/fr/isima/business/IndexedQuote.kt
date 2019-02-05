package fr.isima.business

import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.*


typealias Directions = List<Direction>

interface IndexedQuote {
    val quote: Quote
    val index: Int
    val number: Int
    val previous: IndexedQuote
    val first: IndexedQuote
    val next: IndexedQuote
    val last: IndexedQuote
    val directions: Directions
    fun isFirst(): Boolean
    fun hasPrevious(): Boolean
    fun hasNext(): Boolean
    fun isLast(): Boolean
    fun isOutOfIndex(): Boolean
}

fun Iterable<Quote>.toIndexedQuotes(): IndexedQuotes = toList().let { quotes ->
    IndexedQuotes(
            quotes = quotes,
            indexedQuotes = quotes.map { quote -> IndexedQuoteInBound(quotes = quotes, quote = quote) }.toList()
    )
}

class IndexedQuotes(private val quotes: List<Quote>, private val indexedQuotes: List<IndexedQuote>) :
        List<IndexedQuote> by indexedQuotes {

    fun byNumber(quoteNumber: Int): IndexedQuote =
            if (quoteNumber < 1 || quoteNumber > size) {
                quotes.outOfBound()
            } else {
                this[quoteNumber - 1]
            }
}

private fun List<Quote>.outOfBound(): IndexedQuote = IndexedQuoteOutOfBound(quotes = this)
private fun List<Quote>.inBound(index: Int): IndexedQuote =
        IndexedQuoteInBound(quotes = this, quote = this[index])

private fun List<Quote>.firstQuote(): IndexedQuote = IndexedQuoteInBound(quotes = this, quote = first())
private fun List<Quote>.lastQuote(): IndexedQuote = IndexedQuoteInBound(quotes = this, quote = last())

internal abstract class AbstractIndexedQuote(@JsonIgnore val quotes: List<Quote>) : IndexedQuote {
    override val first: IndexedQuote
        get() = quotes.firstQuote()
    override val last: IndexedQuote
        get() = quotes.lastQuote()
    override val directions: Directions
        get() = listOf(firstDirection(), previousDirection(), nextDirection(), lastDirection())
}

internal class IndexedQuoteInBound(quotes: List<Quote>, override val quote: Quote) : AbstractIndexedQuote(quotes) {
    override val index: Int get() = quotes.indexOf(quote)
    override val number: Int get() = index + 1
    override val previous: IndexedQuote
        get() = except { isFirst() } navigateTo index - 1
    override val next: IndexedQuote
        get() = except { isLast() } navigateTo index + 1

    private infix fun except(predicate: () -> Boolean) = predicate()
    private infix fun Boolean.navigateTo(dest: Int) = if (this) quotes.outOfBound() else quotes.inBound(dest)

    override fun isFirst() = index == 0
    override fun hasPrevious() = index > 0
    override fun hasNext() = !isLast()
    override fun isLast() = index == quotes.size - 1
    override fun isOutOfIndex() = false

    override fun equals(other: Any?): Boolean = if (other is IndexedQuoteInBound) {
        quotes == quotes && quote == quote
    } else {
        false
    }

    override fun hashCode(): Int = Objects.hash(quotes, quote)

    override fun toString() = "Current quote $quote at $index in $quotes"
}


internal class IndexedQuoteOutOfBound(quotes: List<Quote>) : AbstractIndexedQuote(quotes) {
    override val previous: IndexedQuote @JsonIgnore get() = this
    override val next: IndexedQuote @JsonIgnore get() = this
    override val quote =
            Quote(
                    author = "L'auteur de l'application",
                    content = "Vous devez fournir un numero de quote pour que je puisse l'afficher"
            )
    override val index: Int get() = -1
    override val number: Int get() = -1
    override fun isFirst() = false
    override fun hasNext() = false
    override fun isOutOfIndex() = true
    override fun hasPrevious() = false
    override fun isLast() = false
}
