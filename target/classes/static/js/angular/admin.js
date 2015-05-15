/**
 * Created by Tomek on 2015-05-05.
 */
/**
 * Created by Tomek on 2015-05-01.
 */
var admin = angular.module('admin',[]);
var urlbase = "http://localhost:420/";



admin.directive('autoComplete',['$http',function($http){
    return {
        restrict:'AE',
        scope:{
            selectedTags:'=model'
        },
        templateUrl:'/angularviews/autocomplete-template.html',
        link:function(scope,elem,attrs){

            scope.suggestions=[];

            scope.selectedTags=[];

            scope.selectedIndex=-1;

            scope.removeTag=function(index){
                scope.selectedTags.splice(index,1);
            }

            scope.search=function(){
                $http.get(urlbase+'/api2/tag/getAllByName/'+scope.searchText).success(function(data){
                    if(data.indexOf(scope.searchText)===-1){
                        data.unshift(scope.searchText);
                    }
                    scope.suggestions=data;
                    scope.selectedIndex=-1;
                });
            }

            scope.addToSelectedTags=function(index){
                if(scope.selectedTags.indexOf(scope.suggestions[index])===-1){
                    scope.selectedTags.push(scope.suggestions[index]);
                    scope.searchText='';
                    scope.suggestions=[];
                }
            }

            scope.checkKeyDown=function(event){
                if(event.keyCode===40){
                    event.preventDefault();
                    if(scope.selectedIndex+1 !== scope.suggestions.length){
                        scope.selectedIndex++;
                    }
                }
                else if(event.keyCode===38){
                    event.preventDefault();
                    if(scope.selectedIndex-1 !== -1){
                        scope.selectedIndex--;
                    }
                }
                else if(event.keyCode===13){
                    scope.addToSelectedTags(scope.selectedIndex);
                }
            }

            scope.$watch('selectedIndex',function(val){
                if(val!==-1) {
                    scope.searchText = scope.suggestions[scope.selectedIndex];
                }
            });
        }
    }
}]);

admin.controller('tagCtrl',['$scope',function($scope){

}]);

admin.controller('adminCtrl', function($scope, $http){
    $scope.$watchCollection('data.tags',function(val){
        console.log(val);
    });

    $http.get(urlbase + '/api2/tag/getAll').success(function(data) {
        $scope.tags = data;
    }).error(function(data,status,headers,config) {
        console.log('nope');
    });

    $scope.newTag = function() {
        var tag = {};
        tag.id = 420;
        tag.name = $scope.tagname;

        var dataa = angular.toJson(tag);
        console.log(dataa);


        $http.post(urlbase + '/api2/tag/addNew', dataa).
            success(function(data) {
                console.log('worked');
            }).
            error(function(data,status,headers,config) {
                console.log(data + '  ' + status + '  ' + headers + '   ' + config);
            });

    };

    $scope.newPost = function() {
        var post = {};
        post.id = 420;


        post.fullname = $scope.title;
        post.lilname = $scope.subtitle;
        post.content = $scope.content;
        post.tags = $scope.data.tags;
        var dataa = angular.toJson(post);
        console.log(dataa);


        $http.post(urlbase + '/admin/post/new', dataa).
            success(function(data) {
                console.log('worked');
            }).
            error(function(data,status,headers,config) {
                console.log('nope');
            });


    }


});