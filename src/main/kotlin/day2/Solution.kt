package org.aoc.day2

import java.math.BigInteger

class Solution (val partNumber: Int, val useTestData: Int) {
    var idRanges: List<String> = listOf()
    var invalidIDs: MutableList<BigInteger> = mutableListOf()
    var sum: BigInteger = 0.toBigInteger()
    var validID = true

    init {
        idRanges = if(useTestData == 0){
                "16100064-16192119,2117697596-2117933551,1-21,9999936269-10000072423,1770-2452,389429-427594,46633-66991,877764826-877930156,880869-991984,18943-26512,7216-9427,825-1162,581490-647864,2736-3909,39327886-39455605,430759-454012,1178-1741,219779-244138,77641-97923,1975994465-1976192503,3486612-3602532,277-378,418-690,74704280-74781349,3915-5717,665312-740273,69386294-69487574,2176846-2268755,26-45,372340114-372408052,7996502103-7996658803,7762107-7787125,48-64,4432420-4462711,130854-178173,87-115,244511-360206,69-86".split(',')
            }else if(useTestData == 1){
                "11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862,565653-565659,824824821-824824827,2121212118-2121212124".split(',')
            }else{
                "11-22,23-25".split(',')
            }

        when(partNumber){
            1 -> idRanges.forEach({
                println("processing $it")
                partOne(it)
                println(sum)
            })
            2 -> idRanges.forEach({
                println("processing $it")
                partTwo(it)
                print("Sum ($sum)\nInvalid ID's $invalidIDs\n")
            })

        }

    }
    fun getBounds(range: String): Set<BigInteger>?{
        val lowerBound = range.split('-')[0].toBigInteger()
        val upperBound = range.split('-')[1].toBigInteger()
        if(lowerBound > upperBound){
            println("Invalid range input, skipping $lowerBound - $upperBound")
            return null
        }
        return setOf(upperBound, lowerBound)
    }
    fun partOne(range: String){
        val upperBound = getBounds(range)?.elementAt(0)
        val lowerBound = getBounds(range)?.elementAt(1)

        if(upperBound == null || lowerBound == null) return

        var id=lowerBound
        while (id <= upperBound) {
            if (id.toString().length % 2 != 0) {
                id++
                continue
            }
            val startString = id.toString().slice(0..<id.toString().length / 2)
            val endString = id.toString().slice(id.toString().length / 2..<id.toString().length)
            if (startString == endString) {
                validID = false
            }
            if (!validID) {
                println("Invalid ID($id), $startString, $endString")
                invalidIDs.add(id)
                sum += id
                println(invalidIDs.size)
                validID = true
            }
        id++
        }
    }
    fun partTwo(range: String){
        val upperBound = getBounds(range)?.elementAt(0)
        val lowerBound = getBounds(range)?.elementAt(1)

        if(upperBound == null || lowerBound == null) return
        var id = lowerBound
        invalidIDs = mutableListOf()

        fun checkRepeat(testStr: String, seq: String): Boolean{
            var index = 0
            var last = ""
            var testString = testStr
            while (index < testStr.length){
                println("Testing ($testString), Last ($last)")
                if(testString == last){
                    println("repeating numbers found in $testString")
                    return true
                }else{
                    last = testString
                    testString = seq.slice(index..<seq.length)
                    index++
                }
            }
            return false
        }

        fun rec(testStr: String, sequence: String): Boolean{
            if(testStr.length == 1){
                return checkRepeat(testStr, sequence)
            } else {
                if (checkRepeat(testStr, sequence)) return true
                else rec(testStr.slice(1..<testStr.length), sequence)
            }
            return false
        }

        fun checkSequence(seq: String): Boolean{
            var tmp = ""
            for(c in seq){
                tmp += c
                if(rec(tmp, seq)) return true
            }
            return false
        }

        while(id <= upperBound){
            if(checkSequence(id.toString())){
                sum += id
                invalidIDs.add(id)
            }
            id++
        }
    }
}