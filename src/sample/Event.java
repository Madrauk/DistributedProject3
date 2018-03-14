package sample;

public class Event {

    int identifier;
    int[] vector;
    int scalarClock;
    Process hostProcess;

    public Event(Process host, int id, int scalar, int[] vector){
        identifier = id;
        hostProcess = host;
        scalarClock = scalar;
        this.vector = vector;
    }

    @Override
    public String toString() {
        String stringIdentifier = "e" + identifier;
        return stringIdentifier;
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public int[] getVector() {
        return vector;
    }

    public void setVector(int[] vector) {
        this.vector = vector;
    }

    public int getScalarClock() {
        return scalarClock;
    }

    public void setScalarClock(int scalarClock) {
        this.scalarClock = scalarClock;
    }

    public Process getHostProcess() {
        return hostProcess;
    }

    public void setHostProcess(Process hostProcess) {
        this.hostProcess = hostProcess;
    }
}
