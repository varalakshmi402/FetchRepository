package com.example.fetch

class HiringRepository {
    private val hiringApi: HiringApi = HiringApi()

    suspend fun fetchItems(): List<Hiring>? {
        var list = hiringApi.getData().body()
        list = list?.filter { !it.name.isNullOrBlank() }
        list = list?.let { sortList(it) }
        return list
    }
    private fun sortList(fetchRewardsItemList: List<Hiring>): List<Hiring>{
        return fetchRewardsItemList.sortedWith(compareBy<Hiring> {item ->
            item.listId
        }.thenBy { item ->
            item.name?.let { extractInt(it) }
        })
    }

    private fun extractInt(name: String): Int{
        val num = name.replace("Item ", "")
        return num.toInt()
    }
}