public class ReinforcmentLearningTest {
    int statesNumber=3; // Nombre de states
    int actionsNumber=2; // Nombre d'actions
    int epochsNumber=200; // Nombre d'épochs d'apprentissages
    /*
      Matrice des valeurs des actions a0 et a1 pour les états S0,S1 et S2
     */
    double[][] Q={{0,0},{0,0},{0,0}};
    /*
      Matrice des probabilités des transitions entre les états  S0,S1 et S2
     */
    double[][][] T={
            { {0.50,0.00,0.50},{0.00,0.00,1.00}},
            { {0.70,0.10,0.20},{0.00,0.95,0.05}},
            { {0.40,0.00,0.60},{0.30,0.30,0.40}}
    };
    /*
      Matrice des récompenses (Rewards) :
       - 1 récompesne positive de 5 en passant de l'état S1s S0
       - 1 récompense négative de -1 en passant de l'état S2 vers S0
     */
    double[][][] R={
            { {0.00,0.00,0.00},{0.00,0.00,0.00}},
            { {5.00,0.00,0.00},{0.00,0.00,0.00}},
            { {0.00,0.00,0.00},{-1.0,0.00,0.00}}
    };
    double gamma=0.95; // Facteur de Rabais ou facteur d'actualisation

    /**
     * Fonction principale de démarrage de l'application
     * @param args
     */
    public static void main(String[] args) {
        new ReinforcmentLearningTest().start();
    }

    /**
     * Fonction qui retourne le maximum d'un tableau de de nombres
     * @param data
     * @return
     */
    private double max(double[] data){
        double max=data[0];
        for (int i = 1; i <data.length ; i++) {
            if(data[i]>max) max=data[i];
        }
        return max;
    }


    /**
     * Fonction qui retourne dl'index correspondant à de la valeur maximale d'un tableau de données
     * @param data
     * @return
     */
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

    /**
     * Algorithme de D'apprentissage par renforcement
     * L'objctif étant de calculer la matrice d'apprentissage Q
     * qui contient la valeur qui correspond au nombre de récompenses
     * qu'on espère obtenir en prenant une action en étant dans un état
     */
    private void start() {
        for (int i = 0; i < epochsNumber; i++) { // Epochs d'apprentissage
            for (int s = 0; s <statesNumber ; s++) { // Pour chaque état S
                for (int a = 0; a <actionsNumber ; a++) { // Pour chaque action
                    /*
                     Calculer la valeur de l'action a permettant de passer de l'état S à un autre état S'
                     */
                    double somme=0;
                    for (int sp = 0; sp <statesNumber ; sp++) { // Pour chaque état S'
                        somme+=T[s][a][sp]*(R[s][a][sp]+gamma*max(Q[sp]));
                    }
                    Q[s][a]=somme;
                }
            }
            /*
             * Afficher l'évolution de l'algorithme pour chaque époch d'apprentissage
             */
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
