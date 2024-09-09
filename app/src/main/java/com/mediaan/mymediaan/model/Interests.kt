package com.mediaan.mymediaan.model

enum class Interest {
    MUSIC,
    SPORTS,
    HIKING,
    ANIMALS,
    READING,
    TRAVELING,
    COOKING,
    VIDEO_GAMES,
    BOARD_GAMES,
    THEATRE,
    CARS,
    F1,
    DRINKS,
    KARAOKE,
    SUPER_SMASH_BROS,
    PHOTOGRAPHY,
    PAINTING,
    GARDENING,
    WRITING,
    FISHING,
    DANCING,
    TECHNOLOGY;

    override fun toString(): String {
        return name.lowercase().replaceFirstChar { it.uppercase() }.replace("_"," ")
    }
}
