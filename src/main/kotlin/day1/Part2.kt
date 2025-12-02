package org.aoc.day1

import java.io.File

class Part2(var currentIndex: Int = 0){
    var zeroCount = 0

    fun run(): Int{
        File("src/main/kotlin/day1/resources/data.txt").forEachLine {
            turnDial(it)
        }

        return zeroCount
    }

    fun turnDial(turnCode: String){
        val direction = turnCode.elementAt(0)
        val steps = turnCode.slice(1..turnCode.lastIndex).toInt()
        when(direction){
            'L' -> turnLeft(steps)
            'R' -> turnRight(steps)
        }
    }

    fun turnLeft(steps: Int){
//        print("Current: $currentIndex, Moving left $steps, ")
        var positionsMoved = 0
        while(positionsMoved < steps){
            positionsMoved++
            currentIndex++
            if (currentIndex == 100) {
                currentIndex = 0
            }

            if(currentIndex == 0){
                zeroCount += 1
            }
        }
//        println("Ending: $currentIndex")


    }

    fun turnRight(steps: Int){
//        print("Current: $currentIndex, Moving right $steps, ")
        var positionsMoved = 0
        while(positionsMoved < steps){
            positionsMoved++
            currentIndex--
            if(currentIndex == -1){
                currentIndex = 99
            }

            if(currentIndex == 0){
                zeroCount += 1
            }
        }
//        println("Ending: $currentIndex")
    }
}