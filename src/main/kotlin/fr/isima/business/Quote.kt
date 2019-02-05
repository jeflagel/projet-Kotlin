package fr.isima.business


val allQuotes = listOf(
    Quote(
        author = "Georges Clemenceau",
        content = "Un traître est un homme politique qui quitte son parti pour s'inscrire a un autre. Par contre, un converti est un homme politique qui quitte son parti pour s'inscrire au votre."
    ),
    Quote(
        author = "Tristan Bernard",
        content = "Les hommes sont toujours sincères. Ils changent de sincérité, voilà tout."
    ),
    Quote(
        author = "Woody Allen",
        content = "La vie est une maladie mortelle sexuellement transmissible."
    ),
    Quote(
        author = "Woody Allen",
        content = "Dans votre ascension professionnelle, soyez toujours très gentil pour ceux que vous dépassez en montant. Vous les retrouverez au même endroit en redescendant."
    ),
    Quote(
        author = "Albert Einstein",
        content = "Il y a deux choses d'infini au monde : l'univers et la bétise humaine....mais pour l'univers j'en suis pas très sûr. "
    ),
    Quote(
        author = "Marcel Proust",
        content = "L'homme n'est pas fait pour travailler et la preuve, c'est que ça le fatigue. "
    ),
    Quote(
        author = "Oscar Wilde",
        content = "Il n'y a que deux sortes de gens attrayants ; ceux qui savent absolument tout et ceux qui ne savent absolument rien."
    ),
    Quote(
        author = "François de La Rochefoucauld",
        content = "Comme c'est le caractère des grands esprits de faire entendre en peu de paroles beaucoup de choses, les petits esprits au contraire ont le don de beaucoup parler, et de ne rien dire."
    )
)

data class Quote(val author: String, val content: String)


