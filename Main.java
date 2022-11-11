import java.util.ArrayList;

import java.util.Scanner;


public class Main {
    public static void main (String[]args){
        ArrayList <Argument> listeArgument = new ArrayList<Argument>();
        boolean boucle=true;
        Scanner sc = new Scanner(System.in);
        ArrayList <Argument> EnsembleE = new ArrayList<Argument>();
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

        boucle = true;
        while(boucle){
            System.out.println("1) Ajouter un argument \n2)retirer un argument \n3) Verifier la solution \n4) fin");
            int choix = sc.nextInt();
            boolean ajout = false;
            String argu ;

            if (choix ==1){//ajouter un argument
                boolean contientArgu = false;
                System.out.println(listeArgument);
                System.out.println("Ensemble à verifier " +EnsembleE);
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
                    for(int j = 0 ; j<EnsembleE.size();j++) {
                        if (EnsembleE.get(j).isEqual(argu)) {
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
                    EnsembleE.add(a);	
                }
            System.out.println(listeArgument);
            System.out.println("Ensemble à verifier " +EnsembleE);

            }
            if (choix ==2){// supprimer un argument
                boolean refus = false;
                System.out.println(listeArgument);
                System.out.println("Ensemble à verifier " +EnsembleE);
            	System.out.println("Donnez l'argument que vous voulez supprimer :");
            	argu = sc.next();
            	for(int k = 0 ; k<EnsembleE.size();k++) {
            		if (EnsembleE.get(k).isEqual(argu)) {
                        refus = true;
            			EnsembleE.remove(k);
            		
            	    } 
                }
                if(!refus){
                    System.out.println("Argument n'est pas dans la liste ou déjà supprimé");
                }

            System.out.println(listeArgument);
            System.out.println("Ensemble à verifier " +EnsembleE);

            }

            if (choix ==3){// Verifier la solution
                boolean admissible = true;

                //Si l'ensemble est vide => solution admissible
                if( EnsembleE.isEmpty()){
                    System.out.println("Solution admissible car ensemble vide");
                    break;
                }

                //Si l'ensemble a qu'un argument
                if(EnsembleE.size()==1){
                    System.out.println("["+EnsembleE.get(0)+"] est une solution admissible");
                }
                else{
                    //Rechecher si 2 arguments se contredisent
                    for( Argument arg1 : EnsembleE){
                        for( Contradiction contr1 : arg1.getlistContradiction()){

                            Argument arg2 = contr1.getArrivee();
                            Contradiction c1 = new Contradiction(arg2, arg1);
                            
                            for( Contradiction contr2 : arg2.getlistContradiction()){
                                if( contr2.isEqual(c1)){
                                    admissible=false;
                                    System.out.println(arg1 + " et " + arg2 + " se contredisent");
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
                    for( Argument arg1 : EnsembleE ){
                        if (arg1.getlistContradiction().size()!=0){
                            if(arg1.getlisteParent().size()==0){
                                admissible = false;
                                argum = arg1;
                            }
                        }
                    }
                    if( admissible ){
                        System.out.println(" La solution "+ EnsembleE + " est admissible");
                    }
                    else{
                        System.out.println(" Dans la solution " + EnsembleE+", l'argument "+argum +" n'est pas défendu");
                    }
                }

            }

            if (choix ==4){ //Fin
                boucle = false;
            }

      }sc.close();
    }
}