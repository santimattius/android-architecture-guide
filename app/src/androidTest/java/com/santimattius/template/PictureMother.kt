package com.santimattius.template

object PictureMother {

    fun createPictures() = (0..1).map {
        PictureTestModel(
            id = it.toString(),
            author = "Author $it",
            url = "https://www.pexels.com/photo/photo-of-pod-of-dolphins-2422915/",
            height = 250,
            width = 250,
            downloadUrl = "https://images.pexels.com/photos/2422915/pexels-photo-2422915.jpeg?auto=compress&cs=tinysrgb&h=350"
        )
    }
}
