import java.util.ArrayList;


// polymorphism, encapsulation, abstraction
public class Association {
    public static void main(String[] args) {
        ReservationPortal portal = new ReservationPortal();

        // original
        System.out.println("Starting booking (Specific Stations) process by providing members, destination and train code\n");
        // class extension
        Person[] members = {
                new Adult("Jack", 42, Gender.MALE, true),
                new Child("Jill", 13, Gender.FEMALE),
        };

        String from = "MUM";
        String to = "AUR";

        Reservation ticket = portal.bookTicket(members, 12767, from, to);

        System.out.println("Your ticket has been booked\n");
        System.out.println("Your Details are as follows\n===================");
        System.out.println(ticket);


        // full journey
        System.out.println("Starting booking (Full Journey) process by providing members, destination and train code\n");

        Reservation ticketFull = portal.bookTicket(members, 12767);

        System.out.println("Your ticket has been booked\n");
        System.out.println("Your Details are as follows\n===================");
        System.out.println(ticketFull);

        // anonymous
        System.out.println("Starting booking (Anonymous) process by providing members, destination and train code\n");

        Reservation ticketAnon = portal.bookTicket(4, 12767, from, to);

        System.out.println("Your ticket has been booked\n");
        System.out.println("Your Details are as follows\n===================");
        System.out.println(ticketAnon);
    }
}

// reservation

enum Gender {
    MALE, FEMALE, OTHER
}

class Person {
    public String name;
    public int age;
    public Gender gender;

    public Person(String name, int age, Gender gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
}

class Adult extends Person {
    public boolean isWindowPreferred;

    public Adult(String name, int age, Gender gender, boolean isWindowPreferred) {
        super(name, age, gender);
        this.isWindowPreferred = isWindowPreferred;
    }
}

class Child extends Person {
    public Child(String name, int age, Gender gender) {
        super(name, age, gender);
    }
}

class Station {
    public String name;
    public String code;

    public Station(String name, String code) {
        this.name = name;
        this.code = code;
    }
}

class Train {
    public String name;
    public String pnr;
    public int code;

    public Station start;
    public Station end;
    public Station[] stations;

    public int totalSeats;
    public int bookedSeats;
    public float totalDistanceKM;
    float costPerKM;

    public Train(String name, String pnr, int code, Station start, Station end, Station[] stations, int totalSeats, int bookedSeats, float totalDistanceKM, float costPerKM) {
        this.name = name;
        this.pnr = pnr;
        this.code = code;
        this.start = start;
        this.end = end;
        this.stations = stations;
        this.totalSeats = totalSeats;
        this.bookedSeats = bookedSeats;
        this.totalDistanceKM = totalDistanceKM;
        this.costPerKM = costPerKM;
    }

    float distanceFrom(Station from, Station to) {
        return 100F;
    }

    float distanceFrom(String from, String to) {
        Station fromStation = stations[0];
        Station toStation = stations[0];
        return 120f;
    }
}

class Reservation {
    Train train;
    Person[] members;
    Station from;
    Station to;
    float journeyDistance;
    float bill;

    public Reservation(Train train, Person[] members, Station from, Station to, float journeyDistance, float bill) {
        this.train = train;
        this.members = members;
        this.from = from;
        this.to = to;
        this.journeyDistance = journeyDistance;
        this.bill = bill;
    }

    public Reservation(Train train, int totalMembers, Station from, Station to, float journeyDistance, float bill) {
        this.train = train;
        ArrayList<Person> al = new ArrayList<Person>();
        for (int i = 0; i < totalMembers; i++) {
            al.add(new Person("Anonymous", -1, Gender.OTHER));
        }
        this.members = al.toArray(new Person[0]);
        this.from = from;
        this.to = to;
        this.journeyDistance = journeyDistance;
        this.bill = bill;
    }
    public String toString() {
        String header = "Ticket booked from " + from.name + " to " + to.name +
                "\nCost : " + this.bill + "\nMembers = " + this.members.length +
                "\n------------ Members ------------\n";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < members.length; i++) {
            Person p = members[i];
            String gender = p.gender == Gender.MALE ? "M" : p.gender == Gender.FEMALE ? "F" : "Other";
            sb.append(p.name + " (" + p.age + ")  -  " + gender + "\n");
        }
        return header + sb.toString();
    }
}
class ReservationDatabase {
    private Station mumbaiStation;
    private Station aurStation;
    private Station[] allStations;
    private Train[] allTrains;

    public ReservationDatabase() {
        mumbaiStation = new Station("Mumbai", "MUM");
        aurStation = new Station("Aurangabad", "AUR");
        allStations = new Station[]{
                mumbaiStation,
                aurStation
        };
        allTrains = new Train[]{
                new Train("ABC Express", "124JDfJK", 12767, mumbaiStation, aurStation, new Station[]{mumbaiStation, aurStation}, 1000, 10, 420f, 2.1f)
        };
    }

    Train getTrain(int code) {
        return allTrains[0];
    }

    Train getTrain(String pnr) {
        return allTrains[0];
    }

    Station getStation(String code) {
        if (code.equals("AUR")) return aurStation;
        return mumbaiStation;
    }
}

class Portal {
    public String url;

    public Portal(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Welcome to https://" + url;
    }
}

class ReservationPortal extends Portal {
    private ReservationDatabase database;

    public ReservationPortal() {
        super("myticketbooking.com");
        this.database = new ReservationDatabase();
    }

    Reservation bookTicket(Person[] m, int trainCode) {
        Train t = database.getTrain(trainCode);
        // check availability
        if (t.totalSeats - t.bookedSeats <= m.length) {
            System.out.println("Tickets full");
            return null;
        }
        Station toStation = database.getStation(t.start.code);
        Station fromStation = database.getStation(t.end.code);
        float distance = t.distanceFrom(fromStation, toStation);
        float costPerPerson = distance * t.costPerKM;
        float totalCost = m.length * costPerPerson;

        Reservation ticket = new Reservation(t, m, fromStation, toStation, distance, totalCost);
        return ticket;
    }

    Reservation bookTicket(Person[] m, int trainCode, Station from, Station to) {
        Train t = database.getTrain(trainCode);
        // check availability
        if (t.totalSeats - t.bookedSeats <= m.length) {
            System.out.println("Tickets full");
            return null;
        }
        Station toStation = database.getStation(to.code);
        Station fromStation = database.getStation(from.code);
        float distance = t.distanceFrom(fromStation, toStation);
        float costPerPerson = distance * t.costPerKM;
        float totalCost = m.length * costPerPerson;

        Reservation ticket = new Reservation(t, m, fromStation, toStation, distance, totalCost);
        return ticket;
    }

    Reservation bookTicket(int totalMembers, int trainCode, String from, String to) {
        Train t = database.getTrain(trainCode);
        // check availability
        if (t.totalSeats - t.bookedSeats <= totalMembers) {
            System.out.println("Tickets full");
            return null;
        }
        Station toStation = database.getStation(to);
        Station fromStation = database.getStation(from);
        float distance = t.distanceFrom(fromStation, toStation);
        float costPerPerson = distance * t.costPerKM;
        float totalCost = totalMembers * costPerPerson;

        Reservation ticket = new Reservation(t, totalMembers, fromStation, toStation, distance, totalCost);
        return ticket;
    }
    // original
    Reservation bookTicket(Person[] m, int trainCode, String from, String to) {
        Train t = database.getTrain(trainCode);
        // check availability
        if (t.totalSeats - t.bookedSeats <= m.length) {
            System.out.println("Tickets full");
            return null;
        }
        Station toStation = database.getStation(to);
        Station fromStation = database.getStation(from);
        float distance = t.distanceFrom(fromStation, toStation);
        float costPerPerson = distance * t.costPerKM;
        float totalCost = m.length * costPerPerson;

        Reservation ticket = new Reservation(t, m, fromStation, toStation, distance, totalCost);
        return ticket;
    }

    @Override
    public String toString() {
        return "Welcome to My Ticketing Portal !";
    }
}
