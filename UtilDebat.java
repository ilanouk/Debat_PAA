import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * @author Tigran WATTRELOS
 * @author Ilan' DAUMONT-OUK
 * @authour Terry TRAN
 */

public class UtilDebat {

    /** La méthode vérifie si l'ensemble est une solution admissible
     * et retourne un message indiquant quelle est la cause du problème ou si la solution est admissible
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
            */
            Argument argum = null;
            
            for(Argument arg1 : ensembleE){
                boolean contientGP = false;
                for (Argument parent : arg1.getlisteParent()){
                    if(parent.getlisteParent().isEmpty() ){
                        admissible = false;
                        argum = arg1;

                    }
                    else{
                        for (Argument grandPere : parent.getlisteParent()){
                            if (ensembleE.contains(grandPere)){
                                contientGP = true;
                            }
                        }
                        if (!contientGP){
                            admissible = false;
                            argum = arg1;

                        }
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
     /** La méthode crée une contradiction entre l'argument identifié par le string strDepart et l'argument identifié par le string strArrivee
     * 
     *
     * @param strDepart
     * @param strArrivee
     * @param listeArgument
     * @return true si la contradiction a bien été créé et false sinon
     */
    public static boolean ajoutContradiction(String strDepart, String strArrivee,ArrayList<Argument> listeArgument){ //Private ???
        int i;
        
    
        Argument argDepart=null;
        Argument argArrivee=null;
        
        for(i=0;i<listeArgument.size();i++){ //Dans cette boucle, on ajoute une contradiction entre les arguments donné par l'utilisateur
            if (listeArgument.get(i).isEqual(strDepart)){ // on cherche quel argument est associé au titre rentreé
                argDepart = listeArgument.get(i);
            }
            if (listeArgument.get(i).isEqual(strArrivee)){ // on cherche quel argument est associé au titre rentré
                argArrivee = listeArgument.get(i);
            }
            
            
        }
        if (argArrivee!=null && argDepart!=null){//on crée une contradiction entre les titres rentrés
        
            argDepart.addContradiciton(new Contradiction(argDepart, argArrivee));
            argArrivee.addParent(argDepart);
        }
        else {
            return false;
        }
        return true;
    }

    public static void ajoutContradictionSc(Scanner sc,ArrayList<Argument> listeArgument){
        System.out.println("Entrez l'argument cible puis l'argument qu'il contredit ex : A0 A1");
        String strDepart = sc.next();
        String strArrivee = sc.next();
        
    
        boolean ajout=ajoutContradiction(strDepart, strArrivee, listeArgument);
        if (!ajout){
            System.out.println("La Contradiction n'est pas bien rédiger ou le fichier est mal formé");
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

     /** 
     * La méthode prend en argument le nom d'un argument et permet de le créer et de l'ajouter 
     * à une liste en vérifiant si il est déja dans la liste
     * 
     * @param nom
     * @param liste 
     */
     
    public static void ajouterArgumentSansSc(String nom,ArrayList<Argument> liste){ //Private ???
        boolean contientArgu = false;
                
        for(int j = 0 ; j<liste.size();j++) {
            if (liste.get(j).isEqual(nom)) {
                contientArgu = true;
                break;
            }
            
        }
        if (!contientArgu){
            liste.add(new Argument(nom));
        }
        else{
            System.out.println("L'argument est déja dans la liste");
        }
        	
        
    }
    /** 
     * La méthode prend en argument le nom d'un fichier et permet de le lire et d'ajouter un argument ou une contradiction à la solution.
     * 
     * @param lienFichier
     * @throws IOException
     */
    public static void lireFichier(String lienFichier,ArrayList<Argument> liste) {
        int i=0;
        try{
            File f = new File(lienFichier);
            FileReader fis = new FileReader(f);
            BufferedReader bf = new BufferedReader(fis);
            String texte;
        
            while (i>-1){ //boucle infini jusqu'à ce qu'il y ait une erreur IOException
                texte ="";
                texte = bf.readLine();
                StringTokenizer st = new StringTokenizer(texte,"(");
                String argOuContr = st.nextToken();
                if (argOuContr.equals("argument")){
                    texte = st.nextToken();
                    
                    texte=texte.replace(")","");
                    
                    ajouterArgumentSansSc(texte, liste);
                }
                else if (argOuContr.equals("contradiction")){
                    
                    StringTokenizer st2 = new StringTokenizer(st.nextToken(),",");
                    
                    String arg1 = st2.nextToken();
                    String arg2 = st2.nextToken();
                    arg2 = arg2.replace(")","");
                    boolean ajout = ajoutContradiction(arg1,arg2, liste);
                    if (!ajout){
                        System.out.println("fichier mal formé");
                    }

                }
            }
            
            
        }

        catch(FileNotFoundException e){
            System.out.println("Le fichier n'existe pas"); // Quand pas de fichier, voir ce qu'il faut faire
            System.exit(i);
        }
        catch(IOException e){
            System.out.println("Il y a un problème dans la lecture du fichier");
            System.exit(i);
        }
        catch(NullPointerException n){
            
        }
        catch(NoSuchElementException e){

        }


    }


    public static void sauvegarde(ArrayList<Argument> ensembleE,String lienFichier){

        try{
            File f = new File (lienFichier);
            f.delete();
            f.createNewFile();
            FileWriter fw = new FileWriter(f);
        
            BufferedWriter bf = new BufferedWriter(fw);
          
            // Afficher l'ensembleE + admissible ou pas
            String str = UtilDebat.verifSolution(ensembleE);
            if (str.equals("Admissible")){
                bf.write("L'ensemble " +ensembleE+" est une solution admissible" );
            }
            else{
                bf.write(UtilDebat.verifSolution(ensembleE));
            }
            bf.close();
        }

        catch(IOException e){
            System.out.println("Erreur dans la sauvegarde");
        }

    }

    /** 
     * La méthode prend en argument une liste d'argument et une liste d'argument déja vu et retourne un ensemble n'ayant pas déja était 
     * présenter à l'utilisateur
     * 
     * @param listeArgument
     * 
     */
    public static ArrayList<Argument> solutionAdmissible(ArrayList<Argument> listeArgument, ArrayList<ArrayList<Argument>> dejaVu, ArrayList<ArrayList<Argument>> listeSolution){
         
        if(dejaVu.size()==listeSolution.size()){ // Pas la bonne condition
            dejaVu.clear();
        }

        for(int i=0;i<listeSolution.size();i++){
            if (!dejaVu.contains(listeSolution.get(i))){
                dejaVu.add(listeSolution.get(i));
                return(listeSolution.get(i));
            }
        }
        return null;
    }

    /**
     * la méthode prend en argument une liste d'argument et la liste des arguments connus
     * 
     * @param listeArgument
     * @param dejaVu
     * @return une liste de liste d'argument contenant la solution admissible la plus grande
     */
    public static ArrayList<ArrayList<Argument>> recupListeSolution(ArrayList<Argument> listeArgument, ArrayList<ArrayList<Argument>> listeSolution){
         
        ArrayList<ArrayList<Argument>> touteCombianisonArgument = getAllCombinaition(listeArgument);

        for( int i=touteCombianisonArgument.size()-1;i>=0;i-- ){
            if ( verifSolution(touteCombianisonArgument.get(i)).equals("Admissible") ){
                listeSolution.add(touteCombianisonArgument.get(i));
            }
            
        }
        return listeSolution;
    }


    /** 
     * La méthode prend en argument un int et retourne toute les combinaisons possibles en code bianire
     * 
     * @param tailleListe
     * 
     */
    private static ArrayList<String> getAllBinairy(int tailleListe){

        ArrayList<String> allBinairy = new ArrayList<>();
        for (int i=0;i<Math.pow(2, tailleListe);i++){
            String binairy = Integer.toBinaryString(i);
            while(binairy.length()<tailleListe){
                binairy = "0" + binairy;
            }
            allBinairy.add(binairy);
            

        }
        
        return allBinairy;
    }
    /** 
     * La méthode prend en argument une liste d'argument et retourne toute les combinaisons possibles d'arguments
     * 
     * @param listeArgument
     * 
     */
    private static ArrayList<ArrayList<Argument>> getAllCombinaition(ArrayList<Argument> listeArgument){
        ArrayList<String> allBinairy = getAllBinairy(listeArgument.size());
        ArrayList<ArrayList<Argument>> listeTouteSolution = new ArrayList<>();
        for(int i = 0;i<allBinairy.size();i++){
            ArrayList<Argument> inter = new ArrayList<>();
            for (int j =0;j<listeArgument.size();j++){
                if(allBinairy.get(i).charAt(j)=='1'){
                    inter.add(listeArgument.get(j));
                }
            }
            listeTouteSolution.add(inter);
            
        }
        return listeTouteSolution;

    }
}
