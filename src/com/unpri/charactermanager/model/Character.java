package com.unpri.charactermanager.model;

public class Character {
    public static final int MIN_LEVEL = 1;
    public static final int MAX_LEVEL = 99;
    public static final int MAGE_MAX_LEVEL = 60;

    private int id;
    private String name;
    private CharacterType type;
    private int level;
    private int hitPoints;
    private int attackPower;
    private int mana;

    public Character(String name, CharacterType type, int level) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama karakter tidak boleh kosong.");
        }
        if (type == null) {
            throw new IllegalArgumentException("Role karakter tidak boleh null.");
        }
        validateLevelForType(type, level);

        this.name = name.trim();
        this.type = type;
        this.level = level;
        this.hitPoints = calculateBaseHP();
        this.attackPower = calculateBaseAttack();
        this.mana = 100;
    }

    public Character(int id, String name, CharacterType type, int level, int hitPoints, int attackPower) {
        this(name, type, level);
        this.id = id;
        this.hitPoints = hitPoints;
        this.attackPower = attackPower;
    }

    private void validateLevelForType(CharacterType type, int level) {
        if (level < MIN_LEVEL || level > MAX_LEVEL) {
            throw new IllegalArgumentException(
                    "Level karakter harus berada di antara " + MIN_LEVEL + " hingga " + MAX_LEVEL + ".");
        }
        if (type == CharacterType.MAGE && level > MAGE_MAX_LEVEL) {
            throw new IllegalArgumentException(
                    "Karakter dengan role MAGE tidak boleh memiliki level di atas " + MAGE_MAX_LEVEL + ".");
        }
    }

    private int calculateBaseHP() {
        return 100 + (level * 10);
    }

    private int calculateBaseAttack() {
        return 10 + (level * 5);
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public CharacterType getType() { return type; }
    public int getLevel() { return level; }
    public int getHitPoints() { return hitPoints; }
    public int getAttackPower() { return attackPower; }
    public int getMana() { return mana; }

    public void setId(int id) {
        this.id = id;
    }

    public void setLevel(int level) {
        validateLevelForType(this.type, level);
        this.level = level;
        this.hitPoints = calculateBaseHP();
        this.attackPower = calculateBaseAttack();
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public boolean isAlive() {
        return this.hitPoints > 0;
    }

    @Override
    public String toString() {
        return "Character{id=" + id +
               ", name='" + name + '\'' +
               ", type=" + type +
               ", level=" + level +
               ", hp=" + hitPoints +
               ", atk=" + attackPower +
               ", mana=" + mana + '}';
    }
}