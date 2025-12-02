package org.aoc.day1

import java.io.File

class Part1 {

    fun turnDial(dial: Set<Int>, currentIndex: Int, direction: Char, increment: Int): Int{
        var ci = currentIndex
        var inc = increment

//    Handle if increment > 99
        if(inc >= 100){
            inc = increment % 100
        }


        if(direction == 'R'){
            ci += inc
            if(ci > 99){
                ci -= 100
            }
        }else{
            ci -= inc
            if(ci < 0){
                ci += 100
            }
        }
//    println("Initial: ${currentIndex}, Moving: $direction $inc, after-op: $ci")
        return ci

    }


    fun run() {
        val dial: Set<Int> = (0..99).toSet()
        var currentDialValue = 50
        var numbersDialedTo: MutableList<Int> = mutableListOf<Int>()


        File("src/main/kotlin/day1/resources/data.txt").forEachLine {
//        println("$it: [${it.elementAt(0)}, ${it.slice(1..it.lastIndex)}]")

            currentDialValue = (turnDial(
                dial,
                currentDialValue,
                it.elementAt(0),
                it.slice(1..it.lastIndex).toInt()))
            numbersDialedTo.add(currentDialValue)
        }
        var zeroCount = 0
        numbersDialedTo.forEach {
            if(it == 0){
                zeroCount++
            }
        }
        println("\nThere are $zeroCount 0's")
    }
}