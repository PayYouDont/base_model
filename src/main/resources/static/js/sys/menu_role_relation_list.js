var url = '/permission/';
layui.use(['table'], function(){
    var $ = layui.$,table = layui.table;
    initTab(table);
    table.on('tool(data-tab)', function(obj){
        var data = obj.data;
        bindTabEvent(obj.event,data,url,function (event,data,url) {
            console.log(event,data,url)
            if(event=='auth'){
                var id = data.id;
                url += 'toAuth?id='+id;
                toAuth(url);
            }
        });
    })
});
function initTab(table,query) {
    table.render({
        elem: "#data-tab",
        url: url+'page',
        title:'权限表',
        toolbar: 'default', //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
        where:{
            query:query
        },
        request: {
            pageName: 'pageIndex', //页码的参数名称，默认：page
            limitName: 'pageSize' //每页数据量的参数名，默认：limit
        },
        cols: [[
            {type: "checkbox", fixed: "left"},
            {type: 'numbers'},
            {field: "title", title: "菜单",align: 'center', minWidth: 100},
            {field: "isView", title: "查看权" , align: 'center'},
            {field: "isModify", title: "添加权",align: 'center', width:100,templet:function (d) {return d.state==0?"可用":"禁用"}},
            {field: "isAdd", title: "修改权",align: 'center', width:150,templet:function (d) {return d.level==1?"不可编辑":"可编辑"}},
            {field: "isAdd",title: '删除权',width: 250, align: 'center',templet:'#auth-state'}
        ]],
        page: !0,
        limit: 30,
        height: "full-220"
    });
}
function toAuth(url) {
    var $ = layui.$;
    var iframeObj = $(window.frameElement).attr('name');
    page("权限分配", url, iframeObj, "700px", "620px");
}