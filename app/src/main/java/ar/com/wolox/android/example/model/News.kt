package ar.com.wolox.android.example.model

import com.google.gson.annotations.SerializedName

data class News(
    @SerializedName("id") var id: String,
    @SerializedName("userId") var userId: Int,
    @SerializedName("title") var title: String,
    @SerializedName("text") var text: String,
    @SerializedName("picture") var picture: String,
    @SerializedName("createdAt") var createdAt: String,
    @SerializedName("likes") var likes: Array<Int>
)
