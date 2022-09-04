$(document).ready(function() {
	/**
	 * 返回
	 */
	$("#button_cancel").click(function() {
		// history.go(-1);
		window.location.href = BASE_URL + "/back/storeContent/index";
	});
});