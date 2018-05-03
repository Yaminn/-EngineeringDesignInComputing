import java.util.ArrayList;

/**
 * The City class stores all primary values associated with a particular city
 * (e.g. name, transferTime), as well as a list of links the city has. 
 * It contains methods to add links to, and retrieve the time length of a particular
 * link.
 * 
 * @author 			Yaminn Aung, z5061216
 * 					 (Last Edited: 6/06/2016)
 *
 */
public class City {
	private String cityName;
	private int transferTime;
	private ArrayList<Link> links;
	
	/**
	 * Constructs a City with the city's information - City's name and transfer time.
	 * @param name					City's name.
	 * @param transferTime			Time it takes to transfer from this city.								 
	 * <DL>
	 * <DT><b>Preconditions:</b>
	 * <DD>- name is unique.
	 * <DD>- transferTime is >= 0.
	 * </DL>
	 */
	public City(String name, int transferTime){
		links = new ArrayList<Link>();
		this.cityName = name;
		this.transferTime = transferTime;
	}
	
	/**
	 * Refers to the link class to create a new link then adds this link
	 * to the city's list of links.
	 * <DL>
	 * <DT><b>Preconditions:</b>
	 * <DD>- This link does not yet exist within this city's list of links.
	 * <DD>- The parameters passed into it (cityTo and travelTime) are valid.
	 * </DL>
	 * <DL>
	 * <DT><b>Postconditions:</b>
	 * <DD>- The City's list of links size will increase by one.
	 * </DL> 
	 * @see		Link#Link(City, City, int)
	 * @see 	#links
	 */
	public void addLink(City cityTo, int travelTime){
		Link newLink = new Link(this, cityTo, travelTime);
		links.add(newLink);
	}
	
	/**
	 * Returns an integer calculation of the time it takes to travel from City A to
	 * City B.
	 * <DL>
	 * <DT><b>Preconditions:</b>
	 * <DD>- The city that is being searched for and and this city are unique.
	 * <DD>- the transfer time is >= 0.
	 * </DL>
	 * @return	Integer which represents the time it takes to travel from this city to
	 * 			another given city.
	 * @see		Link#getTravelTime()
	 * @see		Link#getOtherEnd(City)
	 * @see		#transferTime
	 */
	public int timeTravelled(City to){
		int timeTravelled = 0;
		for(Link l : links){
			if(l.getOtherEnd(this).equals(to)){
				timeTravelled = l.getTravelTime() + this.transferTime;
			}
		}
		return timeTravelled;
	}
	
	/**
	 * Returns the City's unique name.
	 * @return String that represents the city's name.
	 */
	public String getCityName(){
		return this.cityName;
	}
	
	/**
	 * Returns the transfer time of the city
	 * @return Integer that represents the transfer time.
	 */
	public int getTransferTime(){
		return this.transferTime;
	}
}
