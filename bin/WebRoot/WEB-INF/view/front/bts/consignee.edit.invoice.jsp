<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<ul>
    <div id="add_inv_box">
        <form method="POST" id="inv_form" action="index.php">
            <input type="hidden" value="buy" name="act">
            <input type="hidden" value="add_inv" name="op">
            <input type="hidden" name="form_submit" value="ok"/>
            <div class="ncc-form-default">
                <dl>
                    <dd>
                        <label>
                            <input type="radio" checked name="invoice_type" value="1">
                            普通发票</label>
                    </dd>
                </dl>
            </div>
            <div id="invoice_panel" class="ncc-form-default">
                <dl>
                    <dt>发票抬头：</dt>
                    <dd>
                        <select name="inv_title_select">
                            <option value="person">个人</option>
                            <option value="company">单位</option>
                        </select>
                        <input class="text w200" style="display:none" name="inv_title" id="inv_title" placeholder="单位名称" value="">
                    </dd>
                </dl>
                <dl>
                    <dt>发票内容：</dt>
                    <dd>明细</dd>
                </dl>
            </div>
        </form>
    </div>
</ul>
<div class="hr16"> <a id="hide_invoice_list" class="ncc-btn ncc-btn-red" href="javascript:void(0);">保存发票信息</a> <a id="cancel_invoice" class="ncc-btn ml10" href="javascript:void(0);">不需要发票</a></div>
<script>
    $(function(){
        $.ajaxSetup({async : false});
        //不需要发票
        $('#cancel_invoice').on('click',function(){
            $('#is_invoice').val(0);
            hideInvList('不需要发票');
        });
        //使用新的发票信息
        $('input[nc_type="inv"]').on('click',function(){

            if ($(this).val() == '0') {
                $('.inv_item').removeClass('ncc-selected-item');
                $('#add_inv_box').show();
            } else {
                $('.inv_item').removeClass('ncc-selected-item');
                $(this).parent().addClass('ncc-selected-item');
                $('#add_inv_box').hide();
            }
        });

        //保存发票信息
        $('#hide_invoice_list').on('click',function(){
            var title = $('select[name=inv_title_select]');
            var invoice_belong = title.find('option:selected').text();
            if (title.val() == 'company'){
                invoice_belong += ' ' + $('#inv_title').val();
            }
            $('#is_invoice').val(1);
            $('#invoice_belong').val(invoice_belong);
            hideInvList(invoice_belong);
        });

        $('input[name="invoice_type"]').on('click',function(){
            if ($(this).val() == 1) {
                $('#invoice_panel').show();
                $('#vat_invoice_panel').hide();
            } else {
                $('#invoice_panel').hide();
                $('#vat_invoice_panel').show();
            }
        });
        $('select[name="inv_title_select"]').on('change',function(){
            if ($(this).val()=='company') {
                $('#inv_title').show();
            } else {
                $('#inv_title').hide();
            }
        });

    });
</script>
