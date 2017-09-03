/**
 * Created by LXChild on 18/04/2017.
 */
$(function () {
    $('#datetimepicker').datetimepicker({
        language: 'zh-CN',
        locale: 'ru',
        weekStart: 1,
        todayBtn:  1,
        todayHighlight: 1,
        autoclose: 1,
        startView: 2,
        forceParse: 0,
        showMeridian: 1,
        format: 'yyyy-mm-dd hh:ii:00'
    });
});

