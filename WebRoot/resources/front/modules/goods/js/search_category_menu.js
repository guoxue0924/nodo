/**
 * --------------------------------------------------------------------
 * jQuery tree plugin
 * Author: Scott Jehl, scott@filamentgroup.com
 * Copyright (c) 2009 Filament Group 
 * licensed under MIT (filamentgroup.com/examples/mit-license.txt)
 * --------------------------------------------------------------------
 */
$.fn.tree = function(settings){
	var o = $.extend({
		expanded: ''
	},settings);
	
	return $(this).each(function(){
		if( !$(this).parents('.tree').length ){
		//save reference to tree UL
		var tree = $(this);
		
		//add the role and default state attributes
		if( !$('body').is('[role]') ){ $('body').attr('role','application'); }
		//add role and class of tree
		tree.attr({'role': 'tree'}).addClass('tree');
		//set first node's tabindex to 0
		tree.find('i:eq(0)').attr('tabindex','0');
		//set all others to -1
		tree.find('i:gt(0)').attr('tabindex','-1');
		//add group role and tree-group-collapsed class to all ul children
		tree.find('ul').attr('role','group').addClass('tree-group-collapsed');
		//add treeitem role to all li children
		tree.find('li').attr('role','treeitem');
		//find tree group parents
		tree.find('li:has(ul)')
				.attr('aria-expanded', 'false')
				.find('>i')
				.addClass('tree-parent tree-parent-collapsed');
	
		//expanded at load		
		tree
			.find(o.expanded)
			.attr('aria-expanded', 'true')
				.find('>i')
				.removeClass('tree-parent-collapsed')
				.nextAll('ul')
				.removeClass('tree-group-collapsed');
					
		
		//bind the custom events
		tree
			//expand a tree node
			.bind('expand',function(event){
				var target = $(event.target) || tree.find('i[tabindex=0]');
				target.removeClass('tree-parent-collapsed');
				target.nextAll('ul').hide().removeClass('tree-group-collapsed').slideDown(150, function(){
					$(this).removeAttr('style');
					target.parent().attr('aria-expanded', 'true');
				});
			})
			//collapse a tree node
			.bind('collapse',function(event){
				var target = $(event.target) || tree.find('i[tabindex=0]');
				target.addClass('tree-parent-collapsed');
				target.nextAll('ul').slideUp(150, function(){
					target.parent().attr('aria-expanded', 'false');
					$(this).addClass('tree-group-collapsed').removeAttr('style');
				});
			})
			.bind('toggle',function(event){
				var target = $(event.target) || tree.find('i[tabindex=0]');
				//check if target parent LI is collapsed
				if( target.parent().is('[aria-expanded=false]') ){ 
					//call expand function on the target
					target.trigger('expand');
				}
				//otherwise, parent must be expanded
				else{ 
					//collapse the target
					target.trigger('collapse');
				}
			})
			//shift focus down one item		
			.bind('traverseDown',function(event){
				var target = $(event.target) || tree.find('i[tabindex=0]');
				var targetLi = target.parent();
				if(targetLi.is('[aria-expanded=true]')){
					target.nextAll('ul').find('i').eq(0).focus();
				}
				else if(targetLi.nextAll('ul').length) {
					targetLi.nextAll('ul').find('i').eq(0).focus();
				}	
				else {				
					targetLi.parents('li').nextAll('ul').find('i').eq(0).focus();
				}
			})
			//shift focus up one item
			.bind('traverseUp',function(event){
				var target = $(event.target) || tree.find('i[tabindex=0]');
				var targetLi = target.parent();
				if(targetLi.prev().length){ 
					if( targetLi.prev().is('[aria-expanded=true]') ){
						targetLi.prev().find('li:visible:last i').eq(0).focus();
					}
					else{
						targetLi.prev().find('i').eq(0).focus();
					}
				}
				else { 				
					targetLi.parents('li:eq(0)').find('i').eq(0).focus();
				}
			});

		
		//and now for the native events
		tree	
			.focus(function(event){
				//deactivate previously active tree node, if one exists
				tree.find('[tabindex=0]').attr('tabindex','-1').removeClass('tree-item-active');
				//assign 0 tabindex to focused item
				$(event.target).attr('tabindex','0').addClass('tree-item-active');
			})
			.click(function(event){
				//save reference to event target
				var target = $(event.target);
				//check if target is a tree node
				if( target.is('i.tree-parent') ){
					target.trigger('toggle');
					target.eq(0).focus();
					//return click event false because it's a tree node (folder)
					return false;
				}
			})
			.keydown(function(event){	
					var target = tree.find('i[tabindex=0]');
					//check for arrow keys
					if(event.keyCode == 37 || event.keyCode == 38 || event.keyCode == 39 || event.keyCode == 40){
						//if key is left arrow 
						if(event.keyCode == 37){ 
							//if list is expanded
							if(target.parent().is('[aria-expanded=true]')){
								target.trigger('collapse');
							}
							//try traversing to parent
							else {
								target.parents('li:eq(1)').find('i').eq(0).focus();
							}	
						}						
						//if key is right arrow
						if(event.keyCode == 39){ 
							//if list is collapsed
							if(target.parent().is('[aria-expanded=false]')){
								target.trigger('expand');
							}
							//try traversing to child
							else {
								target.parents('li:eq(0)').find('li i').eq(0).focus();
							}
						}
						//if key is up arrow
						if(event.keyCode == 38){ 
							target.trigger('traverseUp');
						}
						//if key is down arrow
						if(event.keyCode == 40){ 
							target.trigger('traverseDown');
						}
						//return any of these keycodes false
						return false;
					}	
					//check if enter or space was pressed on a tree node
					else if((event.keyCode == 13 || event.keyCode == 32) && target.is('i.tree-parent')){
							target.trigger('toggle');
							//return click event false because it's a tree node (folder)
							return false;
					}
			});
		}
	});
};			

$(function(){
//???????????????
$('a[nctype="add_cart"]').click(function(){
//modify by fxz 2016-07-11 begin
	var content_id = $(this).attr('content-id');
    addgocart(content_id, checkQuantity(),'addcart_callback');
//modify by fxz 2016-07-11 end
});
});


//??????????????????
function checkQuantity(){
    var quantity = parseInt($("#quantity").val());
    if (quantity < 1) {
        alert("?????????????????????");
        $("#quantity").val('1');
        return false;
    }
    max = parseInt($('[nctype="goods_stock"]').text());
    if(quantity > max){
        alert("????????????");
        return false;
    }
    return quantity;
}

//modify by fxz 2016-07-11 begin
/* ??????????????? */
function addgocart(contentId, quantity, callbackfunc) {
    var url = BASE_URL + '/front/cart/addToCart';
    quantity = parseInt(quantity);
    if (quantity){
        $.post(url, {'skuId':contentId, 'quantity':quantity}, function(data) {
            if (data != null) {
                if (data.status == 0) {
                    // ???????????????????????????
                    load_cart_information();
                    $('.ncs-cart-popup').show();
                } else {
                    //alert(data.msg);
                    window.location.href=BASE_URL+'/front/cas/login';
                }
            }
        },"json");
    }
}
//modify by fxz 2016-07-11 end
