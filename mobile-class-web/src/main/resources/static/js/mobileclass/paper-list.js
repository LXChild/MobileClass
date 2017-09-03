/**
 * Created by LXChild on 28/04/2017.
 */
function searchByName() {
    $("#btn-search").attr("href", "/papers?name=" + $("#input-search").val());
}

function editBulletin() {

    $("#textarea-bulletin").val($("#bulletin").text());

    $("#btn-edit-bulletin").removeAttr("data-toggle");
    $("#btn-edit-bulletin").attr("data-toggle", "modal");
    $("#btn-edit-bulletin").attr("data-target", "#bulletin-modal");

}