package ptransport_and_eticket;

class rtss_bus {
    private int BusID, TotalSeats, FairPerSeat;
    private String Class, DepartureTime,  CompanyName, DepartureDate;
    
    public rtss_bus(int BusID, String CompanyName, String Class, int TotalSeats,int FairPerSeat, String DepartureDate,String DepartureTime){
        this.BusID=BusID;
        this.CompanyName=CompanyName;
        this.Class=Class;
        this.TotalSeats=TotalSeats;
        this.FairPerSeat=FairPerSeat;
        this.DepartureDate=DepartureDate;
        this.DepartureTime=DepartureTime;
    }
    public int getBusID(){
        return BusID;
    }
    public String getCompanyName(){
        return CompanyName;
    }
   
    public String getclass(){
        return Class;
    }
    public int getTotalSeats(){
        return TotalSeats;
    }
    public int getFairPerSeat(){
        return FairPerSeat;
    }
     public String getDepartureDate(){
        return DepartureDate;
    }
    public String getDepartureTime(){
        return DepartureTime;
    }
}
