// Prueba 4 - Ciclos grail
summon rondas = 3;
summon mana = 100;

grail (rondas > 0) {
    chant "Nueva ronda iniciada";
    grail (mana > 50) {
        chant "Mana suficiente para invocar";
        summon mana = mana - 10;
    }
    summon rondas = rondas - 1;
}

chant "Batalla terminada!";