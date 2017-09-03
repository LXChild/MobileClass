/**
 * Created by LXChild on 24/04/2017.
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
        $('#user-list-table').bootstrapTable({
            url: '/users/list', //请求后台的URL（*）
            method: 'GET', //请求方式（*）
            dataType: 'json',
            toolbar: '#toolbar', //工具按钮用哪个容器
            striped: true, //是否显示行间隔色
            cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true, //是否显示分页（*）
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
                field: 'user.name',
                title: '用户名'
            }, {
                field: 'user.realName',
                title: '真实姓名'

            }, {
                field: 'user.mobile',
                title: '手机号'
            }, {
                field: 'user.email',
                title: '邮箱'
            }, {
                field: 'user.enable',
                title: '状态',
                formatter: enableFormat
            }, {
                field: 'role.comment',
                title: '角色'
            }]
        });
    };

    //得到查询的参数
    oTableInit.queryParams = function (params) {
        var temp = { //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            limit: params.limit, //页面大小
            offset: params.offset, //页码
            realName: params.search
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
            var userVO = $('#user-list-table').bootstrapTable('getSelections')[0];

            if (typeof(userVO) == "undefined" || userVO == null) {
                alert("请先选择用户");
                return;
            }

            if (userVO.role.tag == "ROLE_SYS_ADMIN") {
                alert("您无权操作");
                return;
            }

            $("#btn-edit").attr('data-target', "#edit-user-model");

            $("#id-edit-user-model").val(userVO.user.id);
            $("#name-edit-user-model").val(userVO.user.name);
            $("#real-name-edit-user-model").val(userVO.user.realName);
            $("#mobile-edit-user-model").val(userVO.user.mobile);
            $("#email-edit-user-model").val(userVO.user.email);
            if (userVO.user.enable) {
                $("#enable-edit-user-model").val('true');
            } else {
                $("#enable-edit-user-model").val('false');
            }
            if (userVO.role.comment == "管理员") {
                $("#role-edit-user-model").val(2);
            } else if (userVO.role.comment == "老师") {
                $("#role-edit-user-model").val(3);
            } else if (userVO.role.comment == "学生") {
                $("#role-edit-user-model").val(4);
            }
        });
    };
    return oInit;
};

function enableFormat(value, row, index) {
    if (value == true) {
        return '启用';
    } else {
        return '禁用';
    }
}