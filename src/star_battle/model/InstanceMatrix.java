package star_battle.model;

import star_battle.exceptions.InvalidInstanceDimensionException;
import star_battle.exceptions.InvalidSectorValueException;
import star_battle.exceptions.InvalidStarsNumberException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class InstanceMatrix {

    private int dimension;
    private int starsNumber;
    private int[][] sectorsMatrix;
    private String instanceFilePath;

    public InstanceMatrix(String instanceFilePath){
        this.instanceFilePath = instanceFilePath;
    }


    public void parseMatrix() throws IOException, InvalidInstanceDimensionException, InvalidSectorValueException, InvalidStarsNumberException {

        // Create the FileReader
        FileReader fileReader = new FileReader(instanceFilePath);

        // Create a BufferedReader to parse the instance file
        BufferedReader br = new BufferedReader(fileReader);
            
        // Read the first line
        String instaceLine = br.readLine();

        // Parse all lines of the input instance file
        for (int i = 0; instaceLine != null; ++i) {

            // Jump the third line (no useful infos there)
            if (i == 2 || (i >= 2 && i == 3 + this.dimension)) {
                // Read another line
                instaceLine = br.readLine();
                continue;
            }

            // Put all elements, separated by a space, of the line into an array
            String[] lineArray = instaceLine.split(" ");

            // If we are parsing the size of the matrix and
            // the stars number
            if (i < 2) {

                // Data files has always matrix dimension as second parameter
                if (i == 1) {
                    try {
                        this.dimension = Integer.parseInt(lineArray[2].substring(0, lineArray[2].length() - 1));

                        if(this.dimension <= 0)
                            throw new InvalidInstanceDimensionException("Matrix dimension is not valid");

                        this.sectorsMatrix = new int[this.dimension][this.dimension];

                    } catch (NumberFormatException e) {
                        throw new InvalidInstanceDimensionException("Matrix dimension is not valid");
                    }

                } else {

                    // Data files has always star number as first parameter
                    try {
                        this.starsNumber = Integer.parseInt(lineArray[2].substring(0, lineArray[2].length() - 1));

                        if(this.starsNumber <= 0)
                            throw new InvalidStarsNumberException("Stars number is not valid");

                    } catch (NumberFormatException e) {
                        throw new InvalidStarsNumberException("Stars number is not valid");
                    }
                }

            } else {

                for (int j = 0; j < this.dimension; ++j) {
                    try {
                        // Remove the comma or the pipe after the sector value
                        this.sectorsMatrix[i - 3][j] = Integer.parseInt(lineArray[j].substring(0, lineArray[j].length() - 1));
                    } catch (NumberFormatException e) {
                        throw new InvalidSectorValueException("Sector value is not valid");
                    }
                }
            }

            // Read another line
            instaceLine = br.readLine();
        }

        // Close the Stream
        br.close();
    }

    public int getDimension() {
        return dimension;
    }

    public int getStarsNumber() {
        return starsNumber;
    }

    public String getInstanceFilePath() {
        return instanceFilePath;
    }

    public void setInstanceFilePath(String instanceFilePath) {
        this.instanceFilePath = instanceFilePath;
    }

    public void setStarsNumber(int starsNumber) {
        this.starsNumber = starsNumber;
    }

    public int getSector(int row, int column) {
        return sectorsMatrix[row][column];
    }
}
