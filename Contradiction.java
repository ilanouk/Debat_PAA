public class Contradiction {
    private Argument depart;
    private Argument arrivee;

    public Contradiction(Argument depart,Argument arrivee){
        this.depart=depart;
        this.arrivee=arrivee;
    }

    public Argument getDepart(){
        return depart;
    }
    public Argument getArrivee(){
        return arrivee;
    }
    public String toString(){
        return depart +" "+ arrivee;
    }

    public boolean isEqual(Contradiction c){
        if (this.depart.equals(c.getDepart()) &&  this.arrivee.equals(c.getArrivee())){
            return true;
        }
        else return false;
    }
}

