layui.use(["pager","form","upload","laydate"],function(){
	var tool = layui.tool ,$ = layui.jquery ,pager = layui.pager ,laydate=layui.laydate ,form = layui.form() ;
	$("#page").hide();
	/*console.log(tool.getFullDate(new Date())) ;
	console.log(pager.reqSerialize("#myForm","id desc")) ;*/
	pager.load({
		url:"comment/list",
		sort:"id desc",
		selector:"#myForm"
	},function(data){
		$("#mytable").html(data) ;
		form.render() ;
	}) ;
	
	$(function($){
		if($("#message").val()!=""){
			tool.info($("#message").val());
		}
	})
	
	form.on('checkbox(allChoose)', function(data){
		var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]');
	    child.each(function(index, item){
	      item.checked = data.elem.checked;
	    });
	    form.render('checkbox');
	  });
	//批量操作
	$("#btnAubit").click(function(){
		var comments="0";
		var sel=$("#sel");
		$(".orderid").each(function(index,item){
			if(item.checked){
				comments+=","+item.value;
			}
		});
		if(comments=="0" || sel.val()==""){
			tool.warning("您未选中要审核通过的评论，或者没有选这操作类型！")
			return false;
		}else if (comments!="0" && sel.val()!=""){
			tool.operInfo("您确认要批量审核改批评论吗？",function(){
				window.location.href="comment/updateComments/"+comments+"/"+sel.val();
			})
		}
	})
}) ;