package me

object Game {

    lateinit var playerA: Player
    lateinit var playerB: Player

    var turns = 0

    var currentTurn = 0

    val hasNext: Boolean
        get() = currentTurn < turns

    var draws = 0

    fun next() {
        val a = playerA.launch(currentTurn)
        val b = playerB.launch(currentTurn++)

        if (a == b)
            draws++
        else when (a) {
            Outcome.Rock -> when (b) {
                Outcome.Paper -> playerB.wins++
                else -> playerA.wins++
            }
            Outcome.Paper -> when(b) {
                Outcome.Rock -> playerA.wins++
                else -> playerA.wins
            }
            Outcome.Scissor -> when(b) {
                Outcome.Rock -> playerB.wins++
                else -> playerA.wins++
            }
        }
    }
}