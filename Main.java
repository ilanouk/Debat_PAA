import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main (String[]args){
        ArrayList <Argument> listeArgument = new ArrayList<Argument>();
        boolean boucle=true;
        Scanner sc = new Scanner(System.in);
        System.out.println("Combien d'argument y a t il dans le débat");
        int nb = sc.nextInt();
        int i=0;
        while (i<nb){
            listeArgument.add(new Argument("A"+i)); // on crée les arguments et on les rentre dans la liste des arguments
            i++;
        }

        while (boucle){
            System.out.println("1) Ajouter une contradiction \n 2) Fin");
            int choix = sc.nextInt(); // On sticke ici le choix de l'utilistateur

            if (choix == 1){ //Ajouter une contradiction

                System.out.println("Entrez l'argument cible puis l'argument qu'il contredit ex : cible A1");
                String contradiction = sc.next();
                StringTokenizer stContradiction = new StringTokenizer(contradiction);

                String strDepart = stContradiction.nextToken();
                String strArrivee = stContradiction.nextToken();
                
                Argument argDepart=null;
                Argument argArrivee=null;
                
                for(i=0;i<listeArgument.size();i++){ //Dans cette boucle, on ajoute une contradiction entre les arguments donné par l'utilisateur
                    if (listeArgument.get(i).isEqual(strDepart)){
                        argDepart= listeArgument.get(i);
                    }
                    if (listeArgument.get(i).isEqual(strArrivee)){
                        argArrivee= listeArgument.get(i);
                    }
                    if (argArrivee!=null && argDepart!=null){

                        argDepart.addContradiciton(new Contradiction(argDepart, argArrivee));
                    }
                    else{
                        System.out.println("Les arguments rentrés n'existe pas");
                    }
                    
                }
                
                
                
                
            }

            if (choix == 2 ){ //FIN
                boucle = false;
            }




        }

        boucle = true;
        while(boucle){
            System.out.println("1) Ajouter un argument \n 2)retirer un argument \n Verifier la solution \n fin");
            int choix = sc.nextInt();

            if (choix ==1){ //ajouter un argument

            }
            if (choix ==2){// supprimer un arrgument
                
            }
            if (choix ==3){// Verifier la solution
                
            }
            if (choix ==4){ //Fin
                boucle = false;
            }
        }
    }
}
