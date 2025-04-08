import java.util.*;

class Joint{
    double matl[][]; // LHS
    double matr[]; // RHS
    double i,j;
    static int count=0; // all object share count
  
    public Joint(String a){//givng position as inpt

        StringTokenizer stk=new StringTokenizer(a);
        i=Double.parseDouble(stk.nextToken());
        j=Double.parseDouble(stk.nextToken());

    }

     void  connect(Joint b){
        double ri=b.i-i;  //finl-intil
        double rj=b.j-j;

        double z=Math.sqrt(ri*ri+rj*rj);

        matl[0][count]=ri/z;//left matrix of A.storing
        matl[1][count]=rj/z;//fill

        b.matl[0][count]=-ri/z;//eqn of B
        b.matl[1][count]=-rj/z;// matrix of b
        count++;
    }

    void setMat(int n){//to set  2x8 nd 2x1 matrix
        matl=new double[2][n];
        matr=new double[2];//define
       
    }

    void addForce(Force f){//represnt force acting on joint
        if(f.magnitude==0){
     
            matl[0][count]=f.i;
            matl[1][count]=f.j;
            
            count++;
            
        }
        else{//values will be stored in right matrix
            matr[0]+=-f.magnitude*(f.i);
            matr[1]+=-f.magnitude*(f.j);
 
        }
        
    }

    public static double[][] con(int len,double[][]a,int p){//making length same 2x8 into 8x8
        double [][]st=new double[len][len];
        st[p]=a[0];
        st[p+1]=a[1];
        return st;
    }

    public static double[] con(int len,double[]a,int p){////making length same 2x1 into 8x1
        double []st=new double[len];
        st[p]=a[0];
        st[p+1]=a[1];
        return st;
    }

    public static double[][] add(double [][]a,double [][]b){//adng four 8x8 mat
        double [][]sum=new double[a.length][a.length];
        for(int i=0;i<a.length;i++){
            for(int j=0;j<a.length;j++){
                sum[i][j]=a[i][j]+b[i][j];
            }
        }
        return sum;
    }

    public static double[] add(double []a,double []b){//add 4 8x1
        double []sum=new double[a.length];
        for(int i=0;i<a.length;i++){

            sum[i]=a[i]+b[i];

        }
        return sum;
    }

}
