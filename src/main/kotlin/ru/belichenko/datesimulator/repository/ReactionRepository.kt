package ru.belichenko.datesimulator.repository

import ru.belichenko.datesimulator.dto.Reaction
import ru.belichenko.datesimulator.dto.ReactionForm

data class ReactionRepository(
    val reactionRepository: MutableList<Reaction>
) {
    fun react(reaction: Boolean, idFrom: Long, idTo: Long) {
        val existingReaction = reactionRepository.find { it.reactionInfo.idFrom == idFrom && it.reactionInfo.idTo == idTo }
        if (existingReaction != null) {
            val index = reactionRepository.indexOf(existingReaction)
            reactionRepository[index].reactionInfo.reaction = reaction
        }
        else {
            reactionRepository.add(Reaction(reactionRepository.size.toLong(), ReactionForm(idFrom, idTo, reaction)))
        }
    }
}