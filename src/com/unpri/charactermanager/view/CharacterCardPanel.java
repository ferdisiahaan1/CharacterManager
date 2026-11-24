package com.unpri.charactermanager.view;

import com.unpri.charactermanager.model.Character;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CharacterCardPanel extends JPanel {

    public CharacterCardPanel(Character character) {
        setLayout(new BorderLayout(8, 6));
        setOpaque(true);
        setBorder(new EmptyBorder(10, 12, 10, 12));

        // Baris atas: Nama karakter di kiri, Level di kanan
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);

        JLabel nameLabel = new JLabel(character.getName());
        JLabel levelLabel = new JLabel("Lv. " + character.getLevel());

        topPanel.add(nameLabel, BorderLayout.WEST);
        topPanel.add(levelLabel, BorderLayout.EAST);

        // Baris bawah: Role, HP, ATK, Mana, dan Status hidup/mati
        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 0));
        infoPanel.setOpaque(false);

        String roleText = (character.getType() != null) ? character.getType().name() : "-";
        String statusText = character.isAlive() ? "Aktif" : "Kalah";

        JLabel roleLabel = new JLabel("Role: " + roleText);
        JLabel hpLabel = new JLabel("HP: " + character.getHitPoints());
        JLabel atkLabel = new JLabel("ATK: " + character.getAttackPower());
        JLabel manaLabel = new JLabel("Mana: " + character.getMana());
        JLabel statusLabel = new JLabel("Status: " + statusText);

        infoPanel.add(roleLabel);
        infoPanel.add(hpLabel);
        infoPanel.add(atkLabel);
        infoPanel.add(manaLabel);
        infoPanel.add(statusLabel);

        add(topPanel, BorderLayout.NORTH);
        add(infoPanel, BorderLayout.CENTER);
    }
}