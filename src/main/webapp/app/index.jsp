<!doctype html>
<html class="no-js">
<head>
    <meta charset="utf-8">
    <title></title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width">
    <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
    <!-- build:css(.) styles/vendor.css -->
    
    
    <!-- bower:css -->

    <link rel="stylesheet" href="bower_components/AngularJS-Toaster/toaster.css" />
    <link rel="stylesheet" href="bower_components/font-awesome/css/font-awesome.min.css" >
    <link rel="stylesheet" href="bower_components/angular-ui-bootstrap/ui-bootstrap-csp.css" >
     <link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap-theme.css" />
     <link rel="stylesheet" href="bower_components/angular/angular-csp.css" />
     <link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.min.css" />
     <link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.css" />
     
	 <link rel="stylesheet" href="styles/welcomepage.css" />
	 <link rel="stylesheet" href="styles/register_page.css" />
	  <link rel="stylesheet" href="styles/login_page.css" /> 
	  <link rel="stylesheet" href="styles/homepage.css" /> 
	    <link rel="stylesheet" href="styles/profile.css" /> 
 	    <link rel="stylesheet" href="styles/tips.css" /> 
  <link rel="stylesheet" href="styles/kraj.css" /> 
 
  

  
    <!-- build:css(.tmp) styles/main.css -->
  
    <!-- endbuild -->
</head>
<!-- 
	ng-app is directive that declares that the element 
	and its children will be handled by angular.js 
-->
<body ng-app="avAngularStartupApp" style="padding-top: 0px; padding-bottom: 0px; background: #fff;">
	<!--[if lt IE 7]>
	<p class="browsehappy">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade
	    your browser</a> to improve your experience.</p>
	<![endif]-->

	   
	    	<!-- 
	    		container for our views
	    	 -->
	        <div ng-view ></div>
	   
	
	
	<!-- build:js(.) scripts/oldieshim.js -->
		<!--[if lt IE 9]>
			<script src="bower_components/es5-shim/es5-shim.js"></script>
			<script src="bower_components/json3/lib/json3.js"></script>
		<![endif]-->
	<!-- endbuild -->
	
	<!-- build:js(.) scripts/vendor.js -->
	<!-- bower:js -->
	<script src="bower_components/jquery/dist/jquery.js"></script>
	<script src="bower_components/angular/angular.js"></script>
	<script src="bower_components/angular/angular.min.js"></script>
	<script src="bower_components/bootstrap/dist/js/bootstrap.js"></script>
	<script src="bower_components/angular-bootstrap/ui-bootstrap-tpls.js"></script>
	<script src="bower_components/angular-animate/angular-animate.js"></script>
	<script src="bower_components/AngularJS-Toaster/toaster.js"></script>
	<script src="bower_components/angular-route/angular-route.js"></script>
	<script src="bower_components/angular-resource/angular-resource.js"></script>
	<script src="bower_components/angular-ui-bootstrap/ui-bootstrap.min.js"></script>
	<script src="bower_components/ngMask-master/dist/ngMask.min.js"></script>
	<script src="bower_components/ngMask-master/dist/ngMask.js"></script>
	
	
	<script src="bower_components/scrollspyscripti/agency.js"></script>
	<script src="bower_components/scrollspyscripti/cbpAnimatedHeader.js"></script>
	<script src="bower_components/scrollspyscripti/cbpAnimatedHeader.min.js"></script>
	<script src="bower_components/scrollspyscripti/classie.js"></script>
	
	
	
	
 	<script src="bower_components/angular-cookies/angular-cookies.min.js"></script>
 	<script src="bower_components/angular-cookies/angular-cookies.js"></script>
		 <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8/jquery.min.js"></script>
    <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=true&libraries=places&language=en-US"></script>
		<script type="text/javascript" src="http://www.skypeassets.com/i/scom/js/skype-uri.js"></script>
		
	<!-- endbower -->
	
	
	<!-- endbuild -->
	
	<!-- These scripts hold the code of the application -->
	<!-- build:js({.tmp,app}) scripts/scripts.js -->
	<!-- The definition and the configuration of the application module -->
	
	
	<script src="scripts/app.js"></script>
	
	<!-- The route configuration -->
	
	<script src="scripts/router.js"></script>
	
	<!-- controllers -->
	<script src="scripts/controllers/main.js"></script>
	<script src="scripts/controllers/WelcomeController.js"></script>
	<script src="scripts/controllers/LoginController.js"></script>
	<script src="scripts/controllers/RegisterController.js"></script>
	<script src="scripts/controllers/HomeController.js"></script>
	<script src="scripts/controllers/googleMapsController.js"></script>
	<script src="scripts/controllers/ProfileController.js"></script>
	<script src="scripts/controllers/MailController.js"></script>
	<script src="scripts/controllers/tipsController.js"></script>
	
	<!-- controllers -->
	
	<!-- services -->
	<script src="scripts/services/services.js"></script>
	<script src="scripts/services/login.js"></script>
	<script src="scripts/services/multipartFormCommentPost.js"></script>
	<script src="scripts/services/multipartFormRegister.js"></script>
	<script src="scripts/services/crud.js"></script>
	<script src="scripts/services/home.js"></script>
	<script src="scripts/services/profile.js"></script>
	<script src="scripts/services/mail.js"></script>
	<script src="scripts/services/scrollFunctionality.js"></script>
	<script src="scripts/services/likecomment.js"></script>
	
	<!-- services -->

	<!-- directives -->
	<script src="scripts/directives/gmDirective.js"></script>
	<script src="scripts/directives/fileUpload.js"></script>
	<script src="scripts/directives/SkypeCall.js"></script>
	<script src="scripts/directives/phoneInput.js"></script>
	
	
	
	<!-- directives -->
	
	
	<!-- filters -->
	<script src="scripts/filters/phoneFilter.js"></script>
	<!-- filters -->
	
	<!-- endbuild -->
</body>
</html>