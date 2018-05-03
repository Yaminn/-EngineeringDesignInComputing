
/**
 * Link stores all primary values associated with with a particular link
 * (e.g. where it starts and ends, travel time). 
 * It contains methods to add links, find links and retrieve the time length of a particular
 * link.
 * 
 * @author 			Yaminn Aung, z5061216
 * 					 (Last Edited: 6/06/2016)
 *
 */
public class Link {
	private int travelTime;
	private City to;
	private City from;
	
	/**
	 * Constructs a Link with specific information - where the link begins and ends,
	 * and transfer time.
	 * @param cityTo				The City this link begins from.
	 * @param cityFrom				The City this links ends at.
	 * @param time					The time it takes to get from cityFrom to cityTo.							 
	 * <DL>
	 * <DT><b>Preconditions:</b>
	 * <DD>- cityTo and cityFrom are unique.
	 * <DD>- travelTime is >= 0.
	 * </DL>
	 */
	public Link(City cityTo, City cityFrom, int time){
		this.to = cityTo;
		this.from = cityFrom;
		this.travelTime = time;
	}
	
	/**
	 * Returns the travel time for this link.
	 * @return Integer that represents the travel time of this link.
	 */
	public int getTravelTime(){
		return this.travelTime;
	}
	
	/**
	 * Returns the City this link goes to.
	 * @return City at the end of this link.
	 */
	public City getTo(){
		return this.to;
	}
	
	/**
	 * Returns the City this link is from
	 * @return City that is the link's start
	 */
	public City getFrom(){
		return this.from;
	}

	/**
	 * This method returns the other end of this particular link and does not check if the
	 * City contains the link passed into it.
	 * @param cityFrom				The city requested to check if this link is from it.							 
	 * @return The City on the other end of this link.
	 */
	public City getOtherEnd(City cityFrom){
		if(from == cityFrom){
			return to;
		}
		return from;
	}
}
