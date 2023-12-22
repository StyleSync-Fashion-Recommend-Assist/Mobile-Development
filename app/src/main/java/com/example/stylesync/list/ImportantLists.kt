package com.example.stylesync.list

object ImportantLists {
    val categoriesList: List<Categories> = mutableListOf (
        Categories(1, "Tops"),
        Categories(2, "Dresses"),
        Categories(3, "Bottoms"),
        Categories(4, "Outerwear"),
        Categories(5, "Activewear"),
        Categories(6, "Shoes"),
        Categories(7, "Accessories"),
        Categories(8, "Swimwear"),
        Categories(9, "Sleepwear"),
        Categories(10, "Special Occasion"),
        Categories(11, "Seasonal"),
        Categories(12, "Workwear"),
        Categories(13, "Ethnic Wear"),
    )

    val occupationList: List<Occupations> = mutableListOf (
        Occupations(1, "Casual"),
        Occupations(2, "Formal"),
        Occupations(3, "Business"),
        Occupations(4, "Sport"),
        Occupations(5, "Party")
    )

    val subCategoryList: List<SubCategories> = mutableListOf (
        SubCategories(1, 1, "Shirt"),
        SubCategories(2, 1, "T-Shirt"),
        SubCategories(1, 1,"Blouse")
    )

    val colorList: List<Colors> = mutableListOf (
        Colors(1, "Hitam"),
        Colors(2, "Putih"),
        Colors(3, "Merah"),
        Colors(4, "Kuning"),
        Colors(5, "Hijau"),
        Colors(6, "Biru"),
        Colors(7, "Ungu"),
        Colors(8, "Cokelat"),
        Colors(9, "Orange"),
        Colors(10, "Abu-abu"),
        Colors(11, "Pink"),
        Colors(12, "Gold")
    )
}