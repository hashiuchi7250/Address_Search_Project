$(document).ready(function() {

	// 検索ボタン押下時の処理
	$('#search').click(function() {
		$('#image').show();
	});

	// 候補選択時の処理
	$('.selection').click(function() {
		var id     = $(this).children('#id').val();
		var zip    = $(this).children('#zipcode').text();
		var jis    = $(this).children("#jiscode").val();
		var pref   = $(this).children('#pref').text();
		var city   = $(this).children('#city').text();
		var street = $(this).children('#street').text();
		var add    = pref + city + street;

		$('#selectId').val(id);
		$('#selectZipcode').val(zip);
		$('#selectJiscode').val(jis);
		$('#selectPref').val(pref);
		$('#selectCity').val(city);
		$('#selectStreet').val(street);
		$('#selectAddredd').val('選択された住所：' + add);

		if($('#edit').length == 0){
	    	$('#Insertion').append('<button type="button" data-toggle="modal" data-target="#editModal" id="edit">編集</button>');
	    }
	});
	// 住所検索結果の処理
	$("#foo-table").DataTable({
		language: {
			"lengthMenu" : "表示件数： _MENU_",
			"search" : "検索：",
			"sInfoEmpty": " 0 件中 0 から 0 まで表示",
			"sInfo": " _TOTAL_ 件中 _START_ から _END_ まで表示",
			"oPaginate": {
				"sPrevious": "前",
				"sNext": "次",
			}
		},
		lengthMenu: [ 5, 10, 15, 20, 25 ],
	    displayLength: 5,
	    order: [ 0, 'des' ],
	    columnDefs :[
	    	{ targets:0, width:120 },
	    	{ targets:1, width:60 },
	    	{ targets:2, width:150 },
	    	{ targets:[0,2,3,4,5], sortable:false }
	    ]
	});

	// モーダルウィンドウの処理
	$("#modal-open").click( function(){
		$("#foo-table2").DataTable({
		    lengthChange: false,
		    searching: false,
		    info: false,
		    paging: false,
		    order: [ 0, 'des' ],
		    columnDefs :[
		    	{ targets:[0,2,3,4], sortable:false }
		    ]
		});
		$( "body" ).append( '<div id="modal-overlay"></div>' ) ;
		$( "#modal-overlay" ).fadeIn( "slow" ) ;
		centeringModalSyncer() ;
		$( "#modal-content" ).fadeIn( "slow" ) ;
		$( "#modal-overlay,#modal-close" ).unbind().click( function(){
			$( "#modal-content,#modal-overlay" ).fadeOut( "slow" , function(){
				$('#modal-overlay').remove() ;
			} ) ;
		} ) ;
	});

	// CSVボタン押下時の処理
	$("#csv").click(function() {
		if (confirm('CSV出力しますか？')) {
			var zipcode = $('#zipcode').val();
			$.ajax({
				url: "download_csv",
	            type: "POST",
				data: {
					zipcode: zipcode,
	            },
	            dataType: "text",
	            success: function(data){
					let bom = new Uint8Array([0xEF, 0xBB, 0xBF]);
					let downloadData = new Blob([bom, data], {type: 'text/csv'});
					let filename = 'File.csv'
					let downloadUrl  = (window.URL || window.webkitURL).createObjectURL(downloadData);
					let link = document.createElement('a');
					link.href = downloadUrl;
					link.download = filename;
					link.click();
	            }
	    	});
		} else {
			return false;
		}
	});

	// 編集押下時の処理
	$("#Insertion").click( function(){
		$( "body" ).append( '<div id="modal-overlay"></div>' ) ;
		$( "#modal-overlay" ).fadeIn( "slow" ) ;
		centeringModalSyncer() ;
		$( "#modal-content" ).fadeIn( "slow" ) ;
		$( "#modal-overlay,#modal-close" ).unbind().click( function(){
			$( "#modal-content,#modal-overlay" ).fadeOut( "slow" , function(){
				$('#modal-overlay').remove() ;
			} ) ;
		} ) ;
	});

	// 新規追加押下時の処理
	$("#insert").click( function(){
		if (confirm('登録してよろしいですか？')) {
			var zipcode     = $('#insZipcode').val();
			var jiscode     = $('#insJiscode').val();
			var pref        = $('#insPref').val();
			var pref_kana   = $('#insPrefKana').val();
			var city        = $('#insCity').val();
			var city_kana   = $('#insCitykana').val();
			var street      = $('#insStreet').val();
			var street_kana = $('#insStreetkana').val();
			var edit        = $('#insert').val();
			$.ajax({
				url: "edit",
	            type: "GET",
				data: {
					selectZipcode     : zipcode,
					selectJiscode     : jiscode,
					selectPref        : pref,
					selectPrefKana    : pref_kana,
					selectCity        : city,
					selectCityKana    : city_kana,
					selectStreet      : street,
					selectStreetKana  : street_kana,
					edit              : edit,
	            },
	            dataType: "text",
	            success: function(data){
		        	 if (data == "その他例外が発生しました。"
		        		 || data == "NullPointerExceptionが発生しました。"
		        			 || data == "IllegalArgumentExceptionが発生しました。") {
		        		 location.href = "http://localhost:8080/address/search/error";
		        	 } else {
		        		 alert("登録しました。");
		        		 location.href = "http://localhost:8080/address/search/addressReference?zipcode=" + zipcode;
		             }
	            }
	    	});
		} else {
			return false;
		}
	});

	// 更新押下時の処理
	$("#update").click( function(){
		if (confirm('更新してよろしいですか？')) {
			var url     = $('#zipcode').val();
			var id      = $('#selectId').val();
			var zipcode = $('#selectZipcode').val();
			var pref    = $('#selectPref').val();
			var city    = $('#selectCity').val();
			var street  = $('#selectStreet').val();
			var update  = $('#update').val();
			$.ajax({
				url: "edit",
	            type: "GET",
				data: {
					selectId     : id,
					selectZipcode: zipcode,
					selectPref   : pref,
					selectCity   : city,
					selectStreet : street,
					edit         : update
	            },
	            dataType: "text",
	            success: function(data){
	            	if (data == "その他例外が発生しました。"
		        		 || data == "NullPointerExceptionが発生しました。"
		        			 || data == "IllegalArgumentExceptionが発生しました。") {
		        		 location.href = "http://localhost:8080/address/error/";
		        	} else {
		        		alert("更新しました。");
		        		location.href = "http://localhost:8080/address/search/addressReference?zipcode=" + zipcode;
		            }
	            }
	    	});
		} else {
			return false;
		}
	});

	// 削除押下時の処理
	$("#del").click( function(){
		if (confirm('削除してよろしいですか？')) {
			var zipcode = $('#zipcode').val();
			var jiscode = $('#selectJiscode').val();
			var del     = $('#del').val();
			$.ajax({
				url: "edit",
	            type: "GET",
				data: {
					selectJiscode: jiscode,
					edit: del
	            },
	            dataType: "text",
	            success: function(data){
	            	alert("削除しました。");
	            	location.href = "http://localhost:8080/address/search/addressReference?zipcode=" + zipcode;
	            }
	    	});
		} else {
			return false;
		}
	});

	// 新規追加バリデーション処理
	$("#insForm").validationEngine("attach", {focusFirstField: false});
	$("#insZipcodem, #insPref, #insCity, #insStreet").change(function () {
        if ($("#formID").validationEngine("validate")) {
            $("#insert").prop("disabled", false);
        } else {
            $("#insert").prop("disabled", true);
        }
    });

	// 更新バリデーション処理
	$("#editForm").validationEngine("attach", {focusFirstField: false});
	$("#selectZipcode, #selectPref, #selectCity, #selectStreet").change(function () {
        if ($("#editForm").validationEngine("validate")) {
            $("#update").prop("disabled", false);
        } else {
            $("#update").prop("disabled", true);
        }
    });


	// モーダルウィンドウ関数
	function centeringModalSyncer() {
		var w  = $( window ).width() ;
		var h  = $( window ).height() ;
		var cw = $( "#modal-content" ).outerWidth();
		var ch = $( "#modal-content" ).outerHeight();
		$( "#modal-content" ).css( {"left": ((w - cw)/2) + "px","top": ((h - ch)/2) + "px"} ) ;
	}
});