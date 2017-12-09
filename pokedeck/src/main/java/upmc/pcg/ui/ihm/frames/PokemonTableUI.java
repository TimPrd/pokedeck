/*
 * Copyright (c)
 *        @author Timothé PARDIEU
 *                 ${PACKAGE_NAME}
 *                 Created on - ${DATE} (${TIME})
 *                 Build for project ${PROJECT_NAME}
 *
 */

package upmc.pcg.ui.ihm.frames;


import upmc.pcg.game.card.Card;
import upmc.pcg.game.card.PokemonCard;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Create a special Model for the pokemon table with specific names in columns
 */
class PokemonTableUI extends AbstractTableModel {

    private static final int NB_OF_COLUMN = 6;
    private List<Card> cards;
    private String[] columnNames;

    /**
     * Constructor
     *
     * @param columnNames the names of all the columns
     * @param cards       the list of cards to add
     */
    PokemonTableUI(String[] columnNames, List<Card> cards) {
        super();
        this.columnNames = columnNames;
        this.cards = cards;

    }

    @Override
    public int getRowCount() {
        return cards.size();
    }

    @Override
    public int getColumnCount() {
        return NB_OF_COLUMN;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = "??";
        PokemonCard card = (PokemonCard) cards.get(rowIndex);
        switch (columnIndex) {
            case 0:
                value = card.getCardTitle();
                break;
            case 1:
                value = card.getType();
                break;
            case 2:
                value = card.getHP();
                break;
            case 3:
                value = card.getAttack(0);
                break;
            case 4:
                value = card.getAttack(1);
                break;
            case 5:
                value = card.getAttack(2);
                break;
        }
        return value;

    }


    Card getPkmnAt(int row) {
        return cards.get(row);
    }
}