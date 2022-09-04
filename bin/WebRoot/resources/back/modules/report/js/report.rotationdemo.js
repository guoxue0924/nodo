Highcharts.setOptions({
    global: {
        useUTC: false
    },
    lang : {
        resetZoom : '<s:text name="highcharts.lang.resetZoom"></s:text>'    
    }
});
var chart;
$(document).ready(function() {
	chart = new Highcharts.Chart({
		chart: {
			renderTo: 'container',
            type: 'column',
			/*type: 'pie',*/
            margin: 75,
            options3d: {
                enabled: true,
                alpha: 15,
                beta: 15,
                depth: 50,
                viewDistance: 25
            }
	      },
	      title: {
	            text: 'Chart rotation demo'
	        },
	        subtitle: {
	            text: 'Test options by dragging the sliders below'
	        },
	        plotOptions: {
	            column: {
	                depth: 25
	            }
	        },
	        series: [{
	            data: [29.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4]
	        }]
	    });

	    function showValues() {
	        $('#R0-value').html(chart.options.chart.options3d.alpha);
	        $('#R1-value').html(chart.options.chart.options3d.beta);
	    }

	    // Activate the sliders
	    $('#R0').on('change', function () {
	        chart.options.chart.options3d.alpha = this.value;
	        showValues();
	        chart.redraw(false);
	    });
	    $('#R1').on('change', function () {
	        chart.options.chart.options3d.beta = this.value;
	        showValues();
	        chart.redraw(false);
	    });

	    showValues();
});