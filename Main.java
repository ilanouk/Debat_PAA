import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Tigran WATTRELOS
 * @author Ilan' DAUMONT-OUK
 * @authour Terry TRAN
 */


public class Main {

    public static void main (String[]args){
        ArrayList <Argument> listeArgument = new ArrayList<Argument>();
        boolean boucle=true;
        Scanner sc = new Scanner(System.in);
        ArrayList <Argument> ensembleE = new ArrayList<Argument>();
        int autoManuel= -1;
        

        while (boucle){
            System.out.println("Voulez vous remplir les arguments et contradictions : \n 1) Manuellement \n 2) A l'aide d'un fichier");
            autoManuel = sc.nextInt();
            if (autoManuel== 1 || autoManuel== 2){
                boucle = false;
            }
            
        }
        
        boucle = true;
        if (autoManuel==1){
            listeArgument = UtilDebat.creationArgument(sc); // On demande le nombre d'argument et on crée les arguments
            while (boucle){
                try{
                    System.out.println(listeArgument);
                    System.out.println("1) Ajouter une contradiction \n2) Fin");
                    int choix = sc.nextInt(); // On sticke ici le choix de l'utilistateur
                    if (choix == 1){ //Ajouter une contradiction

                    UtilDebat.ajoutContradictionSc(sc,listeArgument);
                    }

                    else if (choix == 2 ){ //FIN
                        boucle = false;
                    }

                    else{
                        System.out.println("Entrez un chiffre entre 1 et 4");
                    }
                }
                catch(InputMismatchException e){
                    System.out.println("Veuillez entrer un entier");
                    sc.nextLine();

                }



            }
        }
        if (autoManuel == 2){
            System.out.println("Entrez le chemin du fichier");
            //String nomFichier = sc.next();
            UtilDebat.lireFichier("./test.txt", listeArgument); //test.txt n'est un parametre que pour les tests , decommenter la ligne avant apres les tests
            UtilDebat.sauvegarde(listeArgument,"./sauvegarde");

        }
        boucle = true;
        while(boucle){

            try{
                System.out.println("liste des arguments : "+listeArgument + "\n" + "Ensemble à verifier" + ensembleE);
                System.out.println("1) Ajouter un argument \n2)retirer un argument \n3) Verifier la solution \n4) fin");
                int choix = sc.nextInt();

                if (choix ==1){//ajouter un argument
                
                    UtilDebat.ajouterArgument( listeArgument, ensembleE, sc);    
                }

                else if (choix ==2){
                    UtilDebat.supprimerArgument(listeArgument, ensembleE, sc);
                }

                else if (choix ==3){// Verifier la solution
                    
                    String solution = UtilDebat.verifSolution(ensembleE);
                    if (solution.equals("Admissible")){
                        System.out.println(ensembleE + " est une solution admissible");
                    }
                    else{
                        System.out.println(solution);
                    }
                }

                else if (choix ==4){ //Fin
                    boucle = false;
                }
                else{
                    System.out.println("Entrez un chiffre entre 1 et 4");
                }
            }
            catch(InputMismatchException e){
                System.out.println("Veuillez entrer un entier");
                sc.nextLine();
            }
        }sc.close();
    }
}