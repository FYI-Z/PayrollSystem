layui.define(['jquery'], function (exports) {
    'use strict';

    var $ = layui.jquery;
    var obj = {
        make: function (data, ele, current) {
            var html = ''
                , data_length = data.length
                , percentage = 100 / data_length;

            for (var i = 0; i < data_length; i++) {
                var icon = ''
                    , tail = '';
                if (i < current) {
                    icon = 'icon-right';
                    tail = 'icon-shixin';
                }

                var temp = '<div class="step-item" style="width: 5.6rem;float: left;line-height: .8rem;">';

                if (parseInt(i) + 1 < data_length) {
                    temp += '<div class="step-item-tail" style="float: left;margin-left: -.15rem;margin-right: .2rem;"><i class="iconfont ' + tail + '" style="color: #33a0f5"></i></div>';
                }

                if (icon) {
                    temp += '<div class="step-item-main">' +
                        '<div class="step-item-main-title" style="float: left">' + data[i].title + '</div>' +
                        '<div class="step-item-main-desc" style="float: right;color: #51daa6">'
                        + data[i].desc +
                        '<span class="iconfont ' + icon + '" style="margin-left: .1rem"></span>' +
                        '</div>' +
                        '</div>' ;
                } else {
                    temp +='<div class="step-item-head step-item-head-active" style="float: left;margin-left: -.15rem;margin-right: .2rem;"><i class="iconfont icon-shixin" style="color: #e5e5e5"></i></div>'+
                        '<div class="step-item-main">' +
                        '<div class="step-item-main-title" style="float: left">' + data[i].title + '</div>' +
                        '</div>';

                }
                temp += '';
                temp += '</div>';
                html += temp;
            }

            $(ele).append(html);
        }
    };

    exports('steps', obj);
});