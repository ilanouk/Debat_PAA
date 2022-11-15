/**
 * @author Tigran WATTRELOS
 * @author Ilan' DAUMONT-OUK
 * @authour Terry TRAN
 */

public class Contradiction {
    private Argument depart;
    private Argument arrivee;

    public Contradiction(Argument depart,Argument arrivee){
        this.depart=depart;
        this.arrivee=arrivee;
    }

    
    /** 
     * @return Argument
     */
    public Argument getDepart(){
        return depart;
    }
    
    /** 
     * @return Argument
     */
    public Argument getArrivee(){
        return arrivee;
    }
    
    /** 
     * @return String
     */
    public String toString(){
        return depart +" "+ arrivee;
    }

    
    /** 
     * @param c
     * @return boolean
     */
    public boolean isEqual(Contradiction c){
        if (this.depart.equals(c.getDepart()) &&  this.arrivee.equals(c.getArrivee())){
            return true;
        }
        else return false;
    }
}

