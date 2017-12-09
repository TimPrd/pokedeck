/*
 * Copyright (c)
 *        @author Timothé PARDIEU
 *                 ${PACKAGE_NAME}
 *                 Created on - ${DATE} (${TIME})
 *                 Build for project ${PROJECT_NAME}
 *
 */

package upmc.pcg.ui.cli;

import upmc.pcg.game.player.LectureConsole;
import upmc.pcg.game.player.LectureFichier;
import upmc.pcg.game.player.LecturePseudo;

import java.util.Scanner;

/**
 * Pint the menu until the user make a rigth choice. Used of nextLine and not nextInt to deal with the errors. (check if it's a number..)
 * It will redirect to the right class
 */
class MenuPseudo {
    /**
     * @return the LecturePseudo by a console or a file. Same type from two methods
     */
    public LecturePseudo modeLecturePseudo() {
        Scanner keyboardChoice = new Scanner(System.in);
        String choice;
        do {
            System.out.println("(1) : Fichier");
            System.out.println("(2) : Console");
            choice = keyboardChoice.nextLine();
        } while (!isNumber(choice));

        switch (Integer.parseInt(choice)) {
            case 1:
                return new LectureFichier();
            case 2:
                return new LectureConsole();
            default:
                return null;
        }
    }

    /**
     * Check if it's really a number (between 1 and 2)
     *
     * @param choice the user's input
     * @return if it's a number or not.
     */
    private boolean isNumber(String choice) {
        return choice.matches("[1-2]");
    }
}
