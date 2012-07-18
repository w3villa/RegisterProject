<%@page contentType="text/html;charset=UTF-8" %>
 <%@page pageEncoding="UTF-8" %>
 <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 <%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
 <html>
 <head>
     <link rel="stylesheet" href="resources/uploadify/uploadify.css" type="text/css" media="all" />
     
     <script src="resources/jquery-1.3.2.min.js" type="text/javascript"></script>
     <script type="text/javascript" src="resources/uploadify/swfobject.js"></script>
     <script type="text/javascript" src="resources/uploadify/jquery.uploadify.v2.1.0.min.js"></script>
     <script type="text/javascript">
         $(document).ready(function() {
             $('#upload').click(function() {
                 $('#uploadify').uploadifyUpload();
                     return false;
                 });
                 $('#uploadify').uploadify({
                     'uploader': 'resources/uploadify/uploadify.swf',
                     'script': '/springSourcery/app/secured/uploadPictures.html;jsessionid=${sessionId}',
                     'multi': true,
                     'auto' : true,
                     'fileDesc': 'JPG Image Files (*.jpg),JPG Image Files (*.JPG),JPG Image Files (*.JPEG)',
                     'fileExt' : '*.jpg;*.jpeg;*.JPEG;',
                     'cancelImg': '/springSourcery/resources/uploadify/cancel.png'
                 });
         });
 </script>
 </head>
 <body>
 
     <div id="master">
        <core:import url="MenuNav.jsp" />
    </div>
    <div id="main">
        
        <h3>Upload jpeg images for your profile picture and content.</h3>
        
        <div id="introduction">    
            <p>Click on the select files button to upload <B>(.jpeg, .jpg)</B> images for your profiles, tutorials or questions to SpringSourcery.</p>
            <p><b>Close this window once you have finished uploading your pictures.</p>
        </div>
        
        <BR>
        
        <input id="uploadify" type="file">
         <form:form modelAttribute="uploadbean" method="post" enctype="multipart/form-data">
         </form:form>
     </div>
     <core:import url="Footer.jsp" />
 </body>
 </html>