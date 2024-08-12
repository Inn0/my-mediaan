package com.mediaan.mymediaan.repository

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mediaan.mymediaan.R
import com.mediaan.mymediaan.model.Office
import com.mediaan.mymediaan.model.Profile
import com.mediaan.mymediaan.model.TwoTruthsOneLieEntity
import kotlinx.coroutines.tasks.await

class ProfileRepository {
    private val profiles = listOf(
        Profile(
            id = "johndoe",
            firstName = "John",
            lastName = "Doe",
            age = 28,
            nickName = "Johnny",
            office = Office.HEERLEN,
            twoTruthsOneLie = listOf(
                TwoTruthsOneLieEntity("I love skydiving", true),
                TwoTruthsOneLieEntity("I have a pet snake", false),
                TwoTruthsOneLieEntity("I can speak 5 languages", true)
            ),
            avatarIcon = R.drawable.account_circle
        ),
        Profile(
            id = "janesmith",
            firstName = "Jane",
            lastName = "Smith",
            age = 32,
            nickName = "Janey",
            office = Office.HASSELT,
            interests = listOf("Reading", "Traveling", "Cooking"),
            twoTruthsOneLie = listOf(
                TwoTruthsOneLieEntity("I've been to 10 countries", true),
                TwoTruthsOneLieEntity("I hate chocolate", false),
                TwoTruthsOneLieEntity("I'm a marathon runner", true)
            ),
            avatarIcon = R.drawable.face_woman
        ),
        Profile(
            id = "alicejohnson",
            firstName = "Alice",
            lastName = "Johnson",
            age = 25,
            nickName = "Ally",
            office = Office.DUSSELDORF,
            interests = listOf("Cooking", "Gardening", "Reading", "Traveling"),
            twoTruthsOneLie = listOf(
                TwoTruthsOneLieEntity("I have a twin sister", true),
                TwoTruthsOneLieEntity("I'm allergic to cats", true),
                TwoTruthsOneLieEntity("I once met a movie star", false)
            ),
            avatarIcon = R.drawable.face_woman_profile
        ),
        Profile(
            id = "bobbrown",
            firstName = "Bob",
            lastName = "Brown",
            age = 30,
            nickName = "Bobby",
            office = Office.HEERLEN,
            interests = listOf("Cycling", "Photography", "Gaming"),
            twoTruthsOneLie = listOf(
                TwoTruthsOneLieEntity("I play the guitar", true),
                TwoTruthsOneLieEntity("I can't swim", false),
                TwoTruthsOneLieEntity("I love spicy food", true)
            ),
            avatarIcon = R.drawable.pac_man
        ),
        Profile(
            id = "evedavis",
            firstName = "Eve",
            lastName = "Davis",
            age = 27,
            nickName = "Evie",
            office = Office.HASSELT,
            interests = listOf("Yoga", "Painting", "Photography", "Traveling"),
            twoTruthsOneLie = listOf(
                TwoTruthsOneLieEntity("I have three brothers", false),
                TwoTruthsOneLieEntity("I once lived in Japan", true),
                TwoTruthsOneLieEntity("I can juggle", true)
            ),
            avatarIcon = R.drawable.face_woman_profile
        ),
        Profile(
            id = "frankmiller",
            firstName = "Frank",
            lastName = "Miller",
            age = 35,
            nickName = "Frankie",
            office = Office.DUSSELDORF,
            twoTruthsOneLie = listOf(
                TwoTruthsOneLieEntity("I have climbed Mt. Everest", false),
                TwoTruthsOneLieEntity("I own a classic car", true),
                TwoTruthsOneLieEntity("I once swam with dolphins", true)
            ),
            avatarIcon = R.drawable.face_man_profile
        ),
        Profile(
            id = "gracewilson",
            firstName = "Grace",
            lastName = "Wilson",
            age = 29,
            nickName = "Gracie",
            office = Office.HEERLEN,
            interests = listOf("Writing", "Dancing", "Cycling", "Hiking"),
            twoTruthsOneLie = listOf(
                TwoTruthsOneLieEntity("I have written a book", true),
                TwoTruthsOneLieEntity("I dislike coffee", false),
                TwoTruthsOneLieEntity("I have a black belt in karate", true)
            ),
            avatarIcon = R.drawable.pac_man
        ),
        Profile(
            id = "henrymoore",
            firstName = "Henry",
            lastName = "Moore",
            age = 31,
            nickName = "Hank",
            office = Office.HASSELT,
            interests = listOf("Running", "Cooking", "Fishing"),
            twoTruthsOneLie = listOf(
                TwoTruthsOneLieEntity("I have a fear of heights", false),
                TwoTruthsOneLieEntity("I love to bake", true),
                TwoTruthsOneLieEntity("I have been on TV", true)
            ),
            avatarIcon = R.drawable.account_circle
        ),
        Profile(
            id = "ivytaylor",
            firstName = "Ivy",
            lastName = "Taylor",
            age = 26,
            nickName = "Ives",
            office = Office.DUSSELDORF,
            interests = listOf("Traveling", "Photography", "Reading"),
            twoTruthsOneLie = listOf(
                TwoTruthsOneLieEntity("I have a pet dog", true),
                TwoTruthsOneLieEntity("I dislike ice cream", false),
                TwoTruthsOneLieEntity("I have bungee jumped", true)
            ),
            avatarIcon = R.drawable.face_woman
        ),
        Profile(
            id = "jackanderson",
            firstName = "Jack",
            lastName = "Anderson",
            age = 34,
            nickName = "Jackie",
            office = Office.HEERLEN,
            interests = listOf("Skiing", "Chess", "Cooking", "Traveling"),
            twoTruthsOneLie = listOf(
                TwoTruthsOneLieEntity("I have visited every continent", false),
                TwoTruthsOneLieEntity("I can play the piano", true),
                TwoTruthsOneLieEntity("I have a tattoo", true)
            ),
            avatarIcon = R.drawable.face_man
        ),
        Profile(
            id = "lucywhite",
            firstName = "Lucy",
            lastName = "White",
            age = 24,
            nickName = "Lulu",
            office = Office.HASSELT,
            interests = listOf("Dancing", "Reading"),
            twoTruthsOneLie = listOf(
                TwoTruthsOneLieEntity("I have a pet parrot", true),
                TwoTruthsOneLieEntity("I dislike coffee", true),
                TwoTruthsOneLieEntity("I can speak French", false)
            ),
            avatarIcon = R.drawable.face_woman
        ),
        Profile(
            id = "charliegreen",
            firstName = "Charlie",
            lastName = "Green",
            age = 33,
            nickName = "Chuck",
            office = Office.DUSSELDORF,
            twoTruthsOneLie = listOf(
                TwoTruthsOneLieEntity("I have been to Australia", true),
                TwoTruthsOneLieEntity("I can't ride a bike", false),
                TwoTruthsOneLieEntity("I love sushi", true)
            ),
            avatarIcon = R.drawable.face_man_profile
        ),
        Profile(
            id = "lisabrown",
            firstName = "Lisa",
            lastName = "Brown",
            age = 28,
            nickName = "Lee",
            office = Office.HEERLEN,
            interests = listOf("Cooking", "Yoga"),
            twoTruthsOneLie = listOf(
                TwoTruthsOneLieEntity("I have been skydiving", true),
                TwoTruthsOneLieEntity("I have two cats", false),
                TwoTruthsOneLieEntity("I can play the violin", true)
            ),
            avatarIcon = R.drawable.face_woman_profile
        ),
        Profile(
            id = "michaeljohnson",
            firstName = "Michael",
            lastName = "Johnson",
            age = 30,
            nickName = "Mike",
            office = Office.HASSELT,
            interests = listOf("Photography", "Traveling"),
            twoTruthsOneLie = listOf(
                TwoTruthsOneLieEntity("I have a black belt in karate", true),
                TwoTruthsOneLieEntity("I can't swim", false),
                TwoTruthsOneLieEntity("I love spicy food", true)
            ),
            avatarIcon = R.drawable.face_man
        ),
        Profile(
            id = "sarawilson",
            firstName = "Sara",
            lastName = "Wilson",
            age = 27,
            nickName = "Sari",
            office = Office.DUSSELDORF,
            interests = listOf("Painting", "Hiking", "Yoga"),
            twoTruthsOneLie = listOf(
                TwoTruthsOneLieEntity("I have a twin brother", false),
                TwoTruthsOneLieEntity("I once met a celebrity", true),
                TwoTruthsOneLieEntity("I love coffee", true)
            ),
            avatarIcon = R.drawable.face_woman_profile
        ),
        Profile(
            id = "robertsmith",
            firstName = "Robert",
            lastName = "Smith",
            age = 32,
            nickName = "Rob",
            office = Office.HEERLEN,
            interests = listOf("Cycling", "Cooking"),
            twoTruthsOneLie = listOf(
                TwoTruthsOneLieEntity("I play the drums", true),
                TwoTruthsOneLieEntity("I have been to Africa", false),
                TwoTruthsOneLieEntity("I love horror movies", true)
            ),
            avatarIcon = R.drawable.face_man
        ),
        Profile(
            id = "chrisdavies",
            firstName = "Chris",
            lastName = "Davies",
            age = 29,
            nickName = "Chris",
            office = Office.HASSELT,
            twoTruthsOneLie = listOf(
                TwoTruthsOneLieEntity("I have been skydiving", false),
                TwoTruthsOneLieEntity("I own a motorcycle", true),
                TwoTruthsOneLieEntity("I love classical music", true)
            ),
            avatarIcon = R.drawable.account_circle
        ),
        Profile(
            id = "emilyclark",
            firstName = "Emily",
            lastName = "Clark",
            age = 26,
            nickName = "Em",
            office = Office.DUSSELDORF,
            interests = listOf("Gardening", "Reading", "Traveling"),
            twoTruthsOneLie = listOf(
                TwoTruthsOneLieEntity("I have a pet rabbit", true),
                TwoTruthsOneLieEntity("I dislike chocolate", false),
                TwoTruthsOneLieEntity("I can play the guitar", true)
            ),
            avatarIcon = R.drawable.face_woman
        ),
        Profile(
            id = "davidharris",
            firstName = "David",
            lastName = "Harris",
            age = 34,
            nickName = "Dave",
            office = Office.HEERLEN,
            interests = listOf("Chess", "Cooking"),
            twoTruthsOneLie = listOf(
                TwoTruthsOneLieEntity("I have traveled to 20 countries", true),
                TwoTruthsOneLieEntity("I hate coffee", false),
                TwoTruthsOneLieEntity("I can speak 3 languages", true)
            ),
            avatarIcon = R.drawable.face_man_profile
        ),
        Profile(
            id = "natalieyoung",
            firstName = "Natalie",
            lastName = "Young",
            age = 31,
            nickName = "Nat",
            office = Office.HASSELT,
            interests = listOf("Yoga", "Cooking", "Hiking"),
            twoTruthsOneLie = listOf(
                TwoTruthsOneLieEntity("I have a fear of spiders", true),
                TwoTruthsOneLieEntity("I can juggle", true),
                TwoTruthsOneLieEntity("I dislike pizza", false)
            ),
            avatarIcon = R.drawable.face_woman_profile
        ),
        Profile(
            id = "paulbrown",
            firstName = "Paul",
            lastName = "Brown",
            age = 28,
            nickName = "Paulie",
            office = Office.DUSSELDORF,
            interests = listOf("Running", "Photography", "Fishing"),
            twoTruthsOneLie = listOf(
                TwoTruthsOneLieEntity("I have been to Japan", true),
                TwoTruthsOneLieEntity("I hate sushi", false),
                TwoTruthsOneLieEntity("I love video games", true)
            ),
            avatarIcon = R.drawable.face_man
        ),
    )
    private val databaseIdentifier = "profiles"
    private val db = Firebase.firestore

    suspend fun getAllProfiles(): List<Profile> {
        return try {
            val result = db.collection(databaseIdentifier).get().await()
            result.toObjects(Profile::class.java)
        } catch (exception: Exception) {
            Log.w(this.javaClass.simpleName, "Error getting documents:", exception)
            profiles
        }
    }

    suspend fun getProfileById(id: String): Profile {
        val profiles = getAllProfiles()
        return profiles.first { it.id == id }
    }
}