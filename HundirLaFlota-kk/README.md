[Tareas]

[IDEAS]
-añadir capa para avións (tamaño 3 - 5) e helicopteros (tamaño 1)
    int[][][] tableroFlota = new int[7][7][2]
    (tableroDisparos tamen 3D)             ^
                                           ^
                        tableroFlota[7][7][0] -> barcos (torpedos)
                        tableroFlota[7][7][1] -> avións (misiles)

-os avións poden dañar barcos circundantes (+++++)

-Modo multiplayer (+++++)

-o outro xogador pode usar radares (+++++)
    os radares revelan no tableroDisparosJugador si hai barcos cerca

-añadir dificultad: (constatante en HundirLaFlota.java)
    ·facil:     ·5 radares
                ·0% de probabilidade de impacto dun avion a un barco nas posicións circundantes

    ·normal:    ·3 radares
                ·50% de probabilidade de impacto dun avion a un barco nas posicións circundantes


    ·dificil:   ·1 radar
                ·100% de probabilidade de impacto dun avion a un barco nas posicións circundantes

si creo clases para barcos e avion, guardar en un array do tamaño do barco/avion Puntos3D das posicions, si todas as posicions son impactadas = barco/avion destruido
[p.getFila()][p.getCol()][p.getNivel()]

todas as posicions dun barco no mapa fan referencia ao mismo objeto Barco
