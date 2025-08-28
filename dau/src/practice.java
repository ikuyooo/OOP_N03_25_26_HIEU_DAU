public class practice{
     public String Hoten;
     public String gmail;
     public int tuoi;
     public String diaChi;

 public String getHoten(){
    return Hoten;
 }
 public void setHoten(String Hoten){
    this.Hoten = Hoten;
 }
 public String getgmail(){
    return gmail;
 }
 public void setGmail(String gmail) {
        this.gmail = gmail;
    }
 public int getTuoi() {
        return tuoi;
    }
 public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }
 public String getDiaChi() {
        return diaChi;
    }
 public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
public String thongTin() {
        return "Họ tên: " + Hoten + "\n" +
               "Gmail: " + gmail + "\n" +
               "Tuổi: " + tuoi + "\n" +
               "Địa chỉ: " + diaChi;
    }
}