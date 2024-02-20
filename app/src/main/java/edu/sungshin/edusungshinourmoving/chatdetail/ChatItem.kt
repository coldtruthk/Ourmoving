package edu.sungshin.edusungshinourmoving.chatdetail

data class ChatItem(
    val senderId: String,
    val message: String,
) {
    constructor(): this("", "")
}
