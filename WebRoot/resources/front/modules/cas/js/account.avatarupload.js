$(function(){
    function showPreview(coords)
    {
        if (parseInt(coords.w) > 0){
            var rx = 120 / coords.w;
            var ry = 120 / coords.h;
            $('#preview').css({
                	width: Math.round(rx * 500) + 'px',
                    height: Math.round(ry * 375) + 'px',
                    marginLeft: '-' + Math.round(rx * coords.x) + 'px',
                    marginTop: '-' + Math.round(ry * coords.y) + 'px'
        });
    }
    $('#x1').val(coords.x);
    $('#y1').val(coords.y);
    $('#x2').val(coords.x2);
    $('#y2').val(coords.y2);
    $('#w').val(coords.w);
    $('#h').val(coords.h);
    }
    $('#nccropbox').Jcrop({
        aspectRatio:1,
        setSelect: [ 0, 0, 120, 120 ],
        minSize:[50, 50],
        allowSelect:0,
        onChange: showPreview,
        onSelect: showPreview
    });
    $('#ncsubmit').click(function() {
        var x1 = $('#x1').val();
        var y1 = $('#y1').val();
        var x2 = $('#x2').val();
        var y2 = $('#y2').val();
        var w = $('#w').val();
        var h = $('#h').val();
        var w1 = w/1000;
        var h1 = h/1000;
        if (w1 > h1) {
            $("#multiple").val(w1);
        } else if (w1 < h1) {
        	$("#multiple").val(h1);
        } else {
        	$("#multiple").val(w1);
        }
        var mu = $('#multiple').val();
        if(x1=="" || y1=="" || x2=="" || y2=="" || w=="" || h==""){
            alert("您必须先选定一个区域");
            return false;
        }else{
            $('#form_cut').submit();
        }
    });
    
    var img = document.getElementById("nccropbox");
    var width = img.width;
    var height = img.height;
    var w = width / 500;
    var h = height / 500;
    if (w > h) {
        $("#nccropbox").attr("width",width/w);
        $("#nccropbox").attr("height",height/w);
        $("#multiple").val(w);
    } else if (w < h) {
    	$("#nccropbox").attr("width",width/h);
    	$("#nccropbox").attr("height",height/h);
    	$("#multiple").val(h);
    } else {
    	$("#nccropbox").attr("width",width/w);
    	$("#nccropbox").attr("height",height/w);
    	$("#multiple").val(w);
    }
});