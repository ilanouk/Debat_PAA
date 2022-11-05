import java.util.ArrayList;

public class Argument{
    private String titre;
    private ArrayList<Contradiction> listeContradiction;
    private ArrayList<Argument> listeParents;

    public Argument(String titre){
        this.titre=titre;
        listeContradiction = new ArrayList<>();
        listeParents= new ArrayList<>();

    }

    public void addParent(Argument parent){
        listeParents.add(parent);
    }

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