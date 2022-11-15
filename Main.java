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
        
        listeArgument = UtilDebat.creationArgument(sc); // On demande le nombre d'argument et on crée les arguments

        
        boucle = true;
        while (boucle){
            try{
                System.out.println(listeArgument);
                System.out.println("1) Ajouter une contradiction \n2) Fin");
                int choix = sc.nextInt(); // On sticke ici le choix de l'utilistateur
                if (choix == 1){ //Ajouter une contradiction

                   UtilDebat.ajoutContradiction(sc,listeArgument);
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

        boucle = true;
        while(boucle){

            try{
                System.out.println("1) Ajouter un argument \n2)retirer un argument \n3) Verifier la solution \n4) fin");
                int choix = sc.nextInt();

                if (choix ==1){//ajouter un argument
                    //boolean contientArgu = false;
                    
                    //Argument a = null;

                    UtilDebat.ajouterArgument( listeArgument, ensembleE, sc);

                    /*for(int j = 0 ; j<listeArgument.size();j++) {
                        if (listeArgument.get(j).isEqual(argu)) {
                            contientArgu = true;
                            a = listeArgument.get(j);
                        }
                    }
                    if (contientArgu){
                        for(int j = 0 ; j<ensembleE.size();j++) {
                            if (ensembleE.get(j).isEqual(argu)) {
                                ajout=true;
                            }
                        }
                        
                    }
                    else{
                        System.out.println("L'argument n'existe pas");
                    }
                    
                    if(ajout || !contientArgu){ // Si ajout==true, alors argument deja ajouté a la liste
                        System.out.println("Argument déjà ajouté ou inexistant");
                    }
                    else {
                        ensembleE.add(a);	
                    }
                System.out.println(listeArgument);
                System.out.println("Ensemble à verifier " +ensembleE);*/

                

                }
                if (choix ==2){// supprimer un argument
                    //boolean refus = false;
                   
                    UtilDebat.supprimerArgument(listeArgument, ensembleE, sc);

                    /*for(int k = 0 ; k<ensembleE.size();k++) {
                        if (ensembleE.get(k).isEqual(argu)) {
                            refus = true;
                            ensembleE.remove(k);
                        
                        } 
                    }
                    if(!refus){
                        System.out.println("Argument n'est pas dans la liste ou déjà supprimé");
                    }

                System.out.println(listeArgument);
                System.out.println("Ensemble à verifier " +ensembleE);*/

                }

                if (choix ==3){// Verifier la solution
                    
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