package upmc.pcg.game.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A type of LecturePseudo. Get a file (check if the file exist and if there is at least 2 names in it).
 * If these conditions are followed we add each name in the arraylist, where the first place is for the first player etc.
 */
public class LectureFichier implements LecturePseudo
{
    private Scanner scanner = new Scanner(System.in);
    private File file;
    private ArrayList<String> alPseudos;


    public ArrayList<String> lirePseudo()
    {
        System.out.println("Entrez le nom de votre fichier (avec l'extension) : ");
        this.file =  new File(scanner.nextLine());
        checkFileExist();
        try
        {
            addPseudos();
            return alPseudos;
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * Add in arraylist each name (one name = one line)
     * If there is less than 2 names it will advice the user.
     * @throws FileNotFoundException deal with existing or not file
     * @throws InterruptedException get the 5 sec of rest in program (avoid instant-quit)
     */
    private void addPseudos() throws FileNotFoundException, InterruptedException
    {

            this.scanner = new Scanner(this.file);
            alPseudos = new ArrayList<String>();

            while (this.scanner.hasNext()) //read until the file is ended
            {
                alPseudos.add(this.scanner.nextLine().trim()); //add each name to the list (without whitespaces)
            }

            if(alPseudos.size()<2)
            {
                System.out.println("Erreur, pas assez de noms (1 ligne = 1 nom. 2 lignes min) \nCorrigez svp.");
                Thread.sleep(5000);
                System.exit(0);
            }
    }

    /**
     * Used to check if the file is created. If not it enables to loop on until the user choose a existing file
     */
    private void checkFileExist()
    {
        while(!this.file.exists()) {
            System.out.println("Erreur, ce fichier semble inexistant. Retentez : ");
            this.file =  new File(scanner.nextLine());
        }

    }
}
