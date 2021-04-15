var Obj = null;
var count = 0;
var name;
var params=(function(){
    //自动获取地址栏链接带？以及后面的字符串
    var url=decodeURI(window.location.search);
    //定义一个空对象
    var obj = {};
    //如果字符串里面存在?
    if(url.indexOf("?") != -1){
        //从url的索引1开始提取字符串
        var str = url.substring(1);
        //如果存在&符号，则再以&符号进行分割
        var arr = str.split("&");
        //遍历数组
        for(var i=0; i<arr.length; i++){
            obj[arr[i].split("=")[0]] = unescape(arr[i].split("=")[1]);
        }
    }
    console.log(obj);
    return obj;
})();
layui.use(['form', 'table'], function () {
    var $ = layui.jquery,
        form = layui.form,
        table = layui.table;
    var Url = '/department/findMsg?name='+params.name+"&operator="+params.operator;
    table.render({
        elem: '#currentTableId',
        url: Url,
        toolbar: '#toolbarDemo',
        defaultToolbar: ['filter', 'exports', 'print', {
            title: '提示',
            layEvent: 'LAYTABLE_TIPS',
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
                "data":res,//解析数据列表
            };
        },
        limits: [10, 15, 20, 25, 50, 100],
        limit: 15,
        page: true,
        skin: 'line'
    });

    // 监听搜索操作
    form.on('submit(data-search-btn)', function (data) {
        var name = data.field.name;
        var operator = data.field.operator;
        if(name==""&&operator==""){
            alert("至少填写一项!");
            return ;
        }
        window.location.href=encodeURI("/department/view?name="+name+"&operator="+operator);
        //Ajax("/department/findMsg",false,result,findResult);
        return false;
    });

    /**
     * toolbar监听事件
     */
    table.on('toolbar(currentTableFilter)', function (obj) {
        if (obj.event === 'add') {  // 监听添加操作
            var index = layer.open({
                title: '添加信息',
                type: 2,
                shade: 0.2,
                maxmin:true,
                shadeClose: true,
                area: ['100%', '100%'],
                content: 'AddBill.html',
            });
            $(window).on("resize", function () {
                layer.full(index);
            });
        } else if (obj.event === 'pay') {  // 监听支付操作
            if(count<=0||Obj ==null){
                alert("请选择一条数据进行支付");
                return;
            }
            var status = Obj.data.status;
            var sn = Obj.data.sn;
            var pageStatus = "toPay"
            if(status!=0){
                alert("该状态不可再发起支付");
                return;
            }
            var index = layer.open({
                type: 2,
                shade: 0.2,
                maxmin:true,
                shadeClose: true,
                area: ['100%', '100%'],
                content: "http://localhost:8080/WebShop/alipay?pageStatus="+pageStatus+"&sn="+sn,
            });
            $(window).on("resize", function () {
                layer.full(index);
            });

        } else if (obj.event === 'del') {  // 监听删除订单操作
            //pay(Obj,count);
            if(count<=0||Obj ==null){
                alert("请选择一条数据进行删除");
                return;
            }

            layer.confirm('真的删除该订单信息吗', function (index) {

                var status = Obj.data.status;
                var sn = Obj.data.sn;
                if(status === 1){
                    alert("该状态不可删除");
                    layer.close(index);
                    window.location.reload();
                    return;
                }

                layer.confirm('真的删除该订单吗', function (index) {
                    layer.close(index);
                    DelUser(sn);
                });

            })
        }else if (obj.event === 'detail') {  // 监听添加操作
            if(count<=0||Obj ==null){
                alert("请选择一条数据进行删除");
                return;
            }
            var sn = Obj.data.sn;
            view(sn);
        }

    });

    //监听表格复选框选择
    table.on('checkbox(currentTableFilter)', function (obj) {
        tran(obj,count);

    });

    function tran(obj){
        Obj = obj;
        count++;
        //console.log(Obj);
    }
    //行监听
    table.on('tool(currentTableFilter)', function (obj) {
        if (obj.event === 'edit') {

            var index = layer.open({
                title: '添加',
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
        } else if (obj.event === 'delete') {  // 行监听删除操作
            layer.confirm('真的删除该行吗', function (index) {

                var status = obj.data.status;
                var sn = obj.data.sn;
                var pageStatus = "toPay"
                if(status === 1){
                    alert("该状态不可删除");
                    layer.close(index);
                    return;
                }

                obj.del();
                layer.close(index);
                DelUser(obj.data.sn);
            });
        }
    });
    //del
    function DelUser(sn){
        var pageStatus = "del";
        $.ajax({
            type:"post",
            async:true,
            url:'http://localhost:8080/WebShop/pay?pageStatus='+pageStatus+"&sn="+sn,
            dataType:"text",
            success:function(result){
                window.location.reload();
            },
            error:function(errorMsg){
                alert("无法删除,请联系管理员");
            }

        });
    }//del
    //view
    function view(sn){
        if(count<=0||Obj ==null){
            alert("请选择一条数据进行查看");
            return;
        }
        window.location.href='view.html?sn='+sn;
    }

});
