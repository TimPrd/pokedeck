/*
 * Copyright (c)
 *        @author Timothé PARDIEU
 *                 ${PACKAGE_NAME}
 *                 Created on - ${DATE} (${TIME})
 *                 Build for project ${PROJECT_NAME}
 *
 */

package upmc.pcg.game.card;

/**
 * Enum type of energy
 * All the energy are stocked here.
 * Easier to access
 */
public enum EnergyType {
    ColorLess("0"),
    Metal("1"),
    Fighting("2"),
    Water("4"),
    Lightning("5"),
    Fire("6"),
    Grass("10"),
    Psychic("12"),
    Darkness("16"),
    Fairy("18"),;

    private String img;

    EnergyType(String i) {
        img = i;
    }

    public String getImg() {
        return img;
    }
}