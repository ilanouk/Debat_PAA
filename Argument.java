import java.util.ArrayList;

/**
 * @author Tigran WATTRELOS
 * @author Ilan' DAUMONT-OUK
 * @author Terry TRAN
 */

public class Argument{

    /* Nom de l'argument, e.g A0 */
    private String titre;

    /* Liste des contradictions entre les arguments */
    private ArrayList<Contradiction> listeContradiction;

    /* Liste des arguments parents de chaque argument */
    private ArrayList<Argument> listeParents;

    /** Constructeur
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

    /** Ajoute un argument à la liste des parents
     * 
     * @param parent
     */
    public void addParent(Argument parent){
        listeParents.add(parent);
    }

    /** Ajoute une contradiction à la liste des contradictions
     * 
     * @param c 
     */
    public void addContradiciton(Contradiction c){
        listeContradiction.add(c);
    }

    /** Renvoie la liste des arguments parents 
     * 
     * @return la listeParents
     */
    public ArrayList<Argument> getlisteParent(){
        return listeParents;
    }

    /** Renvoie la liste des contradictions
     * 
     * @return la listeContradiction
     */
    public ArrayList<Contradiction> getlistContradiction(){
        return listeContradiction;
    }

    /** Renvoie le nom de l'argument 
     * 
     * @return le nom de l'argument, e.g A0
     */
    public String getTitre(){
        return titre;
    }

    /** Renvoie le titre sous forme de String 
     * 
     * @return le nom de l'argument
     */
    public String toString(){
        return titre;
    }

    /** Compare 2 titres d'arguments entre eux 
     * 
     * @param titre
     * @return True si le nom d'un argument est égal à l'autre, sinon False
     */
    public boolean isEqual(String titre){
        if (this.titre.equals(titre)){
            return true; 

        }
        else{
            return false;
        }
    }

    /** Compare 2 titres d'arguments entre eux 
     * 
     * @param i
     * @return True si le nom d'un argument est égal à l'autre
     */
    public Contradiction getContradiction(int i){
        return listeContradiction.get(i);
    }
}