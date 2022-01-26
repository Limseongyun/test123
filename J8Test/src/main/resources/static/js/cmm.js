'usestrict';
(function($){
	cmm = {
		test: function(){
			console.log('test');
		},
		isJSON: function(str, url){
			if(typeof(str) === 'object') return true;
			try {
				JSON.parse(str);
			} catch (e) {
				console.log(e)
				if(url) location.href=url;
				else location.href='public/login'; 
			}
		}
	}
})(jQuery)