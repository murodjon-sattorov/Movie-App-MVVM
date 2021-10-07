package uz.murodjon_sattorov.myfilms.core.model

data class MovieListResponse(
    val dates: Dates,
    val page: Int, // 1
    val results: List<Result>,
    val total_pages: Int, // 85
    val total_results: Int // 1698
){
    var title: String = ""
}