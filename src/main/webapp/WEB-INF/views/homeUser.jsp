<div class="hr"></div>
<div class="gap10px"></div>
<div class="content">
	<div class="slideshow">
	<div id="slides">
	    <div class="slides_container">
	        <div>
	            <img src="resources/images/slide_image_1.jpg">
	        </div>
	        <div>
	            <img src="resources/images/slide_image_2.jpg">
	        </div>
	        <div>
	            <img src="resources/images/slide_image_3.jpg">
	        </div>
	    </div>
	</div>
	</div>
	<div class="uploader"></div>
</div>
<script type="text/javascript" src="resources/js/slides.jquery.js"></script>
 <script>
	$(function(){
	    $("#slides").slides({
	    	play: 5000,
	    	pagination: false,
	    	generatePagination: false
	    });
	});
</script>