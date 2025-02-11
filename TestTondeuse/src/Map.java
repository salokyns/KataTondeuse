import java.util.ArrayList;

public class Map {

    public int lengthX; /*largueur de la surface, axe ouest/est */
    public int lengthY; /*hauteur de la surface, axe nord/sud */
    public ArrayList<Tondeuse> tondeuseList; /*la liste de toutes les tondeuses présentes sur le terrain */

    public Map(int lengthX, int lengthY){
        this.lengthX = lengthX;
        this.lengthY = lengthY;
        tondeuseList = new ArrayList<Tondeuse>();
    }

    public int getLengthX() {
        return lengthX;
    }

    public int getLengthY() {
        return lengthY;
    }

    /*ajoute une tondeuse sur le terrain */
    public void addTondeuse(Tondeuse tond) {
        tondeuseList.add(tond);
        tond.setMap(this);
    }
}
