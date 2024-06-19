package com.mediaan.mymediaan.repository

import com.mediaan.mymediaan.model.Office
import com.mediaan.mymediaan.model.Profile
import com.mediaan.mymediaan.model.TwoTruthsOneLieEntity

class ProfileRepository {
    private val profiles = listOf(
        Profile(
            id = "johndoe",
            firstName = "John",
            lastName = "Doe",
            age = 28,
            nickName = "Johnny",
            office = Office.HEERLEN,
            interests = listOf("Programming", "Hiking"),
            twoTruthsOneLie = listOf(
                TwoTruthsOneLieEntity("I love skydiving", true),
                TwoTruthsOneLieEntity("I have a pet snake", false),
                TwoTruthsOneLieEntity("I can speak 5 languages", true)
            ),
            avatarIcon = 0
        ),
        Profile(
            id = "janesmith",
            firstName = "Jane",
            lastName = "Smith",
            age = 32,
            nickName = "Janey",
            office = Office.HASSELT,
            interests = listOf("Reading", "Traveling"),
            twoTruthsOneLie = listOf(
                TwoTruthsOneLieEntity("I've been to 10 countries", true),
                TwoTruthsOneLieEntity("I hate chocolate", false),
                TwoTruthsOneLieEntity("I'm a marathon runner", true)
            ),
            avatarIcon = 0
        ),
        Profile(
            id = "alicejohnson",
            firstName = "Alice",
            lastName = "Johnson",
            age = 25,
            nickName = "Ally",
            office = Office.DUSSELDORF,
            interests = listOf("Cooking", "Gardening"),
            twoTruthsOneLie = listOf(
                TwoTruthsOneLieEntity("I have a twin sister", true),
                TwoTruthsOneLieEntity("I'm allergic to cats", true),
                TwoTruthsOneLieEntity("I once met a movie star", false)
            ),
            avatarIcon = 0
        ),
        Profile(
            id = "bobbrown",
            firstName = "Bob",
            lastName = "Brown",
            age = 30,
            nickName = "Bobby",
            office = Office.HEERLEN,
            interests = listOf("Cycling", "Photography"),
            twoTruthsOneLie = listOf(
                TwoTruthsOneLieEntity("I play the guitar", true),
                TwoTruthsOneLieEntity("I can't swim", false),
                TwoTruthsOneLieEntity("I love spicy food", true)
            ),
            avatarIcon = 0
        ),
        Profile(
            id = "evedavis",
            firstName = "Eve",
            lastName = "Davis",
            age = 27,
            nickName = "Evie",
            office = Office.HASSELT,
            interests = listOf("Yoga", "Painting"),
            twoTruthsOneLie = listOf(
                TwoTruthsOneLieEntity("I have three brothers", false),
                TwoTruthsOneLieEntity("I once lived in Japan", true),
                TwoTruthsOneLieEntity("I can juggle", true)
            ),
            avatarIcon = 0
        ),
        Profile(
            id = "frankmiller",
            firstName = "Frank",
            lastName = "Miller",
            age = 35,
            nickName = "Frankie",
            office = Office.DUSSELDORF,
            interests = listOf("Gaming", "Fishing"),
            twoTruthsOneLie = listOf(
                TwoTruthsOneLieEntity("I have climbed Mt. Everest", false),
                TwoTruthsOneLieEntity("I own a classic car", true),
                TwoTruthsOneLieEntity("I once swam with dolphins", true)
            ),
            avatarIcon = 0
        ),
        Profile(
            id = "gracewilson",
            firstName = "Grace",
            lastName = "Wilson",
            age = 29,
            nickName = "Gracie",
            office = Office.HEERLEN,
            interests = listOf("Writing", "Dancing"),
            twoTruthsOneLie = listOf(
                TwoTruthsOneLieEntity("I have written a book", true),
                TwoTruthsOneLieEntity("I dislike coffee", false),
                TwoTruthsOneLieEntity("I have a black belt in karate", true)
            ),
            avatarIcon = 0
        ),
        Profile(
            id = "henrymoore",
            firstName = "Henry",
            lastName = "Moore",
            age = 31,
            nickName = "Hank",
            office = Office.HASSELT,
            interests = listOf("Running", "Cooking"),
            twoTruthsOneLie = listOf(
                TwoTruthsOneLieEntity("I have a fear of heights", false),
                TwoTruthsOneLieEntity("I love to bake", true),
                TwoTruthsOneLieEntity("I have been on TV", true)
            ),
            avatarIcon = 0
        ),
        Profile(
            id = "ivytaylor",
            firstName = "Ivy",
            lastName = "Taylor",
            age = 26,
            nickName = "Ives",
            office = Office.DUSSELDORF,
            interests = listOf("Traveling", "Photography"),
            twoTruthsOneLie = listOf(
                TwoTruthsOneLieEntity("I have a pet dog", true),
                TwoTruthsOneLieEntity("I dislike ice cream", false),
                TwoTruthsOneLieEntity("I have bungee jumped", true)
            ),
            avatarIcon = 0
        ),
        Profile(
            id = "jackanderson",
            firstName = "Jack",
            lastName = "Anderson",
            age = 34,
            nickName = "Jackie",
            office = Office.HEERLEN,
            interests = listOf("Skiing", "Chess"),
            twoTruthsOneLie = listOf(
                TwoTruthsOneLieEntity("I have visited every continent", false),
                TwoTruthsOneLieEntity("I can play the piano", true),
                TwoTruthsOneLieEntity("I have a tattoo", true)
            ),
            avatarIcon = 0
        )
    )

    fun getAllProfiles(): List<Profile> {
        return profiles
    }

    fun getProfileById(id: String): Profile {
        return profiles.first { it.id == id }
    }
}