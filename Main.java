import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {

    public static void main (String[]args){
        ArrayList <Argument> listeArgument = new ArrayList<Argument>();
        boolean boucle=true;
        Scanner sc = new Scanner(System.in);
        ArrayList <Argument> ensembleE = new ArrayList<Argument>();
        int i=0;
        
        listeArgument = UtilDebat.creationArgument(sc); // On demande le nombre d'argument et on crée les arguments

        
        boucle = true;
        while (boucle){
            try{
                System.out.println(listeArgument);
                System.out.println("1) Ajouter une contradiction \n2) Fin");
                int choix = sc.nextInt(); // On sticke ici le choix de l'utilistateur
                if (choix == 1){ //Ajouter une contradiction

                    System.out.println("Entrez l'argument cible puis l'argument qu'il contredit ex : A0 A1");
                    String strDepart = sc.next();
                    String strArrivee = sc.next();
                    
                
                    Argument argDepart=null;
                    Argument argArrivee=null;
                    
                    for(i=0;i<listeArgument.size();i++){ //Dans cette boucle, on ajoute une contradiction entre les arguments donné par l'utilisateur
                        if (listeArgument.get(i).isEqual(strDepart)){ // on cherche quel argument est associé au titre rentreé
                            argArrivee = listeArgument.get(i);
                        }
                        if (listeArgument.get(i).isEqual(strArrivee)){ // on cherche quel argument est associé au titre rentreé
                            argDepart = listeArgument.get(i);
                        }
                        
                        
                    }
                    if (argArrivee!=null && argDepart!=null){//on crée une contradiction entre les titres rentrés
                    
                        argDepart.addContradiciton(new Contradiction(argDepart, argArrivee));
                        argArrivee.addParent(argDepart);
                    }
                    else{
                        System.out.println("Les arguments rentrés n'existe pas");
                    }
                    
                    
                    
                    
                }

                if (choix == 2 ){ //FIN
                    boucle = false;
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
                boolean ajout = false;
                String argu ;

                if (choix ==1){//ajouter un argument
                    boolean contientArgu = false;
                    System.out.println(listeArgument);
                    System.out.println("Ensemble à verifier " +ensembleE);
                    System.out.println("Donnez l'argument que vous voulez ajouter :");
                    argu = sc.next();
                    Argument a = null;

                    for(int j = 0 ; j<listeArgument.size();j++) {
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
                System.out.println("Ensemble à verifier " +ensembleE);

                }
                if (choix ==2){// supprimer un argument
                    boolean refus = false;
                    System.out.println(listeArgument);
                    System.out.println("Ensemble à verifier " +ensembleE);
                    System.out.println("Donnez l'argument que vous voulez supprimer :");
                    argu = sc.next();

                    for(int k = 0 ; k<ensembleE.size();k++) {
                        if (ensembleE.get(k).isEqual(argu)) {
                            refus = true;
                            ensembleE.remove(k);
                        
                        } 
                    }
                    if(!refus){
                        System.out.println("Argument n'est pas dans la liste ou déjà supprimé");
                    }

                System.out.println(listeArgument);
                System.out.println("Ensemble à verifier " +ensembleE);

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

                if (choix ==4){ //Fin
                    boucle = false;
                }
            }
            catch(InputMismatchException e){
                System.out.println("Veuillez entrer un entier");
                sc.nextLine();
            }
        }sc.close();
    }
}