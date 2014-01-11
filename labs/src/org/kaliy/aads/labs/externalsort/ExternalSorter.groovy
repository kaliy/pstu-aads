package org.kaliy.aads.labs.externalsort

class ExternalSorter {
    private File file
    ExternalSorter(file) {
        this.file = file
    }

    def isSorted() {
        def prev = Integer.MIN_VALUE
        def sorted = true
        file.eachLine { it, number ->
            def current = it as Integer
            if (prev > current) {
                sorted = false
                return
            }
            prev = current
        }
        sorted
    }

    def sort() {
        def counter = 1
        while (!isSorted()) {
            println "Pass ${counter++}"
            def prev = Integer.MIN_VALUE
            def tempLow = new File("${System.getProperty("java.io.tmpdir")}f1")
            def tempHigh = new File("${System.getProperty("java.io.tmpdir")}f2")
            def tempLowWriter = tempLow.newPrintWriter()
            def tempHighWriter = tempHigh.newPrintWriter()
            file.eachLine {
                def currentNumber = it as Integer
                if (prev <= currentNumber) {
                    tempLowWriter.println currentNumber
                } else {
                    tempHighWriter.println currentNumber
                }
                prev = currentNumber
            }
            tempHighWriter.close()
            tempLowWriter.close()
            def highReader = tempHigh.newReader()
            def lowReader = tempLow.newReader()
            def currentHigh = highReader.readLine() as Integer
            def currentLow = lowReader.readLine() as Integer
            def resultFileWriter = file.newPrintWriter()
            while (null != currentHigh || null != currentLow) {
                if (null == currentHigh || currentLow < currentHigh) {
                    resultFileWriter.println currentLow
                    currentLow = lowReader.readLine() as Integer
                } else {
                    resultFileWriter.println currentHigh
                    currentHigh = highReader.readLine() as Integer
                }
            }
            resultFileWriter.close()
            highReader.close()
            lowReader.close()
        }
    }


    static main (args) {
        println "Generate ${FileGenerator.DATA_FILE_NAME}"
        new FileGenerator().generate(FileGenerator.DATA_FILE_NAME)
        println "Sorting file ${FileGenerator.DATA_FILE_NAME}"
        new ExternalSorter(new File(FileGenerator.DATA_FILE_NAME)).sort()
    }
}
