// Prueba 2 - Código con errores léxicos y sintácticos

summon hp = 100
summon mana = @50;
chant "Iniciando batalla"

battle (hp > 0) {
    chant "Atacando"
    summon ataque = 25;
}

grail (mana > 0) {
    chant "Usando mana"