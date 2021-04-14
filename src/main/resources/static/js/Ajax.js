function Ajax(Url,flag, Data,func) {
    $.ajax({
        type: "post",
        async: flag,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
        url: "http://localhost:8888/"+Url,    //请求发送到Servlet处
        data: Data,
        dataType: "json",        //返回数据形式为json
        success: function(result) {
            console.log(result);
            func(result);
        },
        error: function (errorMsg) {
        }
    })
}


