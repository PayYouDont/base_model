let resData;
let $;
layui.config({
    base: '/layuiadmin/mymodules/'
}).extend({
    treetable: 'treetable-lay/treetable'
}).use(['table','treetable','form'],function () {
    let table = layui.table;
    let treeTable = layui.treetable;
    $ = layui.jquery;
    let form = layui.form;
    //初始化表格
    initTab(treeTable)
    //全部展开按钮点击事件
    $('#btnExpandAuth').click(function () {
        treeTable.expandAll('#data-tab');
    });
    //全部折叠按钮点击事件
    $('#btnFoldAuth').click(function () {
        treeTable.foldAll('#data-tab');
    });
    //复选框事件
    table.on('checkbox(data-tab)', function(obj){
        obj.tr.find('.layui-form-switch').click();
    });
    //全选事件
    form.on('checkbox(layTableAllChoose)',function(obj){
        let checked = this.checked;
        let checkbox = $('.layui-table').find('tbody input[type="checkbox"]');
        for (let i=0;i<checkbox.length;i++){
            checkbox[i].checked = checked;
        }
        $.each(resData.data,function (index,data) {
            data.authView=data.authModify=data.authAdd=data.authDelete=true;
            data.LAY_CHECKED = true;
        })
    })
    //单个权限开关事件
    form.on('switch(auth_switch)', function(obj){
        let checked = this.checked;
        let name = this.name;
        let index  = obj.othis.parents('tr').attr("data-index");
        let data = resData.data[index];
        data[name] = checked;
        let td = $('tr[data-index=' + index + '] input[type="checkbox"]').eq(0);
        if(checked&&!data.LAY_CHECKED){
            data.LAY_CHECKED = true;
            td.next().addClass('layui-form-checked');
        }else if(!checked){
            if(!data.authView&&!data.authAdd&&!data.authModify&&!data.authDelete){
                data.LAY_CHECKED = false;
                td.next().removeClass('layui-form-checked');
            }
        }
    })
    //提交保存权限事件
    $('#btnSaveAuth').click(function () {
        let checkStatus = table.checkStatus('authTree');
        $.ajax({
            url: 'saveAuth?id='+id,
            type: 'POST',
            contentType : 'application/json; charset=UTF-8',
            data: JSON.stringify(checkStatus.data),
            dataType: 'json',
            success:function (json) {
                if (json.success){
                    layer.msg('保存成功!',{time:1000},function () {
                        parent.refresh();
                    })
                }else {
                    layer.msg(JSON.stringify(json))
                }
            },
            error:function (json) {
                console.log(json)
            }
        })
    });
    //搜索事件
    form.on('submit(treeSearch)',function (data) {
        let query = data.field.search;
        let keyword = query;
        let searchCount = 0;
        $('#data-tab').next('.treeTable').find('.layui-table-body tbody tr td').each(function () {
            $(this).css('background-color', 'transparent');
            let text = $(this).text();
            if (keyword != '' && text.indexOf(keyword) >= 0) {
                $(this).css('background-color', 'rgba(250,230,160,0.5)');
                if (searchCount == 0) {
                    treeTable.expandAll('#data-tab');
                    $('html,body').stop(true);
                    $('html,body').animate({scrollTop: $(this).offset().top - 150}, 500);
                }
                searchCount++;
            }
        });
        if (keyword == '') {
            layer.msg("请输入搜索内容", {icon: 5});
        } else if (searchCount == 0) {
            layer.msg("没有匹配结果", {icon: 5});
        }
        return false;
    })
})
function initTab(treeTable) {
    // 渲染表格
    treeTable.render({
        id:'authTree',
        elem: '#data-tab',
        url: 'menuAuth',
        page: true,
        //toolbar: 'default',
        treeColIndex:3,          // treetable新增参数 树形图标显示在第几列
        treeSpid: 0,             // treetable新增参数 最上级的父级id
        treeIdName: 'menuId',       // treetable新增参数 id字段的名称
        treePidName: 'menuPid',     // treetable新增参数 pid字段的名称
        treeDefaultClose: true,   // treetable新增参数 是否默认折叠
        treeLinkage: true,        // treetable新增参数 父级展开时是否自动展开所有子级
        loading:true,
        height: "full-200",
        where:{
            id:id
        },
        cols: [[
            {type: 'checkbox'},
            {field: 'menuNumber', title: '序号',width: 60,align: 'center'},
            {field: 'menuId',hide:true},
            {field: 'menuTitle', title: '菜单名称',width: 150,align: 'center'},
            {field: 'authView', title: '查看权',width: 80,align: 'center',templet:'#auth-view'},
            {field: 'authAdd', title: '添加权',width: 80,align: 'center',templet:'#auth-add'},
            {field: 'authModify', title: '修改权',width: 80,align: 'center',templet:'#auth-modify'},
            {field: 'authDelete', title: '删除权',width: 80,align: 'center',templet:'#auth-delete'},
        ]],
        done:function (res) {
            resData = res;
            $.each(res.data,function (index,data) {
                let td = $('tr[data-index=' + index + '] input[type="checkbox"]').eq(0);
                if(data.authView||data.authModify||data.authAdd||data.authDelete){
                    data.LAY_CHECKED = true;
                    td.next().addClass('layui-form-checked');
                }
            })
        }
    });
}