/*
 * Copyright (c)
 *        @author Timothé PARDIEU
 *                 ${PACKAGE_NAME}
 *                 Created on - ${DATE} (${TIME})
 *                 Build for project ${PROJECT_NAME}
 *
 */

package upmc.pcg.ui.ihm.card;

import upmc.pcg.controller.Pokedeck;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * This class enables to create a Panel in form of a Pokemon Card
 */
public class CardUI extends JPanel {

    private JLabel name;
    private JLabel hp;
    private ImageIcon imgPkmn;
    private ImageIcon energy;
    private Pokedeck controller;

    /**
     * Construct a new panel with a form of card
     *
     * @param controller the controller (getting the data)
     * @param name       the name of the pokemon
     * @param hp         the hp of the pokemon
     * @param cardTitle  the name
     * @param energyType the type of th epokemon
     * @param attacks
     * @throws IOException
     */
    public CardUI(Pokedeck controller, String name, String hp, String cardTitle, String energyType, int attacks) throws IOException {
        super();
        this.controller = controller;
        GridBagLayout g = new GridBagLayout();
        Border blackline = BorderFactory.createLineBorder(Color.black);
        this.setLayout(g);
        this.setBorder(blackline);

        this.name = new JLabel(name);
        this.hp = new JLabel(hp);

        //if internet fetch a possible pokemon img
        try {
            URL url = new URL("http://ray0.be/pokeapi/pokemon-img/fr/" + cardTitle);
            BufferedImage img = ImageIO.read(url);
            if (img != null)
                this.imgPkmn = new ImageIcon(img);
            else //if it's not a recognized pkmn
                this.imgPkmn = new ImageIcon("Pikachu.png");

            url = new URL("https://www.pokebip.com/pokedex/images/gen6_types/" + energyType + ".png");
            img = ImageIO.read(url);
            if (img != null)
                this.energy = new ImageIcon(img);
            else
                this.energy = new ImageIcon("energy.png");//new ImageIcon("https://www.pokebip.com/pokedex/images/gen6_types/10.png");
        } catch (Exception e) {
            //if no connection
            this.imgPkmn = new ImageIcon("Pikachu.png");
            this.energy = new ImageIcon("energy.png");//new ImageIcon("https://www.pokebip.com/pokedex/images/gen6_types/10.png");

            e.getStackTrace();
        }

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gbc.gridy = 0; // la grille commence en (0, 0)
        gbc.gridwidth = 2;
        gbc.gridheight = 1; /* une seule cellule verticalement suffit */
        gbc.anchor = GridBagConstraints.BASELINE_LEADING;
        gbc.weightx = 0.;
        gbc.insets = new Insets(0, 15, 10, 10);
        this.add(this.name, gbc);

        gbc.gridx = 2;
        gbc.weightx = 0.3;
        this.add(new JLabel(energy), gbc);

        gbc.gridx = 3; /* pour les dubitatifs, gridy vaut toujours 3 ;-) */
        gbc.gridwidth = GridBagConstraints.RELATIVE; // le bouton est l'avant dernier composant de sa ligne.
        gbc.weightx = 0.3;
        gbc.anchor = GridBagConstraints.BASELINE_TRAILING; // Pas LINE_END, ni EAST.
        gbc.insets = new Insets(0, 0, 0, 15);
        this.add(this.hp, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 0.;
        this.add(new JLabel(imgPkmn), gbc);

        gbc.gridy = 2;
        for (int i = 0; i < attacks; i++) {
            if (!"".equals(controller.getMainPkmn().getAttack(i))) {
                System.out.println(i);
                gbc.gridy = gbc.gridy + i + 1;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.anchor = GridBagConstraints.CENTER;
                gbc.insets = new Insets(3, 5, 2, 15);
                JButton j = new JButton(controller.getMainPkmn().getAttack(i));
                //j.setActionCommand(String.valueOf(i));
                j.addActionListener(this::actionPerformed);
                this.add(j, gbc);
            }
        }


    }

    private void actionPerformed(ActionEvent e) {
        int dmg = Integer.parseInt(e.getActionCommand().substring(e.getActionCommand().indexOf('[') + 1, e.getActionCommand().length()-1));
        System.out.println(dmg);
        this.controller.setAttack(dmg);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(0, 3);
    }

    public void setHP(int hp) {
        this.hp.setText("" + hp);
    }
}
