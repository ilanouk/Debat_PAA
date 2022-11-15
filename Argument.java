import java.util.ArrayList;

/*
 * @author Tigran WATTRELOS
 * @author Ilan' DAUMONT-OUK
 * @authour Terry TRAN
 */

public class Argument{

    /* Nom de l'argument, e.g A0 */
    private String titre;

    /* Liste des contradictions entre les arguments */
    private ArrayList<Contradiction> listeContradiction;

    /* Liste des arguments parents de chaque argument */
    private ArrayList<Argument> listeParents;

    /* Constructeur
     * 
     * Instancie les attributs de la class
     * 
     * @param titre
     */
    public Argument(String titre){
        this.titre=titre;
        listeContradiction = new ArrayList<>();
        listeParents= new ArrayList<>();

    }

    /* Ajoute un argument à la liste des parents
     * 
     * @param parent
     */
    public void addParent(Argument parent){
        listeParents.add(parent);
    }

    /* Ajoute une contradiction à la liste des contradictions
     * 
     * @param c
     */
    public void addContradiciton(Contradiction c){
        listeContradiction.add(c);
    }

    public ArrayList<Argument> getlisteParent(){
        return listeParents;
    }

    public ArrayList<Contradiction> getlistContradiction(){
        return listeContradiction;
    }
    public String getTitre(){
        return titre;
    }

    public String toString(){
        return titre;
    }

    public boolean isEqual(String titre){
        if (this.titre.equals(titre)){
            return true; 

        }
        else{
            return false;
        }
    }
    public Contradiction getContradiction(int i){
        return listeContradiction.get(i);
    }
}