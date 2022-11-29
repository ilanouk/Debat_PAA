import java.io.FileNotFoundException;
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
        ArrayList <ArrayList<Argument>> listeSolution= new ArrayList<ArrayList<Argument>>(); //Pour fichier
        ArrayList <ArrayList<Argument>> dejaVu= new ArrayList<ArrayList<Argument>>();
        int autoManuel= -1;
        

        while (boucle){
            System.out.println("Voulez vous remplir les arguments et contradictions : \n 1) Manuellement \n 2) A l'aide d'un fichier");
            autoManuel = sc.nextInt();
            if (autoManuel== 1 || autoManuel== 2){
                boucle = false;
            }
            
        }
        
        boucle = true;
        if (autoManuel==1){ // Manuel
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
            boucle=true;
            while(boucle){

                try{
                    System.out.println("1) Ajouter un argument \n2) Supprimer un argument \n3) Vérifier la solution \n4) Fin");
                    int choix = sc.nextInt();

                    if( choix==1){
                        
                        UtilDebat.ajouterArgument(listeArgument, ensembleE, sc);
                    }

                    else if (choix ==2){
                        UtilDebat.supprimerArgument(listeArgument, ensembleE, sc);
                    }

                    else if (choix ==3){// Vérifier la solution
                        String solution = UtilDebat.verifSolution(ensembleE);
                        if( solution.equals("Admissible") ){
                            System.out.println(ensembleE+" est une solution admissible");
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
        else if (autoManuel == 2){ //Fichier
            
            System.out.println("Entrez le chemin du fichier");
            //String nomFichier = sc.next();
            
            
            UtilDebat.lireFichier("./test.txt", listeArgument); //test.txt n'est un parametre que pour les tests , decommenter la ligne avant apres les tests
            //-------------- TEST CI DESSOUS ------------------ et dessus
            
            UtilDebat.sauvegarde(listeArgument,"./sauvegarde");
            UtilDebat.solutionAdmissible(listeArgument, listeSolution);
    
            boucle = true;
            boolean solDejaDemande = false;
            while(boucle){

                try{
                    ArrayList <Argument> solAdmissible;
                    
                    System.out.println("1) Chercher une solution admissible \n2) Chercher une solution préférée \n3) Sauvegarder la solution \n4) Fin");
                    int choix = sc.nextInt();

                    if( choix==1){
                        solDejaDemande = true;
                        solAdmissible =UtilDebat.solutionAdmissible(listeArgument,dejaVu);
                        if (solAdmissible==null){
                            dejaVu.clear();
                            solAdmissible =UtilDebat.solutionAdmissible(listeArgument,dejaVu);
                        }
                        System.out.println("solution admissible : "+solAdmissible);
                    }

                    else if (choix ==2){
                        solDejaDemande = true;
                        solAdmissible =UtilDebat.solutionPreferee(listeArgument,dejaVu);
                        System.out.println("solution préférée : "+solAdmissible);   
                    }

                    else if (choix ==3){// Sauvegarder la solution
                        if (solDejaDemande){
                            System.out.println("Veuiller entrer le chemin du fichier ");
                            String lien =sc.nextLine();
                            UtilDebat.sauvegarde(ensembleE, lien);
                        }
                        else{
                            System.out.println("Veuillez séléctionner d'abord le choix 1 ou 2");
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
}