package edu.rit.se.testfilemapping;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static List<TestFile> testFiles;

    public static void main(String[] args) throws IOException {
        if (args == null) {
            System.out.println("Please provide the file containing the paths to the collection of test files");
            return;
        }
        if(!args[0].isEmpty()){
            File inputFile = new File(args[0]);
            if(!inputFile.exists() || inputFile.isDirectory()) {
                System.out.println("Please provide a valid file containing the paths to the collection of test files");
                return;
            }
        }


        System.out.println("Started!");

        MappingDetector mappingDetector;
        BufferedReader in = new BufferedReader(new FileReader(args[0]));
        String str;

        testFiles = new ArrayList<>();

        System.out.println("Reading input.");
        while ((str = in.readLine()) != null) {
            System.out.println("Detecting: "+str);
            mappingDetector = new MappingDetector();
            testFiles.add(mappingDetector.detectMapping(str));
        }

        System.out.println("Saving results. Total lines:"+ testFiles.size());
        ResultsWriter resultsWriter = ResultsWriter.createResultsWriter();
        List<String> columnNames = new ArrayList<>();
        List<String> columnValues = null;
        columnNames.add(0,"App");
        columnNames.add(1,"Tag");
        columnNames.add(2,"TestFilePath");
        columnNames.add(3,"ProductionFilePath");
        columnNames.add(4,"RelativeTestFilePath");
        columnNames.add(5,"RelativeProductionFilePath");
        resultsWriter.writeColumnName(columnNames);

        for (int i = 0; i < testFiles.size(); i++) {
            columnValues = new ArrayList<>();
            columnValues.add(0,testFiles.get(i).getAppName());
            columnValues.add(1,testFiles.get(i).getTagName());
            columnValues.add(2,testFiles.get(i).getFilePath());
            columnValues.add(3,testFiles.get(i).getProductionFilePath());
            columnValues.add(4,testFiles.get(i).getRelativeTestFilePath());
            columnValues.add(5,testFiles.get(i).getRelativeProductionFilePath());
            resultsWriter.writeLine(columnValues);
        }

        System.out.println("Completed!");
    }
}
