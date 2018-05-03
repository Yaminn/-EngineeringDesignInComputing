import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * TripPlanner acts to find the optimal path (i.e, the path with the shortest total travel time)
 * while exploring many different cities and taking into account trips that the user would like
 * to schedule.
 * 
 * This class, TripPlanner, serves to read user input/commands from the input to
 * call the appropriate method given a valid command, run the A* Search Algorithm and a heuristic
 * to find an optimal path and prints the final path.
 * 
 * The Heuristic for this trip planner is admissible as it uses the State's list of trips that have
 * yet to be completed, or the path that requires the least travels to complete the journey, to estimate
 * a cost. Hence, the heuristic function calculates the minimum total amount of travel time left before the
 * journey is completed. The Heuristic included in TripPlanner is of complexity O(N) as it linearly traverses
 * through the state's trips.
 * 
 * @author 			Yaminn Aung, z5061216
 * 					 (Last Edited: 6/05/16)
 *
 */
public class TripPlanner {
	private ArrayList<City> cities;
	private ArrayList<Trip> desiredTrips;
	private int numNodesExpanded;

	/**
	 * Main function for TripPlanner handles the input calls the correct method.
	 * @param args data passed in from standard input.
	 */
	public static void main(String[] args) {
		TripPlanner planner = new TripPlanner();
		Scanner sc = null;
	      try
	      {
	    	  sc = new Scanner(new FileReader(args[0])); 
	          
	          while(sc.hasNext()){
	        	  String [] params = sc.nextLine().split(" ");
	      
	        	  if((params[0]).equalsIgnoreCase("Transfer")){
	        		  Integer transferTime = Integer.parseInt(params[1]);
	        		  String city = params[2];
	        		  
	        		  planner.addCity(city, transferTime);

	        		  
	        	  }
	        	  else if((params[0]).equalsIgnoreCase("Time")){
	        		  Integer travelTime = Integer.parseInt(params[1]);
	        		  String to = params[2];
	        		  String from = params[3];
	        	
	        		  City cityTo = planner.findCity(to);
	        		  City cityFrom = planner.findCity(from);
	        		  
	        		  cityFrom.addLink(cityTo, travelTime);
	        		  cityTo.addLink(cityFrom, travelTime);
	    
	        		  
	        	  } else if((params[0].equalsIgnoreCase("Trip"))){
	        		  
	        		  String from = params[1];
	        		  String to = params[2];
	        		  
	        		  City cityTo = planner.findCity(to);
	        		  City cityFrom = planner.findCity(from);
	        		  
	        		  Trip trip = new Trip(cityTo, cityFrom, cityFrom.timeTravelled(cityTo));
	        		  planner.desiredTrips.add(trip);
	        		  
	        	  }
	          }
	      }
	      catch (FileNotFoundException e) {}
	      finally
	      {
	          if (sc != null) sc.close();
	      }
	     City start = planner.findCity("London");
		 planner.aStarSearch(start);

	}
	
	/**
	 * Constructs a TripPlanner with user's desired trips stored in a list and
	 * the cities on the map.
	 * @see			City
	 * @see			Trip
	 */
	public TripPlanner(){
		cities = new ArrayList<City>();
		desiredTrips = new ArrayList<Trip>();
		numNodesExpanded = 0;
	}
	
	/**
	 * Adds a city to the map which is constructed by TripPlanner and adds it
	 * to the list of cities the map has.
	 * @param name					City's name.
	 * @param transferTime			Time it takes to transfer out of this city.								
	 * 
	 * <DL>
	 * <DT><b>Preconditions:</b>
	 * <DD>- name is unique to this city,
	 * <DD>- transferTime is => 0.
	 * </DL>
	 * @see							City
	 */
	private void addCity(String name, int transferTime){
		City city = new City(name, transferTime);
		this.cities.add(city);
	}
	
	/**
	 * This method finds a specific city when requesting, using the city's
	 * name to search through the list of cities.
	 * @param name					City's name to be found.
	 * <DL>
	 * <DT><b>Preconditions:</b>
	 * <DD>- name is unique to this city,
	 * <DD>- The city currently exists within this map.
	 * </DL>
	 * @return City					The City that was found.
	 */
	private City findCity(String name){
		City curr = null;
		City found = null;
		
		for(int i=0;i<cities.size();i++){
			curr = cities.get(i);
			if(name.equalsIgnoreCase(curr.getCityName())){
				found = curr;
				break;
			}
		}
		return found;
	}
	
	
	/**
	 * Given some state (the final state) from the A* Search, this method
	 * prints the number of nodes expanded during the search, the final
	 * cost and the trips taken to get to the current state.
	 * @param print					the state to print
	 *
	 * <DL>
	 * <DT><b>Preconditions:</b>
	 * <DD>- The journey always starts from London,
	 * <DD>- Final cost is >= the transfer time of London,
	 * <DD>- Number of nodes expanded is >= 0,
	 * <DD>- The state to be printed is the final state.
	 * </DL>
	 * @see							State
	 */
	private void printFinal(State print){
		ArrayList<State> statePath = new ArrayList<State>();
		System.out.println(numNodesExpanded + " nodes expanded");
		int finalCost = print.getGCost() - findCity("London").getTransferTime();
		System.out.println("cost = " + finalCost);
		
		while(print.getprevState() != null){
			statePath.add(print);
			print = print.getprevState();
		}
		statePath.add(print);
		Collections.reverse(statePath);
		for(int i=1; i<statePath.size(); i++){
			State to = statePath.get(i);
			System.out.println("Trip " + to.getprevState().getCity().getCityName() + " to " + to.getCity().getCityName());
		}
		
	}

	/**
	 * This method searches for the optimal path to travel along (the path with the least cost)
	 * given a list of desired trips has been given by the user. It then returns
	 * the final state which has completed the list of desired trips.
	 * 
	 * @param start					the city to start the search from
	 *
	 * <DL>
	 * <DT><b>Preconditions:</b>
	 * <DD>- The journey always starts from London,
	 * <DD>- A list of desired trips for the journey has been made.
	 * </DL>
	 * @return State				Final state, given all desired trips have been fulfilled.
	 * <DL>
	 * <DT><b>PostConditions:</b>
	 * <DD>- No Cities, Trips, or States will be altered, (States will be made),
	 * <DD>- Returned state contains optimal path to complete journey and desired trips.
	 * </DL>					
	 * @see 						State
	 * @see							Trip
	 * @see							City
	 */
	private State aStarSearch(City start){
		PriorityQueue<State> pq = new PriorityQueue<State>();
		State startState = new State(start, 0, desiredTrips, null);
		pq.add(startState);
		State curr = null;
		while(!(pq.isEmpty())){
			
			curr = pq.poll();
			City currCity = curr.getCity();
			
			if(curr.getDesiredTrips().isEmpty() == true){
				printFinal(curr);
				break;
			}
			numNodesExpanded +=1;
			
			boolean added = false;
			for(Trip t : curr.getDesiredTrips()){
				
				if(currCity.equals(t.getFrom())){
						State s = new State(t.getTo(), curr.getGCost() + currCity.timeTravelled(t.getTo()), curr.getDesiredTrips(), curr);
						s.removeDesiredTrip(t.getTo(), t.getFrom());
						pq.add(s);
						
						added = true;
				}
			}
			
			if(added == false){
				for(City c : cities){
					if(!(c.equals(currCity))){
							State s = new State(c, curr.getGCost() + currCity.timeTravelled(c), curr.getDesiredTrips(), curr);
							pq.add(s);
					}
				}
			}
				
		}
			
		return curr;
	}
}
