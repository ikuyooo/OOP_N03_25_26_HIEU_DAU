public class App extends Thread{
    public static int count = 0;

    public static void main(String[] args){
        App threadExp = new App();

        threadExp.start();
        System.out.println("running..");
        
        System.out.println("count " + count);
        count = count + 1;
        System.out.println("In gia tri count: " + count);
        System.out.println("In gia tri count dong so 10: " + count);
    }
    public void run(){
        count++;
        
    }

}