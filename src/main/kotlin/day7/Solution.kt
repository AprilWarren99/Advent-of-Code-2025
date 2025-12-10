package day7

import java.io.File

class Solution (partNumber: Int, dataSource: String) {
    var data: List<String> = listOf()
    var numberOfSplits = 0
    init{
        when(dataSource){
            "sample" -> data = File("src/main/kotlin/day7/resources/sampleData.txt").readLines()
            "live" -> data = File("src/main/kotlin/day7/resources/data.txt").readLines()
        }
        when(partNumber){
            1 -> partOne()
            2 -> partTwo()
        }
    }
    fun partOne() {
        data.forEachIndexed { lineNumber, line ->
            if (lineNumber == 0) return@forEachIndexed
            line.forEachIndexed { columnNumber, c ->
                if (c == '^') {
                    var splitterAbove = false
                    var splitterAdjacentAndAbove = false
                    for(ln in (0..<lineNumber).reversed()){
//                        If there is a splitter adjacent and above, before a splitter above, split.
                        var charAbove = data[ln][columnNumber]
                        var charLeft = data[ln][columnNumber-1]
                        var charRight = data[ln][columnNumber+1]
                        if(charAbove == '^'){
                            splitterAbove = true
                            break
                        }
                        if(charLeft == '^' || charRight == '^'){
                            splitterAdjacentAndAbove = true
                            break
                        }
                    }
                    if(splitterAdjacentAndAbove) numberOfSplits++
                }
            }
        }
    }
    fun partTwo(){}
//    return +1 as the first splitter always splits
    fun getResults(): Int{ return numberOfSplits + 1}
}