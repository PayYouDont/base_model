var url = '/role/';
layui.use(['form'], function () {
    var form = layui.form;
    var $ = layui.jquery;
    form.render();
    //监听提交
    form.on('submit(save)', function (data) {
        $.ajax({
            url:'/role/save',
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
        return false;
    });
})
