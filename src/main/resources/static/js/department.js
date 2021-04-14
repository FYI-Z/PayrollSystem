layui.use(['form', 'table'], function () {
    var $ = layui.jquery,
        form = layui.form,
        table = layui.table;

    table.render({
        elem: '#currentTableId',
        url: '/department/findAll',
        toolbar: '#toolbarDemo',
        defaultToolbar: ['filter', 'exports', 'print', {
            title: '提示',
            layEvent: 'Title',
            icon: 'layui-icon-tips'
        }],
        cols: [[
            {type: "checkbox", width: 50, unresize: true},
            {field: 'departmentid', width: 130, title: '部门ID', sort: true, align: "center",unresize: true},
            {field: 'name', width: 80, title: '部门名称',align: "center", unresize: true},
            {field: 'number', width: 150, title: '部门人数', sort: true,align: "center", unresize: true},
            {field: 'operator', width: 80, title: '创建者', align: "center",unresize: true},
            {field: 'createtime', title: '创建时间', minWidth: 70, align: "center",unresize: true},
            {title: '操作', minWidth: 150, toolbar: '#currentTableBar', align: "center", unresize: true}
        ]],
        parseData:function(res){
            return{
                "code":0,//解析接口状态
                "msg":res.message,//解析提示文本
                "count":res.total,//解析数据长度
                "data":res//解析数据列表
            };
        },
        limits: [10, 15, 20, 25, 50, 100],
        limit: 15,
        page: true,
        skin: 'line'
    });

    // 监听搜索操作
    form.on('submit(data-search-btn)', function (data) {
        //var result=encodeURI(encodeURI(data.field.name));
        var result = data.field.name;
        window.location.href=encodeURI("/department/view?name="+result);
        //Ajax("/department/findMsg",false,result,findResult);
        return false;
    });


    //监听表格复选框选择
    table.on('checkbox(currentTableFilter)', function (obj) {
        console.log(obj)
    });

    table.on('tool(currentTableFilter)', function (obj) {
        var data = obj.data;
        if (obj.event === 'edit') {

            var index = layer.open({
                title: '编辑用户',
                type: 2,
                shade: 0.2,
                maxmin:true,
                shadeClose: true,
                area: ['100%', '100%'],
                content: '../page/table/edit.html',
            });
            $(window).on("resize", function () {
                layer.full(index);
            });
            return false;
        } else if (obj.event === 'delete') {
            layer.confirm('真的删除行么', function (index) {
                console.log(obj);
                layer.close(index);
                Ajax("/department/del",false, data, del);
            });
        }
    });
    function del(result) {
        window.location.reload();
    }

});
