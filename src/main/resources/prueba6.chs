// Prueba 6 - Caída de Servants
summon vida = 0;
summon caido = "Lancelot";

battle (vida == 0) {
    chant "El servant ha caido en batalla";
    skill revivir() {
        summon vida = 100;
        chant "Servant revivido con el Grial!";
    }
    revivir();
}

chant "La orden ha sido completada";