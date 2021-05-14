import algorithmManager.AlgorithmComparator;
import algorithmManager.AlgorithmOutputCollector;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        AlgorithmComparator algorithmComparator = new AlgorithmComparator(Integer.parseInt(args[0]));
        ArrayList<AlgorithmOutputCollector> algorithmOutputCollectors = algorithmComparator.compare();

        for(AlgorithmOutputCollector algorithmOutputCollector : algorithmOutputCollectors)
            algorithmOutputCollector.printPerformance();
    }
}
