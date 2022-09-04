var ckeditor1;
var config = {
	extraPlugins : 'codesnippet',
	codeSnippet_theme : 'monokai_sublime',
	height : 500
};
if (typeof CKEDITOR == 'object') {
	if (CKEDITOR.instances['editor1']) {
		CKEDITOR.remove(CKEDITOR.instances['editor1']);
	}
	ckeditor1 = CKEDITOR.replace('editor1', config);
	ckeditor1.setData(content.body);
}