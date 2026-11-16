package com.unpri.charactermanager.model;

public class Character {
    // ===== ATRIBUT (private — tidak bisa diakses langsung dari luar class) =====
    private int id;
    private String name;
    private CharacterType type;
    private int level;
    private int hitPoints;
    private int attackPower;
    private int mana; // <-- ATRIBUT BARU DITAMBAHKAN DI SINI

    // ===== CONSTRUCTOR 1: untuk membuat karakter BARU =====
    public Character(String name, CharacterType type, int level) {
        this.name = name;              
        this.type = type;
        this.level = level;
        this.hitPoints = calculateBaseHP();      
        this.attackPower = calculateBaseAttack(); 
        this.mana = 100; // Nilai default mana awal
    }

    // ===== CONSTRUCTOR 2: untuk MEMUAT karakter dari database =====
    public Character(int id, String name, CharacterType type, int level, int hitPoints, int attackPower) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.level = level;
        this.hitPoints = hitPoints;
        this.attackPower = attackPower;
    }

    // ===== HELPER PRIVATE: hanya dipakai internal class ini =====
    private int calculateBaseHP() {
        return 100 + (level * 10);   
    }

    private int calculateBaseAttack() {
        return 10 + (level * 5);     
    }
    
    // ===== GETTER: cara AMAN membaca nilai atribut private dari luar =====
    public int getId() { return id; }
    public String getName() { return name; }
    public CharacterType getType() { return type; }
    public int getLevel() { return level; }
    public int getHitPoints() { return hitPoints; }
    public int getAttackPower() { return attackPower; }
    public int getMana() { return mana; } // <-- GETTER MANA BARU

    // ===== SETTER: cara AMAN mengubah atribut, dengan VALIDASI =====
    public void setId(int id) {
        this.id = id;
    }

    public void setLevel(int level) {
        if (level < 1) {                       
            throw new IllegalArgumentException("Level minimal adalah 1");
        }
        this.level = level;
        this.hitPoints = calculateBaseHP();      
        this.attackPower = calculateBaseAttack();
    }

    public void setMana(int mana) { // <-- SETTER MANA BARU
        this.mana = mana;
    }

    // ===== METODE BARU: isAlive =====
    public boolean isAlive() { // <-- METODE ISALIVE BARU
        return this.hitPoints > 0;
    }

    // ===== toString(): representasi teks object, berguna untuk debugging =====
    @Override
    public String toString() {
        return "Character{id=" + id +
               ", name='" + name + '\'' +
               ", type=" + type +
               ", level=" + level +
               ", hp=" + hitPoints +
               ", atk=" + attackPower + 
               ", mana=" + mana + '}'; // Ditambahkan agar mana ikut tercetak
    }
}