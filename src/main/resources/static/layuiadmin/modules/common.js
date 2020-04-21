/** layuiAdmin.std-v1.2.1 LPPL License By http://www.layui.com/admin/ */
;layui.define(function (e) {
    var i = (layui.$, layui.layer, layui.laytpl, layui.setter, layui.view, layui.admin);
    i.events.logout = function () {
        layui.$.ajax({
            url:"/user/logout",
            type:'get',
            dataType:'json',
            success:function (json) {
                if(json.success){
                    i.exit(function () {
                        location.href = "/user/login"
                    })
                }
            }
        })
    }, e("common", {})
});