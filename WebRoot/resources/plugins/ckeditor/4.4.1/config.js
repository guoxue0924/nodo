/**
 * @license Copyright (c) 2003-2013, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.html or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	
//	config.toolbarGroups = [
//		{ name: 'clipboard',   groups: [ 'clipboard', 'undo' ] },
//		{ name: 'editing',     groups: [ 'find', 'selection', 'spellchecker' ] },
//		{ name: 'links' },
//		{ name: 'insert' },
//		{ name: 'forms' },
//		{ name: 'tools' },
//		{ name: 'document',	   groups: [ 'mode', 'document', 'doctools' ] },
//		{ name: 'others' },
//		'/',
//		{ name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] },
//		{ name: 'paragraph',   groups: [ 'list', 'indent', 'blocks', 'align', 'bidi' ] },
//		{ name: 'styles' },
//		{ name: 'colors' }
//	];
	
	config.extraPlugins += (config.extraPlugins ? ',lineheight' : 'lineheight');
	
	config.toolbar = 'Full';   
	  
//	config.toolbar_Full =   
//	[   
//	    ['Source','-','Save','NewPage','Preview','-','Templates'],   
//	    ['Cut','Copy','Paste','PasteText','PasteFromWord','-','Print', 'SpellChecker', 'Scayt'],   
//	    ['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],   
//	    ['BidiLtr', 'BidiRtl'],   
//	    '/',   
//	    ['Styles','Format','Font','FontSize'],   
//	    ['TextColor','BGColor'],   
//	    ['Maximize', 'ShowBlocks','-','About']   
//	];   
	// Remove some buttons, provided by the standard plugins, which we don't
	// need to have in the Standard(s) toolbar.
	config.removeButtons = 'Underline,Subscript,Superscript';

	// Se the most common block elements.
	config.format_tags = 'p;h1;h2;h3;pre';

	// Make dialogs simpler.
	config.removeDialogTabs = 'image:advanced;link:advanced';
	
	config.filebrowserUploadUrl = BASE_URL + '/back/goodsContent/uploadFile';
	
	config.image_previewText=''; //预览区域显示内容
	config.filebrowserImageUploadUrl= BASE_URL + "/back/goodsContent/editUploadImage"; //待会要上传的action或servlet
};
