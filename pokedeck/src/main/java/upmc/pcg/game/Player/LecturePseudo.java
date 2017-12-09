/*
 * Copyright (c)
 *        @author Timothé PARDIEU
 *                 ${PACKAGE_NAME}
 *                 Created on - ${DATE} (${TIME})
 *                 Build for project ${PROJECT_NAME}
 *
 */

package upmc.pcg.game.player;

import java.util.ArrayList;

/**
 * Interface to get a sort of LecturePseudo (CUI or File)
 * It enables to get the same type but with different methods
 */
public interface LecturePseudo {
    ArrayList<String> lirePseudo();
}
