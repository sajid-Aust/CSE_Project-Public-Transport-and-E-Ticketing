package ptransport_and_eticket;

class rts_via {
    private int RouteID, ViaID;
    private int Cost_for_AC, Cost_for_NONAC;
    private String Place, Source;
    
    public rts_via(int ViaID, int RouteID, String Source, String Place, int Cost_for_AC,int Cost_for_NONAC){
        this.ViaID=ViaID;
        this.RouteID=RouteID;
        this.Source=Source;
        this.Place=Place;
        this.Cost_for_AC=Cost_for_AC;
        this.Cost_for_NONAC=Cost_for_NONAC;
    }
    
    public int getViaID(){
        return ViaID;
    }
    
    public int getRouteID(){
        return RouteID;
    }
    
     public String getSource(){
        return Source;
    }
    
    
    public String getPlace(){
        return Place;
    }
    
       
    public int getCost_for_AC(){
        return Cost_for_AC;
    }
        
         public int getCost_for_NONAC(){
        return Cost_for_NONAC;
    }
    
}
