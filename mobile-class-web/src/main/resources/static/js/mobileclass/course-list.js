/**
 * Created by LXChild on 28/04/2017.
 */
function searchByName() {
    $("#btn-search").attr("href", "/courses?name=" + $("#input-search").val());
}