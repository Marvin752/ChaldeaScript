// Prueba 1 - Código válido ChaldeaScript
// Invocación de servants en Chaldea

summon hp = 100;
summon mana = 50;
summon nombre = "Artoria Pendragon";

chant "Bienvenido a Chaldea, Master";

battle (hp > 0) {
    chant "El servant sigue en batalla";
    summon ataque = 25;
}

skill curar() {
    summon vida = 100;
    chant "Servant curado!";
}

grail (mana > 0) {
    chant "Usando mana del Santo Grial";
}