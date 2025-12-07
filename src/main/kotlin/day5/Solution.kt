package day5

import java.io.File
import java.math.BigInteger

class Solution(val partNumber: Int, val dataToUse: String) {
    var data: List<String> = mutableListOf()
    var numberOfValidIDs = 0
    init{
        when(dataToUse){
            "sample" -> data = File("src/main/kotlin/day5/resources/sampleData.txt").readLines()
            "live" -> data = File("src/main/kotlin/day5/resources/data.txt").readLines()
            "test" -> data = File("src/main/kotlin/day5/resources/testData.txt").readLines()
        }

        when(partNumber){
            1 -> partOne()
            2 -> partTwo()
        }
    }
    fun partOne(){
        var readingRanges = true
        val ranges: MutableList<Pair<BigInteger, BigInteger>> = mutableListOf()
        val ids: MutableList<BigInteger> = mutableListOf()
        data.forEach {
            if(it == "") {
                readingRanges = false
                return@forEach
            }
            if(readingRanges){
                val startRange = it.split("-")[0].toBigInteger()
                val endRange = it.split("-")[1].toBigInteger()
                ranges.add(Pair(startRange, endRange))
            }else{
                ids.add(it.toBigInteger())
            }
        }
        ids.forEach { id ->
            var found = false
            ranges.forEach { range ->
                if (range.first <= id && id <= range.second) {
                    found = true
                    return@forEach
                }
            }
            if (found) {
                numberOfValidIDs++
                println("$id found, ($numberOfValidIDs)")
            }
        }

        println("There are $numberOfValidIDs validIDs")
        println("id: ${ids.last()}")
        println("range: ${ranges.last()}")
    }

    fun partTwo(){
//        Filter out the ID's and only process the ranges

        val r = data.slice(0..<data.indexOf("")).sorted()
        val allIDs = mutableListOf<BigInteger>()
        r.forEach {
            val (start, end) = it.split('-').map{it.toBigInteger()}
            var count = start
            if(start < end){
                while(count <= end){
                    if(count !in allIDs){
                        allIDs.add(count)
                        numberOfValidIDs++
                    }
                    println("Processing range $start-$end, number $count, found $numberOfValidIDs")
                    count++
                }
            }
        }
    }

    fun getResults(): Int{return numberOfValidIDs}
}