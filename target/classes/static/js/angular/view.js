/**
 * Created by Tomek on 2015-05-01.
 */
var view = angular.module('view',[]);
var urlbase = "http://localhost:420/";


view.controller('viewCtrl', function($scope, $http){
    console.log('tu');
    //$http.get(urlbase + 'api/getLatest').
    //    success(function(data) {
    //        console.log('tak');
    //        $scope.posts = data;
    //    }).error(function (data, status, headers, config) {
    //        alert(data);
    //        return status;
    //    });


    $scope.teste2 = function() {
        $http.post(urlbase + 'api/post2').success(function(data) {
            $scope.whatever = data;
            console.log('yeah');
        }).error(function(data,status,headers,config) {
            console.log('no');
        });
    };


    $scope.teste = function() {
        var idtest = {};
        idtest.id = 1;
        console.log(idtest);
      $http.post(urlbase + 'api/post', idtest).success(function(data) {
            console.log('it worked');
        }).error(function(data,status,headers,config) {

      });


    };


});