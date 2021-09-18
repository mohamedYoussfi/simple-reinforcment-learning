public class BellmanTest {
    int statesNumber=3;
    int actionsNumber=2;
    int epochsNumber=200;
    double[][] Q={{0,0},{0,0},{0,0}};
    double[][][] T={
            { {0.50,0.00,0.50},{0.00,0.00,1.00}},
            { {0.70,0.10,0.20},{0.00,0.95,0.05}},
            { {0.40,0.00,0.60},{0.30,0.30,0.40}}
    };
    double[][][] R={
            { {0.00,0.00,0.00},{0.00,0.00,0.00}},
            { {5.00,0.00,0.00},{0.00,0.00,0.00}},
            { {0.00,0.00,0.00},{-1.0,0.00,0.00}}
    };
    double gamma=0.95;
    public static void main(String[] args) {
        new BellmanTest().start();
    }

    private double max(double[] data){
        double max=data[0];
        for (int i = 1; i <data.length ; i++) {
            if(data[i]>max) max=data[i];
        }
        return max;
    }

    private int argMax(double[] data){
        int index=0;
        double max=data[index];
        for (int i = 1; i <data.length ; i++) {
            if(data[i]>max) {
                index=i;
            }
        }
        return index;
    }
    private void start() {
        for (int i = 0; i < epochsNumber; i++) {
            for (int s = 0; s <statesNumber ; s++) {
                for (int a = 0; a <actionsNumber ; a++) {
                    double somme=0;
                    for (int sp = 0; sp <statesNumber ; sp++) {
                        somme+=T[s][a][sp]*(R[s][a][sp]+gamma*max(Q[sp]));
                    }
                    Q[s][a]=somme;
                }
            }
            System.out.println("---------------------------------");
            System.out.println("Itération => "+i);
            for (int s = 0; s <statesNumber ; s++) {
                for (int a = 0; a <actionsNumber ; a++) {
                    String text=String.format("State %s, Action %s => %s",s,a,Q[s][a]);
                    if(a==argMax(Q[s])) text+=" <==";
                    System.out.println(text);
                }

            }
        }
    }
}
