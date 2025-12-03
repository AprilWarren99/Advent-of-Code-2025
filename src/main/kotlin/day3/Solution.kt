package day3

import java.io.File

class Solution (val partNumber: Int, val dataToUse: String){
    var data: List<String> = mutableListOf()
    var sum: Int = 0
    init {
        when(dataToUse){
            "sample" -> data = File("src/main/kotlin/day3/resources/sampleData.txt").readLines()
            "live" -> data = File("src/main/kotlin/day3/resources/data.txt").readLines()
        }
        when(partNumber){
            1 -> partOne()
        }
    }

    fun partOne(){

    }

    fun getResult(): Int{return sum}
}