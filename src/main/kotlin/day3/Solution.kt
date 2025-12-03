package day3

import java.io.File

class Solution (val partNumber: Int, val dataToUse: String){
    var data: List<String> = mutableListOf()
    var sum: Int = 0
    init {
        when(dataToUse){
            "sample" -> data = File("src/main/kotlin/day3/resources/sampleData.txt").readLines()
            "live" -> data = File("src/main/kotlin/day3/resources/data.txt").readLines()
            "test" -> data = File("src/main/kotlin/day3/resources/testData.txt").readLines()
        }
        when(partNumber){
            1 -> partOne()
            2 -> partTwo()
        }
    }

    fun partOne(){
        data.forEach {
            var finalJoltage = ""
            it.forEachIndexed { index, char ->
                if(finalJoltage.isEmpty() || finalJoltage.length == 1){
                    finalJoltage += char
                    return@forEachIndexed
                }

                /*
                if not at end of str ->
                  if finalJoltage[1] + char > finalJoltage ->
                      finalJoltage = finalJoltage[1] + char
                */
                //if(index < it.length-1){
                    var testInt = 0
                    if(finalJoltage.last().digitToInt() > finalJoltage.first().digitToInt()){
                         testInt = finalJoltage.last().plus(char.toString()).toInt()
                    }else{
                        testInt = finalJoltage.first().plus(char.toString()).toInt()
                    }

//                    print("testing: $testInt")

                    if(testInt > finalJoltage.toInt()){
//                        println(" -> greater than $finalJoltage, swapping in, char = ($char)")
                        finalJoltage = testInt.toString()
                    }else{
//                        println(" -> not greater than $finalJoltage")
                    }
                /*}
                else{
//                    print("At end with ($char), ")
                    var testInt =  finalJoltage.last().plus(char.toString()).toInt()

                    if(char.digitToInt() > finalJoltage[1].digitToInt()){
                        finalJoltage = finalJoltage[1] + char.toString()
                    }
                }*/
            }
                println("Adding $finalJoltage to sum")
                sum += finalJoltage.toInt()
        }
    }

    fun partTwo(){
//        Find the longest string<int> of length 12 where each digitis the highest of the ones prior??
    }

    fun getResult(): Int{return sum}
}