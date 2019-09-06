angular
  .module("myModule", ["ng", "ngRoute"])  //创建Module控制器


  .controller("login", function($rootScope,$scope,$http,$location,$route) {
    $scope.login = function() {   //登录页面 判断用户名密码为空提示
      if (!$scope.username) { //如果用户名为空
        $("#password").parent().removeClass("has-error");
        $("#password").prev().addClass("hidden");
        $("#username").prev().removeClass("hidden");
        $("#username").parent().addClass("has-error");
      } else if (!$scope.password) {
        $("#username").parent().removeClass("has-error");
        $("#username").prev().addClass("hidden");
        $("#password").prev().removeClass("hidden");
        $("#password").parent().addClass("has-error");
      } else {
    	  $scope.logindata = {
    			  method:"login",
    			  name:$scope.username,
    			  password: $scope.password
    	  }
        $("#username").prev().addClass("hidden");
        $("#password").prev().addClass("hidden");
		$http({
			method:"get",
			params:$scope.logindata,
			url:"Servlet_sql"
		}).then(function success(resp){
			//响应成功时调用，resp是一个响应对象
			if(resp.data != "error"){
				$rootScope.name = $scope.username;
				$rootScope.user_id = resp.data
		        $rootScope.islogin = true;
				$location.path('newsIndex').search({
					user_id: $rootScope.user_id,
					username:$scope.username
				});
			}else{
				alert("用户名或密码错误")
			}
		},function error(resp){
			// 响应失败时调用，resp带有错误信息
			console.log("失败"+resp);
		}
		);
      }
    };
    $scope.exit = function() {
    	$rootScope.name=null;
      $rootScope.islogin = false;
    };
    $scope.reloadCurPage = function(url){
        $route.reload(url)
    };
  })
  
  
  .controller("register",function($scope,$location,$http){
    var username = /^[a-zA-Z0-9_-]{1,16}$/;
    var password = /^[a-zA-Z0-9_-]{6,20}$/; 
    var phone = /^1[34578]\d{9}$/;
    $scope.sex = 1;
    $scope.register = function(){
      function hide_error(){
        var error = $(".register_content")[0].querySelectorAll(".has-error")[0];
        if(error){
          $(error).removeClass("has-error");
        }
      }
      if (!$scope.username) { 
        hide_error();
        $("#username").parent().addClass("has-error");
      } else if (!$scope.password || !password.test($scope.password)) {
        hide_error();
        $("#password").parent().addClass("has-error");
        $scope.password = $scope.psw_confirm = null;
      }else if (!$scope.psw_confirm || ($scope.password != $scope.psw_confirm)) {
        hide_error();
        $("#psw_confirm").parent().addClass("has-error");
      }else if (!$scope.phone || !phone.test($scope.phone)) {
        hide_error();
        $("#phone").parent().addClass("has-error");
      }else if (!$scope.address) {
        hide_error();
        $("#address").parent().addClass("has-error");
      } else {
        hide_error();
        console.log($scope.username,$scope.password,$scope.psw_confirm,$scope.sex,$scope.phone,$scope.address)        //注册的数据信息        

        $scope.registerdata = {
        	method:"register",
        	username:$scope.username,
        	password:$scope.password,
        	sex:$scope.sex,
        	phone:$scope.phone,
        	address:$scope.address
        }
        $http({
			method:"get",
			params:$scope.registerdata,
			url:"Servlet_sql"
		}).then(function success(resp){
			//响应成功时调用，resp是一个响应对象
			console.log(resp.data);
			if(resp.data == "exist"){
				alert("用户已存在")
			}else{
				$location.path("login");
			}
		},function error(resp){
			// 响应失败时调用，resp带有错误信息
			console.log("失败"+resp);
		}
		);
        
        
      }
    }
  })

.controller("indexCtrl",function($rootScope,$scope,$routeParams,$http){
	$scope.user_id=$routeParams.user_id;
	$scope.username=$routeParams.username;
	$rootScope.islogin = true;
	$rootScope.name = $scope.username;
	$rootScope.user_id = $scope.user_id;
	
	$http({
		method:"get",
		params:{method:"index"},
		url:"Servlet_sql"
	}).then(function success(resp){
		//响应成功时调用，resp是一个响应对象
		$scope.index = resp.data;
		console.log(resp.data);
	},function error(resp){
		// 响应失败时调用，resp带有错误信息
		console.log(resp);
	}
	);
	
	$scope.search = function(){
		$http({
			method:"get",
			params:{
				method:"search",
				searchdata:$scope.searchdata
			},
			url:"Servlet_sql"
		}).then(function success(resp){
			//响应成功时调用，resp是一个响应对象
			$scope.index = resp.data;
			console.log(resp.data);
		},function error(resp){
			// 响应失败时调用，resp带有错误信息
			console.log(resp);
		}
		);
	}
	
//  $scope.index = [{
//    title:"德狗是怎样炼成的！",
//    post_time:1544720002226,
//    type:"教育",
//    news_id:1
//  },{
//    title:"宝狗是怎样养成的！",
//    post_time:1555720002226,
//    type:"金融",
//    news_id:2
//  }];
})

	.filter(
		'to_trusted', ['$sce', function ($sce) {
			return function (text) {
				return $sce.trustAsHtml(text);
				}
			  }]
			)
.controller("detailCtrl",function($scope,$routeParams,$http,$rootScope){
//	$scope.user_id=$routeParams.user_id;
//	$scope.username=$routeParams.username;
//	$rootScope.islogin = true;
//	$rootScope.name = $scope.username;

	
	
	$scope.news_id = $routeParams.news_id;
	$http({
		method:"get",
		params:{
			method:"detail",
			news_id:$scope.news_id
			},
		url:"Servlet_sql"
	}).then(function success(resp){
		//响应成功时调用，resp是一个响应对象
		console.log(resp.data)
		$scope.detail = resp.data[0];
	},function error(resp){
		// 响应失败时调用，resp带有错误信息
		console.log(resp);
	}
	);
//  $scope.detail = {
//    news_id:1,
//    title:"德狗是怎样炼成的！",
//    post_time:1544720002226,
//    type:"教育",
//    post_username:'李宝伟',
//    content:'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusamus consequatur inventore, hic maxime optio nemout quidem atque, eos, officiis corporis cumque voluptatum porro commodi id. Quidem fugiat, earum incidunt?Loremipsum dolor sit amet, consectetur adipisicing elit. Sed doloribus maxime nesciunt cum nulla hic recusandaedignissimos nemo blanditiis. Accusantium praesentium, veniam distinctio fugit, repellat ipsum nesciunt deseruntpariatur deleniti.Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusamus consequatur inventore, hic maxime optio nemout quidem atque, eos, officiis corporis cumque voluptatum porro commodi id. Quidem fugiat, earum incidunt?Loremipsum dolor sit amet, consectetur adipisicing elit. Sed doloribus maxime nesciunt cum nulla hic recusandaedignissimos nemo blanditiis. Accusantium praesentium, veniam distinctio fugit, repellat ipsum nesciunt deseruntpariatur deleniti.'
//  };
})

.controller("myNewsCtrl",function($scope,$rootScope,$routeParams,$http,$location,$route){
	$scope.user_id=$routeParams.user_id;
	$scope.username=$routeParams.username;
	$rootScope.islogin = true;
	$rootScope.name = $scope.username;
	$rootScope.user_id = $scope.user_id;
	$http({
		method:"get",
		params:{
			method:"myNews",
			user_id:$scope.user_id
			},
		url:"Servlet_sql"
	}).then(function success(resp){
		//响应成功时调用，resp是一个响应对象
		console.log(resp.data)
		$scope.myNews = resp.data;
	},function error(resp){
		// 响应失败时调用，resp带有错误信息
		console.log(resp);
	}
	);
	$scope.delete = function(news_id){
		$http({
			method:"get",
			params:{
				method:"delete",
				news_id:news_id
				},
			url:"Servlet_sql"
		}).then(function success(resp){
			if( resp.data == "success"){
				console.log(resp.data)
				$route.reload(" myNews ");
			}
		},function error(resp){
			// 响应失败时调用，resp带有错误信息
			console.log(resp);
		}
		);
	}
	
//  $scope.myNews = [{
//    news_id:1,
//    title:"德狗是怎样炼成的！",
//    post_time:1544720002226,
//    type:"教育",
//    user_id:1,
//    post_username:'李宝伟',
//    content:'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusamus consequatur inventore, hic maxime optio nemout quidem atque, eos, officiis corporis cumque voluptatum porro commodi id. Quidem fugiat, earum incidunt?Loremipsum dolor sit amet, consectetur adipisicing elit. Sed doloribus maxime nesciunt cum nulla hic recusandaedignissimos nemo blanditiis. Accusantium praesentium, veniam distinctio fugit, repellat ipsum nesciunt deseruntpariatur deleniti.Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusamus consequatur inventore, hic maxime optio nemout quidem atque, eos, officiis corporis cumque voluptatum porro commodi id. Quidem fugiat, earum incidunt?Loremipsum dolor sit amet, consectetur adipisicing elit. Sed doloribus maxime nesciunt cum nulla hic recusandaedignissimos nemo blanditiis. Accusantium praesentium, veniam distinctio fugit, repellat ipsum nesciunt deseruntpariatur deleniti.'
//  },{
//    news_id:2,
//    title:"宝狗是怎样养成的！",
//    post_time:1555720002226,
//    type:"金融",
//    user_id:1,
//    post_username:'李宝伟',
//    content:'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusamus consequatur inventore, hic maxime optio nemout quidem atque, eos, officiis corporis cumque voluptatum porro commodi id. Quidem fugiat, earum incidunt?Loremipsum dolor sit amet, consectetur adipisicing elit. Sed doloribus maxime nesciunt cum nulla hic recusandaedignissimos nemo blanditiis. '
//  }]
})

.controller("publishCtrl",function($scope,$rootScope,$routeParams,$http,$location,$route){
	$scope.user_id=$routeParams.user_id;
	$scope.username=$routeParams.username;
	$rootScope.islogin = true;
	$rootScope.name = $scope.username;
	$rootScope.user_id = $scope.user_id;
	$scope.public = function() {
		editor.sync();//将KindEditor的数据同步到textarea标签。
		$scope.value_content = $('textarea[name="content"]').val();
		if(!$scope.title){c 
			alert("请输入标题！")
		}else if(!$scope.type){
			alert("请选择类型！")
		}else if(!$scope.value_content){
			alert("请输入内容！")
		}else{
			$scope.timestamp = Date.parse(new Date())
			console.log($scope.title,$scope.type,$scope.value_content,$scope.timestamp);
			$scope.data = {
					method:"publish",
					user_id:$scope.user_id,
					title:$scope.title,
					type:$scope.type,
					content:$scope.value_content,
					post_time:$scope.timestamp
			}
			 console.log($scope.data);
			$http({
				method:"get",
				params:$scope.data,
				url:"Servlet_sql"
			}).then(function success(resp){
				//响应成功时调用，resp是一个响应对象
				console.log(resp.data)
				if(resp.data = "success"){
					$location.path('newsIndex').search({
						user_id: $rootScope.user_id,
						username:$rootScope.name
					});
				}else{
					console.log("失败");
				}
			},function error(resp){
				// 响应失败时调用，resp带有错误信息
				console.log(resp);
			}
			);
		}
	}
})
.controller("updateCtrl",function($scope,$rootScope,$routeParams,$http,$location){
	$scope.user_id=$routeParams.user_id;
	$scope.news_id=$routeParams.news_id;
	$scope.username=$routeParams.username;
	$rootScope.islogin = true;
	$rootScope.name = $scope.username;
	$rootScope.user_id = $scope.user_id;
	
	$http({
		method:"get",
		params:{
			method:"select",
			news_id:$scope.news_id
			},
		url:"Servlet_sql"
	}).then(function success(resp){
		//响应成功时调用，resp是一个响应对象
		$scope.select = resp.data[0];
		console.log($scope.select)
		$scope.title = $scope.select.title;
		$scope.type = $scope.select.type;
		if(!editor){
			console.log("1")
		}else{
			editor.html($scope.select.content);
		}
	},function error(resp){
		// 响应失败时调用，resp带有错误信息
		console.log(resp);
	}
	);
	

	
	$scope.update = function() {
		editor.sync();//将KindEditor的数据同步到textarea标签。
		$scope.value_content = $('textarea[name="content"]').val();
		if(!$scope.title){
			alert("请输入标题！")
		}else if(!$scope.type){
			alert("请选择类型！")
		}else if(!$scope.value_content){
			alert("请输入内容！")
		}else{
			$scope.timestamp = Date.parse(new Date())
			console.log($scope.title,$scope.type,$scope.value_content,$scope.timestamp);
			console.log($scope.news_id);
			$scope.data = {
					method:"update",
					news_id:$scope.news_id,
					title:$scope.title,
					type:$scope.type,
					content:$scope.value_content,
					post_time:$scope.timestamp
			}
			$http({
				method:"get",
				params:$scope.data,
				url:"Servlet_sql"
			}).then(function success(resp){
				//响应成功时调用，resp是一个响应对象
				console.log(resp.data)
				if(resp.data = "success"){
					$location.path('newsIndex').search({
						user_id: $rootScope.user_id,
						username:$rootScope.name
					});
				}else{
					console.log("失败");
				}
			},function error(resp){
				// 响应失败时调用，resp带有错误信息
				console.log(resp);
			}
			);
		}
	}
})

  .config(function($routeProvider) {
    $routeProvider.when('/login',{
		templateUrl:'tpl/login.html',
		controller:'login'
	}).when('/register',{
		templateUrl:'tpl/register.html',
		controller:'register'
	}).when('/newsIndex',{
		templateUrl:'tpl/news_index.html',
		controller:'indexCtrl'
	}).when('/newsDetail',{
		templateUrl:'tpl/news_detail.html',
		controller:'detailCtrl'
	}).when('/myNews',{
		templateUrl:'tpl/my_news.html',
		controller:'myNewsCtrl'
	}).when('/publish',{
		templateUrl:'tpl/publish.html',
		controller:'publishCtrl'
	}).when('/update',{
		templateUrl:'tpl/update.html',
	    controller:'updateCtrl'
	}).otherwise({  
		redirectTo:'/login'   
		//redirectTo:'/indexCtrl'   
	})
  });


