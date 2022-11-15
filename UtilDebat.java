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
}
