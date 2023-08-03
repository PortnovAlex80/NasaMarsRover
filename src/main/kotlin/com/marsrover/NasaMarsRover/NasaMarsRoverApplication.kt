package com.marsrover.NasaMarsRover

import com.marsrover.NasaMarsRover.adapters.RoverInputAdapter
import com.marsrover.NasaMarsRover.domain.entities.Direction
import com.marsrover.NasaMarsRover.domain.entities.Position
import com.marsrover.NasaMarsRover.ui.ConsoleUserInputOutput
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

//@SpringBootApplication
class NasaMarsRoverApplication

fun main(args: Array<String>) {
//	runApplication<NasaMarsRoverApplication>(*args)

	val userInputOutput = ConsoleUserInputOutput()
	val roverInputAdapter = RoverInputAdapter(userInputOutput)
	val numberOfRovers = roverInputAdapter.getNumberOfRovers()

	val rovers = mutableListOf<Pair<Position, Direction>>()
	for (i in 1..numberOfRovers) {
		val roverData = roverInputAdapter.getRoverData(i)
		if (roverData != null) {
			rovers.add(roverData)
			val (position, direction) = roverData
			println("Добавлен ровер №$i. Позиция: ${position.x},${position.y}. Направление: $direction")
		}
	}

	println("Созданы роверы:")
	rovers.forEachIndexed { index, (position, direction) ->
		println("Ровер номер ${index+1}: Позиция: ${position.x},${position.y}, Направление: $direction")
	}
}
