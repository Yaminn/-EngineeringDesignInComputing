import java.util.ArrayList;
/**
 * The State class stores a state's current city, goalCost(gCost), previous state and list of desired trips.
 * It also implements comparable, as it compares final costs, and contains a method to remove a desired trip
 * once it has been fulfilled.
 * 
 * @author 			Yaminn Aung, z5061216
 * 					 (Last Edited: 8/04/2016)
 *
 */
public class State implements Comparable<State> {
	private City curr;
	private int gCost;
	private State prevState;
	ArrayList<Trip> desiredTrips;
	
	/**
	 * Constructs a State with the state's city, cost and list of desired trips to be fulfilled
	 * , as well as this state's previous state.
	 * @param city					The City of the state.
	 * @param cost					The total actual cost so far.								
	 * @param desired				The list of desired trips that have yet to be fulfilled.
	 * @param prev					This state's previous state.
	 * 
	 * <DL>
	 * <DT><b>Preconditions:</b>
	 * <DD>- cost >= 0.
	 * </DL>
	 * 
	 * 
	 */
	public State(City city, int cost, ArrayList<Trip> desired, State prev){
		curr = city;
		gCost = cost;
		prevState = prev;
		
		desiredTrips = new ArrayList<Trip>();
		desiredTrips.addAll(desired);
		
	}


	@Override
	public int compareTo(State o) {
		return getFCost() - o.getFCost();
	}
	
	/**
	 * Calculates the final cost of this state.
	 * <DL>
	 * <DT><b>Preconditions:</b>
	 * <DD>- Heuristic calculated is >= 0
	 * <DD>- gCost >=0.
	 * </DL>
	 * @return	Integer representation of an estimated final cost for this path.
	 * @see		Heuristic#getHCost(State)
	 * @see		#gCost
	 */
	public int getFCost(){
		Heuristic h = new HeuristicDesiredTrips();
		return this.gCost + h.getHCost(this);
	}
	

	/**
	 * Removes a trip from the State's list of desired trips.
	 * <DL>
	 * <DT><b>Postconditions:</b>
	 * <DD>- If this trip exists within the list, the list size will decrease by at least 1.
	 * <DD>- (Multiple if this trip is in the list more than once)
	 * </DL> 
	 * @see		#desiredTrips
	 */
	public void removeDesiredTrip(City to, City from){
		for(int i=0; i<desiredTrips.size(); i++){
			Trip t = desiredTrips.get(i);
			if(from.equals(t.getFrom()) && to.equals(t.getTo())){
				desiredTrips.remove(i);
			}
		}
	}
	
	/**
	 * Returns the city of the state.
	 * @return City of the state.
	 */
	public City getCity(){
		return this.curr;
	}
	
	/**
	 * Returns the actual cost so far.
	 * @return Integer representation of current cost.
	 */
	public int getGCost(){
		return this.gCost;
	}
	
	/**
	 * Returns the state's previous state
	 * @return State that is this state's previous.
	 */
	public State getprevState(){
		return this.prevState;
	}
	
	/**
	 * Returns the state's list of trips to be fulfilled.
	 * @return ArrayList<Trip> of trips yet to be completed.
	 */
	public ArrayList<Trip> getDesiredTrips(){
		return this.desiredTrips;
	}
	
	
}
