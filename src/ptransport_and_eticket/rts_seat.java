package ptransport_and_eticket;

class rts_seat {
    private int BusID, RowNo, ColumnNo, SeatNo;
    private String Availiability;
    
    public rts_seat(int SeatNo,int BusID, int RowNo, int ColumnNo, String Availiability){
        this.SeatNo=SeatNo;
        this.BusID=BusID;
        this.RowNo=RowNo;
        this.ColumnNo=ColumnNo;
        this.Availiability=Availiability;
    }
    public int getSeatNo(){
        return SeatNo;
    }
    public int getBusID(){
        return BusID;
    }
    public int getRowNo(){
        return RowNo;
    }
    public int getColumnNo(){
        return ColumnNo;
    }
    public String getAvailiability(){
        return Availiability;
    }
}
