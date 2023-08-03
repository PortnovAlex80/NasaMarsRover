package com.marsrover.NasaMarsRover

import com.marsrover.NasaMarsRover.adapters.RoverInputAdapter
import com.marsrover.NasaMarsRover.ui.ConsoleUserInputOutput
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

//@SpringBootApplication
class NasaMarsRoverApplication

fun main(args: Array<String>) {
//	runApplication<NasaMarsRoverApplication>(*args)

	val userInputOutput = ConsoleUserInputOutput()
	val roverInputAdapter = RoverInputAdapter(userInputOutput)
	roverInputAdapter.getUserRovers()?.let { rovers ->
		for ((position, direction) in rovers) {
			println("Rover at position ${position.x},${position.y} facing $direction")
		}
	}
}
