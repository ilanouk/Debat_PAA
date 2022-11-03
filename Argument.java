import java.util.ArrayList;

public class Argument{
    private String titre;
    private ArrayList<Argument> listeFils;
    private ArrayList<Argument> listeParents;

    public Argument(String titre){
        this.titre=titre;
        listeFils= new ArrayList<>();
        listeParents= new ArrayList<>();

    }

    public void addParent(Argument parent){
        listeParents.add(parent);
    }

    public void addFils(Argument fils){
        listeFils.add(fils);
    }

    public ArrayList<Argument> getlisteParent(){
        return listeParents;
    }

    public ArrayList<Argument> getlistFils(){
        return listeFils;
    }
    public String getTitre(){
        return titre;
    }
}