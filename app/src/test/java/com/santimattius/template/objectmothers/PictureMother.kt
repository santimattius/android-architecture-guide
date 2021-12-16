package com.santimattius.template.objectmothers


object PictureMother {

    fun create(id: Long = 1234) = TestPicture(
        id = "$id",
        author = UnitTestUtils.randomString(),
        width = UnitTestUtils.randomInt(),
        height = UnitTestUtils.randomInt(),
        url = UnitTestUtils.randomString(),
        downloadUrl = UnitTestUtils.randomString()
    )

    fun generate(size: Int = 10) = (1..size).map { create(id = it.toLong()) }
}

