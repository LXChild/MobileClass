/**
 * Created by LXChild on 28/04/2017.
 */
$('#file').fileinput({
    language: 'zh',
    overwriteInitial: false,
    maxFileSize: 50000,
    allowedFileExtensions: ['jpg', 'png', 'gif']
});

function searchByName() {
    $("#btn-search").attr("href", "/posts?title=" + $("#input-search").val());
}

function editBulletin() {
    $("#textarea-bulletin").val($("#bulletin").text());

    $("#btn-edit-bulletin").removeAttr("data-toggle");
    $("#btn-edit-bulletin").attr("data-toggle", "modal");
    $("#btn-edit-bulletin").attr("data-target", "#bulletin-modal");
}
