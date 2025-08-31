package story;

import java.util.*;

public class Team2Story {
    public static void main(String[] args) {
        System.out.println("\n=== OUR LIFE STORY ===\n");

        // --- Start ---
        Adult p = new Adult("Rahul", 22, Gender.MALE, true);

        // Cafeteria
        Cafeteria cafeteria = new Cafeteria(50, "Ground Floor");
        Dominos dominos = new Dominos(1001);
        FoodItem pizza = new FoodItem("Veg Pizza", 200);
        Coffee coffee = new Coffee("Cappuccino");
        cafeteria.serveFood(pizza, dominos);
        cafeteria.serveCoffee(coffee);

        // Desk & Whiteboard
        Desk desk = new Desk("Wood", 3, 75.0);
        Whiteboard wb = new Whiteboard(120.0, 90.0);
        desk.showDeskDetails();
        wb.showWhiteBoardDetails();
        System.out.println("\"This is it. The start of my career.\"\n");

        // Bike + Service
        BikeServiceStation bss = new BikeServiceStation("Hero ServicePoint");
        bss.serviceBike();

        // Reservation
        MobilePhone phone = new MobilePhone("Samsung", "Galaxy S23", 79999);
        ReservationPortal portal = new ReservationPortal();
        Table table = new Table(12);
        Reservation lunch = new Reservation(null, new Person[]{p}, new Station("Office", "OFF"), new Station("Cafeteria", "CAF"), 0, 0);
        lunch.table = table;
        phone.bookReservation(lunch);

        // Train
        portal.bookTicket(new Person[]{p}, 12767, "MUM", "AUR");

        // Football weekend
        Player messi = new Player("Messi");
        Player ronaldo = new Player("Ronaldo");
        Team barca = new Team("Barcelona");
        barca.addPlayer(messi);
        Team madrid = new Team("Real Madrid");
        madrid.addPlayer(ronaldo);
        Referee ref = new Referee("Pierluigi Collina");
        Match elClasico = new Match(barca, madrid, ref);
        elClasico.start();
        elClasico.addGoal(new Goal(messi, barca, 15));
        elClasico.addGoal(new Goal(ronaldo, madrid, 47), "penalty");
        Scoreline scoreline = new Scoreline(elClasico);
        scoreline.show();
        System.out.println("Monday morning, though, he's back at his Desk at work.\n");

        // Age 35
        Broker broker = new Broker("Santosh", "Wadala", 10000);
        RType room = new RType("3bhk");
        Location loca = new Location("Wadala");
        Preferance pref = new Preferance("Bachelor");
        Facility fac = new Facility("Furnished");
        broker.cal_rent(room.ro_type, loca.loc, pref.pref, fac.fac);

        Payment pay = new Payment(1234);
        pay.calculateTotalCost(new FoodItem[]{pizza});
        Salary sal = new Salary(50000);
        sal.doubleIt();

        // Age 50
        Child c = new Child("Inner Child", 10, Gender.MALE);

        // Reflection
        Reflection reflection = new Reflection();
        reflection.lookBack();

        System.out.println("\n=== END OF STORY ===");
    }
}

class Colleague {
    String name, department;
    int employeeId;

    public Colleague(String n, String d, int id) {
        name = n;
        department = d;
        employeeId = id;
    }

    public void showColleagueDetails() {
        System.out.println("Colleague: " + name + " (" + department + ", ID=" + employeeId + ")");
    }
}

class Cafeteria {
    int capacity;
    String location;

    public Cafeteria(int c, String l) {
        capacity = c;
        location = l;
    }

    public void serveFood(FoodItem item, Dominos d) {
        System.out.println("He walks into the Cafeteria, orders " + item.foodName + " from " + d.getName() + ".");
    }

    public void serveCoffee(Coffee coffee) {
        System.out.println("And drinks far too much coffee: " + coffee.type + ".\n");
    }
}

class MobilePhone {
    String brand, model;
    double price;

    public MobilePhone(String b, String m, double p) {
        brand = b;
        model = m;
        price = p;
    }

    public void bookReservation(Reservation r) {
        System.out.println("Using his " + brand + " " + model + ", he books a reservation for Table " + r.table.tableNo + " at the cafeteria.");
    }
}

class Desk {
    String material;
    int drawers;
    double height;

    public Desk(String m, int d, double h) {
        material = m;
        drawers = d;
        height = h;
    }

    public void showDeskDetails() {
        System.out.println("He gets his Desk of " + material + " with " + drawers + " drawers.");
    }
}

class Whiteboard {
    double width, height;

    public Whiteboard(double w, double h) {
        width = w;
        height = h;
    }

    public void showWhiteBoardDetails() {
        System.out.println("Nearby, there's a Whiteboard (" + width + "x" + height + ").");
    }
}

class Coffee {
    String type;

    Coffee(String t) {
        type = t;
    }
}

class FoodBrand {
}

class Dominos extends FoodBrand {
    int foodBrandId;

    Dominos(int id) {
        foodBrandId = id;
    }

    String getName() {
        return "Dominos";
    }
}

class FoodItem {
    String foodName;
    float foodPrice;

    FoodItem(String n, float p) {
        foodName = n;
        foodPrice = p;
    }

    public String toString() {
        return foodName;
    }
}

class Order {
    FoodItem[] foodItems;
    Table table;
    Payment payment;

    Order(FoodItem[] f, Table t, Payment p) {
        foodItems = f;
        table = t;
        payment = p;
    }
}

class Payment {
    float totalCost;
    int paymentId;

    Payment(int id) {
        paymentId = id;
    }

    public float calculateTotalCost(FoodItem[] f) {
        float c = 0;
        for (FoodItem fi : f) c += fi.foodPrice;
        totalCost = c;
        System.out.println("He pays a bill of " + c + " for his food.");
        return c;
    }
}

class Salary {
    double amount;

    Salary(double a) {
        amount = a;
    }

    void doubleIt() {
        System.out.println("His salary doubled: " + amount + " -> " + (amount * 2));
        amount *= 2;
    }
}

class Table {
    int tableNo;

    Table(int t) {
        tableNo = t;
    }
}

class Human {
    String name;

    Human(String n) {
        name = n;
    }
}

class Person extends Human {
    public Person(String n, int a, Gender g) {
        super(n);
    }
}

class Adult extends Person {
    boolean window;

    public Adult(String n, int a, Gender g, boolean w) {
        super(n, a, g);
        window = w;
        System.out.println("He starts his first day at the office, full of excitement.");
        System.out.println("A Person, fresh out of college, now officially an Adult: " + n + ".\n");
    }
}

class Child extends Person {
    public Child(String n, int a, Gender g) {
        super(n, a, g);
        System.out.println("At 50, the Child in him is long gone...\n");
    }
}

class Player extends Human {
    Player(String n) {
        super(n);
    }
}

class Referee extends Human {
    Referee(String n) {
        super(n);
    }

    void blowWhistle() {
        System.out.println(name + " blows the whistle!");
    }
}

class Team {
    String teamName;
    List<Player> players = new ArrayList<>();

    Team(String n) {
        teamName = n;
    }

    void addPlayer(Player p) {
        players.add(p);
    }
}

class Goal {
    Player scorer;
    Team team;
    int minute;

    Goal(Player s, Team t, int m) {
        scorer = s;
        team = t;
        minute = m;
    }
}

class Match {
    Team t1, t2;
    Referee ref;
    List<Goal> goals = new ArrayList<>();
    int g1 = 0, g2 = 0;

    Match(Team a, Team b, Referee r) {
        t1 = a;
        t2 = b;
        ref = r;
    }

    void start() {
        ref.blowWhistle();
        System.out.println("At 25, he watches Football every weekend. The match begins: " + t1.teamName + " vs " + t2.teamName);
    }

    void addGoal(Goal g) {
        goals.add(g);
        if (g.team == t1) g1++;
        else g2++;
        System.out.println("Goal by " + g.scorer.name + " (" + g.team.teamName + ") at minute " + g.minute);
    }

    void addGoal(Goal g, String type) {
        addGoal(g);
        System.out.println("(" + type + ")");
    }
}

class Scoreline {
    Match m;

    Scoreline(Match m) {
        this.m = m;
    }

    void show() {
        System.out.println("Final Scoreline: " + m.t1.teamName + " " + m.g1 + "-" + m.g2 + " " + m.t2.teamName + "\n");
    }
}

enum Gender {MALE, FEMALE, OTHER}

class Station {
    String name, code;

    Station(String n, String c) {
        name = n;
        code = c;
    }
}

class Train {
    String name, pnr;
    int code;
    Station start, end;
    Station[] stations;
    int totalSeats, bookedSeats;
    float totalDistanceKM, costPerKM;

    Train(String n, String p, int c, Station s, Station e, Station[] st, int ts, int bs, float d, float cp) {
        name = n;
        pnr = p;
        code = c;
        start = s;
        end = e;
        stations = st;
        totalSeats = ts;
        bookedSeats = bs;
        totalDistanceKM = d;
        costPerKM = cp;
    }

    float distanceFrom(Station a, Station b) {
        return 100;
    }
}

class Reservation {
    Train train;
    Person[] members;
    Station from, to;
    Table table;
    float journeyDistance;
    float bill;

    Reservation(Train t, Person[] m, Station f, Station to, float d, float b) {
        train = t;
        members = m;
        from = f;
        this.to = to;
        bill = b;
    }

    public String toString() {
        return "Reservation from " + from.name + " to " + to.name + " cost=" + bill;
    }
}

class ReservationDatabase {
    Station m = new Station("Mumbai", "MUM");
    Station a = new Station("Aurangabad", "AUR");
    Train t = new Train("ABC Express", "PNR123", 12767, m, a, new Station[]{m, a}, 1000, 10, 420, 2.1f);

    Train getTrain(int code) {
        return t;
    }

    Station getStation(String code) {
        return code.equals("AUR") ? a : m;
    }
}

class Portal {
}

class ReservationPortal extends Portal {
    ReservationDatabase db = new ReservationDatabase();

    Reservation bookTicket(Person[] mem, int code, String from, String to) {
        Train t = db.getTrain(code);
        Station f = db.getStation(from), s = db.getStation(to);
        float dist = t.distanceFrom(f, s);
        float cost = mem.length * dist * t.costPerKM;
        Reservation r = new Reservation(t, mem, f, s, dist, cost);
        System.out.println("Sometimes, he books a Train ticket through the ReservationPortal: " + r);
        return r;
    }
}

class Broker extends Human {
    String loc;
    int fee;

    Broker(String n, String l, int f) {
        super(n);
        loc = l;
        fee = f;
        System.out.println("At 35, the story doesn’t change. Broker " + n + " keeps calling with offers in " + loc + ".");
    }

    public float cal_rent(int room, int loc, float pref, float fac) {
        float rent = room + (loc * pref * fac) + fee;
        System.out.println("Broker calculates rent offer: " + rent + "\n");
        return rent;
    }
}

class Location {
    int loc;

    Location(String n) {
        if (n.equals("Wadala")) loc = 25;
        else loc = 50;
    }
}

class Preferance {
    float pref;

    Preferance(String n) {
        pref = n.equals("Bachelor") ? 3f : 4.5f;
    }
}

class RType {
    int ro_type;

    RType(String n) {
        ro_type = n.equals("3bhk") ? 50000 : 20000;
    }
}

class Facility {
    float fac;

    Facility(String n) {
        fac = n.equals("Furnished") ? 2f : 1.5f;
    }
}

class BikeServiceStation {
    String name;

    BikeServiceStation(String n) {
        name = n;
    }

    void serviceBike() {
        System.out.println("He rides his bike daily, stopping at " + name + " whenever the chain squeaks.\n");
    }
}

class Reflection {
    void lookBack() {
        System.out.println("And then one day, he looks back...");
        System.out.println("Life is-a pattern, has-a schedule, uses-a weekend for temporary relief.\n");
        System.out.println("This isn’t just his life. This is our life.");
        System.out.println("We start excited. We grind for years. We repeat routines.");
        System.out.println("We cheer for small wins - pizza, coffee, football goals.");
        System.out.println("Before we know it, we’ve gone from 25 to 50, staring at the same whiteboard, waiting for the weekend.");
    }
}
