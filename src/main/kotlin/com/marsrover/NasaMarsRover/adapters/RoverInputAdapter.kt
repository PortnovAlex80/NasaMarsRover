package com.marsrover.NasaMarsRover.adapters

import com.marsrover.NasaMarsRover.adapters.interfaces.UserInputOutputInterface
import com.marsrover.NasaMarsRover.domain.entities.Direction
import com.marsrover.NasaMarsRover.domain.entities.Position

class RoverInputAdapter(private val userInputOutput: UserInputOutputInterface) {
    fun getUserRovers(): List<Pair<Position, Direction>>? {
        val roversData = mutableListOf<Pair<Position, Direction>>()
        userInputOutput.outputToUser("Enter number of rovers:")
        val numberOfRovers = userInputOutput.readUserInput().toIntOrNull()

        if (numberOfRovers == null || numberOfRovers < 1) {
            userInputOutput.outputToUser("Invalid number of rovers.")
            return null
        }

        for (i in 1..numberOfRovers) {
            userInputOutput.outputToUser("Enter initial position for rover $i in format 'X Y DIRECTION':")
            val roverInput = userInputOutput.readUserInput().split(' ')

            if (roverInput.size != 3) {
                userInputOutput.outputToUser("Invalid input for rover.")
                return null
            }

            val x = roverInput[0].toIntOrNull()
            val y = roverInput[1].toIntOrNull()
            val direction = validateDirection(roverInput[2])

            if (x == null || y == null || direction == null) {
                userInputOutput.outputToUser("Invalid data for rover.")
                return null
            }

            roversData.add(Position(x, y) to direction)
        }

        return roversData
    }

    private fun validateDirection(dir: String): Direction? {
        return try {
            Direction.valueOf(dir.toUpperCase())
        } catch (e: IllegalArgumentException) {
            null
        }
    }
}
