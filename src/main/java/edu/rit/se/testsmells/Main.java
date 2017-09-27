package edu.rit.se.testsmells;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static List<TestFile> testFiles;

    public static void main(String[] args) throws IOException {
        MappingDetector mappingDetector;
        BufferedReader in = new BufferedReader(new FileReader("C:\\Projects\\TestSmells\\testing\\allTests.txt"));
        String str;

        testFiles = new ArrayList<>();

        while ((str = in.readLine()) != null) {
            mappingDetector = new MappingDetector();
            testFiles.add(mappingDetector.detectMapping(str));
        }


        ResultsWriter resultsWriter = ResultsWriter.createResultsWriter();
        List<String> columnNames = new ArrayList<>();
        List<String> columnValues = null;
        columnNames.add(0,"App");
        columnNames.add(1,"TestFilePath");
        columnNames.add(2,"ProductionFilePath");
        columnNames.add(3,"RelativeTestFilePath");
        columnNames.add(4,"RelativeProductionFilePath");
        resultsWriter.writeColumnName(columnNames);

        for (int i = 0; i < testFiles.size(); i++) {
            columnValues = new ArrayList<>();
            columnValues.add(0,testFiles.get(i).getAppName());
            columnValues.add(1,testFiles.get(i).getFilePath());
            columnValues.add(2,testFiles.get(i).getProductionFilePath());
            columnValues.add(3,testFiles.get(i).getRelativeTestFilePath());
            columnValues.add(4,testFiles.get(i).getRelativeProductionFilePath());
            resultsWriter.writeLine(columnValues);
        }
    }
}
