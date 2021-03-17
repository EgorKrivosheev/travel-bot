'use strict';

_TRAVEL_BOT_APP.controller("appController", ['$scope', 'cityApiService', '$rootScope',
    function ($scope, cityApiService, $rootScope) {
        $scope.isVisibleAddForm = 'none';
        $scope.titleAddBtn = "Открыть форму добавления";
        $scope.newCity = {
            "name" : "",
            "info" : ""
        };
        $scope.getCities = function getCities() {
            cityApiService.cityApi({
                method: 'GET',
                url: 'cities'
            }).then(
                function (success){
                    $rootScope.cities = success.data.cities;
                    $scope.addMessage(success.data.message);
                },
                function (error) {
                    $scope.addMessage(error.data.message);
                }
            );
        }
        // Load
        $scope.getCities();
        setInterval($scope.getCities, 10000);
        // If main have scroll add class active-scroll(padding right less on 0.5em)
        $scope.isActiveScroll = function () {
            if (main.clientHeight < main.scrollHeight) {
                return "active-scroll";
            } else {
                return "";
            }
        }
        // Add new message
        $scope.addMessage = function (text) {
            $rootScope.messages.push({"class":"visible", "text":text});
            if ($rootScope.messages.length !== -1) {
                setTimeout(function () {
                    $rootScope.messages.splice(0, 1);
                }, 5000);
            }
        }
        $scope.addForm = function () {
            $scope.isVisibleAddForm = $scope.isVisibleAddForm === 'none' ? '' : 'none';
            $scope.titleAddBtn = $scope.isVisibleAddForm ?
                "Открыть форму добавления" : "Закрыть форму добавления";
            $scope.addMessage($scope.isVisibleAddForm ?
                'Закрыта форма для добавления!' : 'Открыта форма для добавления!');
        }
        $scope.addCity = function () {
            let req = {
                method: 'POST',
                url: 'city/add',
                params: { name: $scope.newCity.name, info: $scope.newCity.info }
            }
            $scope.cityApi(req);
        }
        $scope.deleteCity = function (id) {
            let req = {
                method: 'DELETE',
                url: 'city/delete',
                params: { id: id }
            }
            $scope.cityApi(req);
        }
        $scope.cityApi = function cityApi(url) {
            cityApiService.cityApi(url)
                .then(
                    function (success){
                        $scope.addMessage(success.data.message);
                    },
                    function (error) {
                        $scope.addMessage(error.data.message);
                    }
                );
        }
    }
]);
