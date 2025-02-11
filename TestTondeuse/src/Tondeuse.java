
import java.util.ArrayList;
import java.util.HashMap;

public class Tondeuse {

    public Map map; /*le terrain sur lequelle la tondeuse est */

    public int posX; /*position de la tondeuse sur l'axe ouest/est */
    public int posY; /*position de la tondeuse sur l'axe nord/sud */

    public String orientation; /*orientation de la tondeuse valeur possible : N, E, S et W */
    public static ArrayList<String> orientationList = new ArrayList<String>(); /*liste contenant les différentes valeurs possible pour l'orientation de la tondeuse */
    public int orientationIndex; /*l'index de la valeur de l'orientation dans la liste des valeurs de l'orientation : orientationList */

    public static HashMap<String, Integer[]> movingDic = new HashMap<String, Integer[]>(); /*fait correspondre la valeur de l'orientation avec le déplacement à effectuer sur les deux axes */

    public Tondeuse(int posX, int posY, String orientation){
        this.posX = posX;
        this.posY = posY;
        this.orientation = orientation;
        /*initialisation de orientationList */
        orientationList.add("N");
        orientationList.add("E");
        orientationList.add("S");
        orientationList.add("W");
        this.orientationIndex = orientationList.indexOf(orientation);
        /*initialisation de movingDic */
        movingDic.put("N", new Integer[]{0,1});/*{0,1} correspond à un mouvement d'une case vers le nord : mouvement de 0 sur le premier axe (l'axe x) et mouvement de 1 sur le deuxième axe (l'axe y)*/
        movingDic.put("E", new Integer[]{1,0});
        movingDic.put("S", new Integer[]{0,-1});
        movingDic.put("W", new Integer[]{-1,0});
    }

    public int getPosX(){
        return posX;
    }

    public void setPosX(int posX){
        this.posX = posX;
    }

    public int getPosY(){
        return posY;
    }

    public void setPosY(int posY){
        this.posY = posY;
    }

    public String getOrientation(){
        return orientation;
    }

    public void setOrientation(String orientation){
        this.orientation = orientation;
    }

    public void setMap(Map map){
        this.map = map;
    }

    /*méthode prennant en entrée une commande qui renvoie vers d'autres méthode en fonction de la valeur de cette commande */
    public void action(String command) {
        switch (command) {
            case "A":
                System.out.println("going forwad");
                move();
                break;
            case "D":
                System.out.println("turning right");
                turnRight();
                break;
            case "G":
                System.out.println("turning left");
                turnLeft();
                break;
            default:
                System.out.println("Wrong command");
                break;
        }
    }

    /*méthode qui change l'orientation de la tondeuse afin de la faire tourner à droite */
    public void turnRight(){
        orientationIndex = (orientationIndex + 1)%4; /*changement de l'index d'orientation : +1 (modulo 4) à l'index de la liste d'orientation correspond à tourner à droite*/
        orientation = orientationList.get(orientationIndex);
    }
    
    /*méthode qui change l'orientation de la tondeuse afin de la faire tourner à gauche */
    public void turnLeft(){
        orientationIndex = (orientationIndex + 3)%4; /*changement de l'index d'orientation : -1 (modulo 4 donc +3) à l'index de la liste d'orientation correspond à tourner à gauche*/
        orientation = orientationList.get(orientationIndex);
    }

    /*méthode utilisant l'orientation de la tondeuse et le doictionnaire monvingDic pour faire avancer la tondeuse dans la bonne direction */
    public void move(){
        Integer[] deltaList = movingDic.get(orientation); /*recupère les mouvements à effectuer sur les deux axes en fonction de l'orientation */
        if (0<=posX+deltaList[0] && posX+deltaList[0]<=map.getLengthX()) { /*vérification que le mouvement ne sors pas la tondeuse du terrain */
            posX += deltaList[0]; /*changement de la coordonnée sur l'axe X */
        }
        else{
            System.out.println("mower reach end of map, can't go further in this direction");
        }
        if (0<=posY+deltaList[1] && posY+deltaList[1]<=map.getLengthY()) { /*vérification sur l'axe y */
            posY += deltaList[1]; /*chagement de la coordonnée sur l'axe y */
        }
        else{
            System.out.println("mower reach end of map, can't go further in this direction");
        }
    }

}
