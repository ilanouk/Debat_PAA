import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main (String[]args){
        boolean boucle=true;
        Scanner sc = new Scanner(System.in);
        System.out.println("Combien d'argument y a t il dans le débat");
        int nb = sc.nextInt();

        while (boucle){
            System.out.println("1) Ajouter une contradiction \n 2) Fin");
            int choix = sc.nextInt(); // On sticke ici le choix de l'utilistateur

            //Ajout d'une contradiction
            if (choix == 1){
                System.out.println("Entrez l'argument cible puis tout les arguments qu'il contredis ex : cible A1 A2");
                String contradiction = sc.next();
                StringTokenizer stContradiction = new StringTokenizer(contradiction);
                Argument A = new Argument(stContradiction.nextToken());
                while(stContradiction.hasMoreElements()){
                    
                }
            }

            //Quitter l'ajout d'argument
            if (choix==2){
                //initialiser solution à vide
                ArrayList solAdmissible = new ArrayList<>();

                System.out.println("******************");
                System.out.println("1) Ajouter un argument;");
                System.out.println("2) Retirer un argument;");
                System.out.println("3) Vérifier la solution;");
                System.out.println("4) Fin;");

                int choix2 = sc.nextInt();

                switch(choix2){
                    case(1):
                    System.out.println("Nom de l'argument à ajouter : ");
                    

                }
            }






            sc.close();
        }
    }
}
