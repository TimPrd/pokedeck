/*
 * Copyright (c)
 *        @author Timothé PARDIEU
 *                 ${PACKAGE_NAME}
 *                 Created on - ${DATE} (${TIME})
 *                 Build for project ${PROJECT_NAME}
 *
 */

package upmc.pcg.game.player;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A type of LecturePseudo. Get a file (check if the file exist and if there is at least 2 names in it).
 * If these conditions are followed we add each name in the arraylist, where the first place is for the first player etc.
 */
public class LectureFichier implements LecturePseudo {
    private Scanner scanner = new Scanner(System.in);
    private File file;
    private ArrayList<String> alPseudos;


    public ArrayList<String> lirePseudo() {
        System.out.println("Enter your file's name (with extension) : ");
        this.file = new File(scanner.nextLine());
        checkFileExist();
        try {
            addPseudos();
            return alPseudos;
        } catch (FileNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * Add in arraylist each name (one name = one line)
     * If there is less than 2 names it will advice the user.
     *
     * @throws FileNotFoundException deal with existing or not file
     * @throws InterruptedException  get the 5 sec of rest in program (avoid instant-quit)
     */
    private void addPseudos() throws FileNotFoundException, InterruptedException {

        this.scanner = new Scanner(this.file);
        alPseudos = new ArrayList<>();

        while (this.scanner.hasNext()) //read until the file is ended
        {
            alPseudos.add(this.scanner.nextLine().trim()); //add each name to the list (without whitespaces)
        }

        if (alPseudos.size() < 2) {
            System.out.println("Error, not enough names (1 line = 1 name. 2 lines min) \nPlease, correct it.");
            Thread.sleep(5000);
            System.exit(0);
        }
    }

    /**
     * Used to check if the file is created. If not it enables to loop on until the user choose a existing file
     */
    private void checkFileExist() {
        while (!this.file.exists()) {
            System.out.println("Error, file doesn't exist.. Too bad it's was a good start :( ");
            this.file = new File(scanner.nextLine());
        }

    }
}
