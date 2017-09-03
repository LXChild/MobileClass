/**
 * 角色列表网页脚本
 * Created by LXChild on 26/04/2017.
 */
$(function () {
    //1.初始化Table
    var oTable = new TableInit();
    oTable.Init();

    //2.初始化Button的点击事件
    var oButtonInit = new ButtonInit();
    oButtonInit.Init();
});


var TableInit = function () {
    var oTableInit = {};
    //初始化Table
    oTableInit.Init = function () {
        $('#role-list-table').bootstrapTable({
            url: '/roles/list', //请求后台的URL（*）
            method: 'GET', //请求方式（*）
            dataType: 'json',
            toolbar: '#toolbar', //工具按钮用哪个容器
            striped: true, //是否显示行间隔色
            cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: false, //是否显示分页（*）
            sortable: false, //是否启用排序
            sortOrder: "asc", //排序方式
            queryParams: oTableInit.queryParams,//传递参数（*）
            sidePagination: "server", //分页方式：client客户端分页，server服务端分页（*）
            pageNumber: 1, //初始化加载第一页，默认第一页
            pageSize: 10, //每页的记录行数（*）
            pageList: [10, 25, 50, 100], //可供选择的每页的行数（*）
            search: true, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            showColumns: true, //是否显示所有的列
            showRefresh: true, //是否显示刷新按钮
            minimumCountColumns: 2, //最少允许的列数
            clickToSelect: true, //是否启用点击选中行
            // height: 500, //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "id", //每一行的唯一标识，一般为主键列
            showToggle: false, //是否显示详细视图和列表视图的切换按钮
            cardView: false, //是否显示详细视图
            detailView: false, //是否显示父子表
            columns: [{
                radio: true
            }, {
                field: 'id',
                title: 'ID'
            }, {
                field: 'tag',
                title: '标识'
            }, {
                field: 'comment',
                title: '名称'

            }, {
                field: 'createTime',
                title: '创建时间',
                formatter: dateFormat
            }, {
                field: 'updateTime',
                title: '更改时间',
                formatter: dateFormat
            }]
        });
    };

    //得到查询的参数
    oTableInit.queryParams = function (params) {
        var temp = { //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            limit: params.limit, //页面大小
            offset: params.offset, //页码
            comment: params.search
        };
        return temp;
    };
    return oTableInit;
};


var ButtonInit = function () {
    var oInit = {};
    var postdata = {};

    oInit.Init = function () {
        //初始化页面上面的按钮事件
        $("#btn-edit").click(function (event) {
            event.preventDefault();//使a自带的方法失效，即无法调整到href中的URL(http://www.baidu.com)
            $("#btn-edit").removeAttr('data-target');
            var role = $('#role-list-table').bootstrapTable('getSelections')[0];

            if (typeof(role) == "undefined" || role == null) {
                alert("请先选择角色");
                return;
            }

            if (role.tag == "ROLE_SYS_ADMIN") {
                alert("您无权操作");
                return;
            }

            $("#btn-edit").attr('data-target', "#role-model");
            $("#form-role-model").attr('action', "/roles/update");
            $("#title-role-model").text("编辑");
            $("#id-role-model").val(role.id);
            $("#tag-role-model").val(role.tag);
            $("#comment-role-model").val(role.comment);
        });
    };
    return oInit;
};

Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};

function dateFormat(value, row, index) {
    return new Date(value).Format("yyyy-MM-dd hh:mm:ss");
}
