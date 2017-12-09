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
import upmc.pcg.ui.ihm.card.CardUI;
import upmc.pcg.ui.ihm.menu.PkmnMenu;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Optional;

public class Window extends JFrame {
    private Pokedeck controller;
    private JFrame window;
    private JPanel mainPane;
    private JPanel paneCards;
    private CardUI panelCard;
    private JLabel infoMessage = new JLabel("");
    private CardUI panelCardEnnemy = null;

    public Window(Pokedeck contoller) throws HeadlessException {
        this.controller = contoller;
        this.window = new JFrame();
        this.window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.window.setSize(850, 550);
        //this.window.setResizable(false);
        this.window.setVisible(true);
        mainPane = new JPanel(new BorderLayout());
        paneCards = new JPanel(new GridLayout(1, 2));


        JPanel panelDeck = new JPanel();
        panelDeck.setLayout(new FlowLayout());
        panelDeck.add(infoMessage, SwingConstants.CENTER);

        ImageIcon cardback = new ImageIcon("cardback.jpg");
        JButton deckbtn = new JButton(cardback);
        deckbtn.setBorder(BorderFactory.createEmptyBorder());
        deckbtn.setActionCommand("Choose");
        deckbtn.addActionListener(this::actionPerformed);
        panelDeck.add(deckbtn);
        PkmnMenu pkmnMenu = new PkmnMenu(this.controller);

        JButton fight = new JButton("Fight");
        fight.setActionCommand("Fight");
        fight.addActionListener(this::actionPerformed);

        paneCards.add(panelDeck);
        this.window.setJMenuBar(pkmnMenu);

        mainPane.add(paneCards);
        mainPane.add(fight, BorderLayout.SOUTH);
        mainPane.setBorder(new EmptyBorder(10, 10, 10, 10));


        this.window.add(mainPane);
        this.window.revalidate();
    }

    public void setPlayerColor(Color color) {
        this.mainPane.setBackground(color);
        this.mainPane.revalidate();
    }

    public void start(Pokedeck controller) {
        this.controller.initNames();
        this.setPlayerColor(this.controller.getMainColor());

        //this.panelCard.modifyName();
        this.window.revalidate();
    }


    private void actionPerformed(ActionEvent e) {
        if ("Choose".equals(e.getActionCommand())) {
            ChoserUi choserUI = new ChoserUi(this.controller, Optional.empty());
            //controller.chooseCard(tiModel.getPkmnAt(j.getSelectedRow()));
        }

        if ("Fight".equals(e.getActionCommand()) && (this.controller.getAttack() != null)) {
            try {
                this.controller.play();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void pushCardUI(String name, String hp, String cardTitle, String energyType, int attacks) throws IOException {
        panelCard = new CardUI(controller, name, hp, cardTitle, energyType, attacks);
        if (paneCards.getComponents().length <= 3) //We only have the deck and nothing else
            paneCards.add(panelCard);
        paneCards.revalidate();
        this.revalidate();
    }

    public void setHP(int hp) {
        this.panelCard.setHP(hp);
    }

    public void showMessage(String message) {
        this.infoMessage.setText(message);
        mainPane.revalidate();
        this.revalidate();
    }

    public void rem() {
        this.paneCards.remove(panelCard);
        this.paneCards.revalidate();
        this.mainPane.revalidate();
    }

    public void addEnemy(String name, String hp, String cardTitle, String energyType, int attacks) throws IOException {
        panelCardEnnemy = new CardUI(controller, name, hp, cardTitle, energyType, attacks);
        //Disable all the buttons for attacks
        Component[] components = panelCardEnnemy.getComponents();
        for (Component component : components) {
            if (component.getClass().equals(JButton.class)) {
                component.setEnabled(false);
            }
        }
        paneCards.add(panelCardEnnemy);
        panelCardEnnemy.revalidate();
        paneCards.revalidate();
        mainPane.revalidate();
        this.revalidate();
    }

    public void updateEnemy(int hp) {
        this.panelCardEnnemy.setHP(hp);
    }

    public void alertNetwork() {
        JOptionPane.showMessageDialog(this,
                "Attention, veuillez vous connecter afin d'avoir la meilleure expérience possible ;)",
                "Connexion non detectée",
                JOptionPane.WARNING_MESSAGE);
    }


    public void changeColor() {
        this.setBackground(Color.YELLOW);
    }
}
