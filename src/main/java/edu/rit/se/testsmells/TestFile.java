package edu.rit.se.testsmells;

public class TestFile {
    private String filePath, productionFilePath;
    String[] data;

    public String getFileName() {
        return data[data.length - 1];
    }

    public String getFilePath() {
        return filePath;
    }

    public String getProductionFilePath() {
        return productionFilePath;
    }

    public void setProductionFilePath(String productionFilePath) {
        this.productionFilePath = productionFilePath;
    }

    public String getProjectRootFolder() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            stringBuilder.append(data[i] + "\\");
        }
        return stringBuilder.toString();
    }

    public String getAppName() {
        return data[3];
    }

    public TestFile(String filePath) {
        this.filePath = filePath;
        data = filePath.split("\\\\");
    }
}
