package ru.netology.pusher

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import java.io.FileInputStream


fun main() {
    val options = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(FileInputStream("fcm.json")))
        .setDatabaseUrl(dbUrl)
        .build()

    FirebaseApp.initializeApp(options)


    val messageLike = Message.builder()
        .putData("action", "LIKE")
        .putData("content", """{
            "userId": 1,
            "userName": "Vasiliy",
            "postId": 4,
            "postAuthor": "Netology"
        }""".trimIndent())
        .setToken(token)
        .build()


    val messageFail = Message.builder()
        .putData("action", "LYKE")
        .putData("content", """{
            "userId": 1,
            "userName": "Vasiliy",
            "postId": 4,
            "postAuthor": "Netology"
        }""".trimIndent())
        .setToken(token)
        .build()


    val messageNewPost = Message.builder()
        .putData("action", "NEW_POST")
        .putData("content", """{
            "postId": 5,
            "postAuthor": "Netology",
            "postText": "Работой на удалёнке уже никого не удивить: мифы о ноутбуке под пальмой и большом количестве свободного времени давно развеяны, ведь удалённая работа требует высокого уровня самоорганизованности и ответственности."
        }""".trimIndent())
        .setToken(token)
        .build()


    FirebaseMessaging.getInstance().send(messageLike)
    FirebaseMessaging.getInstance().send(messageFail)
    FirebaseMessaging.getInstance().send(messageNewPost)
}
