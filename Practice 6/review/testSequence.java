
public class testSequence {
   public static void main(String[] args) {
        Sequence s = new Sequence(10);
        for (int i = 1; i <= 10; i++)
           s.add(Integer.toString(i));
           Selector sl = s.selector();
           while(!sl.end()) {
          System.out.println( "Callback number " + sl.current());
          sl.next();
           }
     }
}