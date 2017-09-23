package edu.rit.se.testsmells;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class MappingDetector {

    TestFile testFile;
    String productionFileName, productionFilePath;

    public MappingDetector() {
        productionFileName = "";
        productionFilePath = "";
    }


    public TestFile detectMapping(String testFilePath) throws IOException {

        testFile = new TestFile(testFilePath);

        int index = testFile.getFileName().toLowerCase().lastIndexOf("test");
        if(index == 0){
            //the name of the test file starts with the name 'test'
            productionFileName = testFile.getFileName().substring(4, testFile.getFileName().length());
        }else{
            //the name of the test file ends with the name 'test'
            productionFileName = testFile.getFileName().substring(0, testFile.getFileName().toLowerCase().lastIndexOf("test")) + ".java";
        }

        Path startDir = Paths.get(testFile.getProjectRootFolder());
        Files.walkFileTree(startDir, new FindJavaTestFilesVisitor());

        testFile.setProductionFilePath(productionFilePath);

        return testFile;
    }

    public class FindJavaTestFilesVisitor extends SimpleFileVisitor<Path> {
        @Override
        public FileVisitResult visitFile(Path file,
                                         BasicFileAttributes attrs)
                throws IOException {
            if (file.getFileName().toString().toLowerCase().equals(productionFileName.toLowerCase())) {
                productionFilePath = file.toAbsolutePath().toString();
                return FileVisitResult.TERMINATE;
            }
            return FileVisitResult.CONTINUE;
        }
    }
}

