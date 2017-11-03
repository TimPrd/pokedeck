package upmc.pcg.game.Player;

import upmc.pcg.game.Player.LecturePseudo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * A type of LecturePseudo
 * Enables to get the user's input. Get all the names in one.
 * Add all the names in the arraylist by splitting with ";" (+ trim to remove whitespace)
 * Check also if there is at least 2 names
 */
public class LectureConsole implements LecturePseudo
{
    public ArrayList<String> lirePseudo()
    {
        ArrayList<String> alPseudos;
        Scanner keyboardChoice = new Scanner(System.in);
        do
        {
            System.out.println("Entrez les noms ( >= 2 ) des joueurs séparés par ';' :");
            alPseudos = new ArrayList<String>();
            Collections.addAll(alPseudos, keyboardChoice.nextLine()
                    .trim()
                    .split(";"));
        } while (alPseudos.size() < 2);

        return alPseudos;
    }
}
