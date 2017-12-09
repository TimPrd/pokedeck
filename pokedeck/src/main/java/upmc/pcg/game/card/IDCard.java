/*
 * Copyright (c)
 *        @author Timothé PARDIEU
 *                 ${PACKAGE_NAME}
 *                 Created on - ${DATE} (${TIME})
 *                 Build for project ${PROJECT_NAME}
 *
 */

package upmc.pcg.game.card;


class IDCard {
    private static final int UNIQUE_ID = 0;
    private static int uid = UNIQUE_ID;

    public static int getUid() {
        return ++uid;
    }

}
