package day6

import java.io.File
import java.math.BigInteger

class Solution (partNumber: Int, dataSource: String) {
    var sum: BigInteger = 0.toBigInteger()
    var data: List<String> = listOf()
    init{
        when(dataSource){
            "sample" -> data = File("src/main/kotlin/day6/resources/sampleData.txt").readLines()
            "live" -> data = File("src/main/kotlin/day6/resources/data.txt").readLines()
        }
        when(partNumber){
            1 -> partOne()
            2 -> partTwo()
        }
    }

    fun partOne(){
        var problems: MutableMap<Int, String> = mutableMapOf()
        data.forEach { row ->
            var problemNumber = 1
            var lastChar = 'k'
            row.forEach { c ->
                if(c == ' ' && lastChar in "1234567890") {
                    problemNumber++
                }
                if(c in "+*"){
                    problems[problemNumber] = problems.getOrPut(problemNumber) { "" } + "|" + c.toString()
                    problemNumber++
                    return@forEach
                }
                if(c != ' ') {
                    problems[problemNumber] = problems.getOrPut(problemNumber) { "" } + c.toString()
                    lastChar = c
                } else {
                    problems[problemNumber] = problems.getOrPut(problemNumber) { "" } + "|"
                    lastChar = c
                }
            }
        }

        problems.keys.forEach { problemNumber ->
            problems[problemNumber]?.split(" ")?.forEach {
                if(it.elementAt(0) !in "1234567890"){
                    problems[problemNumber] = it.replace("|||", "")
                        .replace("||", ",")
                        .replace('|', ',')
                        .replace(",,", ",")
                        .substring(1)

                }else{
                    problems[problemNumber] = it.replace("|||", "")
                        .replace("||", ",")
                        .replace('|', ',')
                        .replace(",,", ",")
                }
            }
        }
        val problemSolutions: MutableMap<Int, BigInteger> = mutableMapOf()
        problems.keys.forEach { key ->
            var problemSolution: BigInteger = 0.toBigInteger()
            if(problems[key] != null) {
                if (problems[key]!!.get(problems[key]!!.length-1) == '+') {
                    println(problems[key]?.split(','))
                    for(i in problems[key]!!.split(',')){
                        if(i != "+") problemSolution += i.toBigInteger()
                    }
                    sum += problemSolution
                }
                else if (problems[key]!!.get(problems[key]!!.length-1) == '*') {
                    println(problems[key]?.split(','))
                    for(i in problems[key]!!.split(',')){
                        if(i != "*") {
                            if(problemSolution == 0.toBigInteger()){
                                problemSolution = i.toBigInteger()
                            }else {
                                problemSolution *= i.toBigInteger()
                            }
                        }
                    }
                    sum += problemSolution
                }
            }
            problemSolutions[key] = problemSolution
        }
        println(problemSolutions)
    }

    fun partTwo(){}
    fun getResults(): BigInteger{ return(sum) }
}