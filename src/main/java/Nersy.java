import user.User;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import user.UserDriver;

import java.util.concurrent.Callable;

import java.io.File;

@Command(name = "Nersy")
public class Nersy implements Callable<Integer>{
    @Option(names = "--rm", description = "Remove the output file if it exists")
    boolean remove;
    @Parameters(index = "0", paramLabel = "STEP", description = "Specify the step of the data manipulation process")
    String step;
    @Parameters(index="1", paramLabel = "SRC", description = "data source to process")
    String src;
    @Parameters(index="2", paramLabel = "INPUT", description = "Input directory")
    String input;
    @Parameters(index="3", paramLabel = "OUTPUT", description = "Output directory")
    String output;

    @Override
    public Integer call() throws Exception{
        if (remove){
            File dir = new File(output);
            deleteDirectory(dir);
        }
        switch (src){
            // TODO: Add new data source to process from here
            case "user":{
                UserDriver user = new UserDriver();
                switch (step.toLowerCase()){
                    case "profiling":
                        user.runProfiling(new String[]{input, output});
                }
                break;
            }
            default:{
                break;
            }
        }
        return 0;
    }
    private static void deleteDirectory(File file)
    {
        if (file.listFiles() != null) {
            for (File subfile : file.listFiles()) {
                if (subfile.isDirectory()) {
                    deleteDirectory(subfile);
                }
                subfile.delete();
            }
        }
        file.delete();
    }
    public static void main(String[] args) {
        int exitCode = new CommandLine(new Nersy()).execute(args);
        System.exit(exitCode);
    }
}
