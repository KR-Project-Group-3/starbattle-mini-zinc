package star_battle.model.embasp;

import it.unical.mat.embasp.languages.IllegalAnnotationException;
import it.unical.mat.embasp.languages.ObjectNotValidException;
import it.unical.mat.embasp.languages.asp.ASPMapper;
import it.unical.mat.embasp.languages.asp.AnswerSet;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ASPDynamicInstanceGenerator {

    private ASPConnector aspConnector;
    private final String encodingFilePath = "models" + File.separator + "instance_generator.asp";
    private int matrixDimension;
    private List<AnswerSet> answerSets;
    private ArrayList<Integer> instancesReturned;

    public ASPDynamicInstanceGenerator(int matrixDimension) {
        this.aspConnector =  new ASPConnector(this.encodingFilePath);
        this.matrixDimension = matrixDimension;
        this.instancesReturned = new ArrayList<>();
    }

    public void generateInstances(){

        this.aspConnector.putFact("row(1.." + this.matrixDimension + ").");
        this.aspConnector.putFact("column(1.." + this.matrixDimension + ").");
        this.aspConnector.putFact("sector(1.." + this.matrixDimension + ").");

        try {
            ASPMapper.getInstance().registerClass(MatrixSectorMapping.class);
        } catch (IllegalAnnotationException | ObjectNotValidException e) {
            e.printStackTrace();
        }

        this.answerSets = this.aspConnector.startSync();
    }

    public int[][] getNextMatrix(){
    	
        if(instancesReturned.size() >= this.answerSets.size())
             return null;
        
        int[][] matrixToReturn = new int[this.matrixDimension][this.matrixDimension];
        int instanceIndexToReturn = new Random().nextInt(this.answerSets.size());
        while (instancesReturned.contains(instanceIndexToReturn))
            instanceIndexToReturn = new Random().nextInt(this.answerSets.size());

        AnswerSet answerSet = this.answerSets.get(instanceIndexToReturn);
        instancesReturned.add(instanceIndexToReturn);

        try {
            for (Object o : answerSet.getAtoms()) {
                if(o instanceof MatrixSectorMapping) {
                    MatrixSectorMapping matrixSectorMapping = (MatrixSectorMapping) o;
                    matrixToReturn[matrixSectorMapping.getRow() - 1][matrixSectorMapping.getColumn() - 1] =
                            matrixSectorMapping.getSector();
                }
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | InstantiationException e) {
            e.printStackTrace();
        }

        return matrixToReturn;
    }
}
