package aeroplane;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SeatAllocator {

	private Map<Seat, Passenger> allocation;

	private static final String CREW = "crew";
	private static final String BUSINESS = "business";
	private static final String ECONOMY = "economy";
	public static final int NUM_ROWS = 50;
	public static final int NUM_SEATS = 6;
	public static final int ASCII_A = 65;
	public static final int[] EXIT_ROWS = new int[]{1, 10, 30};
	private final int CREW_SECTION_START = 1;
	private final int BUSINESS_SECTION_START = 2;
	private final int ECONOMY_SECTION_START = 16;
	
	public SeatAllocator() {
		allocation = new HashMap<Seat, Passenger>();
	}

	@Override
	public String toString() {
		return allocation.toString();
	}
	
	private void allocateInRange(Passenger passenger,
			Seat first, Seat last) throws AeroplaneFullException {

		Seat current = first;

		while (!current.equals(last)) {
			if (!allocation.containsKey(current) && (passenger.isAdult() || !current.isEmergencyExit())) {
				allocation.put(current, passenger);
				return;
			} else {
				if (current.hasNext()) {
					current = current.next();
				} else {
					throw new AeroplaneFullException();
				}
			}
		}

		throw new AeroplaneFullException();
	}

	private static String readStringValue(BufferedReader br) throws MalformedDataException, IOException {

		String result = br.readLine();
		
		if(result == null) {
			throw new MalformedDataException();
		}
		
		return result;
		
	}

	private static int readIntValue(BufferedReader br)
			throws MalformedDataException, IOException {
		try {
			return Integer.parseInt(readStringValue(br));
		} catch(NumberFormatException e) {
			throw new MalformedDataException();
		}
	}

	private static Luxury readLuxuryValue(BufferedReader br)
			throws MalformedDataException, IOException {
		try {
			return Luxury.valueOf(readStringValue(br));
		} catch(IllegalArgumentException e) {
			throw new MalformedDataException();
		}
	}

	
	public void allocate(String filename) throws IOException, AeroplaneFullException {
		
		BufferedReader br = new BufferedReader(new FileReader(filename));

		String line;
		while((line = br.readLine()) != null) {
			try {
				if(line.equals(CREW)) {
					allocateCrew(br);
				} else if(line.equals(BUSINESS)) {
					allocateBusiness(br);
				} else if(line.equals(ECONOMY)) {
					allocateEconomy(br);
				} else {
					throw new MalformedDataException();
				}
			} catch(MalformedDataException e) {
				System.out.println("Skipping malformed line of input");
			}
		}
		
	}
	
	private void allocateCrew(BufferedReader br) throws IOException, MalformedDataException, AeroplaneFullException {
		String firstName = readStringValue(br);
		String lastName = readStringValue(br);
		CrewMember crewMember = new CrewMember(firstName, lastName);
		Seat first = new Seat(CREW_SECTION_START, (char) ASCII_A);
		Seat last = new Seat(BUSINESS_SECTION_START - 1, (char) (ASCII_A + NUM_SEATS - 1));
		allocateInRange(crewMember, first, last);
	}

	private void allocateBusiness(BufferedReader br) throws IOException, MalformedDataException, AeroplaneFullException {
		String firstName = readStringValue(br);
		String lastName = readStringValue(br);
		int age = readIntValue(br);
		Luxury luxury = readLuxuryValue(br);
		BusinessPassenger businessPassenger = new BusinessPassenger(firstName, lastName, age, luxury);
		Seat first = new Seat(BUSINESS_SECTION_START, (char) ASCII_A);
		Seat last = new Seat(ECONOMY_SECTION_START - 1, (char) (ASCII_A + NUM_SEATS - 1));
		allocateInRange(businessPassenger, first, last);
	}

	private void allocateEconomy(BufferedReader br) throws IOException, MalformedDataException, AeroplaneFullException {
		String firstName = readStringValue(br);
		String lastName = readStringValue(br);
		int age = readIntValue(br);
		EconomyPassenger economyPassenger = new EconomyPassenger(firstName, lastName, age);
		Seat first = new Seat(ECONOMY_SECTION_START, (char) ASCII_A);
		Seat last = new Seat(NUM_ROWS, (char) (ASCII_A + NUM_SEATS - 1));
		allocateInRange(economyPassenger, first, last);
	}

	public void upgrade() throws AeroplaneFullException {
		Seat economySeat = new Seat(ECONOMY_SECTION_START, (char) ASCII_A);
		while (economySeat.hasNext()) {
			if (!allocation.containsKey(( new Seat(ECONOMY_SECTION_START - 1, (char) (ASCII_A + NUM_SEATS - 1))))) {
				try {
					allocateInRange(allocation.get(economySeat), new Seat(BUSINESS_SECTION_START, (char) ASCII_A), new Seat(ECONOMY_SECTION_START - 1, (char) (ASCII_A + NUM_SEATS - 1)));
				} catch (AeroplaneFullException e) {
					e.printStackTrace();
				}
				allocation.remove(economySeat);
			} else {
				break;
			}
			economySeat = economySeat.next();
		}
	}

}
