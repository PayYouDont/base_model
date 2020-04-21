//1. [文档](https://layuiextend.hsianglee.cn/eletree//)
// 2. [示例](https://layuiextend.hsianglee.cn/eletree/test.html)
//https://whvse.gitee.io/treetable-lay/
layui.config({
    base: "/layuiadmin/mymodules/"
})
layui.use(['eleTree'], function () {
    var eleTree = layui.eleTree;
    var $ = layui.jquery;
    var ele;
    $("[name='pidLabel']").on("click", function (e) {
        e.stopPropagation();
        var id = $('#id').val();
        console.log(id)
        initTree(ele, eleTree, id);
        $("#pidTree").toggle();
    });
    eleTree.on("nodeClick(menuTree)", function (d) {
        $("[name='pidLabel']").val(d.data.currentData.label);
        $('#pid').val(d.data.currentData.id);
        $("#pidTree").hide();
    })
    $(document).on("click", function () {
        $("#pidTree").hide();
    })
});

function initTree(ele, eleTree, id) {
    if (!ele) {
        ele = eleTree.render({
            elem: '#pidTree',
            url: "../menu/tree",
            method: 'post',
            showLine: true,
            //是否每次只打开一个同级树节点展开（手风琴效果）
            accordion: false,
            //是否默认展开所有节点
            defaultExpandAll: false,
            //是否在点击节点的时候展开或者收缩节点
            expandOnClickNode: false,
            //showCheckbox:true,
            //checkStrictly:true,
            //defaultCheckedKeys:[selectedId],
            highlightCurrent: true
        });
    }
}