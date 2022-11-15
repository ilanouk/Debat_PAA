import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Tigran WATTRELOS
 * @author Ilan' DAUMONT-OUK
 * @authour Terry TRAN
 */

public class UtilDebat {

    /** La méthode vérifie si l'ensemble est une solution admissible
     * et retourne un message indiquant quelle est la cause du problème
     *
     * @param ensembleE
     * @return le message affiché par rapport à l'ensemble
     */
    public static String verifSolution(ArrayList <Argument> ensembleE){
        boolean admissible = true;

        //Si l'ensemble est vide => solution admissible
        if( ensembleE.isEmpty()){
            return("Admissible");

        }

        else{
            //Rechecher si 2 arguments se contredisent
            for( Argument arg1 : ensembleE){
                for( Contradiction contr1 : arg1.getlistContradiction()){

                    Argument arg2 = contr1.getArrivee();
                    Contradiction c1 = new Contradiction(arg2, arg1);
                    
                    if(ensembleE.contains(arg2)){
                        for( Contradiction contr2 : arg2.getlistContradiction()){
                            if( contr2.isEqual(c1)){
                                admissible=false;
                                return (arg1 + " et " + arg2 + " se contredisent");

                            }
                        }
                    }
                    
                }
            }

            

            // Rechercher si un argument n'est pas défendu face à toutes ses contradictions
            /*
                * pour tout argument a qui contredit un élément de E , il existe un
                * élément de E qui contredit a ; on dit alors que E se défend contre a
                * Donc :
                * 1) Parcourir les noeuds
                * 2) verifier si il a des contradictions si non alors pas besoin de "3)""
                * 3) Si oui : verifier si il a des parents. : si oui alors parcourir un autre argument sinon la solution n'est pas admissible.
                * 
                */
            Argument argum = null;
            for( Argument arg1 : ensembleE ){
                if (arg1.getlistContradiction().size()!=0){
                    if(arg1.getlisteParent().size()==0){
                        admissible = false;
                        argum = arg1;
                    }
                }
            }
            if( admissible ){
                return("Admissible");
            }
            else{
                return (" Dans la solution " + ensembleE+", l'argument "+argum +" n'est pas défendu");
            }
             
        }
    }

    public static void ajoutContradiction(Scanner sc,ArrayList<Argument> listeArgument){
        System.out.println("Entrez l'argument cible puis l'argument qu'il contredit ex : A0 A1");
        String strDepart = sc.next();
        String strArrivee = sc.next();
        int i;
        
    
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
    }









    /** La méthode demande à l'utilisateur combien d'arguments il souhaite ajouter
     * puis crée une listeArgument avec les arguments numérotés [A0,...,An]
     *
     * @param sc
     * @return la liste de l'ensemble des arguments créés
     */
    public static ArrayList<Argument> creationArgument(Scanner sc){
        ArrayList <Argument> listeArgument = new ArrayList<Argument>();
        boolean boucle = true;
        int i =0;
        while(boucle){
            try{
                System.out.println("Combien d'argument y a t il dans le débat");
                int nb = sc.nextInt();
                
                while (i<nb){
                    listeArgument.add(new Argument("A"+i)); // on crée les arguments et on les rentre dans la liste des arguments
                    i++;
                }
                boucle = false;
                return listeArgument;
            }
            catch(InputMismatchException e){
                System.out.println("Veuillez entrer un entier");
                sc.nextLine();
            }
        }
        return null;
    }

    
    /** 
     * La méthode permet à l'utilisateur d'ajouter un argument dans l'ensemble à vérifier.
     * 
     * @param liste
     * @param ensemble
     * @param sc
     */
    public static void ajouterArgument( ArrayList <Argument> liste,ArrayList <Argument> ensembleE, Scanner sc ){
        boolean contientArgu = false;
        Argument a = null;
        boolean ajout = false;
        System.out.println(liste);

        System.out.println("Ensemble à verifier " +ensembleE);
        System.out.println("Donnez l'argument que vous voulez ajouter :");
        String argu = sc.next();
        
        for(int j = 0 ; j<liste.size();j++) {
            if (liste.get(j).isEqual(argu)) {
                contientArgu = true;
                a = liste.get(j);
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
    System.out.println(liste);
    System.out.println("Ensemble à verifier " +ensembleE);
    }


    /** 
     * La méthode permet à l'utilisateur de supprimer un argument dans l'ensemble à vérifier.
     * 
     * @param argu
     * @param liste
     * @param ensemble
     * @param sc
     */

    public static void supprimerArgument(ArrayList <Argument> liste,ArrayList <Argument> ensembleE, Scanner sc ){


        System.out.println(liste);
        System.out.println("Ensemble à verifier " +ensembleE);
        System.out.println("Donnez l'argument que vous voulez supprimer :");
        String argu = sc.next();

        boolean refus = false;

        for(int k = 0 ; k<ensembleE.size();k++) {
            if (ensembleE.get(k).isEqual(argu)) {
                refus = true;
                ensembleE.remove(k);
            
            } 
        }
        if(!refus){
            System.out.println("Argument n'est pas dans la liste ou déjà supprimé");
        }

    System.out.println(liste);
    System.out.println("Ensemble à verifier " +ensembleE);

    }
}
