/**
 * This class lies within the Heuristic interface as a method of calculating a heuristic.
 * This heuristic uses a state's list of desired trips yet to be fulfilled to give a rough
 * estimation of the minimum time left to complete the desired trips, and hence the journey.
 * 
 * @author 			Yaminn Aung, z5061216
 * 					 (Last Edited: 8/04/2016)
 *
 */
public class HeuristicDesiredTrips implements Heuristic{
	
	public int getHCost(State s){
		int hCost = 0;
		for(Trip t : s.getDesiredTrips()){
			hCost += t.getTime();
		}
		return hCost;
	}


}
