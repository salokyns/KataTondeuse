import java.io.File;
import java.util.Scanner;

public class App {

    public static Map map;
    
    /*méthode qui crée le terrain en fonction de la ligne correspondante du fichier */
    public static void createMap(String line){
        String[] lengths = line.split(" ");
        map = new Map(Integer.parseInt(lengths[0]), Integer.parseInt(lengths[1]));
    }

    /*méthode qui crée et renvoie une tondeuse en focntion de la ligne correspondante du fichier */
    public static Tondeuse createTondeuse(String line){
        String[] data = line.split(" ");
        Tondeuse tond = new Tondeuse(Integer.parseInt(data[0]), Integer.parseInt(data[1]), data[2]); /*initialise la tondeuse */
        map.addTondeuse(tond); /*ajoute la tondeuse au terrain */
        return tond;
    }

    /*méthode prennant en entrée une tondeuse et l'entièreté des actions qu'elle doit effectuer puis qui exécute les actions une à une */
    public static void startActions(Tondeuse tond, String line){
        for (int i=0; i<line.length(); i++){
            System.out.println("making an action");
            tond.action(String.valueOf(line.charAt(i)));
        }
    }
    
    
    public static void main(String[] args) throws Exception {
        /*récupère le fichier d'input */
        File file = new File("./src/data.txt");
        Scanner scanner = new Scanner(file);

        /*crée le terrain avec la première ligne du fichier */
        if (scanner.hasNextLine()){
            createMap(scanner.nextLine());
        }
        else {
            System.out.println("File is empty");
            scanner.close();
            return;
        }

        /*boucle while qui vérifie qu'il reste une ligne non parcourue dans le fichier */
        while (scanner.hasNextLine()) {
            Tondeuse tond = createTondeuse(scanner.nextLine()); /*création d'une nouvelle tondeuse avec la ligne correspondante du fichier */
            if (scanner.hasNextLine()){
                startActions(tond, scanner.nextLine()); /*commence les actions à effectuer par la tondeuse avec la ligne correspondante du fichier */
            }
            else {
                System.out.println("File doesn't have the good format, actions for mower are missing");
                scanner.close();
                return;
            }
        }
        scanner.close();
        
        /*boucle sur toutes les tondeuses du terrain pour récupérer leur position/orientation */
        for (int i=0; i<map.tondeuseList.size(); i++){
            Tondeuse tond = map.tondeuseList.get(i);
            System.out.println(""+tond.getPosX()+" "+tond.getPosY()+" "+tond.getOrientation());
        }
    }
}
