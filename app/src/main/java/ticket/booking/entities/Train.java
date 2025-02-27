package ticket.booking.entities;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Train {
    private String trainId;
    private String trainNo;
    private List<List<Integer>> seats;
    private Map<String, String> stationTimes;
    private List<String> stations;

    public Train(String trainId,String trainNo,List<List<Integer>> seats,Map<String,String> stationTimes,List<String> stations){
        this.trainId = trainId;
        this.trainNo = trainNo;
        this.seats = seats;
        this.stationTimes = stationTimes;
        this.stations = stations;
    }

//   getter

    public String getTrainId(){return this.trainId;}

    public String getTrainNo(){return this.trainNo;}

    public List<List<Integer>> getSeats(){return this.seats;}

    public Map<String,String> getStationTimes(){return  this.stationTimes;}

    public List<String> getStations(){return  this.stations;}

    public String getTrainInfo(){return String.format("Train ID: %s Train No: %s ",this.trainId,this.trainNo);}

}
