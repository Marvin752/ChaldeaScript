// Prueba 7 - Errores sintácticos
summon hp = 100
battle hp > 0 {
    chant "Falta parentesis"
}
skill {
    chant "Falta nombre de skill";
grail (mana > 0)
    chant "Falta llave";
}