/*
 * Copyright (c)
 *        @author Timothé PARDIEU
 *                 ${PACKAGE_NAME}
 *                 Created on - ${DATE} (${TIME})
 *                 Build for project ${PROJECT_NAME}
 *
 */

package upmc.pcg.ui.ihm.frames;

import upmc.pcg.controller.Pokedeck;
import upmc.pcg.game.Utils.GroupButtonUtils;
import upmc.pcg.game.card.Card;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

/**
 * This class create the Table with all the pkmn (Menu > Montrer | click on deck image)
 * It will print a scrollable table with all the pokemon + the search tool on "show" action
 */
@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public class ChoserUi extends JFrame {
    private Pokedeck controller;
    private JFrame window;
    private PokemonTableUI tableModel;
    private JTable j;
    private JPanel panel;

    /**
     * Default constructor to build
     *
     * @param controller the controller to get data
     * @param list       the list to display the deck table or the collection deck / query executed on the table
     */
    public ChoserUi(Pokedeck controller, Optional<ArrayList<Card>> list) {
        super();
        this.controller = controller;
        //generate the common things
        createBasics();
        //If there is a list create with it
        if (list.isPresent())
            initPokemonTable(list);
            //Otherwise create with the deck list
        else
            initPokemonTable(Optional.empty());
        //generate the table
        createTable();
        panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(j));

        //Disable or not the selection
        isChooser(list);

        this.window.add(panel);
        this.window.setVisible(true);
        this.window.pack();
    }


    private void createTable() {
        j = new JTable(tableModel);

        j.setGridColor(Color.blue);

    }

    /**
     * Create the commons for the chooser
     */
    private void createBasics() {
        this.window = new JFrame();
        this.window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.window.setLocation(855, 0);
        this.window.setResizable(true);
        this.window.setVisible(true);
    }

    /**
     * Init with data the table
     *
     * @param arrayList the list, if there is one (collection) otherwise we put the deck in it
     */
    private void initPokemonTable(Optional<ArrayList<Card>> arrayList) {
        ArrayList<Card> al = arrayList.orElseGet(() -> this.controller.getModel().getCurrentPlayer().getCollection().getAlCardsDeck());
        String columnNames[] = {"Nom", "Type", "PV", "Attack 1", "Attack 2", "Attack 3"};
        tableModel = new PokemonTableUI(columnNames, al);
    }

    /**
     * Set the selected
     *
     * @param list the data
     */
    private void isChooser(Optional<ArrayList<Card>> list) {
        //Create a chooser from deck
        if (!list.isPresent())
            j.getSelectionModel().addListSelectionListener(e -> {
                if (e.getValueIsAdjusting())
                    try {
                        controller.chooseCard(tableModel.getPkmnAt(j.getSelectedRow()));
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
            });
            //Create a chooser from the collection + search bar
        else {
            JPanel search = new JPanel(new GridLayout(1, 2));
            JTextField querySearch = new JTextField("Enter the search, then hit enter");
            search.add(querySearch);


            JRadioButton nameButton = new JRadioButton("NAME");
            nameButton.setActionCommand("NAME");
            nameButton.setSelected(true);

            JRadioButton typeButton = new JRadioButton("TYPE");
            typeButton.setActionCommand("TYPE");

            //Group the radio buttons.
            ButtonGroup queryType = new ButtonGroup();
            queryType.add(nameButton);
            queryType.add(typeButton);

            search.add(nameButton);
            search.add(typeButton);

            panel.add(search, BorderLayout.SOUTH);

            querySearch.addActionListener(e -> {
                ArrayList<Card> listSearch = (ArrayList<Card>) controller.findCard(GroupButtonUtils.getSelectedButtonText(queryType), querySearch.getText());
                panel.removeAll();
                panel.remove(j);
                panel.revalidate();

                initPokemonTable(Optional.ofNullable(listSearch));
                createTable();
                panel.add(new JScrollPane(j));
                panel.add(search, BorderLayout.SOUTH);

            });
        }
    }
}
