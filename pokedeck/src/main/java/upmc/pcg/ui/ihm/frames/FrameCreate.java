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
import upmc.pcg.game.actions.Act;
import upmc.pcg.game.card.EnergyType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FrameCreate extends JFrame {

    private final Pokedeck controller;
    private JFrame frame;
    private JPanel mainPanel;
    private JTextField cardTitle;
    private JComboBox cardEnergy;
    private SpinnerNumberModel model1;
    private JSpinner cardHp;
    private JTextField attack;
    private SpinnerNumberModel modelAttack;
    private JSpinner damage;
    private JComboBox comboBox;

    public FrameCreate(Pokedeck controller) {
        this.controller = controller;

    }

    @Act("CreatePkmn")
    public void createPokemonFrame() {
        this.frame = new JFrame();
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.setSize(300, 300);
        this.frame.setResizable(true);


        mainPanel = new JPanel(new GridLayout(6, 2));

        JLabel name = new JLabel("Nom", SwingConstants.CENTER);
        cardTitle = new JTextField("Nom", SwingConstants.CENTER);

        JLabel energy = new JLabel("Energy Type", SwingConstants.CENTER);

        //SpinnerNumberModel modelEnergy = new SpinnerNumberModel(1, 1, EnergyType.values().length, 1);
        cardEnergy = new JComboBox();
        cardEnergy.setModel(new DefaultComboBoxModel(EnergyType.values()));


        JLabel hp = new JLabel("PV", SwingConstants.CENTER);
        model1 = new SpinnerNumberModel(50, 1, 200, 1);
        cardHp = new JSpinner(model1);

        JLabel att = new JLabel("Attack 1", SwingConstants.CENTER);
        attack = new JTextField("Attack", SwingConstants.CENTER);
        modelAttack = new SpinnerNumberModel(5, 1, 200, 1);
        JLabel dmg = new JLabel("Damages", SwingConstants.CENTER);
        damage = new JSpinner(modelAttack);

        mainPanel.add(name);
        mainPanel.add(cardTitle);

        mainPanel.add(energy);
        mainPanel.add(cardEnergy);

        mainPanel.add(hp);
        mainPanel.add(cardHp);

        mainPanel.add(att);
        mainPanel.add(attack);
        mainPanel.add(dmg);
        mainPanel.add(damage);
        JButton addButton = new JButton("Ajouter ! ");
        addButton.setActionCommand("AddPkmn");
        addButton.addActionListener(this::actionPerformed);
        mainPanel.add(addButton);
        this.frame.add(mainPanel);
        this.frame.setVisible(true);
    }


    @Act("AddToDeck")
    public void createAddToDeckFrame() {
        createdropdownframe();

        JButton addButton = new JButton("Ajouter ! ");
        addButton.setActionCommand("AddToDeck");
        addButton.addActionListener(this::actionPerformed);
        mainPanel.add(addButton);
        this.frame.add(mainPanel);
        this.frame.setVisible(true);
    }

    @Act("DeletePkmn")
    public void createDeleteFromDeckFrame() {

        createdropdownframe();

        JButton addButton = new JButton("Supprimer ! ");
        addButton.setActionCommand("DeletePkmn");
        addButton.addActionListener(this::actionPerformed);
        mainPanel.add(addButton);


        this.frame.add(mainPanel);
        this.frame.setVisible(true);
    }


    private void createdropdownframe() {
        this.frame = new JFrame();
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.setSize(700, 50);
        this.frame.setResizable(false);
        mainPanel = new JPanel(new GridLayout(1, 1));
        comboBox = new JComboBox(this.controller.collec().toArray());
        mainPanel.add(comboBox);
    }


    private void actionPerformed(ActionEvent e) {
        ArrayList al = null;
        if ("AddPkmn".equals(e.getActionCommand())) {
            al = new ArrayList<>();
            al.add(this.cardTitle.getText());
            String value = cardEnergy.getSelectedIndex() + "";
            al.add(EnergyType.values()[Integer.parseInt(value)]);
            Map<String, Integer> map = new HashMap<>();

            String value2 = damage.getValue() + "";

            map.put(this.attack.getText(), Integer.parseInt(value2));
            al.add(map);
            al.add(this.cardHp.getValue());


            //this.controller.createCard(al);
        }

        if ("AddToDeck".equals(e.getActionCommand())) {
            al = new ArrayList<Integer>();
            al.add(comboBox.getSelectedIndex());
        }

        if ("DeletePkmn".equals(e.getActionCommand())) {
            al = new ArrayList<Integer>();
            al.add(comboBox.getSelectedIndex());
        }


        this.controller.fetchAction(e.getActionCommand(), al);

    }


}
