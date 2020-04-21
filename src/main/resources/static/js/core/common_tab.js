layui.config({
    base: '/layuiadmin/js/module/'
}).extend({
    dialog: 'dialog',
}).use(['table','form'], function(){
    var table = layui.table;
    var form = layui.form;
    var $ = layui.$;
    table.on('tool(data-tab)', function(obj){
        var data = obj.data;
        bindTabEvent(obj.event,data,url);
    })
    //监听头工具栏事件
    table.on('toolbar(data-tab)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        var data = checkStatus.data; //获取选中的数据
        bindTabEvent(obj.event,data,url);
    });
    form.render();
    form.on('submit(search)',function (data) {
        var query = data.field.search;
        initTab(table,query);
        return false;
    })
    //监听提交
    form.on('submit(save)', function (data) {
        submitData($,url,data);
        return false;
    });
})
/**
 * 控制iframe窗口的刷新操作
 */
var iframeObjName;
//父级弹出页面
function page(title, url, obj, w, h) {
    if (title == null || title == '') {
        title = false;
    }
    ;
    if (url == null || url == '') {
        url = "404.html";
    }
    ;
    if (w == null || w == '') {
        w = '700px';
    }
    ;
    if (h == null || h == '') {
        h = '350px';
    }
    ;
    iframeObjName = obj;
    //如果手机端，全屏显示
    if (window.innerWidth <= 768) {
        var index = layer.open({
            type: 2,
            title: title,
            area: [320, h],
            fixed: false, //不固定
            content: url
        });
        layer.full(index);
    } else {
        var index = layer.open({
            type: 2,
            title: title,
            area: [w, h],
            fixed: false, //不固定
            content: url
        });
    }
}
/**
 * 刷新子页,关闭弹窗
 */
function refresh() {
    //根据传递的name值，获取子iframe窗口，执行刷新
    if (window.frames[iframeObjName]) {
        window.frames[iframeObjName].location.reload();
    } else {
        window.location.reload();
    }
    layer.closeAll();
}
function bindTabEvent(event,data,url,callback) {
    if (callback) {
        callback(event, data, url);
    } else {
        switch (event) {
            case 'add':
                toEdit(url + 'toEdit');
                break;
            case 'update':
                if (data.length === 0) {
                    layer.msg('请选择一行');
                } else if (data.length > 1) {
                    layer.msg('只能同时编辑一个');
                } else {
                    var id = data instanceof Array ? data[0].id : data.id;
                    toEdit(url + 'toEdit?id=' + id);
                }
                break;
            case 'delete':
                if (data.length === 0) {
                    layer.msg('请选择一行');
                } else {
                    let ids = new Array();
                    if (data instanceof Array) {
                        for (var i in data) {
                            ids.push(data[i].id);
                        }
                    } else {
                        ids.push(data.id);
                    }
                    toDelete(url + 'delete', ids)
                }
                break;
        }
    }
}

function toEdit(url) {
    var $ = layui.$;
    var iframeObj = $(window.frameElement).attr('name');
    page("添加", url, iframeObj, "700px", "620px");
}
function toDelete(url,ids) {
    layui.config({
        base: '/layuiadmin/js/module/'
    }).use(['dialog'], function () {
        var dialog = layui.dialog;
        var $ = layui.jquery;
        dialog.confirm({
            message: '您确定要进行删除吗？',
            success: function () {
                $.ajax({
                    url: url,
                    type: 'post',
                    data: {ids: ids},
                    traditional:true,
                    dataType: 'json',
                    success: function (json) {
                        if (json.success) {
                            window.location.reload();
                            layer.msg('删除成功!')
                        } else {
                            layer.msg(JSON.stringify(json))
                        }
                    }
                })
            }
        });
    });
}
//公共监听提交
function submitData($,url,data) {
    $.ajax({
        url:url+'save',
        type:'post',
        data:data.field,
        dataType:'json',
        success:function (json) {
            if (json.success){
                parent.refresh();
            }else {
                layer.msg(JSON.stringify(json))
            }
        },
        error:function (json) {
            console.log(json)
        }
    })
}