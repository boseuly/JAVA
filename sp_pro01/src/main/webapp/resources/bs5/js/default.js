/**
 * 
 */
 
	window.onload = function() {
		var form = document.forms[0];
		form.email.addEventListener("input", enableSaveButton);
		form.phone.addEventListener("input", enableSaveButton);
		
		prevImage.addEventListener("click", function(e) {
			btnImage.click();
		});
		
		btnImage.addEventListener("change", ajaxUploadImage);
	}
	
	function ajaxUploadImage(e) {
		var file = e.target.files[0];
		var fData = new FormData();
		fData.append("uploadImage", file, file.name);
		console.log(fData);
		$.ajax({
			type: "post",
			url: "/ajax/imageUpload",
			enctype: "multipart/form-data",
			data: fData,
			processData: false,
			contentType: false,
			success: function(data, status) {
				prevImage.src = data.loc;
			},
			error: function(data, status) {
				prevImage.src = data.loc;
			}
		});
	}
	
	function showPreview(e) {
		var file = e.target.files[0];
		var imgUrl = URL.createObjectURL(file);
		prevImage.src = imgUrl;
	}
	
	function enableSaveButton(e) {
		var submit = document.querySelector("button[type='submit']");
		var enable = submit.getAttribute("class").replace("disable", "");
		submit.setAttribute("class", enable);
	}