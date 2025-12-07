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
                    if finalJoltage[1] + char > finalJoltage ->
                      finalJoltage = finalJoltage[1] + char
                */

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
                }
            }
                println("Adding $finalJoltage to sum")
                sum += finalJoltage.toInt()
        }
    }

    fun partTwo() {
//        Find the longest string<int> of length 12 where each digit is the highest of the ones prior??

        data.forEach{
            var currentLargest = it.reversed().slice(0..<12)
            val remainingDigits = it.reversed().slice(12..<it.length)

            remainingDigits.forEach { digit ->
//                is there a bigger digit further to in?
                var biggerDigitToCome = false
                val currentLargestLessFirst = remainingDigits.slice(1..<remainingDigits.length)

                currentLargestLessFirst.forEach{d ->
                    if(d.digitToInt() > digit.digitToInt()){
//                        there is a bigger digit further down so we should skip this
                        biggerDigitToCome = true
                    }
                }
                if(biggerDigitToCome) return@forEach

//                if there is no bigger digit to come, remove the 1st digit in currentLargest, add the
//                current digit to the end, if its a larger value, update tempStr to it
                var tempStr = currentLargest.slice(1..<currentLargest.length) + digit
                println("CL: $currentLargest\nTS: $tempStr")
                if(tempStr > currentLargest){
                    currentLargest = tempStr
                }
            }
            println("The largest is $currentLargest")
        }
    }

    fun getResult(): Int{return sum}
}