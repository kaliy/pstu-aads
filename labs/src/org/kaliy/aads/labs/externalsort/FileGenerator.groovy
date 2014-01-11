package org.kaliy.aads.labs.externalsort

class FileGenerator {
    def static DATA_FILE_NAME = "${System.getProperty("java.io.tmpdir")}externalsortdata"
    static main(args) {
        new FileGenerator().generate(DATA_FILE_NAME)
    }

    def generate(filename) {
        def random = new Random()
        new File(filename).withWriter { writer->
            (0..50000).each {
                writer.println random.nextInt()
            }
        }
    }

}
