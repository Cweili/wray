			<div id="layoutbottom"></div>
			<div id="credit" align="center">
				Powered by
				<a href="http://b3log-solo.googlecode.com" target="_blank" class="logo">
					<span style="color: orange;">B</span>
					<span style="font-size: 9px; color: blue;"><sup>3</sup></span>
					<span style="color: green;">L</span>
					<span style="color: red;">O</span>
					<span style="color: blue;">G</span>&nbsp;
					<span style="color: orangered; font-weight: bold;">Solo</span></a>,
				ver ${wrayVersion}&nbsp;&nbsp;
				Theme by <a href="http://www.noday.net" target="_blank">Noday</a> 
				& <a href="http://www.templatesnext.org/" target="_blank">Templates Next</a>
			</div>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script type="text/javascript" src="include/js/functions.js"></script>
<script type="text/javascript">
    
    // set selected navi
    $("#header-navi li").each(function (i) {
        if (i < $("#header-navi li").length - 1) {
            var $it = $(this),
            locationURL = window.location.pathname + window.location.search;
            if (i === 0 && (locationURL === "/")) {
                $it.addClass("selected");
                return;
            }
            if (locationURL.indexOf($it.find("a").attr("href")) > -1 && i !== 0) {
                $it.addClass("selected");
            }
        }
    });
</script>