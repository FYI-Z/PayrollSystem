layui.use('dropdown', function(){
    var dropdown = layui.dropdown,
        $ = layui.jquery;
    dropdown.render({
        elem: '#demo1' //可绑定在任意元素中，此处以上述按钮为例
        ,data: [{
            title: 'menu item 1'
            ,id: 100
            ,href: ''
        },{
            title: 'menu item 2'
            ,id: 101
            ,href: '' //开启超链接
            ,target: '_blank' //新窗口方式打开
        },{type: '-'},{
            title: 'menu item 3'
            ,id: 102
            ,type: 'group'  //菜单类型，支持：normal/group/parent/-
            ,child: [{
                title: 'menu item 3-1'
                ,id: 103
            },{
                title: 'menu item 3-2'
                ,id: 104
                ,child: [{
                    title: 'menu item 3-2-1'
                    ,id: 105
                },{
                    title: 'menu item 3-2-2'
                    ,id: 106
                }]
            },{
                title: 'menu item 3-3'
                ,id: 107
            }]
        },{type: '-'},{
            title: 'menu item 4'
            ,id: 108
        },{
            title: 'menu item 5'
            ,id: 109
            ,child: [{
                title: 'menu item 5-1'
                ,id: 11111
                ,child: [{
                    title: 'menu item 5-1-1'
                    ,id: 2111
                },{
                    title: 'menu item 5-1-2'
                    ,id: 3111
                }]
            },{
                title: 'menu item 5-2'
                ,id: 52
            }]
        },{type:'-'},{
            title: 'menu item 6'
            ,id: 6
            ,type: 'group'
            ,isSpreadItem: false
            ,child: [{
                title: 'menu item 6-1'
                ,id: 61
            },{
                title: 'menu item 6-2'
                ,id: 62
            }]
        }]
        ,id: 'demo1'
        //菜单被点击的事件
        ,click: function(obj){
            console.log(obj);
            layer.msg('回调返回的参数已显示再控制台');
        }
    });
});
