package com.unpri.charactermanager.model;

import java.util.ArrayList;

public class CharacterManager {

    public static final int MAX_CHARACTERS = 20;

    private final ArrayList<Character> characterList;

    public CharacterManager() {
        this.characterList = new ArrayList<>();
    }

    public boolean isNameTaken(String name) {
        if (name == null) {
            return false;
        }
        String normalized = name.trim();
        for (Character c : characterList) {
            if (c.getName().equalsIgnoreCase(normalized)) {
                return true;
            }
        }
        return false;
    }

    public boolean addCharacter(Character character) {
        if (character == null) {
            throw new IllegalArgumentException("Objek karakter tidak boleh null.");
        }
        if (characterList.size() >= MAX_CHARACTERS) {
            throw new IllegalArgumentException(
                    "Kapasitas daftar penuh (maksimal " + MAX_CHARACTERS + " karakter).");
        }
        if (isNameTaken(character.getName())) {
            throw new IllegalArgumentException(
                    "Nama karakter '" + character.getName() + "' sudah digunakan.");
        }
        characterList.add(character);
        return true;
    }

    public Character findByName(String name) {
        if (name == null) {
            return null;
        }
        for (Character c : characterList) {
            if (c.getName().equalsIgnoreCase(name.trim())) {
                return c;
            }
        }
        return null;
    }

    public boolean removeCharacter(String name) {
        Character target = findByName(name);
        if (target == null) {
            return false;
        }
        characterList.remove(target);
        return true;
    }

    public int getTotalCharacters() {
        return characterList.size();
    }

    public ArrayList<Character> getAllCharacters() {
        return new ArrayList<>(characterList);
    }

    public double getAverageLevel() {
        if (characterList.isEmpty()) {
            return 0.0;
        }
        int totalLevel = 0;
        for (Character c : characterList) {
            totalLevel += c.getLevel();
        }
        return (double) totalLevel / characterList.size();
    }

    public ArrayList<Character> findByType(CharacterType type) {
        ArrayList<Character> result = new ArrayList<>();
        for (Character c : characterList) {
            if (c.getType() == type) {
                result.add(c);
            }
        }
        return result;
    }

    public Character findStrongest() {
        if (characterList.isEmpty()) {
            return null;
        }
        Character strongest = characterList.get(0);
        for (Character c : characterList) {
            if (c.getAttackPower() > strongest.getAttackPower()) {
                strongest = c;
            }
        }
        return strongest;
    }
}