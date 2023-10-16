package Elements;

import java.util.Hashtable;
import java.util.Map;
public abstract class Elemental {
    public String ANSI_Code = "\u001B[0m";
    public Map<String, Integer> BonusMalusEnergy = new Hashtable<String, Integer>();
    public Map<String, Integer> BonusMalusMovement = new Hashtable<String, Integer>();
}
