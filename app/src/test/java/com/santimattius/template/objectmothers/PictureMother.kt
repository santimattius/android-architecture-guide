package com.santimattius.template.objectmothers

object PictureMother {

    fun createPictures() = (0..1).map {
        TestPicture(
            id = it.toString(),
            author = "Author $it",
            url = "https://www.pexels.com/photo/photo-of-pod-of-dolphins-2422915/",
            height = 250,
            width = 250,
            downloadUrl = ""
        )
    }

    private fun create(id: Long = 1234) = TestPicture(
        id = "$id",
        author = UnitTestUtils.randomString(),
        width = UnitTestUtils.randomInt(),
        height = UnitTestUtils.randomInt(),
        url = UnitTestUtils.randomString(),
        downloadUrl = UnitTestUtils.randomString()
    )

    fun generate(size: Int = 10) = (1..size).map { create(id = it.toLong()) }
}
