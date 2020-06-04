package me

enum class Outcome { Rock, Paper, Scissor;

    companion object {
        val max = values().size
        infix fun of(i: Int) = values()[i]
    }
}