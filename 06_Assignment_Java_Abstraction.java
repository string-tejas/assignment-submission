/*
 *
 * 										Instrument
 * 										|
 * 						-------------------------------------
 						|									|
 				MusicalInstrument					MeasuringInstrument
 						|									|
 			-------------------------			---------------------------
 			|			|			|			|			|			|
 			SBMI		ABMI		DBMI		Time		Space		Electrical
 			|			|			|           |           |           |
 			Guitar		Flute		Drums      StopWatch   Ruler       Voltmeter
 			Sitar		Harmonica	Tabla      HourGlass   SONAR       Wattmeter
 			Violin		Trumpet		Tambourine  Timer      Telescope   Ohmmeter
 */


public class Abstract {
    public static void main(String[] args) {

    }
}

abstract class Instrument {
}

abstract class MeasuringInstrument extends Instrument {
    abstract void measure();
}

abstract class TimeMeasuringInstrument extends MeasuringInstrument {
    abstract void reset();
}

class StopWatch extends TimeMeasuringInstrument {
    @Override
    void measure() {
        System.out.println("Measuring time elapsed since click");
    }

    @Override
    void reset() {
        System.out.println("Hit the top button");
    }
}

class HourGlass extends TimeMeasuringInstrument {
    @Override
    void reset() {
        System.out.println("Flip");
    }

    @Override
    void measure() {
        System.out.println("Measure how much sand has fallen down");
    }
}

class Timer extends TimeMeasuringInstrument {
    @Override
    void reset() {
        System.out.println("Setting the final time");
    }

    @Override
    void measure() {
        System.out.println("Measuring how much time is remaining");
    }
}

abstract class SpaceMeasuringInstrument extends MeasuringInstrument {
    abstract void setInitial();
}

class Ruler extends SpaceMeasuringInstrument {
    @Override
    void setInitial() {
        System.out.println("Align the start of scale with the object to measure");
    }

    @Override
    void measure() {
        System.out.println("Measuring distance, length, etc");
    }
}

class SONAR extends SpaceMeasuringInstrument {
    @Override
    void setInitial() {
        System.out.println("Send waves");
    }

    @Override
    void measure() {
        System.out.println("Measure, distance, nearby objects with the help of returned waves");
    }
}

class Telescope extends SpaceMeasuringInstrument {
    @Override
    void setInitial() {
        System.out.println("Align the glass to the body to observe");
    }

    @Override
    void measure() {
        System.out.println("Magnified subject");
    }
}

abstract class ElectricalMeasuringInstrument extends MeasuringInstrument {

    abstract void connectToSource();
}

class VoltMeter extends ElectricalMeasuringInstrument {
    @Override
    void connectToSource() {
        System.out.println("Attach to wire");
    }

    @Override
    void measure() {
        System.out.println("Measuring in volts");
    }
}

class OhmMeter extends ElectricalMeasuringInstrument {
    @Override
    void connectToSource() {
        System.out.println("Attach to wire");
    }

    @Override
    void measure() {
        System.out.println("Measuring resistance in ohms");
    }
}

class Wattmeter extends ElectricalMeasuringInstrument {
    @Override
    void connectToSource() {
        System.out.println("Attach to wire");
    }

    @Override
    void measure() {
        System.out.println("Measruing power");
    }
}

abstract class MusicalInstrument extends Instrument {
    abstract void play();
}

abstract class AirBasedMusicalInstrument extends MusicalInstrument {
    abstract void adjustOutlet();
}

class Flute extends AirBasedMusicalInstrument {
    @Override
    void adjustOutlet() {
        System.out.println("Use fingers to manipulate airflow of outlets");
    }

    @Override
    void play() {
        System.out.println("Playing flute");
    }
}

class Harmonica extends AirBasedMusicalInstrument {
    @Override
    void adjustOutlet() {
        System.out.println("Use palm to manipulate airflow");
    }

    @Override
    void play() {
        System.out.println("Playing Harmonica");
    }
}

class Trumpet extends AirBasedMusicalInstrument {
    @Override
    void adjustOutlet() {
        System.out.println("Use valves to adjust airflow");
    }

    @Override
    void play() {
        System.out.println("Playing trumpet");
    }
}

abstract class DrumBasedMusicalInstrument extends MusicalInstrument {
    abstract void prepareStrikingTools();
}

class Drums extends DrumBasedMusicalInstrument {
    @Override
    void prepareStrikingTools() {
        System.out.println("Use sticks for drums");
    }

    @Override
    void play() {
        System.out.println("Plahying drums");
    }
}

class Tabla extends DrumBasedMusicalInstrument {
    @Override
    void prepareStrikingTools() {
        System.out.println("Use hands for tabla");
    }

    @Override
    void play() {
        System.out.println("Playing tabla");
    }
}

class Tambourine extends DrumBasedMusicalInstrument {
    @Override
    void prepareStrikingTools() {
        System.out.println("Use hands and metal jingles");
    }

    @Override
    void play() {
        System.out.println("Playing tambourine");
    }
}


