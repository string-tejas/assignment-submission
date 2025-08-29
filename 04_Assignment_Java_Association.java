// Association.java
public class Association {
    public static void main(String[] args) {
        ReservationPortal portal = new ReservationPortal();

        System.out.println("Starting booking process by providing members, destination and train code\n\n");
        Person[] members = {
                new Adult("Tejas", 22, Gender.MALE, true),
                new Child("Sajet", 13, Gender.MALE),
        };

        String from = "MUM";
        String to = "AUR";

        Reservation ticket = portal.bookTicket(members, 12767, from, to);

        System.out.println("Your ticket has been booked\n");
        System.out.println("Your Details are as follows\n===================");
        System.out.println(ticket);

    }
}

// reservation

enum Gender {
    MALE, FEMALE, OTHER
}

class Person {
    String name;
    int age;
    Gender gender;

    public Person(String name, int age, Gender gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
}

class Adult extends Person {
    boolean isWindowPreferred;

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
    String name;
    String code;

    public Station(String name, String code) {
        this.name = name;
        this.code = code;
    }
}

class Train {
    String name;
    String pnr;
    int code;

    Station start;
    Station end;
    Station[] stations;

    int totalSeats;
    int bookedSeats;
    float totalDistanceKM;
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

    public String toString() {
        String header = "Ticket booked from " + from.name + " to " + to.name +
                "\nCost : " + this.bill + "\nMembers = " + this.members.length +
                "\n------------ Members ------------\n";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < members.length; i++) {
            Person p = members[i];
            String gender = p.gender == Gender.MALE ? "M" : "F";
            sb.append(p.name + " (" + p.age + ")  -  " + gender + "\n");
        }
        return header + sb.toString();
    }
}

class Database { }

class ReservationDatabase extends Database {
    Station mumbaiStation = new Station("Mumbai", "MUM");
    Station aurStation = new Station("Aurangabad", "AUR");

    Station[] allStations = {
            mumbaiStation,
            aurStation
    };

    Train[] allTrains = {
            new Train("ABC Express", "124JDfJK", 12767, mumbaiStation, aurStation, new Station[]{mumbaiStation, aurStation}, 1000, 10, 420f, 2.1f)
    };

    Train getTrain(int code) {
        return allTrains[0];
    }

    Station getStation(String code) {
        if (code.equals("AUR")) return aurStation;
        return mumbaiStation;
    }
}

class Portal {
    String url;
}

class ReservationPortal extends Portal {
    ReservationDatabase database = new ReservationDatabase();
    String url = "myticketbooking.com";

    Reservation bookTicket(Person[] m, int code, String from, String to) {
        Train t = database.getTrain(code);
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
}
