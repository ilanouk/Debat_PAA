import java.util.ArrayList;

import java.util.Scanner;


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
        //System.out.println(listeArgument);

        while (boucle){
            System.out.println(listeArgument);
            System.out.println("1) Ajouter une contradiction \n 2) Fin");
            int choix = sc.nextInt(); // On sticke ici le choix de l'utilistateur
            if (choix == 1){ //Ajouter une contradiction

                System.out.println("Entrez l'argument cible puis l'argument qu'il contredit ex : A0 A1");
                String strDepart = sc.next();
                String strArrivee = sc.next();
                
                Argument argDepart=null;
                Argument argArrivee=null;
                
                for(i=0;i<listeArgument.size();i++){ //Dans cette boucle, on ajoute une contradiction entre les arguments donné par l'utilisateur
                    if (listeArgument.get(i).isEqual(strDepart)){ // on cherche quel argument est associé au titre rentreé
                        argDepart= listeArgument.get(i);
                    }
                    if (listeArgument.get(i).isEqual(strArrivee)){ // on cherche quel argument est associé au titre rentreé
                        argArrivee= listeArgument.get(i);
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

        boucle = true;
        while(boucle){
            System.out.println("1) Ajouter un argument \n 2)retirer un argument \n 3) Verifier la solution \n 4) fin");
            int choix = sc.nextInt();
            boolean ajout = false;
            String argu ;

            if (choix ==1){//ajouter un argument
                System.out.println(listeArgument);
            	System.out.println("Donnez l'argument que vous voulez ajouter :");
            	argu = sc.next();
            	for(int j = 0 ; j<listeArgument.size();j++) {
            		if (listeArgument.get(j).isEqual(argu)) {
            			ajout=true;
            	    }
                }
                if(ajout){ // Si ajout==true, alors argument deja ajouté a la liste
                    System.out.println("Argument déjà ajouté");}
                else {
                    listeArgument.add(new Argument(argu));	
                }
            System.out.println(listeArgument);
            }
            if (choix ==2){// supprimer un argument
                boolean refus = false;
                System.out.println(listeArgument);
            	System.out.println("Donnez l'argument que vous voulez supprimer :");
            	argu = sc.next();
            	for(int k = 0 ; k<listeArgument.size();k++) {
            		if (listeArgument.get(k).isEqual(argu)) {
                        refus = true;
            			listeArgument.remove(k);
            		
            	    } 
                }
                if(!refus){
                    System.out.println("Argument n'est pas dans la liste ou déjà supprimé");
                }

            System.out.println(listeArgument);
            }
            if (choix ==3){// Verifier la solution
                boolean contre = false;
                Argument premArg = null ;
                Argument deuxArg = null;

                //Si l'ensemble est vide ou avec 1 argument => solution admissible
                if(listeArgument.isEmpty() || listeArgument.size()==1){
                    System.out.println("Solution admissible");
                }
                else {
                    for(int b=0 ; b<listeArgument.size()-1 && contre==false ; b++ ){
                        // s'il existe contradiction(argA, argB)==contradiction(argB, argA)
                        //  System.out.println(argA + " et " + argB + " se contredisent");
                        //  if(argument pas défendu contre ses contradictions)
                        //      System.out.println("Argument non défendu")
                        //
                        // sinon System.out.println("Solution admissible");
                        Argument argA = listeArgument.get(b);
                        Argument argB = listeArgument.get(b+1);

                        // Parcourir toutes les contradictions d'un des 2 arguments de l'ensemble
                        for( int idx=0 ; idx<argA.getlistContradiction().size() || idx<argB.getlistContradiction().size() ; idx++ ){
                            if( argA.getContradiction(idx).equals(argB.getContradiction(idx)) ){
                                contre = true;
                                premArg = argA;
                                deuxArg = argB;
                                break;
                            }
                        }
                    }
                    if(contre){
                        System.out.println(premArg + " et " + deuxArg + " se contredisent");
                    }
                    else{ 
                        System.out.println("Solution admissible");
                    }
                }

            }
            if (choix ==4){ //Fin
                boucle = false;
            }
            
      }sc.close();
        
    
    }
    
}
