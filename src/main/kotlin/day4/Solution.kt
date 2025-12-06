package day4

import java.io.File
import kotlin.math.max
import kotlin.math.min

class Solution (partNumber: Int, dataToUse: String){
    var data: List<String> = mutableListOf()
    var accessibleRoles: Int = 0
    init {
        when(dataToUse){
            "sample" -> data = File("src/main/kotlin/day4/resources/sampleData.txt").readLines()
            "live" -> data = File("src/main/kotlin/day4/resources/data.txt").readLines()
            "test" -> data = File("src/main/kotlin/day4/resources/testData.txt").readLines()
        }
        when(partNumber){
            1 -> partOne()
            2 -> partTwo()
        }
    }

    fun partOne(){
        fun checkAccessible(before: String, after: String): Boolean{
            var numberOfRoles = 0
            before.forEach {
                if(it == '@') numberOfRoles++
            }
            after.forEach {
                if(it == '@') numberOfRoles++
            }

            println("$before C $after: [$before, @, $after] Number of adjacent rolls: $numberOfRoles " +
                    "Accessible: ${numberOfRoles <= 4}")
            return numberOfRoles <= 4
        }

        data.forEach {line ->
            line.forEachIndexed { i, c ->

                if(c == '@'){
                    val startIndex = max(i-3, 0)
                    val endIndex = min(i+3, line.lastIndex)
                    val preString = line.slice(startIndex..<i)
                    val postString = line.slice(i+1..endIndex).takeIf { it.isNotEmpty() } ?: ""
                    if(checkAccessible(preString,
                        postString)) accessibleRoles++

                }else return@forEachIndexed
            }
        }
    }
    fun partTwo(){}

    fun getResult(): Int{return accessibleRoles}
}