/**
 * Created by George on 15/4/16.
 */

$(function(){
	/**
	 * 保存设置 - select 类型
	 */
	$('.selCountries_2').on('change', function(){
		region_change($(this).val(),'.selCountries_3');
	});
	$('.selCountries_1').on('change', function(){
		region_change($(this).val(),'.selCountries_2');
	});
});

/**
 * 省级改变
 * @param region_id
 * @param class_name
 */
function region_change(region_id,class_name) {
	var options = {
        url:'/bts/consignee/getRegionRs',
        data:{"pid": region_id},
        dataType:'html',
        type:'POST',
        timeout:60000,
        success:function(htmls){
        	$(class_name).empty();
        	$(class_name).append(htmls);
        }
	};
	$.ajax(options);
}