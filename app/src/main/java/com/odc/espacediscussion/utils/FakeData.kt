package com.odc.espacediscussion.utils

import com.google.gson.GsonBuilder
import com.odc.espacediscussion.models.EspaceModel
import com.odc.espacediscussion.models.MessageModel

object FakeData {
    val rawEspaces = """
        [
          {
            "id": 1, "name":"Echange sur Kotlin", "description" :"Lorem ipsum dolor sit amet, consectetur adipiscing elit sagittis felis sed nisi posuere, auctor volutpat tellus laoreet 
            sagittis felis sed nisi posuere, auctor volutpat tellus laoreet sagittis felis sed nisi posuere,
             auctor volutpat tellus laoreet", "creator_id": 1
          },
          {
            "id": 2, "name":"AI ChatGPT", "description" :"Sed laoreet porta consequat.", "creator_id": 2
          },
          {
            "id": 3, "name":"Concevoir un Robot", "description" :"Curabitur vel scelerisque leo.", "creator_id": 5
          },
          {
            "id": 4, "name":"Fablab solidaire", "description" :"Vivamus sagittis felis sed nisi posuere, auctor volutpat tellus laoreet.", "creator_id": 1
          },
          {
            "id": 5, "name":"NodeJS Communauté", "description" :"Ut vel eros suscipit, viverra velit vel, auctor arcu.", "creator_id": 3
          },
          {
            "id": 6, "name":"Marketing Digital", "description" :"Fusce aliquam congue orci.", "creator_id": 4
          },
          {
            "id": 7, "name":"Aws", "description" :"Suspendisse potenti.", "creator_id": 1
          }
        ]
    """.trimIndent()
    val espaces: List<EspaceModel> =
        GsonBuilder().create().fromJson(rawEspaces, Array<EspaceModel>::class.java).toList()

    val rawMessages = """
        [
            {
                "message":"Welcome",
                "time":"2023-01-09 08:30:50",
                "sender":1,
                "senderName":"ODC"
            },
            {
                "message":"Merci pour l'invitation",
                "time":"2023-01-09 08:30:50",
                "sender":2,
                "senderName":"Orange"
            }
        ]
    """.trimIndent()
    val messages: List<MessageModel> =
        GsonBuilder().create().fromJson(rawMessages, Array<MessageModel>::class.java).toList()
}