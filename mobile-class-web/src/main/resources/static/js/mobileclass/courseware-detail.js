/**
 * Created by LXChild on 19/04/2017.
 */
$('#file').fileinput({
    language: 'zh',
    overwriteInitial: false,
    maxFileSize: 50000
});

function subscription() {
    event.preventDefault();//使a自带的方法失效，即无法调整到href中的URL(http://www.baidu.com)
    var courseId = $("#courseIdSub").val();
    $.ajax({
        type: "POST",
        url: "/courseSubscription",
        data: {courseId:courseId},//参数列表
        success: function(result){
            if (result == true) {
                window.location.reload();
            } else {
                alert("收藏失败");
            }
        },
        error: function(result){
            alert("请求失败");
        }
    });
}