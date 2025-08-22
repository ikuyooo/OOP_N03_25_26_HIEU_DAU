public class tet{
    public String hoten;
    public int tuoi;
    public String gmail;

    public tet(String hoten , int tuoi , String gmail) {
        this.tuoi = 20;
        this.gmail = gmail;
        this.hoten = hoten;
    }
    public String getHoten(){
        return hoten;
    }
    public void setHoten(String hoten) {
        this.hoten = hoten;
    }
    public int getTuoi() {
        return tuoi;
    }
    public void setTuoi(int tuoi){
        this.tuoi = tuoi;
    }
    public String getGmail(){
        return gmail;
    }
    public void setGmail(String gmail){
        this.gmail = gmail;
    }
    public void HienThiThongTin(){
        System.out.println("Thong tin ca nhan :");
        System.out.println("Ho va ten : " +hoten);
        System.out.println("Tuoi : " +tuoi);
        System.out.println("Gmail : " +gmail);
    }
}
