/**
 * Created by Tomek on 2015-05-01.
 */
var comment = angular.module('comment',[]);
var urlbase = "http://localhost:420/";
console.log('tu1');




comment.controller('commentCtrl', function($scope, $http){
    console.log('tu2');
    var postid = document.getElementById('postid').value;
    console.log('tu3');
    $http.get(urlbase + '/api2/comment/get/' + postid).
        success(function(data) {
            $scope.comments = data;
        }).error(function (data, status, headers, config) {
            alert(data);
            return status;
        });



    $scope.getcomments = function() {
        $http.get(urlbase + '/api2/comment/get/' + postid).
            success(function(data) {
                $scope.comments = data;
            }).error(function (data, status, headers, config) {
                alert(data);
                return status;
            });
    }

    $http.defaults.headers.post["Content-Type"] = "application/json";


    $scope.currentparent = -1;

    $scope.selectParent = function (parentid) {
      $scope.currentparent = parentid;
    };

    $scope.vote = function(commentid, points) {
        var postid = document.getElementById('postid').value;
        var commentvote = {};
            commentvote.commentid = commentid;
            commentvote.points = points;
            commentvote.postid = postid;

        $http.post(urlbase + '/api2/comment/vote', commentvote).
            success(function(data) {
                console.log('worked');
                $scope.getcomments();
            }).
            error(function(data,status,headers,config) {
                console.log('nope');
            });



    };

    $scope.cancelparent = function() {
        $scope.currentparent = -1;
    };

    $scope.add = function() {

        var commentar = {}
        commentar.lilname =  $scope.ctitle;
        commentar.content = $scope.ccontent;
        commentar.parentid = $scope.currentparent;

        var dataa = angular.toJson(commentar);
        console.log(dataa);

        $http.post(urlbase + '/api2/comment/add/' + postid, dataa).
            success(function(data) {
                console.log('worked');
               $scope.getcomments();
            }).
            error(function(data,status,headers,config) {
                console.log('nope');
            });

    }


});

//
//$scope.addComment =  function () {
//    console.log('probuje dodac komentarz');
//    var newcomment = {};
//    newcomment.lilname = $scope.ctitle;
//    newcomment.content = $scope.ccontent;
//
//    var dataa = angular.toJson(newcomment);
//    console.log('jego json wyglada mniejwiecej tak: ' + dataa);
//    $http({
//        'url' : urlbase + 'api/add',
//        'method' : 'POST',
//        'headers': {'Content-Type' : 'application/json'},
//        'data' : dataa
//    }).
//        success(function(data)
//        {
//            console.log('udalo sie postnac, powinien sie pojawic nowy komentarz');
//            $scope.comments = data;
//        }).
//        error(function(data, status, headers, config) {
//            console.log('nie udalo sie');
//            alert(data + '    ' + status + '    ' + headers + '   ' +  config);
//        });
