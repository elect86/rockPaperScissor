package me

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import kotlin.random.Random

class Test : StringSpec() {

    init {
        "match" {
            Game.playerA = Player("A") { turn ->
                Outcome of Random(turn).nextInt(0, Outcome.max)
            }
            Game.playerB = Player("B") { Outcome.Rock }

            shouldNotThrow<UninitializedPropertyAccessException> {
                Game.playerA
                Game.playerB
            }

            Game.turns = Random.nextInt(from = 100, until = 1_000)

            Game.turns shouldBeGreaterThan 100

            while (Game.hasNext) {
                assert(Game.currentTurn < Game.turns)
                Game.next()
            }

            Game.currentTurn shouldBe Game.turns

            (Game.playerA.wins + Game.playerB.wins + Game.draws) shouldBe Game.turns

            when (Game.playerA.wins) {
                Game.playerB.wins ->
                    println("Player ${Game.playerA.name} and player ${Game.playerB.name} have drawn the match!\n" +
                            "Results: ${Game.playerA.wins} wins, ${Game.playerA.losts} losts and ${Game.draws} draws each (${Game.turns} turns in total)")
                else -> {
                    val winner = when {
                        Game.playerA.wins > Game.playerB.wins -> Game.playerA
                        else -> Game.playerB
                    }
                    println("Player ${winner.name} has won the match!\n" +
                            "Results: ${winner.wins} wins, ${winner.losts} losts and ${Game.draws} draws (${Game.turns} turns in total)")
                }
            }
        }
    }
}