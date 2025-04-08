import java.util.*;
import Jama.Matrix;
class Truss{
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the weight of w ");
        double w=sc.nextDouble();
        System.out.println("Enter L1 ");
        double l1=sc.nextDouble();
        System.out.println("Enter L2 ");
        double l2=sc.nextDouble();
        System.out.println("Enter L3");
        double l3=sc.nextDouble();

        if((w>=60)&&(w<=180)&&(l1>=100)&&(l1<=300)&&(l2>=200)&&(l2<=400)&&(l3>=400)&&(l3<=700)){
            String lp[]=new String[4];
            lp[0]="0 0";
            lp[1]=l2+" 0";
            lp[2]=l2+" "+l1;
            lp[3]=(l2+l3)+" 0";

            double[][]a=new double[8][8];
            double b[]=new double[8];

            w=w*9.81;

            Force ax=new Force("1 0",0);
            Force ay=new Force("0 1",0);
            // Force bx=new Force("1 0",0);
            Force by=new Force("0 1",0);

            Force wy=new Force("0 -1",w);

            Joint j[]=new Joint[4];
            for(int i=0;i<4;i++){
                j[i]=new Joint(lp[i]);
                j[i].setMat(8);

            }

            j[0].addForce(ax);
            j[0].addForce(ay);
            //j[1].addForce(bx);
            j[1].addForce(by);

            j[0].connect(j[1]);
            j[0].connect(j[2]);
            j[1].connect(j[2]);
            j[1].connect(j[3]);
            j[2].connect(j[3]);

            j[3].addForce(wy);

            
            for(int i=0;i<4;i++){//to convert to 8x8 mat
                double [][]m1=j[i].matl;//for storing
                double []m2=j[i].matr;
                a=Joint.add(a,Joint.con(8,m1,2*i));
                b=Joint.add(b,Joint.con(8,m2,2*i));

            }
           
            double c[][]=new double[8][1];
            for(int i=0;i<8;i++){
                c[i][0]=b[i];
            }
           
            Matrix A=new Matrix(a);
            Matrix B=new Matrix(c);
            Matrix x=A.solve(B);
            System.out.println("Matrix A");
            A.print(9,4);
            System.out.println("Matrix B");
            B.print(9,4);
            
            System.out.println("Ax ="+x.get(0,0));
            System.out.println("Ay ="+x.get(1,0));
            System.out.println("By ="+x.get(2,0));

            System.out.println("Fab ="+x.get(3,0));
            System.out.println("Fac ="+x.get(4,0));
            System.out.println("Fbc ="+x.get(5,0));
            System.out.println("Fbd ="+x.get(6,0));
            System.out.println("Fcd ="+x.get(7,0));

            
        }
        else{
            System.out.println("The value(s) you have entered is out of range");
        }
    }
   
}
