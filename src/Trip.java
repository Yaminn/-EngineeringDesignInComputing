/**
 * Trip stores where a desired trip, as requested by the user, is headed to, where it is from
 * as well as the total time it takes to get from "to" to "from.
 * 
 * @author 			Yaminn Aung, z5061216
 * 					 (Last Edited: 6/06/2016)
 *
 */
public class Trip {
	private City to;
	private City from;
	private int time;
	
	/**
	 * Constructs a Trip with specific information - where the trip begins and ends,
	 * and the time it takes to travel from beginning to end.
	 * @param cityTo				The City this trip ends at.
	 * @param cityFrom				The City this trip starts from.
	 * @param time					The time it takes to get from cityFrom to cityTo.							 
	 * <DL>
	 * <DT><b>Preconditions:</b>
	 * <DD>- cityTo and cityFrom are unique.
	 * <DD>- travel is >= 0.
	 * </DL>
	 * 
	 *@see							City
	 *@see							City#timeTravelled(City)
	 */
	public Trip(City to, City from, int time){
		this.to = to;
		this.from = from;
		this.time = time;
	}
	
	/**
	 * Returns the City of the end of the trip.
	 * @return City this trip ends at.
	 */
	public City getTo(){
		return this.to;
	}
	
	/**
	 * Returns the City this trip begins from.
	 * @return City this trip starts at.
	 */
	public City getFrom(){
		return this.from;
	}

	/**
	 * Returns the total time it takes to get from the beginning to the end of the trip.
	 * @return Integer that represents the total travel time of this trip.
	 */
	public int getTime(){
		return this.time;
	}
	
	
}
