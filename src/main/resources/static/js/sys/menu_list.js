//https://gitee.com/whvse/treetable-lay
var url = '/menu/';
layui.config({
    base: '/layuiadmin/mymodules/'
}).extend({
    treetable: 'treetable-lay/treetable'
}).use(['treetable','form'],function () {
    var treeTable = layui.treetable;
    var $ = layui.jquery;
    var form = layui.form;
    initTab(treeTable)
    $('#btnExpandAuth').click(function () {
        treeTable.expandAll('#data-tab');
    });
    $('#btnFoldAuth').click(function () {
        treeTable.foldAll('#data-tab');
    });
    form.on('submit(treeSearch)',function (data) {
        var query = data.field.search;
        var keyword = query;
        var searchCount = 0;
        $('#data-tab').next('.treeTable').find('.layui-table-body tbody tr td').each(function () {
            $(this).css('background-color', 'transparent');
            var text = $(this).text();
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
function initTab(treeTable,query) {
    // 渲染表格
    treeTable.render({
        elem: '#data-tab',
        url: 'list',
        page: true,
        toolbar: 'default',
        treeColIndex:2,          // treetable新增参数 树形图标显示在第几列
        treeSpid: 0,             // treetable新增参数 最上级的父级id
        treeIdName: 'id',       // treetable新增参数 id字段的名称
        treePidName: 'pid',     // treetable新增参数 pid字段的名称
        treeDefaultClose: true,   // treetable新增参数 是否默认折叠
        treeLinkage: true,        // treetable新增参数 父级展开时是否自动展开所有子级
        loading:true,
        height: "full-220",
        where:{
            query:query
        },
        cols: [[
            {type: 'checkbox'},
            {field: 'number', title: '序号',width: 60,align: 'center'},
            {field: 'title', title: '菜单名称',width: 200,align: 'center'},
            {field: 'url', title: '菜单url',width: 200,align: 'center'},
            {field: 'permissions', title: '权限标识',width: 200,align: 'center'},
            {field: 'content', title: '描述',align: 'center'},
            {field: 'status', title: '状态',width: 100,align: 'center',templet:function (d) {return d.status==0?'显示':'隐藏';}},
            {title: '操作',width: 150, align: 'center',templet:'#auth-state'}
        ]]
    });
}