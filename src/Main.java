// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import Elements.*;
import Enums.Types;
import Managers.SimulationManager;
import Mapping.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Map myMap = new Map(51, 14);
        List<Master> masters = new ArrayList<>();

        SimulationManager manager = SimulationManager.getInstance(myMap);

        manager.launchSimulation();
    }
}