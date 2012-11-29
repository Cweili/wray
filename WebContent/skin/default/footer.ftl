			<div id="layoutbottom"></div>
			<div id="credit" align="center">
				Powered by Wray,
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