package star_battle.model.embasp;

import it.unical.mat.embasp.base.Handler;
import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.base.OptionDescriptor;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.languages.asp.AnswerSet;
import it.unical.mat.embasp.languages.asp.AnswerSets;
import it.unical.mat.embasp.platforms.desktop.DesktopHandler;
import it.unical.mat.embasp.specializations.dlv2.desktop.DLV2DesktopService;

import java.io.File;
import java.util.List;

public class ASPConnector {

    private String logicProgramPath;
    private Handler handler;
    private InputProgram facts;
    private InputProgram logicProgram;

    public ASPConnector(String path){
        logicProgramPath = path;
        boolean isWindows = System.getProperty("os.name")
                .toLowerCase().startsWith("windows");
        if(isWindows)
            handler = new DesktopHandler(new DLV2DesktopService("lib" + File.separator + "dlv2.exe"));
        else
            handler = new DesktopHandler(new DLV2DesktopService("lib" + File.separator + "dlv2"));
        facts = new ASPInputProgram();
        logicProgram = new ASPInputProgram();
        logicProgram.addFilesPath(logicProgramPath);

        OptionDescriptor optionDescriptor = new OptionDescriptor();
        optionDescriptor.setOptions("-n 0");

        // Remove comment to compute all possible Answer Sets
        // handler.addOption(optionDescriptor);
    }

    public void clear(){
        handler.removeAll();
        facts.clearAll();
    }

    public void putFact(Object o){
        try {
            facts.addObjectInput(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void putFact(String fact){
        facts.addProgram(fact);
    }

    public List<AnswerSet> startSync(){
        handler.addProgram(facts);
        handler.addProgram(logicProgram);
        AnswerSets answerSets = (AnswerSets) handler.startSync();
        return answerSets.getAnswersets();
    }
}
