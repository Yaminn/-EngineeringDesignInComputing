/**
 * Heuristic interface to calculate the estimated cost of the journey left to take.
 * 
 * @author 			Yaminn Aung, z5061216
 * 					 (Last Edited: 8/04/2016)
 *
 */
public interface Heuristic {
	public int getHCost(State s);
}
