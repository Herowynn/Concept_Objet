import Elements.*;
import Tokens.*;

import java.util.*;

public class SimulationManager {
    private static SimulationManager instance;
    private static List<Token> AllTokens = new ArrayList<>();

    public static SimulationManager getInstance(List<Elemental> masters) {
        if (instance == null) {
            instance = new SimulationManager(masters);
        }
        return instance;
    }

    private SimulationManager(List<Elemental> masters){
        for (Elemental master : masters) {
            AllTokens.addAll(master.GetTokenList());
        }
    }

    public static List<Token> GetAllTokensFromMasters() {
        return AllTokens;
    }
}
