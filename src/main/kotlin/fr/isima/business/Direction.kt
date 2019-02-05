package fr.isima.business

data class Direction(val id: String, val label: String, val enabled: Boolean, val targetNumber: Int)

internal fun IndexedQuote.firstDirection() = Direction(
    id = "first",
    label = "Premiere citation",
    enabled = !isFirst(),
    targetNumber = first.number
)

internal fun IndexedQuote.previousDirection() = Direction(
    id = "previous",
    label = "Citation précédente",
    enabled = hasPrevious(),
    targetNumber = previous.number
)

internal fun IndexedQuote.nextDirection() = Direction(
    id = "next",
    label = "Citation suivante",
    enabled = hasNext(),
    targetNumber = next.number
)

internal fun IndexedQuote.lastDirection() = Direction(
    id = "last",
    label = "Dernière citation",
    enabled = !isLast(),
    targetNumber = last.number
)
