package me

class Player(
        val name: String,
        val launch: (Int) -> Outcome
) {
    var wins = 0
    val losts: Int
        get() = Game.currentTurn - wins - Game.draws
}
