package ptransport_and_eticket;

class rts {
    private int RouteID, NumberOfPlacesVia, Distance_in_Kilo;
    private String Source, Destination;
    
    public rts(int RouteID, String Source, String Destination,int NumberOfPlacesVia){
        this.RouteID=RouteID;
        this.NumberOfPlacesVia=NumberOfPlacesVia;
        this.Source=Source;
        this.Destination=Destination;
    }
    public int getRouteID(){
        return RouteID;
    }
    public int getNumberOfPlacesVia(){
        return NumberOfPlacesVia;
    }
   
    public String getSource(){
        return Source;
    }
    public String getDestination(){
        return Destination;
    }
}
