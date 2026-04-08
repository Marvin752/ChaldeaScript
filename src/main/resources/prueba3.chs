// Prueba 3 - Skills y operadores lógicos
summon nivel = 10;
summon activo = noble;

skill atacar() {
    summon dano = 50;
    chant "Noble Phantasm activado!";
}

skill defender() {
    summon defensa = 30;
    chant "Defendiendo a Chaldea!";
}

battle (nivel > 5 && activo == noble) {
    chant "Servant listo para batalla";
    atacar();
}