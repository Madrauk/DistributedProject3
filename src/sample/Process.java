package sample;

import java.util.List;

public class Process {

    int identifier;
    int[] vector;
    int scalarClock;

    public Process(int id, int vectorLength) {
        identifier = id;
        vector = new int[vectorLength];
        for (int i : vector) {
            i = 0;
        }
        scalarClock = 0;
    }

    public void SendMessage(Process target, List<Event> eventList){
        scalarClock++;
        vector[identifier]++;
        Event e = new Event(this, eventList.size(), scalarClock, vector);
        eventList.add(e);//watch out to make sure java passes by reference as expected
        target.ReceiveMessage(scalarClock, vector, eventList);
    }

    public void ReceiveMessage(int scalar, int[] newVector, List<Event> eventList){
        if(scalar > scalarClock){
            scalarClock = scalar+1;
        }
        else{
            scalarClock++;
        }
        for(int i=0;i<vector.length;i++){
            if(newVector[i] > vector[i]){
                vector[i] = newVector[i];
            }
        }
        vector[identifier]++;
        Event e = new Event(this, eventList.size(), scalarClock, vector);
        eventList.add(e);
    }

}
