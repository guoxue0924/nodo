<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../wrapper.prefix.jsp"/>

<section class="hbox stretch">
    <table class="table table-striped m-b-none datagrid" id="container">
     </table>
     <div id="sliders">
	    <table>
	        <tr><td>Alpha Angle</td><td><input id="R0" type="range" min="0" max="45" value="15"/> <span id="R0-value" class="value"></span></td></tr>
	        <tr><td>Beta Angle</td><td><input id="R1" type="range" min="0" max="45" value="15"/> <span id="R1-value" class="value"></span></td></tr>
	    </table>
    </div>
</section>
            
<jsp:include page="../wrapper.suffix.jsp"/>

<script src="${STATIC_URL}/back/modules/report/js/report.rotationdemo.js"></script>