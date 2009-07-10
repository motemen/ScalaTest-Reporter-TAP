package net.tokyoenvious.scalatest

import org.scalatest._

class TAPReporter extends Reporter {
    var testNumber : Int = 1

    override def runStarting (testCount : Int) =
        println ("1.." + testCount)

    override def runAborted (report : Report) =
        println ("Bail out! " + report.message)

    override def suiteStarting (report : Report) =
        println ("# Suite " + report.name)

    override def testSucceeded (report : Report) = {
        report match {
            case specReport : SpecReport =>
                if (specReport.includeInSpecOutput) {
                    println ("ok " + testNumber + " " + specReport.formattedSpecText)
                }
            case _ =>
                println ("ok " + testNumber + " - " + report.name)
        }
        testNumber = testNumber + 1
    }

    override def testFailed (report : Report) = {
        println ("not ok " + testNumber + " - " + report.name)
        println ("# " + report.message)
        testNumber = testNumber + 1
    }

    override def infoProvided(report: Report) =
        report match {
            case specReport : SpecReport =>
                if (specReport.includeInSpecOutput) {
                    println ("# " + specReport.formattedSpecText)
                }
            case _ =>
                println ("# " + report.name)
        }
}
