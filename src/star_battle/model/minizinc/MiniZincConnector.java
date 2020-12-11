package star_battle.model.minizinc;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class MiniZincConnector {

    private String modelFilePath;
    private String dataFilePath;
    private Process process;

    public MiniZincConnector(String modelFilePath, String dataFilePath){
        this.modelFilePath = modelFilePath;
        this.dataFilePath = dataFilePath;
    }

    public BufferedReader returnResponse() throws IOException {

        File instanceFile = new File(this.dataFilePath);

        boolean isWindows = System.getProperty("os.name")
                .toLowerCase().startsWith("windows");

        if(isWindows) {
            this.process = Runtime.getRuntime()
                    .exec("minizinc models" + File.separator + "star_puzzle.mzn " +
                                    this.dataFilePath + " -a"
                            , null, new File(new File(instanceFile.getAbsoluteFile().getParent()).getParent()));
        } else {
            this.process = Runtime.getRuntime()
                    .exec("minizinc models" + File.separator + "star_puzzle.mzn " +
                                    this.dataFilePath + " --solver Gecode -a", null,
                            new File(new File(instanceFile.getAbsoluteFile().getParent()).getParent()));

        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        return reader;
    }
}
