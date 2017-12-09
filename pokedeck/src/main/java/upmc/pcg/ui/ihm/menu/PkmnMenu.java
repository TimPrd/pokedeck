/*
 * Copyright (c)
 *        @author Timothé PARDIEU
 *                 ${PACKAGE_NAME}
 *                 Created on - ${DATE} (${TIME})
 *                 Build for project ${PROJECT_NAME}
 *
 */

package upmc.pcg.ui.ihm.menu;

import upmc.pcg.controller.Pokedeck;
import upmc.pcg.game.actions.Act;
import upmc.pcg.ui.ihm.frames.ChoserUi;
import upmc.pcg.ui.ihm.frames.FrameCreate;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Create a specific menu for all the possible action
 */
public class PkmnMenu extends JMenuBar {
    private final Pokedeck controller;

    public PkmnMenu(Pokedeck controller) {
        this.controller = controller;
        JMenuItem createPkmn = new JMenuItem("Pokémon");

        //Remove because not usable in a fight
        //JMenuItem createTrainer = new JMenuItem("Trainer");
        //JMenuItem createItem = new JMenuItem("Item");

        //Sub-Menu
        JMenuItem collecCreate = new JMenu("Créer");
        collecCreate.add(createPkmn);

        KeyStroke keyStroke = KeyStroke.getKeyStroke("control N");
        createPkmn.setAccelerator(keyStroke);
        //collecCreate.add(createTrainer);
        //collecCreate.add(createItem);

        createPkmn.setActionCommand("CreatePkmn");
        createPkmn.addActionListener(this::actionPerformed);

        //createTrainer.setActionCommand("CreatePkmn");
        //createTrainer.addActionListener(new MenuBarAction());

        //createItem.setActionCommand("CreatePkmn");
        //createItem.addActionListener(this::actionPerformed);

        JMenuItem collecDelete = new JMenuItem("Supprimer");
        collecDelete.setActionCommand("DeletePkmn");
        collecDelete.addActionListener(this::actionPerformed);

        JMenuItem collecShow = new JMenuItem("Montrer");
        collecShow.setActionCommand("Show");
        collecShow.addActionListener(e -> new ChoserUi(controller, java.util.Optional.ofNullable(this.controller.collec())));

        JMenu collectionMenu = new JMenu("Collection");
        collectionMenu.add(collecCreate);
        //collectionMenu.add(collecUpdate);
        collectionMenu.add(collecShow);
        collectionMenu.add(collecDelete);

        JMenuItem addToDeck = new JMenuItem("Ajouter au deck");
        addToDeck.setActionCommand("AddToDeck");
        addToDeck.addActionListener(this::actionPerformed);

        JMenu deckMenu = new JMenu("Deck");
        deckMenu.add(addToDeck);

        JMenuItem changePlayer = new JMenuItem("Changer joueur");
        changePlayer.setActionCommand("SwitchPlayer");
        changePlayer.addActionListener(e -> {
            controller.switchPlayer();
        });
        JMenu playerMenu = new JMenu("Joueur");
        playerMenu.add(changePlayer);

        this.add(collectionMenu);
        this.add(deckMenu);
        this.add(playerMenu);
    }

    private void actionPerformed(ActionEvent e) {
        Method[] methods = FrameCreate.class.getMethods();

        for (Method m : methods) {
            if (m.isAnnotationPresent(Act.class)) {
                Act a = m.getAnnotation(Act.class);
                if (a.value().equals(e.getActionCommand()))
                    try {
                        m.invoke(new FrameCreate(this.controller), null);
                    } catch (IllegalAccessException | InvocationTargetException e1) {
                        e1.printStackTrace();
                    }

            }
        }


/*
        if("CreatePkmn".equals(e.getActionCommand()))
        {
            FrameCreate createPkmn = new FrameCreate(this.controller);
            createPkmn.createPokemonFrame();
            //game.createCard();
        }
        if("Open".equals(e.getActionCommand())){
            JOptionPane.showMessageDialog(null, "Selected Item: " + e.getActionCommand());
        }*/
    }


}
