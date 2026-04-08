// Prueba 5 - Programa completo ChaldeaScript
summon master = "Fujimaru";
summon hp = 200;
summon mana = 150;
summon turno = 1;

chant "Bienvenido a Chaldea!";
chant master;

skill invocar() {
    chant "Servant invocado con exito!";
    summon hp = hp + 50;
}

skill usar_grail() {
    chant "Usando el Santo Grial!";
    summon mana = mana + 100;
}

battle (hp > 100) {
    chant "El Master sigue en pie!";
    invocar();
    battle (mana > 100) {
        usar_grail();
    }
}

grail (turno > 0) {
    chant "Turno en progreso";
    summon turno = turno - 1;
}

chant "Fin de la sesion en Chaldea";