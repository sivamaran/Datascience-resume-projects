
import java.util.*;
public class Force {
    double i,j;
    double magnitude;
    public Force(String a,double m) {
        magnitude =m;

        StringTokenizer stk=new StringTokenizer(a);
        i=Double.parseDouble(stk.nextToken());//to convert string to double
        j=Double.parseDouble(stk.nextToken());

        double r=Math.sqrt(i*i+j*j);
        i=i/r;
        j=j/r;

    }
    
}
